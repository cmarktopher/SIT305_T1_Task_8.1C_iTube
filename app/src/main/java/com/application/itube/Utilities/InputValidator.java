package com.application.itube.Utilities;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

/**
 * Class to help handle input validation.
 * A better way to do this perhaps is remove the static and use dependency injection to inject an implementation of an input validator.
 */
public class InputValidator {

    /**
     * Validate our input text and set the respective error message
     */
    public static String validateInputString(TextInputLayout textInputLayout, TextInputEditText textInputEditText, String errorMessage){

        // Clear the error to ensure subsequent entries are taken into account.
        textInputLayout.setError(null);

        // Get the text
        String textInput = textInputEditText.getText().toString();

        // If empty, set the error and throw an exception
        if (textInput.isEmpty()){

            textInputLayout.setError(errorMessage);
            throw new RuntimeException();
        }

        // If we reach here, we have an input to use
        return textInput;
    }
}
