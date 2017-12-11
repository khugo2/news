package news.repository;

import news.domain.Article;
import news.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByCategoriesContaining(Category category);
    @Query(value = "SELECT *, SUM(view_counter.views) as totalViews FROM article " +
                    "LEFT JOIN view_counter ON view_counter.article_id = article.id " +
                    "WHERE view_counter.date IS NULL OR view_counter.date >= :startDate " +
                    "GROUP BY article.id " +
                    "ORDER BY totalViews DESC LIMIT 10 OFFSET 0", nativeQuery = true)
    List<Article> findByPopularity(@Param("startDate") LocalDate startDate);
}
