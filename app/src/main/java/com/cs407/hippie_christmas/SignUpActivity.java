package com.cs407.hippie_christmas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    EditText editTextNewUsername, editTextEmail, editTextNewPassword;
    DatabaseHelper databaseHelper; // Assuming you have a DatabaseHelper class

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize the EditTexts and DatabaseHelper
        editTextNewUsername = findViewById(R.id.editTextNewUsername);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextNewPassword = findViewById(R.id.editTextNewPassword);
        databaseHelper = new DatabaseHelper(this);

        Button buttonSignUp = findViewById(R.id.buttonSignUp);
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newUsername = editTextNewUsername.getText().toString();
                String email = editTextEmail.getText().toString();
                String newPassword = editTextNewPassword.getText().toString();

                // Add input validation here if needed

                // Save the user information to the database
                databaseHelper.addUser(newUsername, email, newPassword);

                // Show a confirmation message
                Toast.makeText(SignUpActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();

                // Navigate back to the MainActivity
                openLoginScreen();
            }
        });
    }

    public void openLoginScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
