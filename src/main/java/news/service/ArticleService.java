package news.service;

import news.domain.Article;
import news.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> findLatestArticles() {
        Pageable lastTen = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "created"));
        return articleRepository.findAll(lastTen).getContent();
    }

    public List<Article> findPopularArticles() {
        Pageable lastTen = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "created"));
        return articleRepository.findByPopularity(LocalDate.now().minusDays(7));
    }
}
