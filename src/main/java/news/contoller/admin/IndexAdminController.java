package news.contoller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexAdminController {
    @GetMapping("/admin")
    public String index() {
        return "redirect:/admin/articles";
    }
}
