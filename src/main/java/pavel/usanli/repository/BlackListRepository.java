package pavel.usanli.repository;

import org.springframework.data.repository.CrudRepository;
import pavel.usanli.domain.Ban;
import pavel.usanli.domain.User;

/**
 * Created by Pavel Usanli on 27.11.2016.
 */
public interface BlackListRepository extends CrudRepository<Ban, Long> {
    Ban findByUser(final User user);
}
