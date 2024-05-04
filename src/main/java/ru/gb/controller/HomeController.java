package ru.gb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.gb.service.AdminService;
import ru.gb.service.HomeService;
import ru.gb.service.UserService;
@Controller
public class HomeController {
    private final HomeService homeService;
    private final AdminService adminService;
    private final UserService userService;

    public HomeController(HomeService homeService, AdminService adminService, UserService userService) {
        this.homeService = homeService;
        this.adminService = adminService;
        this.userService = userService;
    }
    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("text",homeService.getText());
        return "home";
    }
    @GetMapping("/home")
    public String home(){
        return "redirect:/";
    }
    @GetMapping("/user")
    public String user(Model model) {
        model.addAttribute("text",userService.getText());
        return "user";
    }
    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("text", adminService.getText());
        return "admin";
    }
    @GetMapping("private-page")
    public String privatePage(Model model) {
        return "private-page";
    }
    @GetMapping("public-page")
    public String publicPage(Model model) {
        return "public-page";
    }
}
