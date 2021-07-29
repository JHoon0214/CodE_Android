package com.example.mainactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;

public class HomeActivity extends AppCompatActivity {
    private ImageView drawerButton, searchButton, filterButton;
    private Fragment myProject, projectList, newProject;
    private TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawerButton = (ImageView) findViewById(R.id.home_drawer);
        searchButton = (ImageView) findViewById(R.id.home_search);
        filterButton = (ImageView) findViewById(R.id.home_filter);

        myProject = new FragmentMyProject();
        projectList = new FragmentProjectList();
        newProject = new FragmentNewProject();
        getSupportFragmentManager().beginTransaction().add(R.id.frame, myProject).commit();

        tabs = (TabLayout) findViewById(R.id.tabs);

        // 상단부 아이콘 이벤트 부분
        drawerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                    selectedFragment = myProject;
                else if(position == 1)
                    selectedFragment = projectList;
                else
                    selectedFragment = newProject;

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