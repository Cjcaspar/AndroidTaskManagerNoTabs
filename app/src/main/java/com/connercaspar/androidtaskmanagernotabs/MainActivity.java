package com.connercaspar.androidtaskmanagernotabs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements TaskDao, AddTaskFragment.AddTaskCallback, AllTaskFragment.AllTaskCallback, EditTaskFragment.EditCallback, CompleteTaskFragment.CompleteTaskCallback, IncompleteTaskFragment.IncompleteTaskCallback{

    private TaskDatabase taskDatabase;
    private AddTaskFragment addTaskFragment;
    private AllTaskFragment allTaskFragment;
    private EditTaskFragment editTaskFragment;
    private CompleteTaskFragment completeTaskFragment;
    private IncompleteTaskFragment incompleteTaskFragment;

    public static final String TASK_KEY = "Task";


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

    @OnClick(R.id.all_task_button)
    protected void allTaskButtonClicked() {
        allTaskFragment = AllTaskFragment.newInstance();
        allTaskFragment.attachParent(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, allTaskFragment).commit();
    }

    @OnClick(R.id.completed_task_button)
    protected void completedTaskButtonClicked() {
        completeTaskFragment = CompleteTaskFragment.newInstance();
        completeTaskFragment.attachParent(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, completeTaskFragment).commit();
    }

    @OnClick(R.id.incomplete_task_button)
    protected void incompleteTaskButtonClicked() {
        incompleteTaskFragment = IncompleteTaskFragment.newInstance();
        incompleteTaskFragment.attachParent(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, incompleteTaskFragment).commit();
    }




    @Override
    public List<Task> getTasks() {
        return taskDatabase.taskDao().getTasks();
    }

    @Override
    public void launchEditTaskFragment(Task task) {
        editTaskFragment = EditTaskFragment.newInstance();
        Bundle bundle = new Bundle();
        bundle.putParcelable(TASK_KEY, task);
        editTaskFragment.setArguments(bundle);
        editTaskFragment.attachParent(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, editTaskFragment).commit();

    }

    @Override
    public void incompleteBackButtonClicked() {
        getSupportFragmentManager().beginTransaction().remove(incompleteTaskFragment).commit();
    }

    @Override
    public void completeBackButtonClicked() {
        getSupportFragmentManager().beginTransaction().remove(completeTaskFragment).commit();
    }

    @Override
    public void allBackButtonClicked() {
        getSupportFragmentManager().beginTransaction().remove(allTaskFragment).commit();
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

    @Override
    public void saveEditTask(Task task) {
        updateTasks(task);
        getSupportFragmentManager().beginTransaction().remove(editTaskFragment).commit();
        Toast.makeText(this, "Changes have been saved!!", Toast.LENGTH_SHORT).show();
    }

    public void editBackButton() {
        getSupportFragmentManager().beginTransaction().remove(editTaskFragment).commit();
    }
}
