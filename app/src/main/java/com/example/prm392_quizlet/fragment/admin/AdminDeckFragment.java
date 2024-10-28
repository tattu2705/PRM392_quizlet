package com.example.projectprn392flashcard.fragment.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectprn392flashcard.R;
import com.example.projectprn392flashcard.adapter.DeckRecycleViewAdapter;
import com.example.projectprn392flashcard.data.dao.DeckDAO;
import com.example.projectprn392flashcard.data.entity.Deck;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class AdminDeckFragment extends Fragment {

    private RecyclerView recyclerView;
    private DeckRecycleViewAdapter deckAdapter;
    private List<Deck> deckList;
    private DeckDAO deckDAO;

    public AdminDeckFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deckDAO = new DeckDAO(requireContext()); // Khởi tạo DeckDAO (thay thế bằng cách truy xuất dữ liệu thực tế của bạn)
        deckList = new ArrayList<>(); // Khởi tạo danh sách ban đầu
        // Lấy dữ liệu ban đầu từ cơ sở dữ liệu hoặc API và thêm vào deckList
        deckList.addAll(deckDAO.getAll()); // Ví dụ: lấy tất cả các bộ bài từ DAO
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_deck, container, false);

        TextInputLayout searchDeckLayout = view.findViewById(R.id.search_deck_layout);
        TextInputEditText searchEditText = view.findViewById(R.id.search_deck);
        recyclerView = view.findViewById(R.id.search_results);

        // Thiết lập RecyclerView và Adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        deckAdapter = new DeckRecycleViewAdapter(requireContext(), deckList);
        recyclerView.setAdapter(deckAdapter);

        // Thiết lập sự kiện cho nút tìm kiếm
        view.findViewById(R.id.search_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = searchEditText.getText().toString().trim();
                performSearch(query);
            }
        });

        return view;
    }

    private void performSearch(String query) {
        List<Deck> filteredList = new ArrayList<>();

        // Lọc danh sách bộ bài dựa trên query
        for (Deck deck : deckList) {
            if (deck.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(deck);
            }
        }

        // Cập nhật RecyclerView với danh sách đã lọc
        deckAdapter.setList(filteredList);
        deckAdapter.notifyDataSetChanged();

        if (filteredList.isEmpty()) {
            Toast.makeText(requireContext(), "Không tìm thấy bộ bài cho '" + query + "'", Toast.LENGTH_SHORT).show();
        }
    }
}
