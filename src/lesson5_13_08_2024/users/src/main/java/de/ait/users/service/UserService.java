package de.ait.users.service;

import de.ait.users.entity.User;
import de.ait.users.repository.UserRepositoryInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService implements UserServiceInt{
    private final UserRepositoryInt repository;
    @Autowired
    public UserService(UserRepositoryInt repository) {
        this.repository = repository;
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User createNewUser(User user) {
        if (user.getId() !=null){
            user.setId(null);
        }
        return repository.save(user);
    }

    @Override
    public User findById(Long id) {
        return findAll().stream()
                .filter(u-> u.getId().equals(id))
                .findAny()
                .orElse(null);
    }

    @Override
    public List<User> findByName(String name) {
        return findAll()
                .stream()
                .filter(u-> u.getId().equals(name))
                .toList();
    }
}