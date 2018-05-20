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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IncompleteTaskFragment extends Fragment implements Adapter.AdapterCallback{

    @BindView(R.id.all_recycler_view)
    protected RecyclerView recyclerViewAll;


    private Adapter adapter;
    private List<Task> taskList;
    private ArrayList<Task> incompleteList;
    private IncompleteTaskCallback callback;


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
        incompleteList = getIncompleteList(taskList);


        setupList();
        return view;
    }

    private ArrayList<Task> getIncompleteList(List<Task> taskList) {
        ArrayList<Task> newList = new ArrayList<>();

        for (Task task : taskList) {
            if (!task.isComplete()) {
                newList.add(task);
            }
        }

        return newList;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setupList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        adapter = new Adapter(incompleteList, this);

        recyclerViewAll.setAdapter(adapter);
        recyclerViewAll.setLayoutManager(linearLayoutManager);


        adapter.notifyDataSetChanged();
    }

    public void attachParent(IncompleteTaskCallback callback) {
        this.callback = callback;
    }



    public static IncompleteTaskFragment newInstance() {

        Bundle args = new Bundle();

        IncompleteTaskFragment fragment = new IncompleteTaskFragment();
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
        incompleteList = getIncompleteList(taskList);
        adapter.updateList(incompleteList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onTaskClicked(Task task) {
        callback.launchEditTaskFragment(task);
    }

    @OnClick(R.id.back_button)
    public void incompleteBackButtonClicked() {
        callback.incompleteBackButtonClicked();
    }

    interface IncompleteTaskCallback {
        List<Task> getTasks();
        void launchEditTaskFragment(Task task);
        void incompleteBackButtonClicked();
        void deleteTaskClicked(Task task);
    }
}
