package news.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class Article extends AbstractPersistable<Long> {
    private String title;
    @Lob
    private String body;
    private String lead;
    @Lob
    private byte[] image;
    private String imageMimeType;
    private LocalDateTime created;

    public Article(String title, String body, String lead, List<Category> categories, List<Author> authors, byte[] image, String imageMimeType) {
        this.title = title;
        this.body = body;
        this.lead = lead;
        this.categories = categories;
        this.authors = authors;
        this.created = LocalDateTime.now();
        this.image = image;
        this.imageMimeType = imageMimeType;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Category> categories;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Author> authors;

    public String formattedAuthors() {
        return authors.stream().map(Author::fullName).collect(Collectors.joining(", "));
    }

    public String formattedCreateDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm dd.MM.yyyy");
        return created.format(formatter);
    }

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", lead='" + lead + '\'' +
                ", image=byte[" + image.length + "]" +
                ", imageMimeType='" + imageMimeType + '\'' +
                ", created=" + created +
                ", categories=" + categories +
                ", authors=" + authors +
                '}';
    }
}
