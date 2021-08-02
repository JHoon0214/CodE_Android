package com.example.mainactivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.Button;
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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class FragmentProjectList extends Fragment {

    private RecyclerAdapter adapter;
    public static FragmentProjectList newInstance() {
        return new FragmentProjectList();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_project_list, container, false);
        init(view);
        getData();
        return view;
    }

    private void init(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);
    }

    /*
    private void getData() {
        // 임의의 데이터입니다.
        List<Data> listTitle = Arrays.asList(
                new Data("one", "첫번째 프로젝트에 대한 설명"),
                new Data("two", "두번째 프로젝트에 대한 설명"),
                new Data("three", "세번째 프로젝트에 대한 설명"),
                new Data("four", "네번째 프로젝트에 대한 설명"));

        for (int i = 0; i < listTitle.size(); i++) {
            // 각 값이 들어간 data를 adapter에 추가합니다.
            adapter.addItem(listTitle.get(i));;
        }

        // adapter의 값이 변경되었다는 것을 알려줍니다.
        adapter.notifyDataSetChanged();
    }*/

    private void getData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("token", MODE_PRIVATE);
        String token = sharedPreferences.getString("Authorization", "");
        String url = getString(R.string.url) + "/project/projects";
        RequestQueue queue = Volley.newRequestQueue(this.getContext());

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray arr = null;
                        try {
                            arr = response.getJSONArray("ProjectArr");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        try {
                            Data[] data = new Data[arr.length()];
                            for(int i=0; i<arr.length(); i++) {
                                JSONObject jsonObject = arr.getJSONObject(i);
                                data[i] = new Data(jsonObject.getString("name"), jsonObject.getString("description"));
                            }
                            List<Data> listTitle = Arrays.asList(data);
                            for (int i = 0; i < listTitle.size(); i++) {
                                // 각 값이 들어간 data를 adapter에 추가합니다.
                                adapter.addItem(listTitle.get(i));;
                            }

                            // adapter의 값이 변경되었다는 것을 알려줍니다.
                            adapter.notifyDataSetChanged();

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