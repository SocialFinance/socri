package services;

import models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserServiceDao extends CrudRepository<User, Integer> {

    User findFirstByUsername(String username);

}
