package com.example.nikola.repository;

import com.example.nikola.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByName(String name);

    @Override
    boolean existsById(Long aLong);

    List<User> findAll();

}
