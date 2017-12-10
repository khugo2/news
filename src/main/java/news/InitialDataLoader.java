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
        categoryRepository.save(new Category("Category 6"));
        categoryRepository.save(new Category("Category 7"));
        articleRepository.save(new Article("Suomi haluaa tehostaa ulkovaltojen vakoilulennokkien torjuntaa – sotalaivoillekin havitellaan oikeutta ampua lennokit alas kansainvälisillä vesillä",
                "Puolustusvoimat haluaa säädökset lennokkien pakottamiseen alas.",
                "Puolustusvoimat haluaa säädökset lennokkien pakottamiseen alas.",
                categoryRepository.findAll().subList(0, 1), authorRepository.findAll(),
                new byte[0],
                ""));
        articleRepository.save(new Article("Suomi haluaa tehostaa ulkovaltojen vakoilulennokkien torjuntaa – sotalaivoillekin havitellaan oikeutta ampua lennokit alas kansainvälisillä vesillä",
                "Puolustusvoimat haluaa säädökset lennokkien pakottamiseen alas.",
                "Puolustusvoimat haluaa säädökset lennokkien pakottamiseen alas.",
                categoryRepository.findAll().subList(1, 2), authorRepository.findAll(),
                new byte[0],
                ""));
        articleRepository.save(new Article("Suomi haluaa tehostaa ulkovaltojen vakoilulennokkien torjuntaa – sotalaivoillekin havitellaan oikeutta ampua lennokit alas kansainvälisillä vesillä",
                "Puolustusvoimat haluaa säädökset lennokkien pakottamiseen alas.",
                "Puolustusvoimat haluaa säädökset lennokkien pakottamiseen alas.",
                categoryRepository.findAll().subList(2, 3), authorRepository.findAll(),
                new byte[0],
                ""));
    }
}
