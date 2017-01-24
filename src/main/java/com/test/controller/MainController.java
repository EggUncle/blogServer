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

        // 将所有记录传递给要返回的jsp页面，放在userList当中
        modelMap.addAttribute("blogList", blogList);
        //   modelMap.addAttribute("userIdList",userIdLIst);

        return "test";
    }

}

