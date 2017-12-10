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
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ArticleServiceTest {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ViewCounterRepository viewCounterRepository;

    @Autowired
    private ArticleService articleService;

    @Before
    public void setUp() {
        articleRepository.deleteAll();
        viewCounterRepository.deleteAll();
    }

    @Test
    public void returnsLatestArticles() {
        Article article1 = new Article();
        article1.setCreated(LocalDateTime.now().minusDays(1));
        articleRepository.save(article1);

        Article article2 = new Article();
        article2.setCreated(LocalDateTime.now());
        articleRepository.save(article2);

        List<Article> result = articleService.findLatestArticles();
        assertEquals(result.size(), 2);
        assertEquals(result.get(0).getId(), article2.getId());
        assertEquals(result.get(1).getId(), article1.getId());
    }

    @Test
    public void returnsPopularArticles() {
        Article article1 = createArticle();
        createViewCounter(article1, LocalDate.now(), 2);

        Article article2 = createArticle();
        createViewCounter(article2, LocalDate.now(), 1);

        Article article3 = createArticle();
        createViewCounter(article3, LocalDate.now(), 3);

        List<Article> result = articleService.findPopularArticles();
        assertEquals(result.size(), 3);
        assertEquals(result.get(0).getId(), article3.getId());
        assertEquals(result.get(1).getId(), article1.getId());
        assertEquals(result.get(2).getId(), article2.getId());
    }

    @Test
    public void returnsPopularArticlesEvenIfNoViews() {
        Article article1 = createArticle();
        Article article2 = createArticle();
        List<Article> result = articleService.findPopularArticles();
        assertEquals(result.size(), 2);
        assertEquals(result.get(0).getId(), article2.getId());
        assertEquals(result.get(1).getId(), article1.getId());
    }

    @Test
    public void popularArticlesDoesNotCountOldViews() {
        Article article1 = createArticle();
        Article article2 = createArticle();

        createViewCounter(article1, LocalDate.now().minusDays(100), 1000);
        createViewCounter(article1, LocalDate.now(), 2);
        createViewCounter(article2, LocalDate.now(), 10);

        List<Article> result = articleService.findPopularArticles();
        assertEquals(result.size(), 2);
        assertEquals(result.get(0).getId(), article2.getId());
        assertEquals(result.get(1).getId(), article1.getId());
    }

    private Article createArticle() {
        Article article = new Article();
        articleRepository.save(article);
        return article;
    }

    private void createViewCounter(Article article, LocalDate date, int views) {
        ViewCounter viewCounter = new ViewCounter(article, date);
        viewCounter.setViews(views);
        viewCounterRepository.save(viewCounter);
    }
}
