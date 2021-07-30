package com.example.mainactivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.Button;

import java.util.Arrays;
import java.util.List;

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

    private void getData() {
        // 임의의 데이터입니다.
        List<Data> listTitle = Arrays.asList(
                new Data("one", "1"), 
                new Data("two", "2"), 
                new Data("three", "3"),
                new Data("four", "4"));

        for (int i = 0; i < listTitle.size(); i++) {
            // 각 값이 들어간 data를 adapter에 추가합니다.
            adapter.addItem(listTitle.get(i));;
        }

        // adapter의 값이 변경되었다는 것을 알려줍니다.
        adapter.notifyDataSetChanged();
    }
}