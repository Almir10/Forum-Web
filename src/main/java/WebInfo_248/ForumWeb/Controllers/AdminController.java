package WebInfo_248.ForumWeb.Controllers;

import WebInfo_248.ForumWeb.Models.Discussion;
import WebInfo_248.ForumWeb.Models.User;
import WebInfo_248.ForumWeb.Service.DiscussionService;
import WebInfo_248.ForumWeb.Service.UserService;
import WebInfo_248.ForumWeb.dto.DiscussionDto;
import WebInfo_248.ForumWeb.dto.UserDto;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private DiscussionService discussionService;


    @GetMapping("/adminDashboard")
    public String adminDashboard(Model model, HttpSession session) {
        String currentUserRole = (String) session.getAttribute("userRole");
        if (currentUserRole == null || !currentUserRole.equals("admin")) {
            return "redirect:/";
        }

        List<Discussion> discussions = discussionService.getAllDiscussions();
        model.addAttribute("discussions", discussions);

        // Dodajte listu svih korisnika
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);

        return "adminDashboard";
    }

    @PostMapping("/addDiscussion")
    public String addDiscussion(@ModelAttribute Discussion newDiscussion) {
        // Dodajte logiku za dodavanje nove diskusije
        discussionService.addDiscussion(newDiscussion);
        return "redirect:/admin/adminDashboard";
    }

    @GetMapping("/deleteDiscussion/{id}")
    public String deleteDiscussion(@PathVariable int id) {
        // Dodajte logiku za brisanje diskusije
        discussionService.deleteDiscussion(id);
        return "redirect:/admin/adminDashboard";
    }

    @GetMapping("/deactivateUser/{userId}")
    public String deactivateUser(@PathVariable int userId) {
        userService.deactivateUser(userId);
        return "redirect:/admin/adminDashboard";
    }

    @GetMapping("/activateUser/{userId}")
    public String activateUser(@PathVariable int userId) {
        userService.activateUser(userId);
        return "redirect:/admin/adminDashboard";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute UserDto newUserDto, RedirectAttributes redirectAttributes) {
        try {
            // Validacija podataka ako je potrebno

            // Dodavanje novog korisnika
            userService.registerUser(newUserDto);

            // Postavljanje poruke o uspešnom dodavanju
            redirectAttributes.addFlashAttribute("message", "User added successfully");

        } catch (Exception e) {
            // Obrada grešaka
            System.err.println("An error occurred while adding a user: " + e.getMessage());
            redirectAttributes.addFlashAttribute("error", "An error occurred while adding a user");
        }

        // Redirekcija na adminDashboard
        return "redirect:/admin/adminDashboard";
    }


}




