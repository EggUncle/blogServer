package com.test.controller;

import com.test.dao.UserDao;
import com.test.model.BlogEntity;
import com.test.model.BlogJson;
import com.test.model.LoginJson;
import com.test.model.UserEntity;
import com.test.repository.BlogRepository;
import com.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.List;

/**
 * Created by egguncle on 17-1-20.
 * <p>
 * json相关的controller
 */

@Controller
public class JsonController {

    // 自动装配数据库接口，不需要再写原始的Connection来操作数据库
    @Autowired
    BlogRepository blogRepository;

    @Autowired
    UserRepository userRepository;


    @ResponseBody
    @RequestMapping(value = "/json/blog", method = RequestMethod.GET)
    public BlogJson getBlogJson() {
        List<BlogEntity> blogList = blogRepository.findAll();
        BlogJson blogJson = new BlogJson();
        blogJson.setError(false);
        blogJson.setResults(blogList);


        //  String jsonStr = JSON.toJSONString(blogJson);
        return blogJson;
    }

    @ResponseBody
    @RequestMapping(value = "/json/client_login", method = RequestMethod.POST)
    public LoginJson login(@RequestParam("userName") String userName, @RequestParam("passwd") String passwd) {
//        String userName = (String) httpServletRequest.getAttribute("userName");
//        String passwd = (String) httpServletRequest.getAttribute("passwd");

        System.out.println("---------------------------");
        System.out.println(userName + "        " + passwd);
        System.out.println("---------------------------");
        LoginJson loginJson = new LoginJson();
        loginJson.setError(false);

        UserDao userDao = new UserDao();
        UserEntity userEntity = userDao.login(userName, passwd);
        if (userEntity != null) {
            System.out.println("success");
            loginJson.setUserName(userName);
            loginJson.setUserId(userEntity.getUserId());
            loginJson.setSuccess(true);
            return loginJson;
        }else{
            System.out.println("failed");
            loginJson.setUserName("");
            loginJson.setSuccess(false);
            return loginJson;
        }

    }

    @ResponseBody
    @RequestMapping(value = "/client/submit_blog",method = RequestMethod.POST)
    public String login(@RequestParam("userId") int userId,@RequestParam("title") String title,@RequestParam("content") String content){
        java.util.Date date = new java.util.Date();
        Date sqlDate = new Date(date.getTime());
        BlogEntity blogEntity=new BlogEntity();
        blogEntity.setBlogDate(sqlDate);
        blogEntity.setBlogTitle(title);
        blogEntity.setBlogContent(content);

        UserDao userDao=new UserDao();

        UserEntity userEntity=userDao.getUserById(userId);
        blogEntity.setTableUserByUserId(userEntity);

        blogRepository.save(blogEntity);

        return "success";
    }
}
