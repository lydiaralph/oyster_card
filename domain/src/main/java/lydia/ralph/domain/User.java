package lydia.ralph.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @Column
    private String id;

    @Column
    private String user_name;

    @Column
    private BigDecimal balance;

    public void addToBalance(BigDecimal amount) {
        balance = balance.add(amount);
    }

    public void subFromBalance(BigDecimal amount) {
        balance = balance.subtract(amount);
    }

    @Column
    private BigDecimal dayTotal;

    public void addToDayTotal(BigDecimal amount) {
        dayTotal = dayTotal.add(amount);
    }
}
