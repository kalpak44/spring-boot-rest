package pavel.usanli.repository;

import org.springframework.data.repository.CrudRepository;
import pavel.usanli.domain.User;

/**
 * Created by Pavel Usanli on 27.11.2016.
 */
public interface UserRepository extends CrudRepository<User, Long> {
}
