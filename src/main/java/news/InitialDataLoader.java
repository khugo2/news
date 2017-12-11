package news;

import com.google.common.io.ByteStreams;
import news.domain.Article;
import news.domain.Author;
import news.domain.Category;
import news.repository.ArticleRepository;
import news.repository.AuthorRepository;
import news.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
        categoryRepository.save(new Category("Category 1", true));
        categoryRepository.save(new Category("Category 2", true));
        categoryRepository.save(new Category("Category 3", true));
        categoryRepository.save(new Category("Category 4", true));
        categoryRepository.save(new Category("Category 5", true));
        for (int i = 0; i < 20; i++) {
            addArticle();
        }
    }

    private byte[] getImageFixture(String name) throws IOException {
        return ByteStreams.toByteArray(getClass().getClassLoader().getResourceAsStream("fixtures/images/" + name));
    }

    private void addArticle() throws IOException {
        articleRepository.save(new Article("Suomi haluaa tehostaa ulkovaltojen vakoilulennokkien torjuntaa – sotalaivoillekin havitellaan oikeutta ampua lennokit alas kansainvälisillä vesillä",
            "Puolustusvoimat haluaa säädökset lennokkien pakottamiseen alas.",
            "Puolustusvoimat haluaa säädökset lennokkien pakottamiseen alas.",
            categoryRepository.findAll().subList(2, 3), authorRepository.findAll(),
            getImageFixture("image_1.webp"),
            "image/webp"));
    }
}
