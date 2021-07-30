package com.example.mainactivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FragmentMyProject extends Fragment {
    private ImageView recentProjectButton;
    private TextView recentProjectName, recentProjectDescription;
    private Button moreButton;

    public ImageView project1Button, project2Button, project3Button;
    public TextView project1Name, project1Category, project2Name, project2Category, project3Name, project3Category;

    public static FragmentMyProject newInstance() {
        return new FragmentMyProject();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_my_project, container, false);

        recentProjectButton = (ImageView) view.findViewById(R.id.recent_project_button);
        project1Button = (ImageView) view.findViewById(R.id.project1_button);
        project2Button = (ImageView) view.findViewById(R.id.project2_button);
        project3Button = (ImageView) view.findViewById(R.id.project3_button);
        recentProjectName = (TextView) view.findViewById(R.id.recent_project_name);
        recentProjectDescription = (TextView) view.findViewById(R.id.recent_project_description);
        project1Name = (TextView) view.findViewById(R.id.project1_name);
        project1Category = (TextView) view.findViewById(R.id.project1_category);
        project2Name = (TextView) view.findViewById(R.id.project2_name);
        project2Category = (TextView) view.findViewById(R.id.project2_category);
        project3Name = (TextView) view.findViewById(R.id.project3_name);
        project3Category = (TextView) view.findViewById(R.id.project3_category);
        moreButton = (Button) view.findViewById(R.id.project_list_button);

        moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HomeActivity) getActivity()).replaceFragment(FragmentProjectList.newInstance());
            }
        });

        /* 서버나 로컬에서 프로젝트를 생성하면 그 정보를 받아오는 부분 */

        /* 프로젝트 하나를 선택하면 프로젝트 상세 정보 화면을 위해 정보를 넘겨주는 부분 */
        recentProjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String projectName = recentProjectName.getText().toString();

                // 프로젝트 세부 정보 화면에서 프로젝트 아이콘 사진을 받아올 떄 식별용으로 사용할 이미지 이름
                String projectImageName = projectName + "_image";

                // 선택한 프로젝트의 정보를 가지고 세부 정보 화면으로 전환
                /*
                Intent recentProjectIntent = new Intent(getActivity(), ProjectDetailActivity.class);
                recentProjectIntent.putExtra("projectName", projectName);
                recentProjectIntent.putExtra("projectImageName", projectImageName);
                startActivity(recentProjectIntent);*/
            }
        });

        project1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String projectName = project1Name.getText().toString();
                String projectImageName = projectName + "_image";
                /*
                Intent project1Intent = new Intent(getActivity(), ProjectDetailActivity.class);
                project1Intent.putExtra("projectName", projectName);
                project1Intent.putExtra("projectImageName", projectImageName);
                startActivity(project1Intent);*/
            }
        });

        project2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String projectName = project2Name.getText().toString();
                String projectImageName = projectName + "_image";
                /*
                Intent project2Intent = new Intent(getActivity(), ProjectDetailActivity.class);
                project2Intent.putExtra("projectName", projectName);
                project2Intent.putExtra("projectImageName", projectImageName);
                startActivity(project2Intent);*/
            }
        });

        project3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String projectName = project2Name.getText().toString();
                String projectImageName = projectName + "_image";
                /*
                Intent project3Intent = new Intent(getActivity(), ProjectDetailActivity.class);
                project3Intent.putExtra("projectName", projectName);
                project3Intent.putExtra("projectImageName", projectImageName);
                startActivity(project3Intent);*/
            }
        });

        return view;
    }
}