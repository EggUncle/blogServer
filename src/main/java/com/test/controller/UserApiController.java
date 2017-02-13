package com.test.controller;




import com.google.gson.Gson;
import com.squareup.okhttp.*;
import com.squareup.okhttp.RequestBody;
import com.test.jsonbean.RegisteredJson;
import com.test.model.BlogEntity;
import com.test.jsonbean.BlogJson;
import com.test.jsonbean.LoginJson;
import com.test.model.UserEntity;
import com.test.repository.BlogRepository;
import com.test.repository.UserRepository;
import com.test.util.CheckSumBuilder;
import com.test.util.ImgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;


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

    private final static String APP_KEY = "6fdfb363550db8fc24da4d44fb7f1238";
    private final static String APP_SECRET = "9243c16b3ba1";

    /**
     * 客户端进行登录的类
     *
     * @param userName
     * @param passwd   此处的密码是在客户端经过MD5加密的值，加密过程在客户端执行
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/user/login", method = RequestMethod.POST)
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


    /**
     * 用户注册接口
     *
     * @param userName 用户名
     * @param nickName 昵称
     * @param passwd   密码
     * @return 注册的用户实例对象
     */
    @ResponseBody
    @RequestMapping(value = "/api/user/registered", method = RequestMethod.POST)
    public LoginJson registeredWithIM(@RequestParam("userName") String userName, @RequestParam("nickName") String nickName, @RequestParam("passwd") String passwd) {
        //建立返回对象
        LoginJson loginJson=new LoginJson();
        //判断用户提交的用户名是否重复
        List<UserEntity> user = userRepository.getUserByName(userName);
        if (user == null || user.size() == 0) {
            //新建用户实例并且将相关信息存入其中
            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(userName);
            userEntity.setNickname(nickName);
            userEntity.setUserpasswd(passwd);

            //向网易云信发送请求
            String resultJson = registeredToIM(userName, userName);
            //将网易云信返回的字符串转化为bean类
            Gson gson=new Gson();
            RegisteredJson registeredJson=gson.fromJson(resultJson,RegisteredJson.class);

            //如果请求成功
            if (registeredJson.getCode()==200) {
                //从bean类中获取token对象
                String token = registeredJson.getInfo().getToken();
                //将token存入user中
                userEntity.setToken(token);
                //存入数据库
                userRepository.save(userEntity);

                //构建返回的对象
                loginJson.setError(false);
                loginJson.setUserEntity(userEntity);
                return loginJson;
            }

        }
        loginJson.setError(true);



        return loginJson;
    }


    /**
     * 网易云信注册用户部分
     *
     * @param accid
     * @param name
     * @return 请求结果
     * @throws IOException
     */
    private String registeredToIM(String accid, String name) {
        String url = "https://api.netease.im/nimserver/user/create.action";
        OkHttpClient client = new OkHttpClient();
        //   MediaType MEDIA_TYPE_TEXT = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8");
        // RequestBody requestBody = RequestBody.create(MEDIA_TYPE_TEXT, "");
        //设置body
        RequestBody requestBody = new FormEncodingBuilder()
                .add("accid", accid)
                .add("name", name)
                .build();

        //随机数  10000~99999
        String nonce = (new Random().nextInt(89999) + 10000) + "";
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String checkSum = CheckSumBuilder.getCheckSum(APP_SECRET, nonce, curTime);//参考 计算CheckSum的java代码

        Request request = new Request.Builder().url(url)
                //设置header
                .addHeader("AppKey", APP_KEY)
                .addHeader("Nonce", nonce)
                .addHeader("CurTime", curTime)
                .addHeader("CheckSum", checkSum)
                .addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
                .post(requestBody)
                .build();


        Response response = null;
        String responseStr = "";
        System.out.println("---------------------------");
        try {
            //发送请求
            response = client.newCall(request).execute();
            responseStr = response.body().string();
            System.out.println(responseStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("---------------------------");

        return responseStr;
    }

    /**
     *更新用户信息
     * @param token
     * @param json  传递过来的用户对象转化成的json
     * @param base64StrOfImg
     * @param imageType
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/user/updateinfo", method = RequestMethod.POST)
    private UserEntity updateUserInfo(@RequestParam("token")String token,@RequestParam("json")String json, @RequestParam(value = "base64StrOfImg", required = false) String base64StrOfImg, @RequestParam(value = "imgtype") String imageType, HttpServletRequest request){
        Gson gson=new Gson();
        UserEntity userEntity=gson.fromJson(json,UserEntity.class);
        String userName=userEntity.getUsername();
        UserEntity userFind=userRepository.loginWithToken(userName,token);
        if (userFind==null){
            return null;
        }

        System.out.println(token);
        System.out.println(json);
        System.out.println(base64StrOfImg);
        System.out.println(imageType);

        //获得物理路径webapp所在路径
        String pathRoot = request.getSession().getServletContext().getRealPath("");
        //保存头像图片并获取路径
        String path= ImgUtil.saveImg(pathRoot,base64StrOfImg,imageType,ImgUtil.USER_ICON);
        System.out.println(path+"-------------------------");
        userEntity.setIconPath(path);

        userEntity.setUserpasswd(userFind.getUserpasswd());

        userRepository.saveAndFlush(userEntity);
        return userEntity;
    }

}
