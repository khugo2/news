package news.domain;

import lombok.Data;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;

@Entity
@Data
public class Article extends AbstractPersistable<Long> {
    private String title;
}
