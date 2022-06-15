package com.xjt.javase.dataStructure.huffmancode;

import java.io.*;
import java.util.*;

public class HuffManCodeDemo {
    public static void main(String[] args) {
        /*String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        System.out.println(Arrays.toString(contentBytes));
        System.out.println(contentBytes.length);        //40

        byte[] huffmanCodesZip = huffmanZip(contentBytes);
        System.out.println("huffmanZip===>" + Arrays.toString(huffmanCodesZip));

        //测试解压
        byte[] sourceBytes = decode(huffmanCodes, huffmanCodesZip);
        System.out.println("decode--->" + Arrays.toString(sourceBytes));
        System.out.println("原来的字符串=" + new String(sourceBytes));*/

        zipFile("D:\\视频\\111.flv","D:\\视频\\222.zip");


//        unZipFile("D:\\myDocs\\Java_Learning\\Java学习笔记\\【尚硅谷】\\【尚硅谷】韩顺平_数据结构与算法\\资料\\压缩测试文件\\src.zip","D:\\myDocs\\Java_Learning\\Java学习笔记\\【尚硅谷】\\【尚硅谷】韩顺平_数据结构与算法\\资料\\压缩测试文件\\src111.bmp");
    }

    //编写方法，将一个文件进行压缩
    /**
     *
     * @param srcFile 你传入的希望压缩的文件的全路径
     * @param dstFile 我们压缩后将压缩文件放到哪个目录
     */
    public static void zipFile(String srcFile, String dstFile) {
        OutputStream os = null;
        ObjectOutputStream oos = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(srcFile);
            int available = fis.available();        //获取文件字节长度
            byte[] bytes = new byte[available];
            fis.read(bytes);
            byte[] huffmanBytes = huffmanZip(bytes);
            os = new FileOutputStream(dstFile);
            oos = new ObjectOutputStream(os);
            //把 赫夫曼编码后的字节数组写入压缩文件
            oos.writeObject(huffmanBytes);
            //这里我们以对象流的方式写入 赫夫曼编码，是为了以后我们恢复源文件时使用
            //注意一定要把赫夫曼编码表 写入压缩文件
            oos.writeObject(huffmanCodes);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fis.close();
                oos.close();
                os.close();
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }


