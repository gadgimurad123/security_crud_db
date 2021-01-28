package com.gaz.web.controller;

import com.gaz.web.entity.Role;
import com.gaz.web.entity.User;
import com.gaz.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class AppController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String welcome() {
        return "welcome.html";
    }

    @GetMapping("/user")
    public String getInfoOnlyForUser(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.getUserByName(username);

        model.addAttribute("currentUser", user);
        return "user";
    }

    @GetMapping("/admin")
    public String getInfoOnlyForAdmin(Model model) {

        List<User> allUsersList = userService.getAllUsers();
        model.addAttribute("allUsersList", allUsersList);

        return "admin";
    }

    @GetMapping("/admin/user/{id}")
    public String getInfoAboutUserForAdmin(@PathVariable("id") Long id, Model model) {

        User user = userService.getUserById(id);

        model.addAttribute("user", user);
        return "about_user_for_admin";
    }

    @RequestMapping("/admin/addNewUser")
    public String addNewUser(Model model) {
        model.addAttribute("listRole", userService.getListRole());
        model.addAttribute("user", new User());
        return "new_user";
    }

    @PostMapping("/admin/saveUser")
    public String saveUser(@ModelAttribute("user") User user, @RequestParam("role") String[] role) {
        user.setRoles(getRoles(role));
        userService.saveUser(user);

        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/update/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        model.addAttribute("listRole", userService.getListRole());
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "editing_user_form";
    }

    @PostMapping(value = "/admin/edit")
    public String edit(@ModelAttribute("user") User user, @RequestParam("role") String[] role) {
        user.setRoles(getRoles(role));
        user.setId(99L);
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @RequestMapping("/admin/delete/{id}")
    public String delete(@PathVariable("id") Long id) {

        userService.deleteUser(id);
        return "redirect:/admin";
    }

    private Set<Role> getRoles(String[] role) {
        Set<Role> roleSet = new HashSet<>();
        for (String s : role) {
            roleSet.add(userService.getRoleByName(s));
        }
        return roleSet;
    }
}
