package com.users.controllers;

import com.mongodb.MongoClient;
import com.mongodb.client.result.DeleteResult;
import com.users.database.MongoApp;
import com.users.model.User;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@RestController
public class UsersController {

    private static final String username = System.getenv("MONGODB_CLUSTER_USERNAME");
    private static final String password = System.getenv("MONGODB_CLUSTER_PASSWORD");
    private MongoClient client;
    private AtomicLong next_id;

    public UsersController() {
        this.client = MongoApp.getMongoClient(username, password);
        this.next_id = new AtomicLong();
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        List<User> r = null;
        MongoOperations ops = new MongoTemplate(this.client, "users");
        r = ops.findAll(User.class, "users");
        return r;
    }

    // !!!: retorna o objeto retornado pelo Mongo
    @PostMapping("/users")
    public User addUser(@RequestBody User u) {
        User r;
        MongoOperations ops = new MongoTemplate(this.client, "users");
        u.setId(next_id.incrementAndGet());
        r = ops.insert(u);
        return r;
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable("id") long user_id) {
        User r;
        MongoOperations ops = new MongoTemplate(this.client, "users");
        r = ops.findOne(new Query(where("_id").is(user_id)), User.class);
        if(r == null) throw new IllegalArgumentException();
        return r;
    }

    @DeleteMapping("/users/{id}")
    public DeleteResult removeUser(@PathVariable("id") long user_id) {
        MongoOperations ops = new MongoTemplate(this.client, "users");
        return ops.remove(new Query(where("_id").is(user_id)), User.class);
    }

    @PostMapping("/users/{id}")
    public User updateUser(@PathVariable("id") long user_id, @RequestBody User d) {
        MongoOperations ops = new MongoTemplate(this.client, "users");
        Query query = new Query(where("_id").is(user_id));
        Update update = new Update();
        update.set("username", d.getUsername());
        update.set("email", d.getEmail());
        update.set("phone_number", d.getPhone_number());
        update.set("premium", d.isPremium());
        ops.updateFirst(query, update, User.class);
        return ops.findOne(query, User.class);
    }

    // Exception Handlers
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "User ID not found")
    @ExceptionHandler(IllegalArgumentException.class)
    public void badIdExceptionHandler() {
        // não tem de fazer nada, I guess
    }

}
