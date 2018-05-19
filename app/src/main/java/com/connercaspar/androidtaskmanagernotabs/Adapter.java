package com.connercaspar.androidtaskmanagernotabs;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.OnClick;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    List<Task> taskList;

    public Adapter(List<Task> taskList) {
        this.taskList = taskList;
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


        public ViewHolder(View itemView) {
            super(itemView);
            taskTitle = itemView.findViewById(R.id.item_title);
            dueDate = itemView.findViewById(R.id.item_due_date);
            //root = dueDate.getRootView();
        }

        public void bind(int position) {
            taskTitle.setText(taskList.get(position).getTitle());
            dueDate.setText(taskList.get(position).getDueDate());
//            if (taskList.get(position).isPriority()) {
//                root.setBackgroundColor(ContextCompat.getColor(root.getContext(), R.color.colorAccent));
//            }
        }

        //TODO: ONCLICK LISTENER FOR ITEMS

        public View.OnClickListener onItemClick(Task task) {
            return new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            };
        }
    }

}
