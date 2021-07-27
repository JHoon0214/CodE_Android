package com.example.mainactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {
    private Button registerButton;
    private ImageView backButton, passwordIcon, passwordConfirmIcon;
    private EditText emailText, firstNameText, lastNameText, passwordText, passwordConfirmText;
    private TextView emailAlert, nameAlert, passwordAlert, passwordConfirmAlert, developCategoryText;
    private Spinner categorySpinner;

    private String[] developerOption;
    private String address, code;
    private boolean checkEmail, checkFirstName, checkLastName, checkPassword, checkPasswordConfirmed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .permitDiskReads()
                .permitDiskWrites()
                .permitNetwork().build());

        registerButton = (Button) findViewById(R.id.register);
        backButton = (ImageView) findViewById(R.id.back_button);
        passwordIcon = (ImageView) findViewById(R.id.register_password_icon);
        passwordConfirmIcon = (ImageView) findViewById(R.id.register_password_confirmation_icon);
        emailText = (EditText) findViewById(R.id.register_email);
        firstNameText = (EditText) findViewById(R.id.register_first_name);
        lastNameText = (EditText) findViewById(R.id.register_last_name);
        passwordText = (EditText) findViewById(R.id.register_password);
        passwordConfirmText = (EditText) findViewById(R.id.register_password_confirmation);
        emailAlert = (TextView) findViewById(R.id.register_email_alert);
        nameAlert = (TextView) findViewById(R.id.register_name_alert);
        passwordAlert = (TextView) findViewById(R.id.register_password_alert);
        passwordConfirmAlert = (TextView) findViewById(R.id.register_password_confirmation_alert);
        developCategoryText = (TextView) findViewById(R.id.register_develop_category);
        categorySpinner = (Spinner) findViewById(R.id.register_develop_category_spinner);

        developerOption = getResources().getStringArray(R.array.developer_option);
        checkEmail = false;
        checkFirstName = false;
        checkLastName = false;
        checkPassword = false;
        checkPasswordConfirmed = false;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, developerOption);
        categorySpinner.setAdapter(adapter);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back_intent = new Intent(SignUpActivity.this, MainActivity.class);
                finish();
                startActivity(back_intent);
            }
        });
        emailText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!Patterns.EMAIL_ADDRESS.matcher(s.toString()).matches()) {
                    checkEmail = false;
                    emailAlert.setVisibility(View.VISIBLE);
                } else {
                    checkEmail = true;
                    emailAlert.setVisibility(View.INVISIBLE);
                }
            }
        });
        firstNameText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().matches("^[a-zA-Z가-힣]*$")) {
                    checkFirstName = false;
                    nameAlert.setVisibility(View.VISIBLE);
                } else {
                    checkEmail = true;
                    nameAlert.setVisibility(View.INVISIBLE);
                }
            }
        });
        lastNameText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().matches("^[a-zA-Z가-힣]*$]")) {
                    checkLastName = false;
                    nameAlert.setVisibility(View.VISIBLE);
                } else {
                    checkLastName = true;
                    nameAlert.setVisibility(View.INVISIBLE);
                }
            }
        });
        passwordText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String password = passwordText.getText().toString();

                if ((password.length() < 7) || !(Pattern.matches("^[a-zA-Z0-9]*$", password))) {
                    checkPassword = false;
                    passwordAlert.setVisibility(View.VISIBLE);
                    passwordIcon.setVisibility(View.VISIBLE);
                } else {
                    checkPassword = true;
                    passwordAlert.setVisibility(View.INVISIBLE);
                    passwordIcon.setVisibility(View.INVISIBLE);
                }
            }
        });
        passwordConfirmText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String password = passwordText.getText().toString();
                String passwordConfigure = passwordConfirmText.getText().toString();

                if (!(password.equals(passwordConfigure))) {
                    checkPasswordConfirmed = false;
                    passwordConfirmAlert.setVisibility(View.VISIBLE);
                    passwordConfirmIcon.setVisibility(View.VISIBLE);
                } else {
                    checkPasswordConfirmed = true;
                    passwordConfirmAlert.setVisibility(View.INVISIBLE);
                    passwordConfirmIcon.setVisibility(View.INVISIBLE);
                }
            }
        });
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                developCategoryText.setText(developerOption[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                developCategoryText.setText("");
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // 서버 파트
        /*
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailAuthentic = address;
                String password = passwordText.getText().toString();
                String firstName = firstNameText.getText().toString();
                String lastName = lastNameText.getText().toString();
                String category = developCategoryText.getText().toString();

                String url = getString(R.string.url) + "/user/validation/email/" + address;

                System.out.println(url);

                if (checkEmail && checkFirstName && checkLastName && checkPassword && checkPasswordConfirmed) {
                    RequestQueue queue = Volley.newRequestQueue(SignUpActivity.this);
                    final StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    if (response.equals("false")) {
                                        Toast.makeText(SignUpActivity.this, "입력한 정보를 확인해주십시오.", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Intent next_intent = new Intent(SignUpActivity.this, HomeActivity.class);
                                        next_intent.putExtra("emailText", emailAuthentic);
                                        next_intent.putExtra("password", password);
                                        next_intent.putExtra("firstname", firstName);
                                        next_intent.putExtra("lastname", lastName);
                                        next_intent.putExtra("category", category);
                                        startActivity(next_intent);
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(SignUpActivity.this, "오류입니다.", Toast.LENGTH_SHORT).show();
                        }
                    });
                    queue.add(stringRequest);
                } else {
                    Toast.makeText(SignUpActivity.this, "입력한 정보를 다시 확인해주세요 :)", Toast.LENGTH_SHORT).show();
                }
            }
        }); */
    }
}