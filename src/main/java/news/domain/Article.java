package news.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class Article extends AbstractPersistable<Long> {
    private String title;
    private String text;
    private String lead;
    private LocalDateTime created;

    public Article(String title, String text, String lead, List<Category> categories, List<Author> authors) {
        this.title = title;
        this.text = text;
        this.lead = lead;
        this.categories = categories;
        this.authors = authors;
        this.created = LocalDateTime.now();
    }

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Category> categories;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Author> authors;

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", lead='" + lead + '\'' +
                ", created=" + created +
                ", categories=" + categories +
                ", authors=" + authors +
                '}';
    }
}
