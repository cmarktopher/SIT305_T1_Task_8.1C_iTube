package com.application.itube.DataAccessObjects;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.application.itube.DataModels.User;

@Dao
public interface UsersDAO {

    @Query("SELECT * FROM users WHERE user_name = :userName")
    User getUserByUserName(String userName);

    @Insert
    void insertNewUser(User newUser);
}

