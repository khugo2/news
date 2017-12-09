package news.contoller.admin;

import news.domain.Author;
import news.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorAdminController {
    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/admin/authors")
    public String index(Model model) {
        model.addAttribute("authors", authorRepository.findAll());
        return "admin/author/index";
    }

    @GetMapping("/admin/authors/new")
    public String createAuthorPage() {
        return "admin/author/create";
    }

    @PostMapping("/admin/authors")
    public String createAuthor(@RequestParam String firstName, @RequestParam String lastName) {
        Author author = new Author(firstName, lastName);
        authorRepository.save(author);
        return "redirect:/admin/authors";
    }
}
