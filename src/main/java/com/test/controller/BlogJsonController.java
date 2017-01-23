package com.test.controller;

import com.test.model.BlogEntity;
import com.test.model.BlogJson;
import com.test.model.UserEntity;
import com.test.repository.BlogRepository;
import com.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by egguncle on 17-1-23.
 * <p>
 * 返回博客相关的json的controller
 */

@Controller
public class BlogJsonController {

    // 自动装配数据库接口，不需要再写原始的Connection来操作数据库
    @Autowired
    BlogRepository blogRepository;

    @Autowired
    UserRepository userRepository;

    /**
     * 用来提交博客的方法
     *
     * @param userId
     * @param title
     * @param content
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/client/submit_blog", method = RequestMethod.POST)
    public String submitBlog(@RequestParam("userId") int userId, @RequestParam("title") String title, @RequestParam("content") String content) {

        //获取当前时间
        java.util.Date date = new java.util.Date();
        Date sqlDate = new Date(date.getTime());

        //生成博客对象
        BlogEntity blogEntity = new BlogEntity();
        blogEntity.setBlogDate(sqlDate);
        blogEntity.setBlogTitle(title);
        blogEntity.setBlogContent(content);

        //通过userID来获取对应用户的ID
        UserEntity user = userRepository.getOne(userId);
        blogEntity.setTableUserByUserId(user);

        //存入数据库
        blogRepository.save(blogEntity);

        return "success";
    }

//    /**
//     * 获取博客的方法（默认）
//     *
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping(value = "/json/blog", method = RequestMethod.GET)
//    public BlogJson getBlogJson() {
//        //获取20条最新的博客数据
//        List<BlogEntity> blogList = blogRepository.findBlogList();
//        BlogJson blogJson = new BlogJson();
//        blogJson.setError(false);
//        blogJson.setResults(blogList);
//
//        return blogJson;
//    }
//
//    /**
//     * 获取更大的博客数据的方法
//     * @param blogId
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping(value = "/json/blog_max/{blogId}", method = RequestMethod.GET)
//    public BlogJson getBlogJsonMax(@PathVariable("blogId") int blogId) {
//        //获取20条比当前ID大的博客数据
//        List<BlogEntity> blogList = blogRepository.getBlogListMax(blogId);
//        BlogJson blogJson = new BlogJson();
//        blogJson.setError(false);
//        blogJson.setResults(blogList);
//
//        return blogJson;
//    }


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
    @RequestMapping(value = "/json/blog/{type}/{blogId}", method = RequestMethod.GET)
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


}
