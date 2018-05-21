package com.connercaspar.androidtaskmanagernotabs;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.connercaspar.androidtaskmanagernotabs.MainActivity.SCREEN;
import static com.connercaspar.androidtaskmanagernotabs.MainActivity.TASK_KEY;

public class EditTaskFragment extends Fragment{

    @BindView(R.id.title_textview)
    public TextView titleTextView;

    @BindView(R.id.detail_textview)
    public TextView detailTextView;

    @BindView(R.id.due_date_textview)
    public TextView dueDateTextView;

    @BindView(R.id.priority_textview)
    public TextView priorityTextView;

    @BindView(R.id.date_completed_textview)
    public TextView dateCompletedTextView;

    @BindView(R.id.date_created_textview)
    public TextView dateCreatedTextView;

    @BindView(R.id.title_edittext)
    public EditText titleEditText;

    @BindView(R.id.detail_edittext)
    public EditText detailEditText;

    @BindView(R.id.due_date_day_edittext)
    public EditText dueDateDayEditText;

    @BindView(R.id.priority_edittext)
    public EditText priorityEditText;

    @BindView(R.id.edit_task_button)
    protected Button editTaskButton;

    @BindView(R.id.mark_task_complete_button)
    protected Button markCompleteButton;

    @BindView(R.id.cancel_changes_button)
    protected Button cancelChangesButton;

    @BindView(R.id.save_changes_button)
    protected Button saveChangesButton;

    @BindView(R.id.edit_back_button)
    protected Button editBackButton;

    @BindView(R.id.due_date_month_edittext)
    protected EditText dueDateMonthEditText;

    @BindView(R.id.due_date_year_edittext)
    protected EditText dueDateYearEditText;

    private Task task;
    private EditCallback callback;
    private int previousScreen;

    SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
    Calendar calendar = Calendar.getInstance();
    Date now = calendar.getTime();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        task = bundle.getParcelable(TASK_KEY);
        previousScreen = bundle.getInt(SCREEN);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_task, container, false);
        ButterKnife.bind(this, view);
        setupTextView();

        return view;
    }

    public static EditTaskFragment newInstance() {

        Bundle args = new Bundle();

        EditTaskFragment fragment = new EditTaskFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void attachParent(EditCallback callback) {
        this.callback = callback;
    }

    @OnClick(R.id.edit_task_button)
    protected void editTaskClicked() {
        titleTextView.setVisibility(View.INVISIBLE);
        detailTextView.setVisibility(View.INVISIBLE);
        dueDateTextView.setVisibility(View.INVISIBLE);
        priorityTextView.setVisibility(View.INVISIBLE);
        editTaskButton.setVisibility(View.INVISIBLE);
        markCompleteButton.setVisibility(View.INVISIBLE);
        editBackButton.setVisibility(View.INVISIBLE);
        dateCompletedTextView.setVisibility(View.INVISIBLE);
        dateCreatedTextView.setVisibility(View.INVISIBLE);

        titleEditText.setVisibility(View.VISIBLE);
        detailEditText.setVisibility(View.VISIBLE);
        dueDateDayEditText.setVisibility(View.VISIBLE);
        dueDateMonthEditText.setVisibility(View.VISIBLE);
        dueDateYearEditText.setVisibility(View.VISIBLE);
        priorityEditText.setVisibility(View.VISIBLE);
        saveChangesButton.setVisibility(View.VISIBLE);
        cancelChangesButton.setVisibility(View.VISIBLE);

    }

    @OnClick(R.id.mark_task_complete_button)
    protected void markTaskCompleteClicked() {
        task.setComplete(true);
        task.setDateCompleted(now);
        callback.saveEditTask(task);
    }

    @OnClick(R.id.save_changes_button)
    protected void saveChangesClicked() {

        if (titleEditText.getText().toString().isEmpty() ||
                detailEditText.getText().toString().isEmpty() ||
                dueDateDayEditText.getText().toString().isEmpty() ||
                dueDateMonthEditText.getText().toString().isEmpty() ||
                dueDateYearEditText.getText().toString().isEmpty() ||
                priorityEditText.getText().toString().isEmpty()) {

            Toast.makeText(getContext(), "All fields are required", Toast.LENGTH_LONG).show();

        } else if (!priorityEditText.getText().toString().equalsIgnoreCase("y") && !priorityEditText.getText().toString().equalsIgnoreCase("n")) {

            Toast.makeText(getContext(), "Please enter Y/N into the priority field", Toast.LENGTH_LONG).show();

        } else {

            try {
                calendar.set(Calendar.MONTH, (Integer.parseInt(dueDateMonthEditText.getText().toString())-1));
                calendar.set(Calendar.YEAR, Integer.parseInt(dueDateYearEditText.getText().toString()));
                calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dueDateDayEditText.getText().toString()));

                String sDate = format.format(calendar.getTime());

                task.setDueDate(sDate);


                if (priorityEditText.getText().toString().equalsIgnoreCase("y")) {
                    task.setPriority(true);
                } else {
                    task.setPriority(false);
                }

                task.setTitle(titleEditText.getText().toString());
                task.setDetails(detailEditText.getText().toString());

                callback.saveEditTask(task);

            } catch (Exception e) {
                Toast.makeText(getContext(), "Invalid date format. Please try again.", Toast.LENGTH_LONG).show();
            }

        }
    }


    @OnClick(R.id.cancel_changes_button)
    protected void cancelChangesButtonClicked() {
        titleTextView.setVisibility(View.VISIBLE);
        detailTextView.setVisibility(View.VISIBLE);
        dueDateTextView.setVisibility(View.VISIBLE);
        priorityTextView.setVisibility(View.VISIBLE);
        editTaskButton.setVisibility(View.VISIBLE);
        editBackButton.setVisibility(View.VISIBLE);
        dateCreatedTextView.setVisibility(View.VISIBLE);

        titleEditText.setVisibility(View.INVISIBLE);
        detailEditText.setVisibility(View.INVISIBLE);
        dueDateDayEditText.setVisibility(View.INVISIBLE);
        dueDateYearEditText.setVisibility(View.INVISIBLE);
        dueDateMonthEditText.setVisibility(View.INVISIBLE);
        priorityEditText.setVisibility(View.INVISIBLE);
        saveChangesButton.setVisibility(View.INVISIBLE);
        cancelChangesButton.setVisibility(View.INVISIBLE);

        if (!task.isComplete()) {
            markCompleteButton.setVisibility(View.VISIBLE);
        } else {
            dateCompletedTextView.setVisibility(View.VISIBLE);
        }
    }

    private void setupTextView() {
        titleTextView.setText(getResources().getString(R.string.title_view, task.getTitle()));
        titleEditText.setText(task.getTitle());
        detailTextView.setText(getResources().getString(R.string.details_view, task.getDetails()));
        detailEditText.setText(task.getDetails());
        dueDateTextView.setText(getResources().getString(R.string.due_date_view, task.getDueDate()));

        String sDateCreated = format.format(task.getDateCreated());
        dateCreatedTextView.setText(getResources().getString(R.string.created_date_view, sDateCreated));


        if (task.isPriority()) {
            priorityTextView.setText(getResources().getString(R.string.priority_view, "Yes"));
        } else {
            priorityTextView.setText(getResources().getString(R.string.priority_view, "No"));
        }



        if (task.isComplete()) {
            markCompleteButton.setVisibility(View.INVISIBLE);
            String sDateCompleted = format.format(task.getDateCompleted());
            priorityTextView.setText(getResources().getString(R.string.complete_date_view, sDateCompleted));
        } else  {
            dateCompletedTextView.setVisibility(View.INVISIBLE);
        }
    }

    @OnClick(R.id.edit_back_button)
    protected void backButtonClicked() {
            callback.editBackButton(previousScreen);
    }


    interface EditCallback {
        void saveEditTask(Task task);
        void editBackButton(int previousScreen);
    }


}
