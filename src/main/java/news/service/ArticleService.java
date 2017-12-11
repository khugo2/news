package news.service;

import news.domain.Article;
import news.domain.Category;
import news.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    private static final int ARTICLES_PER_PAGE = 5;

    @Transactional
    public List<Article> findLatestArticles(int page) {
        Pageable pageable = PageRequest.of(page, ARTICLES_PER_PAGE, Sort.by(Sort.Direction.DESC, "created"));
        return articleRepository.findAll(pageable).getContent();
    }

    @Transactional
    public List<Article> findPopularArticles(int page) {
        return articleRepository.findByPopularity(LocalDate.now().minusDays(7), page * ARTICLES_PER_PAGE, ARTICLES_PER_PAGE);
    }

    @Transactional
    public List<Article> findByCategory(Category category, int page) {
        Pageable pageable = PageRequest.of(page, ARTICLES_PER_PAGE, Sort.by(Sort.Direction.DESC, "created"));
        return articleRepository.findByCategoriesContaining(category, pageable);
    }

    @Transactional
    public Optional<Article> findById(Long id) {
        return articleRepository.findById(id);
    }
}
