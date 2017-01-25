package com.test.controller;

import com.test.dao.UserDao;
import com.test.model.BlogEntity;
import com.test.model.BlogJson;
import com.test.model.LoginJson;
import com.test.model.UserEntity;
import com.test.repository.BlogRepository;
import com.test.repository.UserRepository;
import com.test.util.CipherUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by egguncle on 17-1-20.
 * <p>
 * 用户相关的controller
 */

@Controller
public class UserController {

    // 自动装配数据库接口，不需要再写原始的Connection来操作数据库
    @Autowired
    BlogRepository blogRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/log_out")
    public String logOut(HttpSession session, ModelMap model) {
        session.invalidate();
        List<BlogEntity> blogList = blogRepository.findAll();
        // 将所有记录传递给要返回的jsp页面，放在List当中
        model.addAttribute("blogList", blogList);
        return "home";
    }

    /**
     * 用于用户登录
     *
     * @param userEntity
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/login_user", method = RequestMethod.POST)
    public String loginUser(@ModelAttribute("my_login") UserEntity userEntity,
                            ModelMap model, HttpSession session) {
        //获取提交表单的用户和你妈的数据
        String userName = userEntity.getUsername();
        String passwd = userEntity.getUserpasswd();
        //将输入的密码进行MD5加密，再与数据库里的MD5值比对
        //进行MD5加密
        CipherUtil cipherUtil = new CipherUtil();
        String passwdByMD5 = cipherUtil.generatePassword(passwd);

        //查询是否有对应用户名密码的用户
        UserEntity user = userRepository.login(userName, passwdByMD5);

        //如果用户存在，跳转到首页，并表示为登录状态
        if (user != null) {
            session.setAttribute("user", user);
            //查询表中所有记录
            List<BlogEntity> blogList = blogRepository.findAll();
            // 将所有记录传递给要返回的jsp页面，放在List当中
            model.addAttribute("blogList", blogList);

            return "home";
        }

        //如果用户不存在，则跳转回登录页面
        model.addAttribute("login", false);
        return "login";
    }

    /**
     * 用于跳转到注册界面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/registered", method = RequestMethod.GET)
    public String registered(ModelMap model) {
        model.addAttribute("repeat", false);
        return "registered";
    }

    /**
     * 用于跳转到登录界面
     *
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap modelMap) {
        modelMap.addAttribute("login", true);
        return "login";
    }

    /**
     * 用于用户注册
     *
     * @param userEntity
     * @param model
     * @return
     */
    @RequestMapping(value = "/add_user", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("my_user") UserEntity userEntity,
                          ModelMap model,HttpServletRequest request) {
        System.out.println("-----------------------------");
        System.out.println(userEntity.getBgPath());
        System.out.println(userEntity.getIconPath());
        System.out.println(userEntity.getIconFile().getName());
        String name=userEntity.getIconFile().getName();
        String type=name.substring(name.indexOf("."),name.length());
        System.out.println(type);
        System.out.println(request.getSession().getServletContext().getRealPath(""));
        System.out.println("-----------------------------");
        // 查询表中所有记录
        //List<TableBlogEntity> blogList = blogRepository.findAll();

        return "registered";
        //判断用户提交的用户名是否重复
//        String userName = userEntity.getUsername();
//        List<UserEntity> user = userRepository.getUserByName(userName);
//        if (user == null || user.size() == 0) {
//            //获取提交来的用户实体类的密码
//            String inputPassWd = userEntity.getUserpasswd();
//            //进行MD5加密
//            CipherUtil cipherUtil = new CipherUtil();
//            String passwdByMD5 = cipherUtil.generatePassword(inputPassWd);
//            //将用户实体类的密码设置为加密好的MD5值
//            userEntity.setUserpasswd(passwdByMD5);
//            userRepository.save(userEntity);
//        } else {
//            model.addAttribute("repeat", true);
//            return "registered";
//        }
//        //查询表中所有记录
//        List<UserEntity> userList = userRepository.findAll();
//        // 将所有记录传递给要返回的jsp页面，放在List当中
//        model.addAttribute("userList", userList);
//        return "test";
    }

    /**
     * 跳转到用户主页
     * @param userId
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/user_index/{userId}")
    public String myBlog(@PathVariable("userId") int userId, ModelMap modelMap) {
        //从数据库中获取对应用户ID的博客实体类
        List<BlogEntity> listBlog = blogRepository.getBlogListByUserId(userId);
        modelMap.addAttribute("blogList", listBlog);
        return "userIndex";
    }

}
