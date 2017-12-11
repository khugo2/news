package news.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

/**
 * This class is used to track an article's views for one day.
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class ViewCounter extends AbstractPersistable<Long> {
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Article article;
    private LocalDate date;
    private int views;

    public ViewCounter(Article article, LocalDate date) {
        this.article = article;
        this.date = date;
    }

    public void increment() {
        views++;
    }
}
