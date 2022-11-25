package com.example.demo.Entity; // bu sınıfın java dan sonraki yerini söylemesi lazım
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table (name = "User_Sinifi")
public class UserSinifi {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long ID;
    @Column (name = "UserName")
    private String userName;
    @Column (name = "Name")
    private String name;
    @Column (name = "SurName")
    private String surName;
    @Column (name = "CreTime")
    private LocalDateTime creTime;
    @Column (name = "UpdateTime")
    private LocalDateTime updateTime;
    @Column (name = "UpdateUser")
    private String updateUser;
    @Column (name = "Password")
    private String password;

    // todo bütün parametrelerle constructor yazılacak

   public UserSinifi(){ //default ta gelen şeyin kendisi

  }

//   public UserSinifi(String name, String surName){ //constructor
//       this.name = name;
//       this.surName = surName;
//   }

    public UserSinifi(long ID, String userName, String name, String surName, LocalDateTime creTime,
                      LocalDateTime updateTime, String updateUser, String password){ //constructor
        this.ID = ID;
        this.userName = userName;
        this.name = name;
        this.surName = surName;
        this.creTime = creTime;
        this.updateTime = updateTime;
        this.updateUser = updateUser;
        this.password = password;
    }

    public long getID(){ //getter olduğu için parametreye ihtiyaç yok, sadece var olanı getirecek
        return this.ID;
    }
    public void setID(long ID){ // tipini
        this.ID = ID; // kullanıcı ismimimi yenisine eşitlemem lazım, ismim olmayadabilir
    }
    public String getUserName(){
        return this.userName;
    }
    public void setUserName(String userName){ this.userName = userName; }  // void cevap dönmez demek
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurName() {
        return surName;
    }
    public void setSurName(String surName) {
        this.surName = surName;
    }
    public LocalDateTime getCreTime() {
        return creTime;
    }
    public void setCreTime(LocalDateTime creTime) {
        this.creTime = creTime;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
    public String getUpdateUser() {
        return updateUser;
    }
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
    public String getPassword() { return password; }
    public void setPassword(String password) {
        this.password = password;
    }

}


