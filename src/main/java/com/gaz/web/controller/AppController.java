package com.gaz.web.controller;

import com.gaz.web.dao.UserDao;
import com.gaz.web.entity.Role;
import com.gaz.web.entity.User;
import com.gaz.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AppController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/user")
    public String getInfoOnlyForUser(Model model, HttpServletRequest request) { //@RequestParam("userName") String nameAdmin,
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userService.getUserByName(name);

        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/admin")
    public String getInfoOnlyForAdmin(Model model) {

        List<User> allUsersList = userService.getAllUsers();
        model.addAttribute("allUsersList", allUsersList);

        return "admin";
    }

    @GetMapping("/admin/user")
    public String getInfoOnlyForUser(@RequestParam("userName") String name, Model model, HttpServletRequest request) { //,

        User user = userService.getUserByName(name);

        model.addAttribute("user", user);
        return "about_user_for_admin";
    }

    @RequestMapping("/admin/addNewUser")
    public String addNewUser(Model model) {

        User user = new User();
        model.addAttribute("user", user);
        return "editing_user_form";
    }

    @RequestMapping("/admin/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        // FIXME: add auto-generated ID and select ROLES
        user.setId((long) 99);
        user.setRoles(getUserRoles().keySet());

        userService.saveUser(user);

        return "redirect:/admin";
    }

    @RequestMapping("/admin/update")
    public String updateUser(@RequestParam("userName") String name, Model model) {

        User user = userService.getUserByName(name);
        model.addAttribute("user", user);
        return "editing_user_form";
    }

    @RequestMapping("/admin/delete")
    public String deleteUser(@RequestParam("userId") Long id) {

        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @ModelAttribute("userRolesList")
    public Map<Role, String> getUserRoles() {
        Map<Role, String> userRolesList = new HashMap<>();
        userRolesList.put(new Role(1L, "ROLE_USER"), "User");
        userRolesList.put(new Role(2L, "ROLE_ADMIN"), "Admin");
        return userRolesList;
    }

    @GetMapping("/all")
    public String forOther() {
        return "for_other";
    }
}
