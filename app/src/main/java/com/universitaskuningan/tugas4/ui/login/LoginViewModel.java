package com.universitaskuningan.tugas4.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.universitaskuningan.tugas4.LoginSuccessActivity;
import com.universitaskuningan.tugas4.User;

import java.util.HashMap;
import java.util.Map;

public class LoginViewModel extends ViewModel {

    private Context context;
    private Map<String, User> registeredUsers; // Menyimpan pengguna yang diregistrasikan

    // Constructor untuk inisialisasi konteks
    public LoginViewModel(Context context) {
        this.context = context;
        this.registeredUsers = new HashMap<>();
        // Simulasikan data pengguna yang diregistrasikan
        registeredUsers.put("user1", new User("user1", "password1"));
        registeredUsers.put("user2", new User("user2", "password2"));
        // Tambahkan pengguna lain sesuai kebutuhan
    }

    // Method to handle login action
    public void login(String username, String password) {
        if (registeredUsers.containsKey(username)) {
            User user = registeredUsers.get(username);
            if (user.getPassword().equals(password)) {
                // Login berhasil, pindah ke SuccessActivity
                Intent intent = new Intent(context, LoginSuccessActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("username", username);
                intent.putExtras(bundle);
                context.startActivity(intent);
            } else {
                // Password salah
                Toast.makeText(context, "Password salah", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Username tidak ditemukan
            Toast.makeText(context, "Username tidak ditemukan", Toast.LENGTH_SHORT).show();
        }
    }
}
