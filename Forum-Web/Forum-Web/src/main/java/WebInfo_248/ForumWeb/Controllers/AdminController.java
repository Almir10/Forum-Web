package WebInfo_248.ForumWeb.Controllers;

import WebInfo_248.ForumWeb.Models.Discussion;
import WebInfo_248.ForumWeb.Service.DiscussionService;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private DiscussionService discussionService;

    @GetMapping("/dashboard")
    public String adminDashboard(Model model, HttpSession session) {
        // Provera da li je korisnik administrator
        String currentUserRole = (String) session.getAttribute("userRole");
        if (currentUserRole == null || !currentUserRole.equals("admin")) {
            // Ako korisnik nije administrator, možete redirektovati ili prikazati poruku o grešci
            return "redirect:/";
        }

        // Dohvat diskusija ili druge logike specifične za admin panel
        List<Discussion> discussions = discussionService.getAllDiscussions();
        model.addAttribute("discussions", discussions);

        return "admin/dashboard";
    }


    @PostMapping("/addDiscussion")
    public String addDiscussion(@ModelAttribute Discussion discussion) {
        // Dodajte validaciju i logiku po potrebi
        discussionService.addDiscussion(discussion);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/deleteDiscussion/{id}")
    public String deleteDiscussion(@PathVariable int id) {
        // Dodajte logiku provere prava pristupa ako je potrebno
        discussionService.deleteDiscussion(id);
        return "redirect:/admin/dashboard";
    }
}