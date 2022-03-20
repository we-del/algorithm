package com.data_structure.tree.huffmanTree;


import java.util.*;

/**
 * ClassName: HuffmanCode
 * Description:
 * date: 2022/2/26 11:20
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */

public class HuffmanCode {
    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] bytes = content.getBytes();

        byte[] huffmanCodesBytes = huffmanZip(bytes);
        System.out.println(Arrays.toString(huffmanCodesBytes)+"长度位："+huffmanCodesBytes.length);

        byte[] sourceBytes = decode(huffmanCodes, huffmanCodesBytes);
        System.out.println(sourceBytes.toString());
    }

    // =============================================
    // 完成数据的解压

    // 将一个byte 转成一个二进制字符串
    private static String byteToBitString(byte b,boolean flag){
        int temp =b; // 将 b 转成 int
        temp |= 256; // 过滤掉正数溢出问题
        String s = Integer.toBinaryString(temp); // 返回 int temp 对应的二进制补码

        if(flag){ // flag为true 表示传入为正数 需要补高位
            return s.substring(s.length()-8);
        }else{
            return s; // 按补码返回字符串
        }
    }

    // 完成堆压缩数据的解码
    private static byte[] decode(Map<Byte,String>huffmanCodes,byte[] huffmanBytes){
        // 得到 huffmanBytes 对应的 二进制字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            // 判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length-1);
            stringBuilder.append( byteToBitString(huffmanBytes[i], !flag));
        }

        // 把字符串按照指定的赫夫曼编码进行解码
        // 把赫夫曼编码表进行调换
        HashMap<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        ArrayList<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while(flag){
                // 取出一个 '1' '0'
                if(i+count> stringBuilder.length() ){
                    stringBuilder.substring(i);
                    break;
                }
                String key = stringBuilder.substring(i,i+count);
                b = map.get(key);
                if(b == null){
                    count++;
                }else{
                    flag = false;
                }
            }
            list.add(b);
            i+=count;
        }
        // 当for循环结束时，list中存放了所有的字符

        // 把list 中的数据放入到byte[] 并返回
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
             b[i] = list.get(i);
        }
        return b;
    }

    // =================================================


    // 创建一个方法，将赫夫曼编码的一系列方法封装起来，便于调用（压缩过程）
    private static byte[] huffmanZip(byte[] bytes){
        List<Node1> nodes = getNodes(bytes); // 将所有字符封装到一个集合中
        Node1 huffmanTree = getHuffmanTree(nodes); // 生成对应的赫夫曼树
        Map<Byte, String> huffmanCodes = getCodes(huffmanTree);
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
        return huffmanCodeBytes;
    }

    // 存放赫夫曼编码表
    private static Map<Byte, String> huffmanCodes = new HashMap<>();

    // 存储某个叶子节点的路径
    private static StringBuilder stringBuilder = new StringBuilder();

    // 重载getCodes(为了调用方便)
    private static Map<Byte, String> getCodes(Node1 node) {
        getCodes(node, "", stringBuilder);
        return huffmanCodes;
    }

    // 完成赫夫曼编码表，并把路径存入到huffmanCodes集合中
    private static void getCodes(Node1 node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);
        stringBuilder1.append(code); // 将code加入到stringBuilder1中
        if (node != null) { // node不等于空才可以获取节点
            if (node.getData() == null) { // 非叶子节点
                getCodes(node.getLeft(), "0", stringBuilder1);
                getCodes(node.getRight(), "1", stringBuilder1);
            } else { //说明是叶子节点
                huffmanCodes.put(node.getData(), stringBuilder1.toString());
            }
        }
    }

    // 将字符串对应的byte[] 数组，通过生成的赫夫曼编码表，返回一个赫夫曼编码 压缩后的byte[]
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {

        // 利用 huffmanCodes 将 bytes 转成 赫夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (byte aByte : bytes) {
            stringBuilder.append(huffmanCodes.get(aByte));
        }

        // 统计返回 byte[] huffmanCodeBytes 长度
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }

        // 创建 存储压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0; // 记录第几个byte
        //因为每8位对应一个bytes,所以步长+8
        for (int i = 0; i < stringBuilder.length(); i+=8) {

            String strByte;
            if(i+8>stringBuilder.length()){
                strByte = stringBuilder.substring(i);
            }else {
                strByte = stringBuilder.substring(i,i+8);
                huffmanCodeBytes[index] = (byte)Integer.parseInt(strByte,2);
                index++;
            }

        }
        return huffmanCodeBytes;
    }

    // 将所有的字符包装成一个对象放到集合中
    public static List<Node1> getNodes(byte[] bytes) {
        Map<Byte, Integer> hashMap = new HashMap<>();
        for (byte aByte : bytes) {
            if (hashMap.get(aByte) == null) {
                hashMap.put(aByte, 1);
            } else {
                hashMap.put(aByte, hashMap.get(aByte) + 1);
            }
        }
        List<Node1> nodes = new ArrayList<>();
        for (Map.Entry<Byte, Integer> entry : hashMap.entrySet()) {
            nodes.add(new Node1(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    // 得到赫夫曼树
    public static Node1 getHuffmanTree(List<Node1> list) {
        while (list.size() > 1) {
            Collections.sort(list);
            Node1 left = list.get(0);
            Node1 right = list.get(1);
            Node1 node1 = new Node1(left.getCount() + right.getCount());
            node1.setLeft(left);
            node1.setRight(right);
            list.remove(left);
            list.remove(right);
            list.add(node1);
        }
        return list.get(0);
    }
}

class Node1 implements Comparable<Node1> {
    private Byte data;
    private int count;
    private Node1 left;
    private Node1 right;

    @Override
    public String toString() {
        return "Node1{" +
                "data=" + data +
                ", count=" + count +
                '}';
    }

    public Node1(int count) {
        this.count = count;
    }

    @Override
    public int compareTo(Node1 o) {
        return this.count - o.count;
    }

    // 前序遍历该树
    public void preOrder() {
        if (this == null) {
            return;
        }
        System.out.println(this);
        if (this.left != null) {

            this.left.preOrder();
        }
        if (this.right != null) {

            this.right.preOrder();
        }
    }

    public Node1(byte data, int count) {
        this.data = data;
        this.count = count;
    }

    public Byte getData() {
        return data;
    }

    public void setData(byte data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Node1 getLeft() {
        return left;
    }

    public void setLeft(Node1 left) {
        this.left = left;
    }

    public Node1 getRight() {
        return right;
    }

    public void setRight(Node1 right) {
        this.right = right;
    }
}