package com.connercaspar.androidtaskmanagernotabs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddTaskFragment extends Fragment {

    private AddTaskCallback callback;



    @BindView(R.id.title_input_edittext)
    protected EditText titleInput;

    @BindView(R.id.detail_input_edittext)
    protected EditText detailInput;

    @BindView(R.id.duedate_input_edittext)
    protected EditText dueDateInput;

    @BindView(R.id.priority_input_edittext)
    protected EditText priorityInput;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_task, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    public void attachParent(AddTaskCallback callback) {
        this.callback = callback;
    }

    @OnClick(R.id.add_task_button_fragment)
    protected void addTaskClicked() {
        if (titleInput.getText().toString().isEmpty() ||
                detailInput.getText().toString().isEmpty() ||
                dueDateInput.getText().toString().isEmpty() ||
                priorityInput.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "All fields are required", Toast.LENGTH_LONG).show();
        } else {
            Task task = new Task(titleInput.getText().toString(), dueDateInput.getText().toString(), detailInput.getText().toString(), false, " ", false, DateConverter.fromTimestamp((long)Calendar.DATE), null);
            callback.addTask(task);
        }

    }

    public static AddTaskFragment newInstance() {

        Bundle args = new Bundle();

        AddTaskFragment fragment = new AddTaskFragment();
        fragment.setArguments(args);
        return fragment;
    }



    public interface AddTaskCallback {
        void addTask(Task task);
    }

}
