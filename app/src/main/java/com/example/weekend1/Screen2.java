package com.example.weekend1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Screen2 extends AppCompatActivity {

    Button btnBack;
    Button nextButton;
    EditText email;
    EditText password1;
    EditText password2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_screen2);
        email = findViewById(R.id.email_input);
        password1 = findViewById(R.id.password1_input);
        password2 = findViewById(R.id.password2_input);
        email.setBackgroundResource(R.drawable.redborder);
        password1.setBackgroundResource(R.drawable.redborder);
        password2.setBackgroundResource(R.drawable.redborder);

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (validEmail(email.getText().toString())) {
                    email.setBackgroundResource(R.drawable.greenborder);
                } else {
                    email.setBackgroundResource(R.drawable.redborder);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        password1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (validPassword(password1.getText().toString())) {
                    password1.setBackgroundResource(R.drawable.greenborder);
                } else {
                    password1.setBackgroundResource(R.drawable.redborder);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        password2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (validPassword(password2.getText().toString())) {
                    password2.setBackgroundResource(R.drawable.greenborder);
                } else {
                    password2.setBackgroundResource(R.drawable.redborder);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        nextButton = findViewById(R.id.next_button);
        nextButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (password1.getText().toString().equals(password2.getText().toString())) {
                            if (validPassword(password1.getText().toString())) {

                                if (validPassword(password2.getText().toString())) {
                                    if (validEmail(email.getText().toString())) {
                                        Intent goNext = new Intent(Screen2.this, Screen3.class);
                                        startActivity(goNext);
                                    } else {
                                        Toast.makeText(Screen2.this, "Email is invalid", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(Screen2.this, "Password is invalid", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(Screen2.this, "Password is invalid", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(Screen2.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );


        btnBack = findViewById(R.id.back);
        btnBack.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent goBack = new Intent(Screen2.this, MainActivity.class);
                        startActivity(goBack);
                    }
                }
        );

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public boolean validPassword(String password) {
        boolean upperCase = false;
        boolean lowerCase = false;
        boolean length = false;
        boolean number = false;

        if (password.length() > 7) {
            length = true;
        }
        for (int i = 0; i < password.length(); i++) {
            char character = password.charAt(i);
            if (character >= 'A' && character <= 'Z') {
                upperCase = true;
            }
            if (character >= 'a' && character <= 'z') {
                lowerCase = true;
            }
            if (character >= '0' && character <= '9') {
                number = true;
            }

        }
        return upperCase && lowerCase && length && number;
    }

    public boolean validEmail(String email) {
        boolean valid = false;
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            valid = true;

        }
        return valid;
    }


}