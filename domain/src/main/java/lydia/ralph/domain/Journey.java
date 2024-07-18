package lydia.ralph.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "journeys")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Journey {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private String userId;

    @Column
    private String startedAtStation;

    @Column
    private String endedAtStation;

    @Column
    private LocalDate journeyDate;
}
