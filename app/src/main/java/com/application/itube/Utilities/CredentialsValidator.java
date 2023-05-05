package com.application.itube.Utilities;

import android.app.Activity;
import android.util.Log;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import com.application.itube.DataModels.User;
import com.application.itube.ViewModels.UserViewModel;

/**
 * Class to help handle credentials validation.
 * A better way to do this perhaps is remove the static and use dependency injection to inject an implementation of an credentials validator.
 */
public class CredentialsValidator {

    public static Boolean validateCredentials(ViewModelStoreOwner viewModelStoreOwner, String username, String password){

        UserViewModel userViewModel = new ViewModelProvider(viewModelStoreOwner).get(UserViewModel.class);
        User user = userViewModel.getUserByUserName(username.toLowerCase());

        Log.d("test", username);

        if (user == null){

            // Just a note here, we can either simply return false or thrown an exception and handle the error differently.
            // I've opted to return false.
            //throw new RuntimeException("User could not be found!");
            return false;
        }

        if (user.passWord.matches(password)){

            return true;
        }

        return false;
    }
}
