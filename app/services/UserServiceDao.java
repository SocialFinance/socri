package services;

import models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserServiceDao extends CrudRepository<User, Integer> {

    List<User> findByUsername(String username);

}

