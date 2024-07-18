package lydia.ralph.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.domain.Persistable;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "stations")
@Entity
public class Station { // implements Persistable<String> {

    @Id
    @Column
    private String name;

    @Column
    private Integer mainZone;

    @Column
    private Integer extraZone;

//    @Transient
//    private boolean isNew = true;
//
//    @Override
//    public String getId() {
//        return getName();
//    }
}
