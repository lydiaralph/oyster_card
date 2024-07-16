package lydia.ralph.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class Station {
    private String name;
    private Integer minZone;
    private Integer maxZone;
}
