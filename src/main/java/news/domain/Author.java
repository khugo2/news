package news.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author extends AbstractPersistable<Long> {
    private String firstName;
    private String lastName;

    public String fullName() {
        return firstName + " " + lastName;
    }
}
