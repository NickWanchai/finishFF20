package ff20.demo.contoller;

import ff20.demo.model.Player;
import ff20.demo.repository.PlayerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PlayerController {

    private PlayerRepository playerRepository;


    public PlayerController(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    @GetMapping("/players")
    public String players(Model model){
        model.addAttribute("players", playerRepository.findAll());
        return "players";
    }

    @GetMapping("/addPlayer")
    public String addPlayer(Model model){
        model.addAttribute("player", new Player());
        return "addPlayer";
    }

    @PostMapping("/addPlayer")
    public String addPlayer(HttpServletRequest request){
        int shirtNumber = Integer.parseInt(request.getParameter("shirtNumber"));
        int age = Integer.parseInt(request.getParameter("age"));
        String name = request.getParameter("name");
        String position = request.getParameter("position");
        String nationality = request.getParameter("nationality");

        Player player= new Player(shirtNumber, age, name, position, nationality);
        playerRepository.save(player);

        return "redirect:/players";
    }

    @GetMapping("/deletePlayer")
    public String deletePlayer(Model model){
        model.addAttribute("players", playerRepository.findAll());
        return "deletePlayer";
    }

    @GetMapping("/verifyDeletePlayer")
    public String verifyDelete(@RequestParam("id") long id, Model model){
        model.addAttribute("player", playerRepository.findById(id));
        return "verifyDeletePlayer";
    }

    @PostMapping("/verifyDeletePlayer")
    public String verifyDelete(HttpServletRequest request){
        String id = request.getParameter("id");
        playerRepository.deleteById(Long.valueOf(id));
        return "redirect:/";
    }




}
