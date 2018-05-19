package com.connercaspar.androidtaskmanagernotabs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements TaskDao, AddTaskFragment.AddTaskCallback, AllTaskFragment.AllTaskCallback{

    private List<Task> taskList;
    private TaskDatabase taskDatabase;
    private AddTaskFragment addTaskFragment;
    private AllTaskFragment allTaskFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        taskDatabase = TaskDatabase.getDatabase(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @OnClick(R.id.add_task_button)
    protected void addTaskButtonClicked() {
        addTaskFragment = AddTaskFragment.newInstance();
        addTaskFragment.attachParent(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, addTaskFragment).commit();

    }

    @OnClick(R.id.completed_task_button)
    protected void completedTaskButtonClicked() {
        allTaskFragment = AllTaskFragment.newInstance();
        allTaskFragment.attachParent(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, allTaskFragment).commit();
    }


    @Override
    public List<Task> getTasks() {
        return taskDatabase.taskDao().getTasks();
    }

    @Override
    public void addTask(Task task) {
        taskDatabase.taskDao().addTask(task);
        getSupportFragmentManager().beginTransaction().remove(addTaskFragment).commit();
        Toast.makeText(this, "Task Added!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateTasks(Task task) {
        taskDatabase.taskDao().updateTasks(task);
    }

    @Override
    public void deleteTask(Task task) {
        taskDatabase.taskDao().deleteTask(task);
    }
}
