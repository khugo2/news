package news;

import news.domain.Author;
import news.domain.Category;
import news.repository.ArticleRepository;
import news.repository.AuthorRepository;
import news.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class InitialDataLoader implements ApplicationRunner {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        authorRepository.save(new Author("Author", "1"));
        authorRepository.save(new Author("Author", "2"));
        categoryRepository.save(new Category("Category 1"));
        categoryRepository.save(new Category("Category 2"));
    }
}
