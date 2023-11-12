package com.cs407.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.cs407.hippie_christmas.R;

public class SignUpActivity extends AppCompatActivity {

    EditText editTextNewUsername, editTextEmail, editTextNewPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editTextNewUsername = findViewById(R.id.editTextNewUsername);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextNewPassword = findViewById(R.id.editTextNewPassword);

        Button buttonSignUp = findViewById(R.id.buttonSignUp);
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newUsername = editTextNewUsername.getText().toString();
                String email = editTextEmail.getText().toString();
                String newPassword = editTextNewPassword.getText().toString();

                // Implement your sign-up logic here
                // Store new user information or perform desired actions
            }
        });
    }

    public void openLoginScreen(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
