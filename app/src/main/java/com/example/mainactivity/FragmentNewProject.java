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
import android.widget.EditText;
import android.widget.TextView;

import static android.app.Activity.RESULT_OK;

public class FragmentNewProject extends Fragment {
    TextView startDate;
    TextView endDate;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_new_project, container, false);

        EditText projectName = (EditText)view.findViewById(R.id.new_project_name);
        EditText projectDescription = (EditText)view.findViewById(R.id.new_project_description);
        startDate = (TextView)view.findViewById(R.id.startDate);
        endDate = (TextView)view.findViewById(R.id.endDate);
        EditText eMail = (EditText)view.findViewById(R.id.eMail);
        EditText role = (EditText)view.findViewById(R.id.Role);

        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext().getApplicationContext(), SelectingCalendarActivity.class);

                intent.putExtra("projectName",projectName.getText().toString());
                intent.putExtra("projectDescription",projectDescription.getText().toString());
                intent.putExtra("eMail",eMail.getText().toString());
                intent.putExtra("role",role.getText().toString());
                startActivityForResult(intent, 0);
            }
        });
        return view;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 0) {
            if(resultCode == RESULT_OK) {
                String s = data.getStringExtra("startDate");
                String e = data.getStringExtra("endDate");
                startDate.setText(s);
                endDate.setText(e);

            }
        }
    }
}