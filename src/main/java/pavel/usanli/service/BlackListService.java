package pavel.usanli.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pavel.usanli.repository.BlackListRepository;
import pavel.usanli.repository.UserRepository;
import pavel.usanli.service.interfaces.BlackListServiceInterface;

/**
 * Created by Pavel Usanli on 27.11.2016.
 */

@Service
public class BlackListService implements BlackListServiceInterface {
    @Autowired
    private BlackListRepository blackListRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean hasInBlackList(long userId) {
        return blackListRepository.findByUser(userRepository.findOne(userId)) != null;
    }
}
