package controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pojo.User;

@Controller
public class UserController {
    // 跳转到系统首页
    @RequestMapping("/main")
    public String toMain() {
        return "main";
    }

    // 跳转到登录页面
    @RequestMapping("/login")
    public String toLogin() {
        return "login";
    }

    // 跳转到订单信息页面
    @RequestMapping("/orderInfo")
    public String toOrderInfo() {
        return "orderInfo";
    }

    // 用户登录
    @RequestMapping("/doLogin")
    public String doLogin(User user, HttpSession session, Model model) {

        if ("heima".equals(user.getUsername())
                && "123456".equals(user.getPassword())) {
            session.setAttribute("USER_SESSION", user);
            return "redirect:/main";
        }
        model.addAttribute("msg", "Username or password is incorrect!");
        return "login";
    }
    // 用户退出
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
