package com.universitaskuningan.tugas4.ui.register;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.universitaskuningan.tugas4.R;
import com.universitaskuningan.tugas4.ui.login.LoginFragment;

public class RegisterFragment extends Fragment {

    private RegisterViewModel registerViewModel;
    private EditText editTextUsername;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonRegister;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        editTextUsername = view.findViewById(R.id.editTextUsername);
        editTextEmail = view.findViewById(R.id.editTextEmail);
        editTextPassword = view.findViewById(R.id.editTextPassword);
        buttonRegister = view.findViewById(R.id.btn_register);
        CheckBox checkboxPolicy = view.findViewById(R.id.checkbox_policy);


        buttonRegister.setOnClickListener(v -> {
            String username = editTextUsername.getText().toString();
            String email = editTextEmail.getText().toString();
            String password = editTextPassword.getText().toString();

            if (!checkboxPolicy.isChecked()) {
                Toast.makeText(getContext(), "Harap setujui syarat dan ketentuan yang berlaku", Toast.LENGTH_SHORT).show();
                return; // Hentikan proses pendaftaran jika checkbox tidak dicentang
            }
            if (validateForm(username, email, password)) {
                registerViewModel.registerUser(username, email, password);
            }
        });

        registerViewModel.getRegistrationStatus().observe(getViewLifecycleOwner(), isSuccess -> {
            if (isSuccess) {
                Toast.makeText(getActivity(), "Registration Successful", Toast.LENGTH_SHORT).show();
                // Navigate to login fragment
                navigateToLoginFragment();
            } else {
                Toast.makeText(getActivity(), "Registration Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validateForm(String username, String email, String password) {
        // Validation logic
        if (username.isEmpty()) {
            editTextUsername.setError("Username cannot be empty");
            return false;
        }
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Enter a valid email address");
            return false;
        }
        if (password.length() < 8) {
            editTextPassword.setError("Password must be at least 8 characters");
            return false;
        }
        return true;
    }

    private void navigateToLoginFragment() {
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new LoginFragment())
                .addToBackStack(null)
                .commit();
    }

}
