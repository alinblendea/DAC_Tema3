package com.example.tema3.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import com.example.tema3.R;
import com.example.tema3.adapters.BookAdapter;
import com.example.tema3.data.LibraryRepository;
import com.example.tema3.helpers.UtilsValidators;
import com.example.tema3.interfaces.ActivityFragmentCommunication;
import com.example.tema3.interfaces.OnItemClickListener;
import com.example.tema3.models.BookItemElement;
import com.example.tema3.models.dbEntities.BookItem;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private ActivityFragmentCommunication activityFragmentCommunication;
    private List<BookItemElement> bookItemElemnts = new ArrayList<BookItemElement>();
    private LibraryRepository libraryRepository = new LibraryRepository();

    private BookAdapter bookAdapter = new BookAdapter(bookItemElemnts, new OnItemClickListener() {
        @Override
        public void onBookItemClick(BookItemElement bookItemElement) {
            activityFragmentCommunication.addBookDescriptionFragment(bookItemElement);
        }
    });

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public HomeFragment() {

    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        RecyclerView booksRecyclerView = (RecyclerView) view.findViewById(R.id.rv_books);
        booksRecyclerView.setLayoutManager(linearLayoutManager);
        booksRecyclerView.setAdapter(bookAdapter);

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof ActivityFragmentCommunication) {
            activityFragmentCommunication = (ActivityFragmentCommunication) context;
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.btn_add_update).setOnClickListener(v -> {
            validateFields();
        });
        getAllBooks();
    }

    public void getAllBooks() {
        libraryRepository.getAllBooks(bookResult -> {
            Log.e("Error!", "This is an error.");
            bookItemElemnts.clear();

            for (BookItem book : bookResult) {
                bookItemElemnts.add(book.convert());
            }
        });
    }

    public void validateFields() {
        if (getView() == null) {
            return;
        }

        boolean blankFieldFound = false;

        EditText titleEditText = getView().findViewById(R.id.editable_title);
        EditText authorEditText = getView().findViewById(R.id.editable_author);
        EditText descriptionEditText = getView().findViewById(R.id.editable_description);
        String title = titleEditText.getText().toString();
        String author = authorEditText.getText().toString();
        String description = descriptionEditText.getText().toString();

        if (!UtilsValidators.isValidTitle(title)) {
            titleEditText.setError("Book Title required!");
            blankFieldFound = true;
        } else {
            titleEditText.setError(null);
        }
        if (!UtilsValidators.isValidAuthor(author)) {
            authorEditText.setError("Author required");
            blankFieldFound = true;
        } else {
            authorEditText.setError(null);
        }
        if (!UtilsValidators.isValidDescription(description)) {
            descriptionEditText.setError("Description required");
            blankFieldFound = true;
        } else {
            descriptionEditText.setError(null);
        }
        if (blankFieldFound) {
            return;
        }

        BookItem bookItem = new BookItem(title, author, description);
        addOrUpdateBookItem(bookItem);
    }

    private void addOrUpdateBookItem(BookItem bookItem) {
        boolean found = false;
        int position = -1;

        for (int index = 0; index < bookItemElemnts.size(); index++) {
            if (bookItemElemnts.get(index).getTitle().equals(bookItem.title) &&
                    bookItemElemnts.get(index).getAuthor().equals(bookItem.author)) {
                found = true;
                position = index;
                bookItem.id = bookItemElemnts.get(index).getId();
            }
        }

        if (found) {
            libraryRepository.updateBook(bookItem, () ->
                    Toast.makeText(getContext(), "Updated!", Toast.LENGTH_SHORT).show());
            bookItemElemnts.get(position).setDescription(bookItem.description);
            bookAdapter.notifyItemChanged(position);
        } else {
            libraryRepository.insertBook(bookItem, () ->
                    Toast.makeText(getContext(), "Added!", Toast.LENGTH_SHORT).show());
            bookItemElemnts.add(bookItem.convert());
            bookAdapter.notifyItemChanged(bookItemElemnts.size() - 1);
            getAllBooks();
        }
    }
}