package com.xjt.filesupload.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author jtxiong
 * @version 1.0
 * @description: 大文件上传 下载 断点续传
 * @date 2023/7/31 21:50
 */
@RestController
@RequestMapping("bigfile")
public class UploadController {
    private final static String utf8 = "utf-8";

    @RequestMapping("/up")
    public void upload(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setCharacterEncoding(utf8);
        //长传时候会有多个分片，需要记录当前为那个分片
        Integer schunk = null;
        //总分片数
        Integer schunks = null;
        //名字
        String name = null;
        //文件目录
        String path = "D:\\file";
        BufferedOutputStream os = null;
        try {
            //设置缓冲区大小  先读到内存里在从内存写
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(1024);
            factory.setRepository(new File(path));
            //解析
            ServletFileUpload upload = new ServletFileUpload(factory);
            //设置单个大小与最大大小
            upload.setFileSizeMax(5l * 1024l * 1024l * 1024l);
            upload.setSizeMax(10l * 1024l * 1024l * 1024l);
            List<FileItem> items = upload.parseRequest(request);
            for (FileItem item : items) {
                if (item.isFormField()) {
                    //获取分片数赋值给遍量
                    if ("chunk".equals(item.getFieldName())) {
                        schunk = Integer.parseInt(item.getString(utf8));
                    }
                    if ("chunks".equals(item.getFieldName())) {
                        schunks = Integer.parseInt(item.getString(utf8));
                    }
                    if ("name".equals(item.getFieldName())) {
                        name = item.getString(utf8);
                    }
                }
            }
            //取出文件基本信息后
            for (FileItem item : items) {
                if (!item.isFormField()) {
                    //有分片需要临时目录
                    String temFileName = name;
                    if (name != null) {
                        if (schunk != null) {
                            temFileName = schunk + "_" + name;
                        }
                        //判断文件是否存在
                        File temfile = new File(path, temFileName);
                        //断点续传  判断文件是否存在，若存在则不传
                        if (!temfile.exists()) {
                            item.write(temfile);
                        }
                    }
                }
            }
            //文件合并  当前分片为最后一个就合并
            if (schunk != null && schunk.intValue() == schunks.intValue() - 1) {
                File tempFile = new File(path, name);
                os = new BufferedOutputStream(new FileOutputStream(tempFile));
                //根据之前命名规则找到所有分片
                for (int i = 0; i < schunks; i++) {
                    File file = new File(path, i + "_" + name);
                    //并发情况 需要判断所有  因为可能最后一个分片传完，之前有的还没传完
                    while (!file.exists()) {
                        //不存在休眠100毫秒后在从新判断
                        Thread.sleep(100);
                    }
                    //分片存在  读入数组中
                    byte[] bytes = FileUtils.readFileToByteArray(file);
                    os.write(bytes);
                    os.flush();
                    file.delete();
                }
                os.flush();
            }
            response.getWriter().write("上传成功");
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping("/down")
    public void downLoadFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding(utf8);
        //定义文件路径
        File file = new File("D:\\File\\a.mp4");
        InputStream is = null;
        OutputStream os = null;
        try {
            //分片下载
            long fSize = file.length();//获取长度
            response.setContentType("application/x-download");
            String fileName = URLEncoder.encode(file.getName(), utf8);
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            //根据前端传来的Range  判断支不支持分片下载
            response.setHeader("Accept-Range", "bytes");
            //获取文件大小
            response.setHeader("fSize", String.valueOf(fSize));
            response.setHeader("fName", fileName);
            //定义断点
            long pos = 0, last = fSize - 1, sum = 0;
            //判断前端需不需要分片下载
            if (null != request.getHeader("Range")) {
                response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
                String numRange = request.getHeader("Range").replaceAll("bytes=", "");
                String[] strRange = numRange.split("-");
                if (strRange.length == 2) {
                    pos = Long.parseLong(strRange[0].trim());
                    last = Long.parseLong(strRange[1].trim());
                    //若结束字节超出文件大小 取文件大小
                    if (last > fSize - 1) {
                        last = fSize - 1;
                    }
                } else {
                    //若只给一个长度  开始位置一直到结束
                    pos = Long.parseLong(numRange.replaceAll("-", "").trim());
                }
            }
            long rangeLenght = last - pos + 1;
            String contentRange = new StringBuffer("bytes").append(pos).append("-").append(last).append("/").append(fSize).toString();
            response.setHeader("Content-Range", contentRange);
            response.setHeader("Content-Lenght", String.valueOf(rangeLenght));
            os = new BufferedOutputStream(response.getOutputStream());
            is = new BufferedInputStream(new FileInputStream(file));
            is.skip(pos);//跳过已读的文件
            byte[] buffer = new byte[1024];
            int lenght = 0;
            //相等证明读完
            while (sum < rangeLenght) {
                lenght = is.read(buffer, 0, (rangeLenght - sum) <= buffer.length ? (int) (rangeLenght - sum) : buffer.length);
                sum = sum + lenght;
                os.write(buffer, 0, lenght);

            }
            System.out.println("下载完成");
        } finally {
            if (is != null) {
                is.close();
            }
            if (os != null) {
                os.close();
            }
        }
    }

    private final static long per_page = 1024l * 1024l * 50l;
    //分片存储临时目录 当分片下载完后在目录中找到文件合并
    private final static String down_path = "D:\\File";
    //多线程下载
    ExecutorService pool = Executors.newFixedThreadPool(10);

    //文件大小 分片数量 文件名称
    //使用探测 获取变量
    //使用多线程分片下载
    //最后一个分片下载完 开始合并
    @RequestMapping("/downloadFile")
    public String downloadFile() throws IOException {
        FileInfo fileInfo = download(0, 10, -1, null);
        if (fileInfo != null) {
            long pages = fileInfo.fSize / per_page;
            for (int i = 0; i <= pages; i++) {
                pool.submit(new Download(i * per_page, (i + 1) * per_page - 1, i, fileInfo.fName));
            }
        }

        return "成功";
    }

    class Download implements Runnable {
        long start;
        long end;
        long page;
        String fName;

        public Download(long start, long end, long page, String fName) {
            this.start = start;
            this.end = end;
            this.page = page;
            this.fName = fName;
        }

        @Override
        public void run() {
            try {
                FileInfo fileInfo = download(start, end, page, fName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //返回文件名 跟大小
    private FileInfo download(long start, long end, long page, String fName) throws IOException {
        //断点下载 文件存在不需要下载
        File file = new File(down_path, page + "-" + fName);
        //探测必须放行 若下载分片只下载一半就锻炼需要重新下载所以需要判断文件是否完整
        if (file.exists() && page != -1 && file.length() == per_page) {
            return null;
        }
        //需要知道  开始-结束 = 分片大小
        HttpClient client = HttpClients.createDefault();
        //httpclient进行请求
        HttpGet httpGet = new HttpGet("http://127.0.0.1:8080/down");
        //告诉服务端做分片下载
        httpGet.setHeader("Range", "bytes=" + start + "-" + end);
        HttpResponse response = client.execute(httpGet);
        String fSize = response.getFirstHeader("fSize").getValue();
        fName = URLDecoder.decode(response.getFirstHeader("fName").getValue(), "utf-8");
        HttpEntity entity = response.getEntity();//获取文件流对象
        InputStream is = entity.getContent();
        //临时存储分片文件
        FileOutputStream fos = new FileOutputStream(file);
        byte[] buffer = new byte[1024];//定义缓冲区
        int ch;
        while ((ch = is.read(buffer)) != -1) {
            fos.write(buffer, 0, ch);
        }
        is.close();
        fos.flush();
        fos.close();
        //判断是不是最后一个分片
        if (end - Long.valueOf(fSize) > 0) {
            //合并
            try {
                mergeFile(fName, page);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new FileInfo(Long.valueOf(fSize), fName);
    }

    private void mergeFile(String fName, long page) throws Exception {
        //归并文件位置
        File file = new File(down_path, fName);
        BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(file));
        for (int i = 0; i <= page; i++) {
            File tempFile = new File(down_path, i + "-" + fName);
            //分片没下载或者没下载完需要等待
            while (!file.exists() || (i != page && tempFile.length() < per_page)) {
                Thread.sleep(100);
            }
            byte[] bytes = FileUtils.readFileToByteArray(tempFile);
            os.write(bytes);
            os.flush();
            tempFile.delete();
        }
        File file1 = new File(down_path, -1 + "-null");
        file1.delete();
        os.flush();
        os.close();
    }

    //使用内部类实现
    class FileInfo {
        long fSize;
        String fName;

        public FileInfo(long fSize, String fName) {
            this.fSize = fSize;
            this.fName = fName;
        }
    }
}
