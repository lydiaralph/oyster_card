package lydia.ralph.repositories;

import lydia.ralph.domain.Journey;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JourneyRepository extends CrudRepository<Journey, Integer> {

    List<Journey> findByUserId(String userId);
}
