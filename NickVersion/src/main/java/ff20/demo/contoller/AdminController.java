package ff20.demo.contoller;

import ff20.demo.model.Admin;
import ff20.demo.repository.AdminDbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {

    @Autowired
    private AdminDbRepository adminDbRepository;

    @GetMapping("/login")
    public String index(HttpSession session) {

        if (session.getAttribute("isLoggedIn") != null) {
            return "adminSide";
        }

        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Admin admin, Model model, HttpSession session) {

        Admin a = adminDbRepository.read(admin.getEmail());
        if (a != null && a.getPassword().equals(admin.getPassword())) {
            //model.addAttribute("admins", adminDbRepository.findAll());
            session.setAttribute("isLoggedIn", "yes");
            return "adminSide";
        }

        return "index";
    }

    @GetMapping("/logud")
    public String logout(HttpSession session) {
        session.removeAttribute("isLoggedIn");
        if (session.getAttribute("isLoggedIn") != null) {
            return "adminSide";
        }
        return "index";
    }

    @GetMapping("/adminPlayer")
    public String adminPlayer(){
        return "adminPlayers";
    }

    @GetMapping("/adminShop")
    public String adminShop(){
        return "adminShop";
    }

    @GetMapping("/adminStaff")
    public String adminStaff(){
        return "adminStaff";
    }





}
