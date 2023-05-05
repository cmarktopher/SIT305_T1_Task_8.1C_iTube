package com.application.itube.Repositories;

import android.app.Application;
import android.util.Log;

import com.application.itube.DataAccessObjects.UsersDAO;
import com.application.itube.DataModels.User;
import com.application.itube.Database.ITubeDatabase;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Repository to handle interaction with the user table in the database via the user data access object.
 */
public class UserRepository {

    // Cache the application ref
    private Application currentApplication;

    // User data access object
    private UsersDAO usersDAO;

    // Constructor to initialize our repository
    public UserRepository(Application application){

        currentApplication = application;
        usersDAO = ITubeDatabase.getDatabase(application).usersDao();
    }

    // Get a user by user name
    public User getUserByUserName(String userName){

        FutureTask<User> futureTask = new FutureTask<>(() -> usersDAO.getUserByUserName(userName));
        ITubeDatabase.getDatabase(currentApplication).databaseWriteExecutor.execute(futureTask);

        User user = null;

        try {

            user = futureTask.get();

        } catch (ExecutionException e) {

            throw new RuntimeException(e);

        } catch (InterruptedException e) {

            throw new RuntimeException(e);
        }

        return user;
    }

    // Insert operation to create a new user entry for the database
    public void insertNewUser(User user){

        // Insert the user to the database
        ITubeDatabase.databaseWriteExecutor.execute(() -> {
            usersDAO.insertNewUser(user);
        });
    }

}
