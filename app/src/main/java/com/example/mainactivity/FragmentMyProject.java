package com.example.mainactivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FragmentMyProject extends Fragment {
    private ImageView recentProjectImage, project1Image, project2Image;
    private TextView recentProjectName, recentProjectDescription, project1Name, project1Category,
            project2Name, project2Category, project3Name, project3Category;
    private Button moreButton;

    public static FragmentMyProject newInstance() {
        return new FragmentMyProject();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_my_project, container, false);

        recentProjectImage = (ImageView) view.findViewById(R.id.recent_project_image);
        project1Image = (ImageView) view.findViewById(R.id.project1_image);
        project2Image = (ImageView) view.findViewById(R.id.project2_image);
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
        return view;
    }
}