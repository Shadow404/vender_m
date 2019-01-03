package com.wemjingwang.demo1.controller.api;

import com.wemjingwang.demo1.domain.Url;
import com.wemjingwang.demo1.domain.User;
import com.wemjingwang.demo1.repository.UrlRepository;
import com.wemjingwang.demo1.repository.UserRepository;
import com.wemjingwang.demo1.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.xml.soap.SAAJResult;
import java.util.List;

@Controller
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UrlRepository urlRepository;
    @RequestMapping("adminLogin")
    public String adminLogin(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return "adminLogin.html";
    }
    @RequestMapping("doAdminLogin")
    public String doAdminLogin(RedirectAttributes redirectAttributes, ModelMap modelMap, @RequestParam String userName, @RequestParam String userPassWord, HttpServletRequest request){
        User user=userRepository.findByUserName(userName);
        if(user!=null&&user.getUserPassWord().equals(userPassWord)){
            log.info("{} user",user);
            List<Url> urlList=urlRepository.findByUrlType(user.getUserType());
            modelMap.addAttribute("urlList",urlList);
            modelMap.addAttribute("userName",user.getUserRealName());
            request.getSession().setAttribute("user",user);
            return "forward:adminIndex";
        }
        String msg="登录失败！请检查用户名或者密码！";
        redirectAttributes.addFlashAttribute("msg",msg);
        return "redirect:/adminLogin";
    }
    @RequestMapping("adminIndex")
    public String adminIndex(){
        return "adminIndex.html";
    }
}
