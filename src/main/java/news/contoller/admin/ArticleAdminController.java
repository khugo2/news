package news.contoller.admin;

import news.domain.Article;
import news.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ArticleAdminController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("articles", articleRepository.findAll());
        return "admin/articles/index";
    }

    @GetMapping("/admin/articles/new")
    public String newArticlePage() {
        return "admin/articles/create";
    }

    @PostMapping("/admin/articles")
    public String createArticle(@RequestParam String title) {
        Article article = new Article(title);
        articleRepository.save(article);
        return "redirect:/admin/articles";
    }
}
