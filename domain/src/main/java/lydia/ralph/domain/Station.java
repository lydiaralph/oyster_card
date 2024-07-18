package lydia.ralph.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.*;
import org.springframework.data.domain.Persistable;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "stations")
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
