package com.xinyu.test_boot.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xinyu.test_boot.bean.User;

@Controller
public class ThymeleafController {
	
	// 不带样式
    @RequestMapping(value = "show", method = RequestMethod.GET)
    public String show(Model model){
        model.addAttribute("uid","123456789");
        model.addAttribute("name","Jerry");
        return "show";
    }
    
    // 带样式
    @RequestMapping(value = "showStyle", method = RequestMethod.GET)
    public String showStyle(Model model){
        model.addAttribute("uid","123456789");
        model.addAttribute("name","<span style='color:red'>Jerry</span>");
        return "show_style";
    }
    
    // 返回bean
    @RequestMapping(value = "/message/member_show", method = RequestMethod.GET)
    public String memberShow(Model model) {
        User vo = new User();
        vo.setUid(12345678L);
        vo.setName("尼古拉丁.赵四");
        vo.setAge(59);
        vo.setSalary(1000.00);
        vo.setBirthday(new Date());
        model.addAttribute("member", vo);
        return "message/member_show";
    }
    
    // 数据处理
    @RequestMapping(value = "/user/set", method = RequestMethod.GET)
    public String set(Model model) {
        Set<String> allNames = new HashSet<String>() ;
        List<Integer> allIds = new ArrayList<Integer>() ;
        for (int x = 0 ; x < 5 ; x ++) {
            allNames.add("boot-" + x) ;
            allIds.add(x) ;
        }
        model.addAttribute("names", allNames) ;
        model.addAttribute("ids", allIds) ;
        model.addAttribute("mydate", new java.util.Date()) ;
        return "user_set" ;
    }
    
    // 操作内置对象
    @RequestMapping(value = "/inner", method = RequestMethod.GET)
    public String inner(HttpServletRequest request, Model model) {
        request.setAttribute("requestMessage", "springboot-request");
        request.getSession().setAttribute("sessionMessage", "springboot-session");
        request.getServletContext().setAttribute("applicationMessage",
                "springboot-application");
        model.addAttribute("url", "www.baidu.cn");
        request.setAttribute("url2",
                "<span style='color:red'>www.baidu.cn</span>");
        return "show_inner";
    }
    
    // 逻辑处理
    @RequestMapping(value = "/message/member_condition", method = RequestMethod.GET)
    public String memberCondition(Model model) {
        User vo = new User();
        vo.setUid(12345678L);
        vo.setName("阿里里");
        vo.setAge(16);
        vo.setSalary(1000.00);
        vo.setBirthday(new Date());
        model.addAttribute("member", vo);
        return "message/member_condition";
    }
    
    // 数据遍历
    @RequestMapping(value = "/user/map", method = RequestMethod.GET)
    public String map(Model model) {
        Map<String,User> allMembers = new HashMap<String,User>();
        for (int x = 0; x < 10; x++) {
            User vo = new User();
            vo.setUid(101L + x);
            vo.setName("赵四 - " + x);
            vo.setAge(9);
            vo.setSalary(99999.99);
            vo.setBirthday(new Date());
            allMembers.put("mldn-" + x, vo);
        }
        model.addAttribute("allUsers", allMembers);
        return "user_map";
    }

    // 数据遍历 list
    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<User> allMembers = new ArrayList<User>();
        for (int x = 0; x < 10; x++) {
            User vo = new User();
            vo.setUid(101L + x);
            vo.setName("赵四 - " + x);
            vo.setAge(9);
            vo.setSalary(99999.99);
            vo.setBirthday(new Date());
            allMembers.add(vo) ;
        }
        model.addAttribute("allUsers", allMembers);
        return "user_list";
    }
    
    // 页面引入测试
    @RequestMapping(value = "/import", method = RequestMethod.GET)
    public String importS(Model model) {
        model.addAttribute("content", "页面引入测试");
        return "import";
    }
}