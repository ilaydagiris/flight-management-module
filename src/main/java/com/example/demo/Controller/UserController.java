package com.example.demo.Controller;

//import com.example.demo.Entity.FlightEntity;
import com.example.demo.Entity.UserSinifi;
import com.example.demo.Repository.UserRepository;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping (path = "user")

public class UserController {

    @Autowired
    private UserRepository repo;


    @GetMapping (path = "users") // get ile request gelecek demek
    public ResponseEntity<?> getUsers(@RequestParam(required = false) String userName, @RequestParam(required = false) String name){ //herhangi bir class koyabilirim, List<userSinifi> yazabilirim
        try{
            List<UserSinifi> ilaydaUserSinifiList = new ArrayList<>();
            if (userName == null)
                repo.findAll().forEach(ilaydaUserSinifiList::add);
            else
                repo.findByUserNameAndName(userName, name).forEach(ilaydaUserSinifiList::add);
             //   repo.findByUserName(userName).forEach((ilaydaUserSinifiList::add));
            //if (name != null)
              // repo.findByName(name).forEach((ilaydaUserSinifiList::add));
            if(ilaydaUserSinifiList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(ilaydaUserSinifiList, HttpStatus.OK); // 200, her şeyin olması gerektiği gibi olduğunu gösterir

        } catch (Exception hata){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping (path = "users/{id}")
    public ResponseEntity<UserSinifi> getUserSinifiById(@PathVariable("id")Long ID){
        Optional<UserSinifi> userVeri= repo.findById(ID); // null durumunda if else yazmaya gerek kalmıyor
        if (userVeri.isPresent()) {
            return new ResponseEntity<>(userVeri.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping (path = "users")
    public ResponseEntity<UserSinifi> createUser(@RequestBody UserSinifi user) {
        try {
            UserSinifi _user = repo.save(new UserSinifi(user.getID(), user.getUserName(), user.getName(), user.getSurName(),
                    LocalDateTime.now(), LocalDateTime.now(), user.getUpdateUser(), user.getPassword()));
            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        } catch (Exception hata) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping (path = "users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id")Long ID, @RequestBody UserSinifi user){
        Optional <UserSinifi> userVeri = repo.findById(ID);
        if (userVeri.isPresent()){  //!= null a denk geliyor
            UserSinifi _user = userVeri.get();
           // _user.setID(user.getID()); airline controller da yazdığım sebepten
            _user.setUserName(user.getUserName());
            _user.setName(user.getName());
            _user.setSurName(user.getSurName());
           // _user.setCreTime(LocalDateTime.now()); // normalde buradan yapılmıyormuş çünkü kötü niyetli biri gelip burdan değiştirebilirmiş, o yüzden server katmanından yapılıyormuş
            _user.setUpdateTime(LocalDateTime.now());
            _user.setUpdateUser(user.getUpdateUser());
            _user.setPassword(user.getPassword());
            return new ResponseEntity<>(repo.save(_user),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

   @DeleteMapping (path = "users/{id}")
        public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id")Long ID){
            try {
                repo.deleteById(ID);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }catch (Exception hata){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }


    @DeleteMapping(path = "users")
    public ResponseEntity<HttpStatus> deleteAllUsers() {
        try {
            repo.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception hata) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}


