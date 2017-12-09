package news.contoller.admin;

import news.domain.Article;
import news.domain.Author;
import news.domain.Category;
import news.repository.ArticleRepository;
import news.repository.AuthorRepository;
import news.repository.CategoryRepository;
import news.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ArticleAdminController {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ArticleService articleService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("articles", articleRepository.findAll());
        return "admin/article/index";
    }

    @GetMapping("/admin/articles/new")
    public String newArticlePage(Model model) {
        model.addAttribute("authors", authorRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return "admin/article/create";
    }

    @PostMapping("/admin/articles")
    public String createArticle(@RequestParam String title,
                                @RequestParam String lead,
                                @RequestParam String body,
                                @RequestParam(name = "authors") List<Long> authorIds,
                                @RequestParam(name = "categories") List<Long> categoryIds) {
        Article article = new Article(
                title,
                lead,
                body,
                categoryIds.stream().map(Category::new).collect(Collectors.toList()),
                authorIds.stream().map(Author::new).collect(Collectors.toList())
        );
        articleRepository.save(article);

        return "redirect:/admin/articles";
    }
}
