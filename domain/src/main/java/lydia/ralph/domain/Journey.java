package lydia.ralph.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.domain.Persistable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "journeys")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Journey { //implements Persistable<Integer> {

    @Id
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

//    @Transient
//    private boolean isNew = true;
}
