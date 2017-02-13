package com.test.controller;

import com.test.model.BlogEntity;
import com.test.jsonbean.BlogJson;
import com.test.model.UserEntity;
import com.test.repository.BlogRepository;
import com.test.repository.UserRepository;
import com.test.util.ImgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by egguncle on 17-1-23.
 * <p>
 * 返回博客相关的json的controller
 */

@Controller
public class BlogApiController {

    // 自动装配数据库接口，不需要再写原始的Connection来操作数据库
    @Autowired
    BlogRepository blogRepository;

    @Autowired
    UserRepository userRepository;

    /**
     * 用来提交博客的方法
     *
     * @param userName
     * @param token
     * @param title
     * @param content
     * @param base64StrOfImg
     * @param imageType
     * @param request
     * @return
     */
    @ResponseBody
    @Transactional
    @RequestMapping(value = "/api/submit_blog", method = RequestMethod.POST)
    public String submitBlog(@RequestParam("userName") String userName, @RequestParam("token") String token, @RequestParam("title") String title, @RequestParam("content") String content,
                             @RequestParam(value = "base64StrOfImg", required = false) String base64StrOfImg, @RequestParam(value = "imgtype") String imageType, HttpServletRequest request) {

        UserEntity user = userRepository.loginWithToken(userName, token);
        if (user == null) {
            return "failed";
        }


        //获取当前时间
        java.util.Date date = new java.util.Date();
        Date sqlDate = new Date(date.getTime());

        //生成博客对象
        BlogEntity blogEntity = new BlogEntity();
        blogEntity.setBlogDate(sqlDate);
        blogEntity.setBlogTitle(title);
        blogEntity.setBlogContent(content);


        //获得物理路径webapp所在路径
        String pathRoot = request.getSession().getServletContext().getRealPath("");
        String path = ImgUtil.saveImg(pathRoot, base64StrOfImg, imageType,ImgUtil.BLOG);
        if (!"".equals(path)){
            blogEntity.setImgPath(path);
        }
//        String path = "";
//
//        //生成uuid作为文件名称
//        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
//        //获得文件后缀名称
//        //  String imageName = contentType.substring(contentType.indexOf("/") + 1);
//        String imageName = imageType;
//        path = "static/images/blog/" + uuid + "." + imageName;
//        //写入图片
//        if (ImgUtil.generateImage(base64StrOfImg, pathRoot + path)) {
//            System.out.println("success");
//            // path = "/static/images/blog/" + uuid + "." + imageName;
//            //给博客对象设置图片路径
//            blogEntity.setImgPath(path);
//
//        } else {
//            System.out.println("failed");
//        }

        System.out.println(user.getUserId());
        System.out.println(user.getBgPath());
        System.out.println(user.getIconPath());
        System.out.println(user.getUsername());
        System.out.println(user.getNickname());
        System.out.println(user.getDescription());

        blogEntity.setUser(user);
        //存入数据库
        blogRepository.save(blogEntity);


        return "success";
    }


    /**
     * 获取博客（20条）
     *
     * @param type   获取类型 one：获取指定ID的博客数据
     *               max：获取比输入ID大的数据
     *               min：获取比输入ID小的数据
     *               max_user：获取用户比ID大的数据
     *               min_user：获取用户比ID小的数据
     * @param blogId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/blog/{type}/{blogId}", method = RequestMethod.GET)
    public BlogJson getBlogJson(@PathVariable("type") String type, @PathVariable("blogId") int blogId) {
        List<BlogEntity> blogList = new ArrayList<>();

        switch (type) {
            case "one": {
                blogList.add(blogRepository.findOne(blogId));
            }
            break;
            case "max": {
                blogList.addAll(blogRepository.getBlogListMax(blogId));
            }
            break;
            case "min": {
                blogList.addAll(blogRepository.getBlogListMin(blogId));
            }
            break;
        }
        BlogJson blogJson = new BlogJson();
        blogJson.setError(false);
        blogJson.setResults(blogList);

        return blogJson;
    }

    /**
     * 删除博客请求
     *
     * @param blogId   需要删除的博客的ID
     * @param userName
     * @param token
     * @return
     */
    @ResponseBody
    @Transactional
    @RequestMapping(value = "/api/blog/delete", method = RequestMethod.POST)
    public String deleteBlogById(@RequestParam("blogId") int blogId, @RequestParam("userName") String userName, @RequestParam("token") String token) {
        UserEntity user = userRepository.loginWithToken(userName, token);
        if (user != null) {
            System.out.println("用户存在");
            System.out.println(blogId);
            System.out.println(user.getUserId());
            blogRepository.deleteBlogById(blogId, user.getUserId());
        } else {
            return "failed";
        }

        return "success";
    }

}
