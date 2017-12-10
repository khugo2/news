package news.service;

import news.domain.Article;
import news.repository.ArticleRepository;
import news.repository.AuthorRepository;
import news.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public List<Article> findLatestArticles() {
        Pageable lastTen = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "created"));
        return articleRepository.findAll(lastTen).getContent();
    }

    public List<Article> findPopularArticles() {
        Pageable lastTen = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "created"));
        return articleRepository.findAll(lastTen).getContent();
    }
}
