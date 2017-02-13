package com.test.util;

import sun.misc.BASE64Decoder;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.UUID;

/**
 * Created by egguncle on 17-2-13.
 */
public class ImgUtil {

    public final static String USER_ICON="icon";
    public final static String BLOG="blog";

    /**
     * 对字节数组字符串进行Base64解码并生成图片
     *
     * @param imgStr      Base64字符串
     * @param imgFilePath 生成图片保存路径
     * @return boolean
     */
    public static boolean generateImage(String imgStr, String imgFilePath) {
        System.out.println(imgFilePath);
        if (imgStr == null) // 图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] bytes = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {// 调整异常数据
                    bytes[i] += 256;
                }
            }
            // 生成图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(bytes);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String saveImg(String pathRoot,String base64StrOfImg,String imageType,String saveType){


        String path = "";

        //生成uuid作为文件名称
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        //获得文件后缀名称
        //  String imageName = contentType.substring(contentType.indexOf("/") + 1);
        String imageName = imageType;
        path = "static/images/"+saveType+"/" + uuid + "." + imageName;
        //写入图片
        if (ImgUtil.generateImage(base64StrOfImg, pathRoot + path)) {
            System.out.println("success");
            // path = "/static/images/blog/" + uuid + "." + imageName;
            //给博客对象设置图片路径
            return path;

        } else {
            System.out.println("failed");
            return "";
        }
    }
}
