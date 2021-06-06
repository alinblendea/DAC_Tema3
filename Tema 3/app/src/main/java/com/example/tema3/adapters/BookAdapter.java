package com.example.tema3.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tema3.R;
import com.example.tema3.data.LibraryRepository;
import com.example.tema3.interfaces.OnItemClickListener;
import com.example.tema3.models.BookItemElement;
import com.example.tema3.models.dbEntities.BookItem;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookItemViewHolder> {
    private List<BookItemElement> bookItemElements;
    private OnItemClickListener onItemClickListener;

    public BookAdapter(List<BookItemElement> bookItemElements, OnItemClickListener onItemClickListener) {
        this.bookItemElements = bookItemElements;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public BookItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_cell_book, parent, false);
        BookItemViewHolder bookItemViewHolder = new BookItemViewHolder(view);

        return bookItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookItemViewHolder holder, int position) {
        BookItemElement bookItemElement = bookItemElements.get(position);
        holder.bind(bookItemElement);
    }

    @Override
    public int getItemCount() {
        return bookItemElements.size();
    }

    class BookItemViewHolder extends RecyclerView.ViewHolder {
        private TextView bookTitle;
        private TextView bookAuthor;
        private TextView bookDescription;
        private Button deleteButon;
        private View view;

        public BookItemViewHolder(View view) {
            super(view);
            this.view = view;
            bookTitle = view.findViewById(R.id.book_title);
            bookAuthor = view.findViewById(R.id.book_author);
            bookDescription = view.findViewById(R.id.book_description);
            deleteButon = view.findViewById(R.id.btn_delete);
        }

        public void bind(BookItemElement bookItemElement) {
            bookTitle.setText(bookItemElement.getTitle());
            bookAuthor.setText(bookItemElement.getAuthor());
            bookDescription.setText(bookItemElement.getDescription());
            view.setOnClickListener(v -> onItemClickListener.onBookItemClick(bookItemElement));

            deleteButon.setOnClickListener((v -> {
                LibraryRepository libraryRepository = new LibraryRepository();
                libraryRepository.deleteBook(
                        new BookItem(bookItemElement.getId(),
                                bookItemElement.getTitle(),
                                bookItemElement.getAuthor(),
                                bookItemElement.getDescription()),
                        () -> {

                        });

                int position = bookItemElements.indexOf(bookItemElement);
                bookItemElements.remove(position);
                notifyItemRemoved(position);
            }));
        }
    }
}
