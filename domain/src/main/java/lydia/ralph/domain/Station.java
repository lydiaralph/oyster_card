package lydia.ralph.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "stations")
@Entity
public class Station {

    @Id
    @Column
    private String name;

    @Column
    private Integer mainZone;

    @Column
    private Integer extraZone;
}
