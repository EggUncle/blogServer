package com.test.controller;

import com.test.model.BlogEntity;
import com.test.model.UserEntity;
import com.test.repository.BlogRepository;
import com.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;

/**
 * Created by egguncle on 17-1-24.
 *
 * 用于网页部分与blog相关的controller
 */
public class BlogController {
    // 自动装配数据库接口，不需要再写原始的Connection来操作数据库
    @Autowired
    BlogRepository blogRepository;

    @Autowired
    UserRepository userRepository;

    /**
     * 获取博客的详细内容
     * @param blogId
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/get_content/{blogId}")
    public String getContent(@PathVariable("blogId") int blogId, ModelMap modelMap) {
        //从数据库中获取对应的blog实体类
        BlogEntity blogEntity = blogRepository.findOne(blogId);
        modelMap.addAttribute("blog", blogEntity);
        return "content";
    }

    /**
     * 获取对应用户的博客列表
     * @param userId
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/myblog/{userId}")
    public String myBlog(@PathVariable("userId") int userId, ModelMap modelMap) {
        //从数据库中获取对应用户ID的博客实体类
        List<BlogEntity> listBlog = blogRepository.getBlogListByUserId(userId);
        modelMap.addAttribute("blogList", listBlog);
        return "myHome";
    }

    /**
     * 跳转到编辑博客的界面
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/jsps/edit_blog", method = RequestMethod.GET)
    public String editBlogs(ModelMap modelMap) {
        return "edit_blog";
    }

    /**
     * 添加博客
     * @param blogEntity   博客实体类
     * @param session      会话（登录用户的相关信息）
     * @param model
     * @return
     */
    @RequestMapping(value = "/add_blog", method = RequestMethod.POST)
    public String addBlog(@ModelAttribute("my_blog") BlogEntity blogEntity, HttpSession session,
                          ModelMap model) {

        System.out.println("---------------------------------------");
        System.out.println();
        System.out.println(blogEntity.getBlogTitle());
        System.out.println(blogEntity.getBlogContent());
        System.out.println("---------------------------------------");
        java.util.Date date = new java.util.Date();
        Date sqlDate = new Date(date.getTime());
        blogEntity.setBlogDate(sqlDate);

        UserEntity userEntity = (UserEntity) session.getAttribute("user");
        blogEntity.setTableUserByUserId(userEntity);


        blogRepository.save(blogEntity);

        //查询表中所有记录
        List<BlogEntity> blogList = blogRepository.findAll();
        // 将所有记录传递给要返回的jsp页面，放在List当中
        model.addAttribute("blogList", blogList);
        return "home";
    }



}
