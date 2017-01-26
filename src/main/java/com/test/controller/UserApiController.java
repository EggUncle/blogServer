package com.test.controller;

import com.test.model.LoginJson;
import com.test.model.UserEntity;
import com.test.repository.BlogRepository;
import com.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by egguncle on 17-1-24.
 * <p>
 * 用于与用户以及json相关的controller
 */

@Controller
public class UserApiController {


    // 自动装配数据库接口，不需要再写原始的Connection来操作数据库
    @Autowired
    BlogRepository blogRepository;

    @Autowired
    UserRepository userRepository;

    /**
     * 客户端进行登录的类
     * @param userName
     * @param passwd   此处的密码是在客户端经过MD5加密的值，加密过程在客户端执行
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/client_login", method = RequestMethod.POST)
    public LoginJson login(@RequestParam("userName") String userName, @RequestParam("passwd") String passwd) {


        System.out.println("---------------------------");
        System.out.println(userName + "        " + passwd);
        System.out.println("---------------------------");

        LoginJson loginJson = new LoginJson();
        loginJson.setError(false);
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
