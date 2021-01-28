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

    /*
    Done
     */
    @GetMapping("/")
    public String welcome() {
        return "welcome.html";
    }

    /*
    Done
     */
    @GetMapping("/user")
    public String getInfoOnlyForUser(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.getUserByName(username);

        model.addAttribute("currentUser", user);
        return "user";
    }

    /*
    Done
     */
    @GetMapping("/admin")
    public String getInfoOnlyForAdmin(Model model) {

        List<User> allUsersList = userService.getAllUsers();
        model.addAttribute("allUsersList", allUsersList);

        return "admin";
    }

    /*
    Done
     */
    @GetMapping("/admin/user/{id}")
    public String getInfoAboutUserForAdmin(@PathVariable("id") Long id, Model model) {

        User user = userService.getUserById(id);

        model.addAttribute("user", user);
        return "about_user_for_admin";
    }


    @RequestMapping("/admin/addNewUser")
    public String addNewUser(Model model) {
        model.addAttribute("listRole", userService.getListRole());
        User user = new User();
        model.addAttribute("user", user);
        return "new_user";
    }

    @PostMapping("/admin/saveUser")
    public String saveUser(@ModelAttribute("user") User user, @RequestParam("role") String[] role) {
        user.setRoles(getAddRole(role));
        userService.saveUser(user);

        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/update/{id}")
    public String updateUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("listRole", userService.getListRole());
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "editing_user_form";
    }

    @PostMapping(value = "/admin/userEdit")
    public String Update(@ModelAttribute("user") User user, @RequestParam("role") String[] role) {
        user.setRoles(getAddRole(role));
        user.setId(99L);
        userService.saveUser(user);
        return "redirect:/admin";
    }

    /*
    Do not work
    Request processing failed; nested exception is
    javax.persistence.TransactionRequiredException:
    Executing an update/delete query
     */
    @RequestMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {

        userService.deleteUser(id);
        return "redirect:/admin";
    }

    private Set<Role> getAddRole(String[] role) {
        Set<Role> roleSet = new HashSet<>();
        for (String s : role) {
            roleSet.add(userService.getRoleByName(s));
        }
        return roleSet;
    }

    @GetMapping("/all")
    public String forOther() {
        return "for_other";
    }
}


//    @RequestMapping("/admin/update")
//    public String updateUser(@RequestParam("username") String username, Model model) {
//
//        User user = userService.getUserByName(username);
//        model.addAttribute("user", user);
//        return "editing_user_form";
//    }


//    @RequestMapping(method = RequestMethod.POST)
//    public String waiter(@ModelAttribute Role selectForm, Model model) {
//        System.out.println(selectForm.getRole());
//
//        model.addAttribute("selectForm", selectForm);
////        model.addAttribute("userList", userService.findAll());
//
//        return "editing_user_form";
//    }

//    @ModelAttribute("userRolesList")
//    public Map<Role, String> getUserRoles() {
//        Map<Role, String> userRolesList = new HashMap<>();
//        userRolesList.put(new Role(1L, "ROLE_USER"), "User");
//        userRolesList.put(new Role(2L, "ROLE_ADMIN"), "Admin");
//        return userRolesList;
//    }