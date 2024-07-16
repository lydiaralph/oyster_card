package lydia.ralph.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Journey {
    private Integer id;
    private Integer user_id;
    private Integer startedAtStation;
    private Integer endedAtStation;
    private boolean completed;
    private Date startedAt;

}
