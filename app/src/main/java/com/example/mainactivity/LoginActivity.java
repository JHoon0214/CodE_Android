package com.example.mainactivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private Button loginButton;
    private ImageView backButton;
    private EditText loginText, passwordText;

    private int cnt = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = (Button) findViewById(R.id.login);
        backButton = (ImageView) findViewById(R.id.back_button);
        loginText = (EditText) findViewById(R.id.login_email);
        passwordText = (EditText) findViewById(R.id.login_password);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back_intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(back_intent);
                finish();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = loginText.getText().toString();
                String password = passwordText.getText().toString();

                System.out.println(email);
                System.out.println(password);

                // 서버 구간
                JSONObject infoForLogin = new JSONObject();
                try {
                    infoForLogin.put("eMail", email);
                    infoForLogin.put("password", password);
                } catch (JSONException exception) {
                    exception.printStackTrace();
                }

                String url = getString(R.string.url) + "/auth/login";
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, infoForLogin, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            saveToken(response);
                        } catch (JSONException exception) {
                            exception.printStackTrace();
                        }
                        logIn();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, "등록되지 않은 아이디/비밀번호입니다. 다시 확인해주세요 :)", Toast.LENGTH_SHORT).show();
                    }
                });

                queue.add(jsonObjectRequest);
            }

            private void saveToken(JSONObject response) throws JSONException {
                SharedPreferences sharedPreferences = getSharedPreferences("token", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Authorization", response.get("access_token").toString());
                editor.apply();
            }

            private void logIn() {
                SharedPreferences sharedPreferences = getSharedPreferences("token", MODE_PRIVATE);
                String token = sharedPreferences.getString("Authorization", "");
                String url = getString(R.string.url) + "/profile";
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);

                final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, "오류입니다", Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> heads = new HashMap<String, String>();
                        heads.put("Authorization", "Bearer " + token);
                        return heads;
                    }
                };
                queue.add(jsonObjectRequest);
            }
        });
    }
}
