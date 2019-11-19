package com.example.userinterface.GameManager;

public class UserDAO implements DAO<User> {

    User user;

    public UserDAO(User user){
        this.user = user;
    }

    @Override
    public User get() {
        return this.user;
    }


    @Override
    public void delete() {
        this.user = null;
    }
}
