package news.contoller.admin;

import news.domain.Category;
import news.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CategoryAdminController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/admin/categories")
    public String index(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "admin/category/index";
    }

    @GetMapping("/admin/categories/new")
    public String createPage() {
        return "admin/category/create";
    }

    @PostMapping("/admin/categories")
    public String create(@RequestParam String name) {
        Category author = new Category(name);
        categoryRepository.save(author);
        return "redirect:/admin/categories";
    }
}

