package com.example.mainactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;

public class ProjectDetailActivity extends AppCompatActivity {
    private ImageView backButton, searchButton, filterButton;
    private Fragment projectProcess, projectSchedule, projectMessage;
    private TabLayout tabs;
    private String projectNameText, projectCategoryText, projectDescriptionText, projectImageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);

        backButton = (ImageView) findViewById(R.id.back_button);
        searchButton = (ImageView) findViewById(R.id.project_search);
        filterButton = (ImageView) findViewById(R.id.project_filter);

        Intent intent = getIntent();
        projectNameText = intent.getExtras().getString("projectName");
        System.out.println("projectNameText: " + projectNameText);
        projectCategoryText = intent.getExtras().getString("projectCategory");
        System.out.println("projectPeriodText: " + projectCategoryText);
        projectDescriptionText = intent.getExtras().getString("projectDescription");
        System.out.println("projectDescriptionText: " + projectDescriptionText);

        /*
        projectImageName = intent.getExtras().getString("projectImageName");
        System.out.println("projectImageName: " + projectImageName);
        */

        projectProcess = new FragmentProgress();
        projectSchedule = new FragmentSchedule();
        projectMessage = new FragmentMessage();

        /* 프로젝트 상세 탭 중 Progress 탭에 프로젝트 정보를 전달하여 세부 정보 로드 */
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("projectName", projectNameText);
        bundle.putString("projectCategory", projectCategoryText);
        bundle.putString("projectDescription", projectDescriptionText);
        projectProcess.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.frame, projectProcess).commit();

        tabs = (TabLayout) findViewById(R.id.tabs);

        // 상단부 아이콘 이벤트 부분
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent(ProjectDetailActivity.this, HomeActivity.class);
                finish();
                startActivity(backIntent);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // 탭 구현 구간
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Fragment selectedFragment = null;

                if(position == 0)
                    selectedFragment = projectProcess;
                else if(position == 1)
                    selectedFragment = projectSchedule;
                else
                    selectedFragment = projectMessage;

                replaceFragment(selectedFragment);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    public void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, fragment).commit();
    }
}