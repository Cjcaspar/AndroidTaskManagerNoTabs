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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    @BindView(R.id.title_edittext)
    public EditText titleEditText;

    @BindView(R.id.detail_edittext)
    public EditText detailEditText;

    @BindView(R.id.due_date_edittext)
    public EditText dueDateEditText;

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

    private Task task;
    private EditCallback callback;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        task = bundle.getParcelable(TASK_KEY);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_task, container, false);
        ButterKnife.bind(this, view);
        titleTextView.setText(getResources().getString(R.string.title_view, task.getTitle()));
        titleEditText.setText(task.getTitle());
        detailTextView.setText(getResources().getString(R.string.details_view, task.getDetails()));
        detailEditText.setText(task.getDetails());
        dueDateEditText.setText(getResources().getString(R.string.due_date_view, task.getDueDate()));
        dueDateTextView.setText(task.getDueDate());
        if (task.isComplete()) {
            markCompleteButton.setVisibility(View.INVISIBLE);
        }
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

        titleEditText.setVisibility(View.VISIBLE);
        detailEditText.setVisibility(View.VISIBLE);
        dueDateEditText.setVisibility(View.VISIBLE);
        priorityEditText.setVisibility(View.VISIBLE);
        saveChangesButton.setVisibility(View.VISIBLE);
        cancelChangesButton.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.mark_task_complete_button)
    protected void markTaskCompleteClicked() {
        task.setComplete(true);
        callback.saveEditTask(task);
    }

    @OnClick(R.id.save_changes_button)
    protected void saveChangesClicked() {
        task.setTitle(titleEditText.getText().toString());
        task.setDetails(detailEditText.getText().toString());
        task.setDueDate(dueDateEditText.getText().toString());
        //task.setPriority();
        callback.saveEditTask(task);
    }

    @OnClick(R.id.cancel_changes_button)
    protected void cancelChangesButtonClicked() {
        titleTextView.setVisibility(View.VISIBLE);
        detailTextView.setVisibility(View.VISIBLE);
        dueDateTextView.setVisibility(View.VISIBLE);
        priorityTextView.setVisibility(View.VISIBLE);
        editTaskButton.setVisibility(View.VISIBLE);
        editBackButton.setVisibility(View.VISIBLE);

        titleEditText.setVisibility(View.INVISIBLE);
        detailEditText.setVisibility(View.INVISIBLE);
        dueDateEditText.setVisibility(View.INVISIBLE);
        priorityEditText.setVisibility(View.INVISIBLE);
        saveChangesButton.setVisibility(View.INVISIBLE);
        cancelChangesButton.setVisibility(View.INVISIBLE);

        if (!task.isComplete()) {
            markCompleteButton.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.edit_back_button)
    protected void backButtonClicked() {
            callback.editBackButton();
    }


    interface EditCallback {
        void saveEditTask(Task task);
        void editBackButton();
    }


}
