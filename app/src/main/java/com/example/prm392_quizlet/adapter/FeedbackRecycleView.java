package com.example.projectprn392flashcard.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectprn392flashcard.R;
import com.example.projectprn392flashcard.activity.feedback.FeedbackDetailActivity;
import com.example.projectprn392flashcard.data.dao.FeedbackDAO;
import com.example.projectprn392flashcard.data.entity.Feedback;

import java.util.List;

public class FeedbackRecycleView extends RecyclerView.Adapter<FeedbackRecycleView.ViewHolder>{
    List<Feedback> list;
    Context context;

    FeedbackDAO feedbackDAO;

    public FeedbackRecycleView(Context context, List<Feedback> list) {
        this.list = list;
        this.context = context;
        feedbackDAO= new FeedbackDAO(context);
    }

    public void setList(List<Feedback> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= View.inflate(context, R.layout.item_feedback, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Feedback feedback= list.get(position);
        holder.title.setText(feedback.getTitle());
        holder.username.setText(feedbackDAO.getUsernameOfFeedback(feedback.getUser_id()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title, username;

        public ViewHolder(View itemView) {
            super(itemView);
            title= itemView.findViewById(R.id.title);
            username= itemView.findViewById(R.id.userName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(context, FeedbackDetailActivity.class);
                    intent.putExtra("feedback", list.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
