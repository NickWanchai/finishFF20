package ff20.demo.contoller;

import ff20.demo.model.Staff;
import ff20.demo.repository.StaffRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class StaffController {

    private StaffRepository staffRepository;

    public StaffController(StaffRepository staffRepository){
        this.staffRepository = staffRepository;
    }

    @GetMapping("/staff")
    public String staff(Model model){
        model.addAttribute("staffs", staffRepository.findAll());
        return "staff";
    }

    @GetMapping("/addStaff")
    public String addStaff(Model model){
        model.addAttribute("staff", new Staff());
        return "addStaff";
    }

    @PostMapping("/addStaff")
    public String addStaff(HttpServletRequest request){
        String name = request.getParameter("name");
        String jobTitel = request.getParameter("jobTitel");
        String email = request.getParameter("email");

        Staff staff= new Staff(name, jobTitel, email);
        staffRepository.save(staff);

        return "redirect:/staff";
    }
/*
    @GetMapping("/deleteStaff")
    public String deleleStaff(Model model){
        model.addAttribute("staff", staffRepository.findAll());
        return "deletestaff";
    }

    @GetMapping("/verifyDelete")
    public String verifyDelete(@RequestParam("id") long id, Model model){
        model.addAttribute("staff", staffRepository.findById(id));
        return "verifyDelete";
    }

    @PostMapping("/verifyDelete")
    public String verifyDelete(HttpServletRequest request){
        String id = request.getParameter("id");
        StaffRepository.deleteById(Long.valueOf(id));
        return "redirect:/";
    }
 */




}