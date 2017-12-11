package news.contoller;

import news.domain.Article;
import news.domain.Category;
import news.repository.ArticleRepository;
import news.repository.CategoryRepository;
import news.service.ArticleService;
import news.service.ViewCounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Controller
public class ArticleController {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ViewCounterService viewCounterService;

    @GetMapping("/")
    public String index() {
        return "redirect:/categories/latest";
    }

    @GetMapping("/articles/{id}")
    public String articlePage(Model model, @PathVariable Long id) {
        Article article = articleService.findById(id).get();
        viewCounterService.registerView(article);

        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("article", article);
        model.addAttribute("latestArticles", articleService.findLatestArticles(0));
        model.addAttribute("popularArticles", articleService.findPopularArticles(0));
        return "article";
    }

    @GetMapping("/articles/{id}/image")
    public void articleImage(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Article article = articleService.findById(id).get();
        response.setContentType(article.getImageMimeType());
        response.getOutputStream().write(article.getImage());
    }

    @GetMapping("/categories/latest")
    public String latestPage(Model model, @RequestParam Optional<Integer> page) {
        int newPage = page.orElse(0);
        model.addAttribute("page", newPage);
        model.addAttribute("articles", articleService.findLatestArticles(newPage));
        model.addAttribute("categories", categoryRepository.findAll());
        return "index";
    }

    @GetMapping("/categories/popular")
    public String popularPage(Model model, @RequestParam Optional<Integer> page) {
        int newPage = page.orElse(0);
        model.addAttribute("page", newPage);
        model.addAttribute("articles", articleService.findPopularArticles(0));
        model.addAttribute("categories", categoryRepository.findAll());
        return "index";
    }

    @GetMapping("/categories/{id}")
    public String categoryPage(Model model, @PathVariable Long id, @RequestParam Optional<Integer> page) {
        int newPage = page.orElse(0);
        Category category = new Category(id);
        model.addAttribute("page", newPage);
        model.addAttribute("articles", articleService.findByCategory(category, newPage));
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("selectedCategory", category);
        return "index";
    }
}
