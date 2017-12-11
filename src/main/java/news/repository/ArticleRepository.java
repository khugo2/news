package news.repository;

import news.domain.Article;
import news.domain.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByCategoriesContaining(Category category, Pageable pageable);

    @Query(value = "SELECT article.*, SUM(view_counter.views) as totalViews FROM article " +
                    "LEFT JOIN view_counter ON view_counter.article_id = article.id " +
                    "WHERE view_counter.date IS NULL OR view_counter.date >= :startDate " +
                    "GROUP BY article.id " +
                    "ORDER BY totalViews DESC LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Article> findByPopularity(@Param("startDate") LocalDate startDate,
                                   @Param("offset") int offset,
                                   @Param("limit") int limit);
}
