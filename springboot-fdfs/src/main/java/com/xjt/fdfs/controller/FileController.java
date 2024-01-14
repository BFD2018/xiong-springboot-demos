package com.xjt.fdfs.controller;

import cn.hutool.core.io.FileUtil;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.xjt.fdfs.domain.FastdfsFile;
import com.xjt.fdfs.mapper.FdfsMapper;
import com.xjt.fdfs.service.FdfsService;
import com.xjt.fdfs.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "FastDFS测试")
@RestController
@RequestMapping("/fastdfs")
public class FileController {
    /**
     * Field generateStorageClient in com.xjt.fdfs.controller.FileController required a single bean, but 3 were found:
     * - defaultAppendFileStorageClient: defined in URL [jar:file:/D:/softwares/maven_repository/com/github/tobato/fastdfs-client/1.27.2/fastdfs-client-1.27.2.jar!/com/github/tobato/fastdfs/service/DefaultAppendFileStorageClient.class]
     * - defaultFastFileStorageClient: defined in URL [jar:file:/D:/softwares/maven_repository/com/github/tobato/fastdfs-client/1.27.2/fastdfs-client-1.27.2.jar!/com/github/tobato/fastdfs/service/DefaultFastFileStorageClient.class]
     * - defaultGenerateStorageClient: defined in URL [jar:file:/D:/softwares/maven_repository/com/github/tobato/fastdfs-client/1.27.2/fastdfs-client-1.27.2.jar!/com/github/tobato/fastdfs/service/DefaultGenerateStorageClient.class]
     * // 注意：这几个方法类似 都是单例bean  不能同时注入
     *
     * @Autowired private FastFileStorageClient storageClient;
     * @Autowired private TrackerClient trackerClient;
     * @Autowired private AppendFileStorageClient appendFileStorageClient;
     * @Autowired private GenerateStorageClient generateStorageClient;
     */
    @Autowired
    private FastFileStorageClient storageClient;

    @Value("${fdfs.web-server-url}")
    private String server_host;

    @Autowired
    private FdfsService fdfsService;


    @GetMapping("fileinfo")
    public R getFileInfo() {
        // TODO 获取文件信息
//        List<GroupState> groupStates = trackerClient.listGroups();
//
//        List<StorageState> storageStates = trackerClient.listStorages("group1");
//
//        System.out.println(appendFileStorageClient);
//
//        Set<MetaData> group1 = generateStorageClient.getMetadata("group1", "M00/00/00/wKidgWIOXFuAQzikABEWl5K2xMo104.jpg");

        //Set<MetaData> group1 = storageClient.getMetadata("group1", "M00/00/00/wKidgWIOXFuAQzikABEWl5K2xMo104.jpg");

        //FileInfo fileInfo = storageClient.queryFileInfo("group1", "http://192.168.157.129:8888/group1/M00/00/00/wKidgWIOXFuAQzikABEWl5K2xMo104.jpg");

        //return R.ok(fileInfo.getSourceIpAddr());

        return null;
    }

    @ApiOperation(value = "上传文件", httpMethod = "POST")
    @PostMapping("/upload")
    public R uploadFile(@ApiParam("文件") MultipartFile file) throws IOException {
        Object o = fdfsService.uploadFile(file);

        if (o != null) {
            return R.ok(o);
        }

        return R.fail();
    }

    @ApiOperation(value = "上传图片", httpMethod = "POST")
    @PostMapping("/uploadImg")
    public R uploadImage(@ApiParam("上传图片") MultipartFile file, @RequestParam(value = "size", required = false) String size) throws IOException {
        Object o = fdfsService.uploadImage(file, size);

        if (o != null) {
            return R.ok(o);
        }

        return R.fail();
    }

    @ApiOperation(value = "删除文件", httpMethod = "POST")
    @PostMapping("/delete")
    public R deleteFile(@RequestParam(value = "id", required = false) String fileId, @RequestParam(value = "fileUrl", required = false) String fileUrl) {
        if (StringUtils.hasLength(fileId) && StringUtils.hasLength(fileUrl)) {
            return R.fail("文件路径或id不能为空");
        } else if (StringUtils.hasLength(fileId)) {
            //按id删除
            try {
                FastdfsFile fastdfsFile = fdfsService.getById(fileId);
                fastdfsFile.setStatus(1);

                fdfsService.saveOrUpdate(fastdfsFile);

                return R.ok();
            } catch (Exception e) {
                e.printStackTrace();
                return R.fail(e.getMessage());
            }
        } else {
            try {
                StorePath storePath = StorePath.parseFromUrl(fileUrl);
                storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
                Map<String, Object> map = new HashMap<>();
                map.put("url", fileUrl);
                fdfsService.removeByMap(map);

                return R.ok();
            } catch (Exception e) {
                e.printStackTrace();
                return R.fail(e.getMessage());
            }
        }


    }

    @ApiOperation(value = "根据文件绝对路径下载", httpMethod = "POST")
    @PostMapping("/download")
    public R downloadFileByURl(HttpServletResponse response, @RequestParam(value = "fileUrl") String fileUrl) {
        try {
            StorePath storePath = StorePath.parseFromUrl(fileUrl);

            byte[] bytes = storageClient.downloadFile(storePath.getGroup(), storePath.getPath(), new DownloadByteArray());
            response.getOutputStream().write(bytes);

            FileUtil.writeBytes(bytes, FileUtil.file(storePath.getPath()));

            return R.ok();
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
    }


    @ApiOperation(value = "根据文件id下载", httpMethod = "POST")
    @PostMapping("/download/{id}")
    public R downloadFileById(@PathVariable("id") String id) {
        int i = fdfsService.downloadFileByFileId(id);
        if (i > 0) {
            return R.ok();
        }

        return R.fail();
    }
}
