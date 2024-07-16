package lydia.ralph;

import lydia.ralph.domain.Journey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JourneyRepositoryImpl {

    public List<Journey> findAllBy(Integer userId) {
        System.out.println("TODO: Implement journeys by user ID");
        return List.of();
    }

    public void upsertJourney(Journey journey) {
        System.out.println("TODO: Implement upsert journey");
    }

}
