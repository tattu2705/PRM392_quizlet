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

public class DeckRecycleViewAdapter extends RecyclerView.Adapter<DeckRecycleViewAdapter.ViewHolder> {
    private DeckDAO deckDAO;

    private Context context;

    private List<Deck> list;
    public  Deck getItem(int position){
        return list.get(position);
    }

    public DeckRecycleViewAdapter(Context context, List<Deck> list) {
        this.context = context;
        this.list = list;
        deckDAO= new DeckDAO(context);
    }


    public void setList(List<Deck> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_deck, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Deck deck= list.get(position);
        holder.tv_set_name.setText(deck.getName());
        int cardCount= deckDAO.getNumberCardInDeck(deck.getId());
        holder.tv_term_count.setText(cardCount + " cards");
        holder.tv_user_name.setText(deckDAO.getUserNameOfDeck(deck.getId()));
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
                    Deck deck = list.get(getAdapterPosition());
                    Intent intent = new Intent(context, DeckDetailActivity.class);
                    intent.putExtra("deck", deck);
                    context.startActivity(intent);
                }
            });
        }
    }

}
