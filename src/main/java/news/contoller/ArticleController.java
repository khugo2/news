package news.contoller;

import news.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepo;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("articles", articleRepo.findAll());
        return "index.html";
    }
}