    //编写一个方法，完成对压缩文件的解压
    /**
     *
     * @param zipFile 准备解压的文件
     * @param dstFile 将文件解压到哪个路径
     */
    public static void unZipFile(String zipFile, String dstFile) {
        //定义文件输入流
        InputStream is = null;
        //定义一个对象输入流
        ObjectInputStream ois = null;
        //定义文件的输出流
        OutputStream os = null;
        try {
            //创建文件输入流
            is = new FileInputStream(zipFile);
            //创建一个和  is关联的对象输入流
            ois = new ObjectInputStream(is);
            //读取byte数组  huffmanBytes
            byte[] huffmanBytes = (byte[])ois.readObject();
            //读取赫夫曼编码表
            Map<Byte,String> huffmanCodes = (Map<Byte,String>)ois.readObject();

            //解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            //将bytes 数组写入到目标文件
            os = new FileOutputStream(dstFile);
            //写数据到 dstFile 文件
            os.write(bytes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (Exception e2) {
                // TODO: handle exception
                System.out.println(e2.getMessage());
            }
        }
    }


    /**
     * 编写一个方法，完成对压缩数据的解码
     * @param huffmanCodes 赫夫曼编码表 map
     * @param huffmanBytes 赫夫曼编码得到的字节数组
     * @return 就是原来的字符串对应的数组
     */
    private static byte[] decode(Map<Byte,String> huffmanCodes, byte[] huffmanBytes) {
        //1. 先得到 huffmanBytes 对应的 二进制的字符串 ， 形式 1010100010111...
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            //当 flag==true 最后一个字节
            boolean flag = (i == (huffmanBytes.length - 1));

            sb.append(byteToBitString(!flag,b));
        }

        //把字符串 按照指定的赫夫曼编码进行解码
        //把赫夫曼编码表k-v 进行调换，因为反向查询 a->100   100->a
        HashMap<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(),entry.getKey());
        }

        //创建集合，存放byte
        List<Byte> list = new ArrayList<>();
        //i 可以理解成就是索引,扫描 sb
        for (int i = 0; i < sb.length(); ) {
            int count = 1;
            boolean flag = true;
            Byte b = null;

            while (flag){
                //1010100010111...
                //递增的取出 key 1  10  101  1010 ... 在map中查找是否有
                String key = sb.substring(i,i+count);
                b = map.get(key);
                if(b == null){  //map中没匹配到
                    count++;
                }else{
                    //匹配到
                    flag = false;
                }
            }

            list.add(b);
            i += count;     //i直接移动到count位置 继续遍历
        }

        //当for循环结束后，我们list中就存放了所有的字符  "i like like like java do you like a java"
        //把list 中的数据放入到byte[] 并返回
        byte[] bt = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            bt[i] = list.get(i);
        }

        return bt;

    }

    /**
     * 将一个byte 转成一个二进制的字符串
     * （-1 补码）11111111111111111111111111111111
     * （-1 反码）11111111111111111111111111111110
     * （-1 原码）10000000000000000000000000000001
     * @param b 字节
     * @param flag 标记是否是 byte[]的最后一位，最后一个正数不用补位
     * @return 一个byte字节 对应的 二进制字符串
     */
    private static String byteToBitString(boolean flag,byte b){
        int temp = b;
        //Integer.toBinaryString 返回int类型的 32位bit 字符串表示
        // Integer.toBinaryString(1) == 1   需要补高位为 00000001
        if(flag){
            temp |= 256;  //256 | 1 按位与 ：1 0000 0000  | 0000 0001 => 1 0000 0001  =>截取后8位位 00000001
        }
        String s = Integer.toBinaryString(temp);   //返回的是二进制的补码

        if(flag || temp < 0){
            String ss = s.substring(s.length() - 8);
            return ss;
        }else{
            return s;
        }
    }

    /**
     *
     * @param bytes 原始的字符串对应的字节数组
     * @return 是经过 赫夫曼编码处理后的字节数组(压缩后的数组)
     * 例如：字符串.getBytes()  ==> [105, 32, 108, 105, 107, 101, 32, 108, 10 。。。
     * 返回：[-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
     */
    private static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);

        //根据 nodes 创建的赫夫曼树
        Node huffmanTreeRoot = createHuffmanTree(nodes);

        //对应的赫夫曼编码(根据 赫夫曼树)
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);

        //根据生成的赫夫曼编码，压缩得到压缩后的赫夫曼编码字节数组
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
        return huffmanCodeBytes;
    }

    /**
     * 功能：将字符串对应的byte[] 数组 通过赫夫曼编码表 压缩得到byte[]
     * @param stringBytes  原始的字符串对应的 byte[]
     * @param huffmanCodes  生成的赫夫曼编码表map
     * @return 返回赫夫曼编码表处理后的压缩 byte[]
     * 举例： String content = "i like like like java do you like a java"; =》 byte[] contentBytes = content.getBytes();
     * 返回的是 字符串 "1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100"
     * => 对应的 byte[] huffmanCodeBytes  ，即 8位对应一个 byte,放入到 huffmanCodeBytes
     * huffmanCodeBytes[0] =  10101000(补码) => byte  [推导  10101000=> 10101000 - 1 => 10100111(反码)=> 11011000= -88 ]
     * huffmanCodeBytes[0] = -88
     */
    private static byte[] zip(byte[] stringBytes,Map<Byte, String> huffmanCodes){
        //（第一步）、利用huffmanCodes 将bytes 转成 赫夫曼编码对应的字符串
        StringBuilder sb = new StringBuilder();
        //遍历字符串对应的字节数组
        for (byte stringByte : stringBytes) {
            sb.append(huffmanCodes.get(stringByte));
        }

        //（第二步）将 “1010100010111111110010001011111111...” 压缩成 字节数组 byte[]
        //2.1 统计返回  byte[] huffmanCodeBytes 长度(8位bit对应一个byte)
        int len;
        if(sb.length() % 8 == 0){
            len = sb.length() / 8;
        }else{
            len = sb.length() / 8 + 1;
        }
        //上面的if-else用一句代码可以写为：
        // int len = (stringBuilder.length() + 7) / 8;

        //2.2 创建 存储压缩后的 byte数组
        byte[] huffmanCodeBytes = new byte[len];
        //记录 huffmanCodeBytes 索引
        int index = 0;
        for (int i = 0; i < sb.length(); i+=8) {        //因为是每8位对应一个byte,所以步长 +8
            String strByte;
            if(i+8 > sb.length()){
                strByte = sb.substring(i);
            }else{
                strByte = sb.substring(i,i+8);
            }
            //将strByte 转成一个byte,放入到 huffmanCodeBytes
            huffmanCodeBytes[index] = (byte)Integer.parseInt(strByte,2);
            index++;
        }

        return huffmanCodeBytes;
    }

    //生成赫夫曼树对应的赫夫曼编码
    //思路:
    //1. 将赫夫曼编码表存放在 Map<Byte,String> 形式
    //   生成的赫夫曼编码表{32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101, 121=11010, 106=0010, 107=1111, 108=000, 111=0011}
    static Map<Byte, String> huffmanCodes = new HashMap<Byte,String>();
    //2. 在生成赫夫曼编码表示，需要去拼接路径, 定义一个StringBuilder 存储某个叶子结点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    //为了调用方便，我们重载 getCodes
    private static Map<Byte, String> getCodes(Node root) {
        if(root == null) {
            return null;
        }
        //处理root的左子树
        getCodes(root.left, "0", stringBuilder);
        //处理root的右子树
        getCodes(root.right, "1", stringBuilder);
        return huffmanCodes;
    }

    /**
     * 功能：将传入的node结点的所有叶子结点的赫夫曼编码得到，并放入到huffmanCodes集合
     * @param node  传入结点
     * @param code  路径： 左子结点是 0, 右子结点 1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        //将code 加入到 stringBuilder2
        stringBuilder2.append(code);
        if(node != null) { //如果node == null不处理
            //判断当前node 是叶子结点还是非叶子结点
            if(node.data == null) { //非叶子结点
                //递归处理
                //向左递归
                getCodes(node.left, "0", stringBuilder2);
                //向右递归
                getCodes(node.right, "1", stringBuilder2);
            } else { //说明是一个叶子结点
                //就表示找到某个叶子结点的最后
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }


    //可以通过List 创建对应的赫夫曼树
    private static Node createHuffmanTree(List<Node> nodes) {

        while(nodes.size() > 1) {
            //排序, 从小到大
            Collections.sort(nodes);
            //取出第一颗最小的二叉树
            Node leftNode = nodes.get(0);
            //取出第二颗最小的二叉树
            Node rightNode = nodes.get(1);
            //创建一颗新的二叉树,它的根节点 没有data, 只有权值
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;

            //将已经处理的两颗二叉树从nodes删除
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将新的二叉树，加入到nodes
            nodes.add(parent);

        }
        //nodes 最后的结点，就是赫夫曼树的根结点
        return nodes.get(0);

    }

    /**
     *
     * @param bytes 接收字节数组
     * @return 返回的就是 List 形式  [Node[date=97 ,weight = 5], Node[]date=32,weight = 9]......],
     */
    private static List<Node> getNodes(byte[] bytes) {
        //1创建一个ArrayList
        ArrayList<Node> nodes = new ArrayList<Node>();

        //遍历 bytes , 统计 每一个byte出现的次数->map[key,value]
        Map<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) { // Map还没有这个字符数据,第一次
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }

        //把每一个键值对转成一个Node 对象，并加入到nodes集合
        //遍历map
        for(Map.Entry<Byte, Integer> entry: counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }
}


//创建Node ,数据 + 权值
class Node implements Comparable<Node>  {
    Byte data;  // 存放数据(字符)本身，比如'a' => 97 ' ' => 32
    int weight;     //权值, 表示字符出现的次数
    Node left;      //
    Node right;
    public Node(Byte data, int weight) {

        this.data = data;
        this.weight = weight;
    }
    @Override
    public int compareTo(Node o) {
        // 从小到大排序
        return this.weight - o.weight;
    }

    public String toString() {
        return "Node [data = " + data + " weight=" + weight + "]";
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if(this.left != null) {
            this.left.preOrder();
        }
        if(this.right != null) {
            this.right.preOrder();
        }
    }
}
