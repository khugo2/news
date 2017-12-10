package news.repository;

import news.domain.Article;
import news.domain.ViewCounter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface ViewCounterRepository extends JpaRepository<ViewCounter, Long> {
    Optional<ViewCounter> findByArticleAndDate(Article article, LocalDate date);
}
