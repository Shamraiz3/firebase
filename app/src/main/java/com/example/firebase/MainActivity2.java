package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2 extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText editTextEmail, editTextPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

         mAuth = FirebaseAuth.getInstance();

        editTextEmail = findViewById(R.id.t1);
        editTextPassword = findViewById(R.id.t2);

        Button buttonSignIn = findViewById(R.id.buttonSignin);


        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity2.this, "Please enter both email and password ", Toast.LENGTH_SHORT).show();
                } else {
                    signInUser(email, password);
                }
            }
        });
    }


    private void signInUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent=new Intent(MainActivity2.this,MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(MainActivity2.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity2.this, "Sign in failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}