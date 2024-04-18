package com.universitaskuningan.tugas4.ui.register;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RegisterViewModel extends ViewModel {

    private MutableLiveData<Boolean> isRegistrationComplete = new MutableLiveData<>();
    private MutableLiveData<String> registrationError = new MutableLiveData<>();

    public LiveData<Boolean> getRegistrationStatus() {
        return isRegistrationComplete;
    }

    public LiveData<String> getRegistrationError() {
        return registrationError;
    }

    public void registerUser(String username, String email, String password) {
        try {
            // Simulate network request
            boolean isSuccess = true;  // Example condition
            if (isSuccess) {
                isRegistrationComplete.postValue(true);
            } else {
                registrationError.postValue("Registration failed due to server error");
            }
        } catch (Exception e) {
            registrationError.postValue("An error occurred: " + e.getMessage());
        }
    }
}
