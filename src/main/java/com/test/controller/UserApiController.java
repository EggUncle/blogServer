package com.test.controller;

import com.test.model.BlogEntity;
import com.test.model.BlogJson;
import com.test.model.LoginJson;
import com.test.model.UserEntity;
import com.test.repository.BlogRepository;
import com.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
     *
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
        UserEntity user = userRepository.login(userName, passwd);

        if (user != null) {
            System.out.println("success");
            loginJson.setError(false);
            loginJson.setUserEntity(user);
            return loginJson;
        } else {
            System.out.println("failed");
            loginJson.setError(true);
            return loginJson;
        }

    }

    /**
     * 获取对应用户的博客（20条）
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
    @RequestMapping(value = "/api/blog/{type}/{userId}/{blogId}", method = RequestMethod.GET)
    public BlogJson getBlogJson(@PathVariable("type") String type, @PathVariable("userId") int userId, @PathVariable("blogId") int blogId) {

        List<BlogEntity> blogList = new ArrayList<>();

        switch (type) {
            case "one": {
                blogList.add(blogRepository.findOne(blogId));
            }
            break;
            case "max": {
                blogList.addAll(blogRepository.getBlogListMaxWithUser(blogId, userId));
            }
            break;
            case "min": {
                blogList.addAll(blogRepository.getBlogListMinWithUser(blogId, userId));
            }
            break;
        }
        BlogJson blogJson = new BlogJson();
        blogJson.setError(false);
        blogJson.setResults(blogList);

        return blogJson;
    }


}
