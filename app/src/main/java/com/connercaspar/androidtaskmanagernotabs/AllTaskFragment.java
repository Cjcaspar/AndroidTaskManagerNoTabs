package com.connercaspar.androidtaskmanagernotabs;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AllTaskFragment extends Fragment implements Adapter.AdapterCallback{


    @BindView(R.id.all_recycler_view)
    protected RecyclerView recyclerViewAll;


    private Adapter adapter;
    private List<Task> taskList;
    private AllTaskCallback callback;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_task, container, false);
        ButterKnife.bind(this, view);
        taskList = callback.getTasks();


        setupList();
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setupList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        adapter = new Adapter(taskList, this);

        recyclerViewAll.setAdapter(adapter);
        recyclerViewAll.setLayoutManager(linearLayoutManager);


        adapter.notifyDataSetChanged();
    }

    public void attachParent(AllTaskCallback callback) {
        this.callback = callback;
    }



    public static AllTaskFragment newInstance() {

        Bundle args = new Bundle();

        AllTaskFragment fragment = new AllTaskFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onTaskClicked(Task task) {
        callback.launchEditTaskFragment(task);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onTaskLongClicked(final Task task) {
        callback.deleteTaskClicked(task);
        updateList();
        Toast.makeText(getContext(), "Task Deleted!", Toast.LENGTH_LONG).show();
    }

    private void updateList() {
        taskList = callback.getTasks();
        adapter.updateList(taskList);
        adapter.notifyDataSetChanged();
    }

    @OnClick(R.id.back_button)
    public void allBackButtonClicked() {
        callback.allBackButtonClicked();
    }

    public interface AllTaskCallback {
        List<Task> getTasks();
        void launchEditTaskFragment(Task task);
        void allBackButtonClicked();
        void deleteTaskClicked(Task task);
    }

}
