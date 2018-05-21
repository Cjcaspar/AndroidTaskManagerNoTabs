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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddTaskFragment extends Fragment {

    private AddTaskCallback callback;

    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");




    @BindView(R.id.title_input_edittext)
    protected EditText titleInput;

    @BindView(R.id.detail_input_edittext)
    protected EditText detailInput;

    @BindView(R.id.duedate_month_input)
    protected EditText dueDateMonthInput;

    @BindView(R.id.duedate_day_input)
    protected EditText dueDateDayInput;

    @BindView(R.id.duedate_year_input)
    protected EditText dueDateYearInput;

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
                dueDateDayInput.getText().toString().isEmpty() ||
                dueDateMonthInput.getText().toString().isEmpty() ||
                dueDateYearInput.getText().toString().isEmpty() ||
                priorityInput.getText().toString().isEmpty()) {

            Toast.makeText(getContext(), "All fields are required", Toast.LENGTH_LONG).show();

        } else if (!priorityInput.getText().toString().equalsIgnoreCase("y") && !priorityInput.getText().toString().equalsIgnoreCase("n")) {

            Toast.makeText(getContext(), "Please enter Y/N into the priority field", Toast.LENGTH_LONG).show();

        } else if (Integer.parseInt(dueDateDayInput.getText().toString()) < 1 ||
                Integer.parseInt(dueDateDayInput.getText().toString()) > 31 ||
                Integer.parseInt(dueDateMonthInput.getText().toString()) < 1 ||
                Integer.parseInt(dueDateMonthInput.getText().toString()) > 12) {
            Toast.makeText(getContext(), "Invalid date format. Please try again.", Toast.LENGTH_LONG).show();
        } else {


                calendar.set(Calendar.MONTH, (Integer.parseInt(dueDateMonthInput.getText().toString())-1));
                calendar.set(Calendar.YEAR, Integer.parseInt(dueDateYearInput.getText().toString()));
                calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dueDateDayInput.getText().toString()));

                String sDate = format.format(calendar.getTime());



                Task task = new Task(titleInput.getText().toString(), sDate, detailInput.getText().toString(), false, null, false, null, null);
                if (priorityInput.getText().toString().equalsIgnoreCase("y")) {
                    task.setPriority(true);
                } else {
                    task.setPriority(false);
                }

                callback.addTask(task);
        }
    }

    @OnClick(R.id.back_add_task_button_fragment)
    protected void backButtonClicked() {
        callback.backButtonClicked();
    }



    public static AddTaskFragment newInstance() {

        Bundle args = new Bundle();

        AddTaskFragment fragment = new AddTaskFragment();
        fragment.setArguments(args);
        return fragment;
    }



    public interface AddTaskCallback {
        void addTask(Task task);
        void backButtonClicked();
    }

}
