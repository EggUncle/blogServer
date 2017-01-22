package com.test.controller;

import com.test.dao.BlogDao;
import com.test.dao.UserDao;
import com.test.model.BlogEntity;
import com.test.model.BlogJson;

import com.test.model.UserEntity;
import com.test.repository.BlogRepository;
import com.test.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;

/**
 * Created by egguncle on 17-1-11.
 */

@Controller
public class MainController {

    @Autowired
    // 自动装配数据库接口，不需要再写原始的Connection来操作数据库
            BlogRepository blogRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(ModelMap modelMap) {
        //查询表中所有记录
        List<BlogEntity> blogList = blogRepository.findBlogList();
        // 将所有记录传递给要返回的jsp页面，放在List当中
        modelMap.addAttribute("blogList", blogList);

        return "home";
    }

    @RequestMapping(value = "/jsps/test", method = RequestMethod.GET)
    public String getBlogs(ModelMap modelMap) {
        // 查询user表中所有记录
        List<BlogEntity> blogList = blogRepository.findAll();
//
//        for (TableBlogEntity blog : blogList) {
//            // userIdLIst.add(blog.getTableUserByUserId().getUserId());
//            String name = "";
//            if (blog.getTableUserByUserId() == null) {
//
//              TableUserEntity userEntity=  new TableUserEntity();
//              userEntity.setUsername("未知");
//              blog.setTableUserByUserId(userEntity);
//            }
//        }

        // 将所有记录传递给要返回的jsp页面，放在userList当中
        modelMap.addAttribute("blogList", blogList);
        //   modelMap.addAttribute("userIdList",userIdLIst);

        return "test";
    }

    @RequestMapping(value = "/jsps/edit_blog", method = RequestMethod.GET)
    public String editBlogs(ModelMap modelMap) {
        return "edit_blog";
    }

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
        // 查询表中所有记录
        //List<TableBlogEntity> blogList = blogRepository.findAll();
//        long id = System.currentTimeMillis();
//        blogEntity.setBlogId(id);
        blogRepository.save(blogEntity);

        //查询表中所有记录
        List<BlogEntity> blogList = blogRepository.findAll();
        // 将所有记录传递给要返回的jsp页面，放在List当中
        model.addAttribute("blogList", blogList);
        return "home";
    }


//    @RequestMapping(value = "/jsps/registered", method = RequestMethod.GET)
//    public String registered(ModelMap model) {
//
//        model.addAttribute("repeat",false);
//        return "registered";
//    }

    @RequestMapping(value = "/add_user", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("my_user") UserEntity userEntity,
                          ModelMap model) {
        System.out.println("---------------------------------------");
        System.out.println(userEntity.getUsername());
        System.out.println(userEntity.getUserpasswd());
        System.out.println("---------------------------------------");

        // 查询表中所有记录
        //List<TableBlogEntity> blogList = blogRepository.findAll();

        //判断用户提交的用户名是否重复
        String userName = userEntity.getUsername();
        List<UserEntity> user = userRepository.getUserByName(userName);
        if (user==null||user.size()==0){
            userRepository.save(userEntity);
        }else{
            model.addAttribute("repeat",true);
            return "registered";
        }
        //查询表中所有记录
        List<UserEntity> userList = userRepository.findAll();
        // 将所有记录传递给要返回的jsp页面，放在List当中
        model.addAttribute("userList", userList);
        return "test";
    }

    @RequestMapping(value = "/registered", method = RequestMethod.GET)
    public String registered(ModelMap model) {

        model.addAttribute("repeat",false);
        return "registered";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap modelMap) {
        modelMap.addAttribute("login", true);
        return "login";
    }

    @RequestMapping(value = "/login_user", method = RequestMethod.POST)
    public String loginUser(@ModelAttribute("my_login") UserEntity userEntity,
                            ModelMap model, HttpSession session) {
        System.out.println("---------------------------------------");
        System.out.println(userEntity.getUsername());
        System.out.println(userEntity.getUserpasswd());
        System.out.println("---------------------------------------");

//        UserDao userDao = new UserDao();
//        UserEntity tableUserEntity = userDao.login(userEntity.getUsername(), userEntity.getUserpasswd());

        String userName = userEntity.getUsername();
        String passwd = userEntity.getUserpasswd();
        //查询是否有对应用户名密码的用户
        UserEntity user = userRepository.login(userName, passwd);

        if (user != null) {
            session.setAttribute("user", user);
            //查询表中所有记录
            List<BlogEntity> blogList = blogRepository.findAll();
            // 将所有记录传递给要返回的jsp页面，放在List当中
            model.addAttribute("blogList", blogList);

            return "home";
        }


        model.addAttribute("login", false);
        return "login";
    }

    @RequestMapping(value = "/log_out")
    public String logOut(HttpSession session, ModelMap model) {
        session.invalidate();
        List<BlogEntity> blogList = blogRepository.findAll();
        // 将所有记录传递给要返回的jsp页面，放在List当中
        model.addAttribute("blogList", blogList);
        return "home";
    }

    @RequestMapping(value = "/get_content/{blogId}")
    public String getContent(@PathVariable("blogId") int blogId, ModelMap modelMap) {
        BlogEntity blogEntity = blogRepository.findOne(blogId);

        System.out.println("----------------------------");
        System.out.println(blogId);
        System.out.println(blogEntity.getBlogTitle());
        System.out.println("----------------------------");
        modelMap.addAttribute("blog", blogEntity);
        return "content";
    }

    @RequestMapping(value = "/myblog/{userId}")
    public String myBlog(@PathVariable("userId") int userId, ModelMap modelMap) {

        List<BlogEntity> listBlog = blogRepository.getBlogListByUserId(userId);
        modelMap.addAttribute("blogList", listBlog);
        return "myHome";
    }
}

