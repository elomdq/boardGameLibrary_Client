package com.rodriguez.boardGameslibrary.client.repositories;

import com.rodriguez.boardGameslibrary.client.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query
    User findByUsername(String username);

}
