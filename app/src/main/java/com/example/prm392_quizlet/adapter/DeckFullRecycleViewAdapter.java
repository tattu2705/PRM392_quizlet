package com.example.projectprn392flashcard.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectprn392flashcard.R;
import com.example.projectprn392flashcard.activity.deck.DeckDetailActivity;
import com.example.projectprn392flashcard.data.dao.DeckDAO;
import com.example.projectprn392flashcard.data.entity.Deck;

import java.util.List;

public class DeckFullRecycleViewAdapter  extends RecyclerView.Adapter<DeckFullRecycleViewAdapter.ViewHolder>{
    private DeckDAO deckDAO;
    private Context context;
    private List<Deck> list;

    public DeckFullRecycleViewAdapter(Context context, List<Deck> list) {
        this.context = context;
        this.list = list;
        this.deckDAO = new DeckDAO(context);
    }

    public void setList(List<Deck> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public Deck getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_deck_full, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Deck deck = list.get(position);
        holder.bind(deck);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_set_name, tv_term_count, tv_user_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_set_name = itemView.findViewById(R.id.tv_set_name);
            tv_term_count = itemView.findViewById(R.id.tv_term_count);
            tv_user_name = itemView.findViewById(R.id.tv_user_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Deck deck = list.get(position);
                        Intent intent = new Intent(context, DeckDetailActivity.class);
                        intent.putExtra("deck", deck);
                        context.startActivity(intent);
                    }
                }
            });
        }

        public void bind(Deck deck) {
            tv_set_name.setText(deck.getName());
            int cardCount = deckDAO.getNumberCardInDeck(deck.getId());
            tv_term_count.setText(cardCount + " cards");
            String userName = deckDAO.getUserNameOfDeck(deck.getId());
            tv_user_name.setText(userName);
        }
    }
}
