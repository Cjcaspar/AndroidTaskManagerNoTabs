package com.connercaspar.androidtaskmanagernotabs;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CompleteTaskFragment extends Fragment implements Adapter.AdapterCallback {

    @BindView(R.id.all_recycler_view)
    protected RecyclerView recyclerViewAll;


    private Adapter adapter;
    private List<Task> taskList;
    private List<Task> completeList;
    private CompleteTaskCallback callback;
    private int previousScreen = 2;


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

//        taskList = callback.getTasks();
//        completeList = getCompleteList(taskList);
        completeList = callback.getCompletedTasks(true);


        setupList();
        return view;
    }

    private ArrayList<Task> getCompleteList(List<Task> taskList) {
        ArrayList<Task> newList = new ArrayList<>();

        for (Task task : taskList) {
            if (task.isComplete()) {
                newList.add(task);
            }
        }

        return newList;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setupList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        adapter = new Adapter(completeList, this);

        recyclerViewAll.setAdapter(adapter);
        recyclerViewAll.setLayoutManager(linearLayoutManager);


        adapter.notifyDataSetChanged();
    }

    public void attachParent(CompleteTaskCallback callback) {
        this.callback = callback;
    }



    public static CompleteTaskFragment newInstance() {

        Bundle args = new Bundle();

        CompleteTaskFragment fragment = new CompleteTaskFragment();
        fragment.setArguments(args);
        return fragment;
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
        completeList = getCompleteList(taskList);
        adapter.updateList(completeList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onTaskClicked(Task task) {
        callback.launchEditTaskFragment(task, previousScreen);
    }

    @OnClick(R.id.back_button)
    public void completeBackButtonClicked() {
        callback.completeBackButtonClicked();
    }

    interface CompleteTaskCallback {
        List<Task> getTasks();
        void launchEditTaskFragment(Task task, int previousScreen);
        void completeBackButtonClicked();
        void deleteTaskClicked(Task task);
        List<Task> getCompletedTasks(boolean isComplete);
    }

}
