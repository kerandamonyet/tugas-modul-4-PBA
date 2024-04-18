package com.universitaskuningan.tugas4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.universitaskuningan.tugas4.ui.login.LoginFragment;

public class LoginSuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.success_login);

        // Ambil reference ke tombol
        Button backButton = findViewById(R.id.btn_backToHome);

        // Set OnClickListener untuk tombol
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kembali ke halaman login
                startActivity(new Intent(LoginSuccessActivity.this, LoginFragment.class));
                finish(); // Tutup halaman saat ini agar tidak bisa kembali ke halaman login success lagi
            }
        });

        // Mendapatkan username dan password dari Intent
        Intent intent = getIntent();
        if (intent != null) {
            String username = intent.getStringExtra("username");
            String password = intent.getStringExtra("password");

            // Tampilkan username dan password di TextView
            TextView usernameTextView = findViewById(R.id.usernameTextView);
            TextView passwordTextView = findViewById(R.id.passwordTextView);

            usernameTextView.setText("Username: " + username);
            passwordTextView.setText("Password: " + password);
        }
    }
}

