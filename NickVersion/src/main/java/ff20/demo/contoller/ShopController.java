package ff20.demo.contoller;

import ff20.demo.model.Item;
import ff20.demo.model.Player;
import ff20.demo.repository.ItemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ShopController {

    private ItemRepository itemRepository;

    public ShopController(ItemRepository itemRepository){ this.itemRepository = itemRepository;}

    @GetMapping("/shop")
    public String shop(Model model){
        model.addAttribute("items", itemRepository.findAll());
        return "shop";
    }

    @GetMapping("/addItem")
    public String addPlayer(Model model){
        model.addAttribute("item", new Item());
        return "addItem";
    }

    @PostMapping("/addItem")
    public String addItem(HttpServletRequest request){
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));

        Item item= new Item(name, price);
        itemRepository.save(item);

        return "redirect:/shop";
    }

    @GetMapping("/addBasket")
    public String addBasket(){
        return "addBasket";
    }

}