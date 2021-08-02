package com.example.mainactivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

import static android.content.Context.MODE_PRIVATE;

public class FragmentMyProject extends Fragment {
    private ImageView recentProjectButton;
    private TextView recentProjectName, recentProjectDescription;
    private Button moreButton;

    public ImageView project1Button, project2Button, project3Button;
    public TextView project1Name, project1Category, project2Name, project2Category, project3Name, project3Category;
    private String project1Id, project2Id, project3Id;

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


        /* 임시로 프로젝트 객체 생성. 추후 로컬이나 서버에서 정보를 불러올 예정임
        Project project1 = new Project("프로젝트 1", "안드로이드 어플 제작", "Android");
        Project project2 = new Project("프로젝트 2", "iOS 어플 제작", "iOS");
        Project project3 = new Project("프로젝트 3", "웹페이지 제작", "Web Front-end");*/

        /* recentProject 객체에는 가장 최신 수정 사항이 있는 프로젝트 객체를 연결 */
        //Project recentProject = project1;

        getRecentProject();

        /*
        project1Name.setText(project1.name);
        project1Category.setText(project1.category);
        project2Name.setText(project2.name);
        project2Category.setText(project2.category);
        project3Name.setText(project3.name);
        project3Category.setText(project3.category);
        recentProjectName.setText(recentProject.name);
        recentProjectDescription.setText(recentProject.description);
        */

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
                /*
                String projectName = recentProject.name;
                String projectCategory = recentProject.category;
                String projectDescription = recentProject.description;*/

                // 프로젝트 세부 정보 화면에서 프로젝트 아이콘 사진을 받아올 떄 식별용으로 사용할 이미지 이름
                //String projectImageName = projectName + "_image";

                // 선택한 프로젝트의 정보를 가지고 세부 정보 화면으로 전환

                Intent recentProjectIntent = new Intent(getActivity(), ProjectDetailActivity.class);
                recentProjectIntent.putExtra("projectId", project1Id);
                /*
                recentProjectIntent.putExtra("projectName", projectName);
                recentProjectIntent.putExtra("projectDescription", projectDescription);
                recentProjectIntent.putExtra("projectCategory", projectCategory);
                recentProjectIntent.putExtra("projectImageName", projectImageName);
                 */
                startActivity(recentProjectIntent);
            }
        });

        project1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                String projectName = project1.name;
                String projectCategory = project1.category;
                String projectDescription = project1.description;
                String projectImageName = projectName + "_image";
                 */
              

                Intent project1Intent = new Intent(getActivity(), ProjectDetailActivity.class);
                project1Intent.putExtra("projectId", project1Id);
                /*
                project1Intent.putExtra("projectName", projectName);
                project1Intent.putExtra("projectImageName", projectImageName);
                */

                startActivity(project1Intent);
            }
        });

        project2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                String projectName = project2.name;
                String projectCategory = project2.category;
                String projectDescription = project2.description;
                String projectImageName = projectName + "_image";

                Intent recentProjectIntent = new Intent(getActivity(), ProjectDetailActivity.class);
                recentProjectIntent.putExtra("projectName", projectName);
                recentProjectIntent.putExtra("projectDescription", projectDescription);
                recentProjectIntent.putExtra("projectCategory", projectCategory);
                recentProjectIntent.putExtra("projectImageName", projectImageName);
                startActivity(recentProjectIntent);*/


                Intent project2Intent = new Intent(getActivity(), ProjectDetailActivity.class);
                project2Intent.putExtra("projectId", project2Id);
                /*
                project2Intent.putExtra("projectName", projectName);
                project2Intent.putExtra("projectImageName", projectImageName);
                */

                startActivity(project2Intent);
            }
        });

        project3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                String projectName = project3.name;
                String projectCategory = project3.category;
                String projectDescription = project3.description;
                String projectImageName = projectName + "_image";

                Intent recentProjectIntent = new Intent(getActivity(), ProjectDetailActivity.class);
                recentProjectIntent.putExtra("projectName", projectName);
                recentProjectIntent.putExtra("projectDescription", projectDescription);
                recentProjectIntent.putExtra("projectCategory", projectCategory);
                recentProjectIntent.putExtra("projectImageName", projectImageName);
                startActivity(recentProjectIntent);
                */

                Intent project3Intent = new Intent(getActivity(), ProjectDetailActivity.class);
                project3Intent.putExtra("projectId", project3Id);
                /*
                project3Intent.putExtra("projectName", projectName);
                project3Intent.putExtra("projectImageName", projectImageName);
                */
                startActivity(project3Intent);
            }
        });

        return view;
    }

    public class Project {
        String name, description, category;
        boolean completed;

        Project(String setName, String setCategory) {
            completed = false;
            this.name = setName;
            this.category = setCategory;
        }

        Project(String setName, String setDescription, String setCategory) {
            completed = false;
            this.name = setName;
            this.description = setDescription;
            this.category = setCategory;
        }

        public void finishProject() {
            completed = true;
        }
    }

    private void getRecentProject() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("token", MODE_PRIVATE);
        String token = sharedPreferences.getString("Authorization", "");
        String url = getString(R.string.url) + "/project/recent";
        RequestQueue queue = Volley.newRequestQueue(this.getContext());

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray arr = null;
                        try {
                            arr = response.getJSONArray("recentProjectArr");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        try {
                            // project1
                            if(arr.getJSONObject(0).getString("name").equals("")) {
                                project1Name.setText("새 프로젝트를 시작해 보세요");
                                project1Category.setText("");
                                project1Id = "";
                            }
                            else {
                                project1Name.setText(arr.getJSONObject(0).getString("name"));
                                project1Category.setText(arr.getJSONObject(0).getString("myRole"));
                                project1Id = arr.getJSONObject(0).getString("classification");
                            }

                            // project2
                            if(arr.getJSONObject(1).getString("name").equals("")) {
                                project2Name.setText("새 프로젝트를 시작해 보세요");
                                project2Category.setText("");
                                project2Id = "";
                            }
                            else {
                                project2Name.setText(arr.getJSONObject(1).getString("name"));
                                project2Category.setText(arr.getJSONObject(1).getString("myRole"));
                                project2Id = arr.getJSONObject(1).getString("classification");
                            }

                            // project3
                            if(arr.getJSONObject(2).getString("name").equals("")) {
                                project3Name.setText("새 프로젝트를 시작해 보세요");
                                project3Category.setText("");
                                project3Id = "";
                            }
                            else {
                                project3Name.setText(arr.getJSONObject(2).getString("name"));
                                project3Category.setText(arr.getJSONObject(2).getString("myRole"));
                                project3Id = arr.getJSONObject(2).getString("classification");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "오류입니다", Toast.LENGTH_SHORT).show();
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
}