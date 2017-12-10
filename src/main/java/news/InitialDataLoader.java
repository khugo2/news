package news;

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
import java.util.Objects;

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
        categoryRepository.save(new Category("Category 3"));
        categoryRepository.save(new Category("Category 4"));
        categoryRepository.save(new Category("Category 5"));
        articleRepository.save(new Article("Suomi haluaa tehostaa ulkovaltojen vakoilulennokkien torjuntaa – sotalaivoillekin havitellaan oikeutta ampua lennokit alas kansainvälisillä vesillä",
                "Puolustusvoimat haluaa säädökset lennokkien pakottamiseen alas.",
                "Puolustusvoimat haluaa säädökset lennokkien pakottamiseen alas.",
                categoryRepository.findAll().subList(0, 1), authorRepository.findAll(),
                getImageFixture("image_1.webp"),
                "image/webp"));
        articleRepository.save(new Article("Suomi haluaa tehostaa ulkovaltojen vakoilulennokkien torjuntaa – sotalaivoillekin havitellaan oikeutta ampua lennokit alas kansainvälisillä vesillä",
                "Puolustusvoimat haluaa säädökset lennokkien pakottamiseen alas.",
                "Puolustusvoimat haluaa säädökset lennokkien pakottamiseen alas.",
                categoryRepository.findAll().subList(1, 2), authorRepository.findAll(),
                getImageFixture("image_1.webp"),
                "image/webp"));
        articleRepository.save(new Article("Suomi haluaa tehostaa ulkovaltojen vakoilulennokkien torjuntaa – sotalaivoillekin havitellaan oikeutta ampua lennokit alas kansainvälisillä vesillä",
                "Puolustusvoimat haluaa säädökset lennokkien pakottamiseen alas.",
                "Puolustusvoimat haluaa säädökset lennokkien pakottamiseen alas.",
                categoryRepository.findAll().subList(2, 3), authorRepository.findAll(),
                getImageFixture("image_1.webp"),
                "image/webp"));
    }

    private byte[] getImageFixture(String name) throws URISyntaxException, IOException {
        Path path = Paths.get(getClass().getClassLoader().getResource("fixtures/images/" + name).toURI());
        return Files.readAllBytes(path);
    }
}
