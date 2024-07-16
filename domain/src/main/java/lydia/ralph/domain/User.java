package lydia.ralph.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Persistable;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User implements Persistable<String> {

    @Id
    @Column
    private String id;

    @Column
    private String user_name;

    @Column
    private BigDecimal balance;

    @Column
    private BigDecimal dayTotal;

    @Transient
    private boolean isNew = true;
}
