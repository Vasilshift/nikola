package com.example.nikola.repository;

import com.example.nikola.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostgresRepository extends JpaRepository<User, Long> {

    Object findByName(String name);

}
