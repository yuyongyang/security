package com.example.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping("/test1")
    public @ResponseBody Object saveUser1() {
        return"ok";
    }

    @GetMapping("/test2")
    @PreAuthorize("hasRole('test')")
    public @ResponseBody Object saveUser2() {
        return "权限测试";
    }

    @GetMapping("/test3")
    public @ResponseBody Object saveUser3() {
        return "权限测试3";
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
