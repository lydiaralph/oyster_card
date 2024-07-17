package lydia.ralph.repositories;

import lydia.ralph.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {

}
