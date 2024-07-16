package lydia.ralph;

import lydia.ralph.domain.Journey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JourneyRepository extends JpaRepository<Journey, Integer> {

    List<Journey> findAllBy(Integer userId);

    void upsertJourney(Journey journey);
}
