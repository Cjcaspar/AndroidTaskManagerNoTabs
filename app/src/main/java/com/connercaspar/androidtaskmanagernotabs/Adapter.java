package com.connercaspar.androidtaskmanagernotabs;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.OnClick;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private final AdapterCallback callback;
    List<Task> taskList;

    public Adapter(List<Task> taskList, AdapterCallback callback) {
        this.taskList = taskList;
        this.callback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.bind(position);
        holder.layout.setOnClickListener(holder.onItemClicked(taskList.get(position)));

    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public void updateList(List<Task> taskList) {
        this.taskList = taskList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView taskTitle;
        private TextView dueDate;
        private ConstraintLayout layout;
        private FrameLayout priorityBackground;


        public ViewHolder(View itemView) {
            super(itemView);
            taskTitle = itemView.findViewById(R.id.item_title);
            dueDate = itemView.findViewById(R.id.item_due_date);
            layout = itemView.findViewById(R.id.item_row_layout);
            priorityBackground = itemView.findViewById(R.id.item_frame);
        }

        public void bind(int position) {
            taskTitle.setText(taskList.get(position).getTitle());
            dueDate.setText(taskList.get(position).getDueDate());
            if (taskList.get(position).isPriority()) {
                priorityBackground.setBackgroundColor(App.context().getResources().getColor(R.color.red));
            }
        }

        //TODO: ONCLICK LISTENER FOR ITEMS

        public View.OnClickListener onItemClicked(final Task task) {
            return new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callback.onTaskClicked(task);
                }
            };
        }

    }

    interface AdapterCallback {
        void onTaskClicked(Task task);
    }

}
