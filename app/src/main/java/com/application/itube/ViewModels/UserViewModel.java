package com.application.itube.ViewModels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import com.application.itube.DataModels.User;
import com.application.itube.Repositories.UserRepository;

/**
 * View model for the user to bridge the UI to the repository.
 */
public class UserViewModel extends AndroidViewModel {

    // Reference to user repository
    private UserRepository userRepository;

    /**
     * Initialize our user view model
     * @param application
     */
    public UserViewModel(@NonNull Application application) {

        super(application);

        userRepository = new UserRepository(application);
    }

    /**
     * Get a user by user name
     * @param userName
     * @return
     */
    public User getUserByUserName(String userName){

        return userRepository.getUserByUserName(userName);
    }

    /**
     *  Insert operation to create a new user entry for the database
     * @param user
     */
    public void insertNewUser(User user){
        userRepository.insertNewUser(user);
    }
}
