package com.example.demo.Repository;

import com.example.demo.Entity.UserSinifi;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<UserSinifi,Long> {

    List<UserSinifi> findByUserName (String userName);
    List<UserSinifi> findByName (String name);
    List<UserSinifi> findByUserNameAndName (String userName, String name);
}



