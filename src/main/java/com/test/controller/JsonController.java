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
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping(value = "/json/client_login", method = RequestMethod.POST)
    public LoginJson login(@RequestParam("userName") String userName, @RequestParam("passwd") String passwd) {
//        String userName = (String) httpServletRequest.getAttribute("userName");
//        String passwd = (String) httpServletRequest.getAttribute("passwd");

        System.out.println("---------------------------");
        System.out.println(userName + "        " + passwd);
        System.out.println("---------------------------");
        LoginJson loginJson = new LoginJson();
        loginJson.setError(false);

//        UserDao userDao = new UserDao();
//        UserEntity userEntity = userDao.login(userName, passwd);
//        String username=userEntity.getUsername();
//        String passwd=userEntity.getUserpasswd();
        //查询是否有对应用户名密码的用户
        UserEntity user = userRepository.login(userName, passwd);

        if (user != null) {
            System.out.println("success");
            loginJson.setUserName(userName);
            loginJson.setUserId(user.getUserId());
            loginJson.setSuccess(true);
            return loginJson;
        } else {
            System.out.println("failed");
            loginJson.setUserName("");
            loginJson.setSuccess(false);
            return loginJson;
        }

    }

}
