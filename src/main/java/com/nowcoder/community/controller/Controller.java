package com.nowcoder.community.controller;

import com.nowcoder.community.service.AlphaService;
import com.nowcoder.community.util.CommunityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@org.springframework.stereotype.Controller
@RequestMapping("/alpha")
public class Controller {
    @RequestMapping("/first")
    @ResponseBody//响应
    public String hello(){
        return "firstpage";
    }

    @Autowired
    private AlphaService alphaService;
    @RequestMapping("/second")
    @ResponseBody
    public String second(){
        return alphaService.find();
    }

    @RequestMapping(path="/teacher",method= RequestMethod.GET)
    public String getTeacher(Model model){
        model.addAttribute("name","lisi");
        model.addAttribute("age",30);
        return "/demo/view";
    }
    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response){
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        Enumeration<String> enumeration=request.getHeaderNames();
        while(enumeration.hasMoreElements()){
            String name=enumeration.nextElement();
            String value=request.getHeader(name);
            System.out.println(name+": "+value);
        }
        System.out.println(request.getParameter("code"));

        response.setContentType("text/html;charset=utf-8");
        try ( PrintWriter writer=response.getWriter();){
            writer.write("<h1>fff</h1>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @RequestMapping(path="/get",method=RequestMethod.GET)
    @ResponseBody
    public String getMethod(
            @RequestParam(name="current",required = false,defaultValue = "hello") String current,
            @RequestParam(name="num",required = false,defaultValue="2") int num){
        return current+num;
    }
   @RequestMapping(path = "/student/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id){
       System.out.println(id);
       return "a student";
   }
   @RequestMapping(path = "/studentq",method=RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name,int age){
       System.out.println(name);
       System.out.println(age);
        return "hello";
   }
   @RequestMapping(path="/teacher2",method=RequestMethod.GET)
    public ModelAndView getTeacher2(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("name","zhangsan");
        modelAndView.addObject("age",50);
        modelAndView.setViewName("demo/view1");
        return modelAndView;
   }

   @RequestMapping(path="/emp",method = RequestMethod.GET)
   @ResponseBody
    public Map<String,Object>getEmp(){
        Map<String,Object>emp=new HashMap<>();
        emp.put("name","zhangsan");
        emp.put("age",30);
        emp.put("salary",50000);
        return emp;
   }

   //cookie示例
    @RequestMapping(path="/cookie/set",method = RequestMethod.GET)
    @ResponseBody
    public String setCookie(HttpServletResponse response){
        //创建cookie
         Cookie cookie=new Cookie("code", CommunityUtil.generateUUID());
         //设置cookie生效范围
        cookie.setPath("/community/alpha");
        //设置cookie生存时间
        cookie.setMaxAge(60*10);
        //发送cookie
        response.addCookie(cookie);

        return "set cookie";
    }

    @RequestMapping(path="/cookie/get",method = RequestMethod.GET)
    @ResponseBody

    public String getcookie(@CookieValue("code")String code){
        System.out.println(code);
        return "get cookie";
    }

    //session示例
    @RequestMapping(path="/session/set",method = RequestMethod.GET)
    @ResponseBody
    public String setSession(HttpSession session){
        session.setAttribute("id",1);
        session.setAttribute("name","test");
        return "set session";
    }

    @RequestMapping(path="/session/get",method = RequestMethod.GET)
    @ResponseBody
    public String getSession(HttpSession session){
        System.out.println(session.getAttribute("id"));
        System.out.println(session.getAttribute("name"));
        return "get session";
    }
}
