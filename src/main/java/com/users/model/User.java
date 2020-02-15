package com.users.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {

    @Id
    private long id;
    private String username;
    private String email;
    private String phone_number;
    private boolean premium;

    public User() {

    }

    public User(final long id, final String username, final String email, final String phone_number, final boolean premium) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.phone_number = phone_number;
        this.premium = premium;
    }

    public long getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

   public String getEmail() {
        return this.email;
   }

   public String getPhone_number() {
        return this.phone_number;
   }

   public boolean isPremium() {
        return this.premium;
   }

   public void setId(final long id) {
        this.id = id;
   }

   public void setUsername(final String username) {
        this.username = username;
   }

   public void setEmail(final String email) {
        this.email = email;
   }

   public void setPhone_number(final String phone_number) {
        this.phone_number = phone_number;
   }

   public void setPremium(final boolean premium) {
        this.premium = premium;
   }
}
