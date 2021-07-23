package com.example.mainactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class SignUpActivity extends AppCompatActivity {
    private Button registerButton;
    private ImageView backButton;
    private EditText emailText, firstNameText, lastNameText, passwordText;
    private Spinner optionSpinner;

    private int[] locationPmGrade;
    private int[] locationNum;
    private int cnt = 1;
    private String[] developerOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        registerButton = (Button) findViewById(R.id.register);
        backButton = (ImageView) findViewById(R.id.back_button);
        emailText = (EditText) findViewById(R.id.register_email);
        firstNameText = (EditText) findViewById(R.id.register_first_name);
        lastNameText = (EditText) findViewById(R.id.register_last_name);
        passwordText = (EditText) findViewById(R.id.register_password);
        developerOption  = getResources().getStringArray(R.array.developer_option);

    }
}