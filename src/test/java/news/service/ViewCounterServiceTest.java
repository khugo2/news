package news.service;

import news.domain.Article;
import news.domain.ViewCounter;
import news.repository.ArticleRepository;
import news.repository.ViewCounterRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ViewCounterServiceTest {
    @Autowired
    private ViewCounterService viewCounterService;

    @Autowired
    private ViewCounterRepository viewCounterRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Before
    public void setUp() {
        articleRepository.deleteAll();
    }

    @Test
    public void registerViewCreatesNewCounterIfDoesNotExist() {
        Article article = createArticle();
        assertEquals(viewCounterRepository.findAll().size(), 0);

        viewCounterService.registerView(article);

        List<ViewCounter> counters = viewCounterRepository.findAll();
        assertEquals(counters.size(), 1);
        assertEquals(counters.get(0).getDate(), LocalDate.now());
        assertEquals(counters.get(0).getViews(), 1);
    }

    @Test
    public void registerViewIncrementsExistingCounter() {
        Article article = createArticle();
        ViewCounter counter = createViewCounter(article, LocalDate.now(), 3);

        viewCounterService.registerView(article);

        assertEquals(viewCounterRepository.findById(counter.getId()).get().getViews(), 4);
    }

    @Test
    public void registerViewDoesNotIncrementUnrelatedCounters() {
        Article article = createArticle();
        ViewCounter counter = createViewCounter(article, LocalDate.now(), 3);
        ViewCounter counter2 = createViewCounter(article, LocalDate.now().minusDays(1), 0);

        viewCounterService.registerView(article);

        assertEquals(viewCounterRepository.findById(counter.getId()).get().getViews(), 4);
        assertEquals(viewCounterRepository.findById(counter2.getId()).get().getViews(), 0);
    }

    private Article createArticle() {
        Article article = new Article();
        articleRepository.save(article);
        return article;
    }

    private ViewCounter createViewCounter(Article article, LocalDate date, int views) {
        ViewCounter viewCounter = new ViewCounter(article, date);
        viewCounter.setViews(views);
        viewCounterRepository.save(viewCounter);
        return viewCounter;
    }
}
