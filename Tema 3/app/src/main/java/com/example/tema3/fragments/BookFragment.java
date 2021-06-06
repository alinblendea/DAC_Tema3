package com.example.tema3.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.tema3.R;
import com.example.tema3.models.BookItemElement;

public class BookFragment extends Fragment {

    private BookItemElement bookItemElement;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public BookFragment() {

    }

    public BookFragment(BookItemElement bookItemElement) {
        this.bookItemElement = bookItemElement;
    }

    public static BookFragment newInstance(String param1, String param2, BookItemElement bookItemElement) {
        BookFragment fragment = new BookFragment(bookItemElement);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_book, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView bookTitle = view.findViewById(R.id.book_title_book_fragment);
        TextView bookAuthor = view.findViewById(R.id.book_author_book_fragment);
        TextView bookDescription = view.findViewById(R.id.book_description_book_fragment);
        bookTitle.setText(bookItemElement.getTitle());
        bookAuthor.setText(bookItemElement.getAuthor());
        bookDescription.setText(bookItemElement.getDescription());
    }
}
