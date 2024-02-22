package WebInfo_248.ForumWeb.Controllers;

import WebInfo_248.ForumWeb.Models.Discussion;
import WebInfo_248.ForumWeb.Models.User;
import WebInfo_248.ForumWeb.Service.DiscussionService;
import WebInfo_248.ForumWeb.Service.UserService;
import WebInfo_248.ForumWeb.dto.LoginDto;
import WebInfo_248.ForumWeb.dto.UserDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private DiscussionService discussionService;


    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        String currentUser = (String) session.getAttribute("currentUser");

        if (currentUser != null) {
            model.addAttribute("username", currentUser);

            // Provera uloge
            String userRole = (String) session.getAttribute("userRole");
            if ("admin".equals(userRole)) {
                // Redirektovanje na Admin Dashboard
                return "redirect:adminDashboard";
            }
        }

        List<Discussion> discussions = discussionService.getAllDiscussions();
        model.addAttribute("discussions", discussions);

        return "index";
    }


    @PostMapping("/register")
    public String registerUser(UserDto userDTO, RedirectAttributes redirectAttributes, HttpSession session) {
        userService.registerUser(userDTO);
        session.setAttribute("currentUser", userDTO.getUsername());
        redirectAttributes.addFlashAttribute("message", "Registration successful");
        System.out.println("User registered and logged in: " + userDTO.getUsername());
        return "redirect:/";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute LoginDto loginDTO, RedirectAttributes redirectAttributes, HttpSession session) {
        try {
            User user = userService.loginUser(loginDTO);

            if (user != null) {
                session.setAttribute("currentUser", user.getUsername());
                session.setAttribute("userRole", user.getRole()); // Dodajte ovu liniju za postavljanje uloge
                System.out.println("User logged in: " + user.getUsername());
                redirectAttributes.addFlashAttribute("message", "Login successful");
            } else {
                redirectAttributes.addFlashAttribute("error", "Invalid credentials");
            }

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            redirectAttributes.addFlashAttribute("error", "An error occurred during login");
        }

        return "redirect:/";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        // Poništavanje trenutne sesije
        session.invalidate();

        // Dodavanje poruke o uspešnom odjavljivanju
        redirectAttributes.addFlashAttribute("message", "Logout successful");

        // Preusmeravanje na početnu stranicu
        return "redirect:/";
    }


    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "registration"; // Assuming your registration page is named registration.html
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginDto", new LoginDto());
        return "login";
    }

}