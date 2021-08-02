package com.example.mainactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class SubprojectDetailActivity extends AppCompatActivity {
    private EditText name, description, period;
    private ImageView backButton;
    private String nameText, descriptionText, periodText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subproject_detail);

        name = (EditText) findViewById(R.id.subproject_name);
        description = (EditText) findViewById(R.id.subproject_description);
        period = (EditText) findViewById(R.id.subproject_period);
        backButton = (ImageView) findViewById(R.id.back_button);

        Intent intent = getIntent();
        nameText = intent.getStringExtra("projectName");
        descriptionText = intent.getStringExtra("projectDescription");
        periodText = intent.getStringExtra("projectPeriod");

        name.setText(nameText);
        description.setText(descriptionText);
        period.setText(periodText);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent(SubprojectDetailActivity.this, ProjectDetailActivity.class);
                finish();
                startActivity(backIntent);
            }
        });
    }
}