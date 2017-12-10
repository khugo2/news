package news.contoller;

import news.domain.Article;
import news.domain.Category;
import news.repository.ArticleRepository;
import news.repository.CategoryRepository;
import news.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepo;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ArticleService articleService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("articles", articleRepo.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return "index";
    }

    @GetMapping("/articles/{id}")
    public String articlePage(Model model, @PathVariable Long id) {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("article", articleRepo.findById(id).get());
        model.addAttribute("latestArticles", articleService.findLatestArticles());
        model.addAttribute("popularArticles", articleService.findPopularArticles());
        return "article";
    }

    @GetMapping("/articles/{id}/image")
    public void articleImage(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Article article = articleRepo.findById(id).get();
        response.setContentType(article.getImageMimeType());
        response.getOutputStream().write(article.getImage());
    }

    @GetMapping("/categories/{id}")
    public String categoryPage(Model model, @PathVariable Long id) {
        Category category = new Category(id);
        model.addAttribute("articles", articleRepo.findByCategoriesContaining(category));
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("selectedCategory", category);
        return "index";
    }
}
