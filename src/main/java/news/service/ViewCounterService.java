package news.service;

import news.domain.Article;
import news.domain.ViewCounter;
import news.repository.ViewCounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
public class ViewCounterService {
    @Autowired
    private ViewCounterRepository viewCounterRepository;

    @Transactional
    public void registerView(Article article) {
        LocalDate today = LocalDate.now();
        ViewCounter viewCounter = viewCounterRepository.findByArticleAndDate(article, today)
                .orElse(new ViewCounter(article, today));
        viewCounter.increment();
        viewCounterRepository.save(viewCounter);
    }
}
