package com.sciencom.InterceptorsSpring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jupiterzhuo on 03/05/19.
 */
@Controller
public class HomeController {
    @RequestMapping(value = {"/","/index"})
    public String Home (){
        System.out.println("Home Controller");
        return "home";
    }
    @Deprecated
    @RequestMapping(value = {"/old-page"})
    public String OldPage(){
        System.out.println("Old Page Controller");
        return "oldPage";
    }
    @RequestMapping(value = {"/new-page"})
    public String NewPage(){
        System.out.println("New Page Controller");
        return "newPage";
    }

}
