package com.example.mainactivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FragmentProgress extends Fragment {
    private Button moreButton;
    private ImageView projectImage, subproject1Button, subproject2Button, subproject3Button;
    private ImageView progress100, progress85, progress70, progress55, progress40, progress25, progress10, progress0;
    private TextView projectName, projectDescription, subproject1Name, subproject1Period,
        subproject2Name, subproject2Period, subproject3Name, subproject3Period;

    private String projectNameText, projectCategoryText, projectDescriptionText, projectImageName;
    private ArrayList<Subproject> subProjectArray = new ArrayList<Subproject>();

    public static FragmentProgress newInstance() {
        return new FragmentProgress();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_progress, container, false);

        projectImage = (ImageView) view.findViewById(R.id.project_image);
        subproject1Button = (ImageView) view.findViewById(R.id.subproject1_button);
        subproject2Button = (ImageView) view.findViewById(R.id.subproject2_button);
        subproject3Button = (ImageView) view.findViewById(R.id.subproject3_button);
        projectName = (TextView) view.findViewById(R.id.project_name);
        projectDescription = (TextView) view.findViewById(R.id.project_description);
        subproject1Name = (TextView) view.findViewById(R.id.subproject1_name);
        subproject1Period = (TextView) view.findViewById(R.id.subproject1_period);
        subproject2Name = (TextView) view.findViewById(R.id.subproject2_name);
        subproject2Period = (TextView) view.findViewById(R.id.subproject2_period);
        subproject3Name = (TextView) view.findViewById(R.id.subproject3_name);
        subproject3Period = (TextView) view.findViewById(R.id.subproject3_period);
        moreButton = (Button) view.findViewById(R.id.subproject_list_button);

        progress100 = (ImageView) view.findViewById(R.id.progress_100);
        progress85 = (ImageView) view.findViewById(R.id.progress_85);
        progress70 = (ImageView) view.findViewById(R.id.progress_70);
        progress55 = (ImageView) view.findViewById(R.id.progress_55);
        progress40 = (ImageView) view.findViewById(R.id.progress_40);
        progress25 = (ImageView) view.findViewById(R.id.progress_25);
        progress10 = (ImageView) view.findViewById(R.id.progress_10);
        progress0 = (ImageView) view.findViewById(R.id.progress_0);

        /* 임시로 서브프로젝트(일/업무) 객체 생성. 추후 로컬이나 서버에서 정보를 불러올 예정임 */
        Subproject subProject1 = new Subproject("첫번째", "첫번째 프로젝트 설명", "21.08.01~21.08.03");
        Subproject subProject2 = new Subproject("두번째", "두번째 프로젝트 설명", "21.08.01~21.08.03");
        Subproject subProject3 = new Subproject("세번째", "세번째 프로젝트 설명", "21.08.01~21.08.03");
        subProjectArray.add(subProject1);
        subProjectArray.add(subProject2);
        subProjectArray.add(subProject3);

        System.out.println(subProjectArray);

        Bundle bundle = getArguments();
        if(bundle != null) {
            projectNameText = bundle.getString("projectName");
            projectCategoryText = bundle.getString("projectCategory");
            projectDescriptionText = bundle.getString("projectDescription");
        }

        projectName.setText(projectNameText);
        projectDescription.setText(projectDescriptionText);
        subproject1Name.setText(subProject1.name);
        subproject1Period.setText(subProject1.period);
        subproject2Name.setText(subProject2.name);
        subproject2Period.setText(subProject2.period);
        subproject3Name.setText(subProject3.name);
        subproject3Period.setText(subProject3.period);

        /* 완료한 서브프로젝트 개수에 따라 진척도 출력 */
        subProject1.completed = true;
        subProject2.completed = false;
        subProject3.completed = true;
        setProgressPercentage();

        moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        /* 서버나 로컬에서 프로젝트를 생성하면 그 정보를 받아오는 부분 */

        /* To-Do List 중 하나를 선택하면 해당 업무의 정보를 보여주는 화면 실행 */
        subproject1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String projectName = subProject1.name;
                String projectPeriod = subProject1.period;
                String projectDescription = subProject1.description;
            //    String projectImageName = projectName + "_image";

                Intent project1Intent = new Intent(getActivity(), ProjectDetailActivity.class);
                project1Intent.putExtra("projectName", projectName);
                project1Intent.putExtra("projectPeriod", projectPeriod);
                project1Intent.putExtra("projectDescription", projectDescription);
            //    project1Intent.putExtra("projectImageName", projectImageName);
                startActivity(project1Intent);
            }
        });

        subproject2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String projectName = subProject2.name;
                String projectPeriod = subProject2.period;
                String projectDescription = subProject2.description;
                //    String projectImageName = projectName + "_image";

                Intent project2Intent = new Intent(getActivity(), ProjectDetailActivity.class);
                project2Intent.putExtra("projectName", projectName);
                project2Intent.putExtra("projectPeriod", projectPeriod);
                project2Intent.putExtra("projectDescription", projectDescription);
                //    project1Intent.putExtra("projectImageName", projectImageName);
                startActivity(project2Intent);
            }
        });

        subproject3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String projectName = subProject3.name;
                String projectPeriod = subProject3.period;
                String projectDescription = subProject3.description;
                //    String projectImageName = projectName + "_image";

                Intent project3Intent = new Intent(getActivity(), ProjectDetailActivity.class);
                project3Intent.putExtra("projectName", projectName);
                project3Intent.putExtra("projectPeriod", projectPeriod);
                project3Intent.putExtra("projectDescription", projectDescription);
                //    project1Intent.putExtra("projectImageName", projectImageName);
                startActivity(project3Intent);
            }
        });

        return view;
    }

    private void setProgressPercentage() {
        int n = 0;
        float completedPercent;

        for(int i = 0; i < subProjectArray.size(); i++) {
            if(subProjectArray.get(i).completed)
                n++;
        }

        completedPercent = (float) n * 100 / subProjectArray.size();

        System.out.println("n: " + n);
        System.out.println("percent: " + completedPercent);

        if(completedPercent == 100) {
            progress100.setImageResource(R.drawable.progress_bar_true);
            progress85.setImageResource(R.drawable.progress_bar_true);
            progress70.setImageResource(R.drawable.progress_bar_true);
            progress55.setImageResource(R.drawable.progress_bar_true);
            progress40.setImageResource(R.drawable.progress_bar_true);
            progress25.setImageResource(R.drawable.progress_bar_true);
            progress10.setImageResource(R.drawable.progress_bar_true);
            progress0.setImageResource(R.drawable.progress_bar_true);
        }
        else if(completedPercent >= 85) {
            progress100.setImageResource(R.drawable.progress_bar_false);
            progress85.setImageResource(R.drawable.progress_bar_true);
            progress70.setImageResource(R.drawable.progress_bar_true);
            progress55.setImageResource(R.drawable.progress_bar_true);
            progress40.setImageResource(R.drawable.progress_bar_true);
            progress25.setImageResource(R.drawable.progress_bar_true);
            progress10.setImageResource(R.drawable.progress_bar_true);
            progress0.setImageResource(R.drawable.progress_bar_true);
        }
        else if(completedPercent >= 70) {
            progress100.setImageResource(R.drawable.progress_bar_false);
            progress85.setImageResource(R.drawable.progress_bar_false);
            progress70.setImageResource(R.drawable.progress_bar_true);
            progress55.setImageResource(R.drawable.progress_bar_true);
            progress40.setImageResource(R.drawable.progress_bar_true);
            progress25.setImageResource(R.drawable.progress_bar_true);
            progress10.setImageResource(R.drawable.progress_bar_true);
            progress0.setImageResource(R.drawable.progress_bar_true);
        }
        else if(completedPercent >= 55) {
            progress100.setImageResource(R.drawable.progress_bar_false);
            progress85.setImageResource(R.drawable.progress_bar_false);
            progress70.setImageResource(R.drawable.progress_bar_false);
            progress55.setImageResource(R.drawable.progress_bar_true);
            progress40.setImageResource(R.drawable.progress_bar_true);
            progress25.setImageResource(R.drawable.progress_bar_true);
            progress10.setImageResource(R.drawable.progress_bar_true);
            progress0.setImageResource(R.drawable.progress_bar_true);
        }
        else if(completedPercent >= 40) {
            progress100.setImageResource(R.drawable.progress_bar_false);
            progress85.setImageResource(R.drawable.progress_bar_false);
            progress70.setImageResource(R.drawable.progress_bar_false);
            progress55.setImageResource(R.drawable.progress_bar_false);
            progress40.setImageResource(R.drawable.progress_bar_true);
            progress25.setImageResource(R.drawable.progress_bar_true);
            progress10.setImageResource(R.drawable.progress_bar_true);
            progress0.setImageResource(R.drawable.progress_bar_true);
        }
        else if(completedPercent >= 25) {
            progress100.setImageResource(R.drawable.progress_bar_false);
            progress85.setImageResource(R.drawable.progress_bar_false);
            progress70.setImageResource(R.drawable.progress_bar_false);
            progress55.setImageResource(R.drawable.progress_bar_false);
            progress40.setImageResource(R.drawable.progress_bar_false);
            progress25.setImageResource(R.drawable.progress_bar_true);
            progress10.setImageResource(R.drawable.progress_bar_true);
            progress0.setImageResource(R.drawable.progress_bar_true);
        }
        else if(completedPercent >= 10) {
            progress100.setImageResource(R.drawable.progress_bar_false);
            progress85.setImageResource(R.drawable.progress_bar_false);
            progress70.setImageResource(R.drawable.progress_bar_false);
            progress55.setImageResource(R.drawable.progress_bar_false);
            progress40.setImageResource(R.drawable.progress_bar_false);
            progress25.setImageResource(R.drawable.progress_bar_false);
            progress10.setImageResource(R.drawable.progress_bar_true);
            progress0.setImageResource(R.drawable.progress_bar_true);
        }
        else {
            progress100.setImageResource(R.drawable.progress_bar_false);
            progress85.setImageResource(R.drawable.progress_bar_false);
            progress70.setImageResource(R.drawable.progress_bar_false);
            progress55.setImageResource(R.drawable.progress_bar_false);
            progress40.setImageResource(R.drawable.progress_bar_false);
            progress25.setImageResource(R.drawable.progress_bar_false);
            progress10.setImageResource(R.drawable.progress_bar_false);
            progress0.setImageResource(R.drawable.progress_bar_true);
        }
    }

    public class Subproject {
        String name, description, period;
        boolean completed;

        Subproject(String setName, String setPeriod) {
            completed = false;
            this.name = setName;
            this.period = setPeriod;
        }

        Subproject(String setName, String setDescription, String setPeriod) {
            completed = false;
            this.name = setName;
            this.description = setDescription;
            this.period = setPeriod;
        }

        public void finishSubProject() {
            completed = true;
        }
    }
}