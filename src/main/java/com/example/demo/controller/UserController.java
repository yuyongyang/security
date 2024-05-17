package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



// RestContoller 会使重定向失效
@Controller
public class UserController {

//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private DeptMapper deptMapper;

    @GetMapping("/saveUser2")
    public @ResponseBody Object saveUser2() {
        return"ok";
    }

    @GetMapping("/saveUser")
    public Object saveUser() {
        return "权限测试";
    }


    /**
     * 跳转到登录页面
     * @return 试图解析器
     */
    @GetMapping("/mylogin")
    public String login(){
        return "/mylogin/login";
    }

    /**
     * 退出登录页面
     * @param model 数据model
     */
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "Hello, Thymeleaf!");
        return "loginout";
    }




}
