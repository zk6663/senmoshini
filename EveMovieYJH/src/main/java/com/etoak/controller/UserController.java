package com.etoak.controller;

import com.etoak.bean.Page;
import com.etoak.bean.User;
import com.etoak.exception.LoginException;
import com.etoak.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 跳转页面
     */
    @RequestMapping("/to_login")
    public String toLoginPage() {
        return "login";
    }

    @RequestMapping("/to_admin")
    public String toAdminPage() {
        return "admin";
    }

    @RequestMapping("/to_user")
    public String toUserPage() {
        return "user";
    }


    /**
     * 登录请求
     *
     * @param request
     * @param user
     * @return
     */
    @RequestMapping("/login")
    public String login(HttpServletRequest request, //
                        User user) {
        if (StringUtils.isEmpty(user.getName()) //
                || StringUtils.isEmpty(user.getPassword())) {
            throw new LoginException("用户名或密码不能为空");
        }

        String password = user.getPassword();
        user = userService.queryUser(user.getName(), password);
        if (ObjectUtils.isEmpty(user)) {
            throw new LoginException("用户名或密码错误");
            // return "redirect:/user/to_login";
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        if (user.getLevel() == 0) {
            return "redirect:/user/to_user";
        }
        if (user.getLevel() == 1) {
            return "redirect:/user/to_admin";
        }

        return "redirect:/user/to_login";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("user");

        // 销毁session，这样的话，session中所有的数据都会被清空
        session.invalidate();
        return "redirect:/user/to_login";
    }

}
