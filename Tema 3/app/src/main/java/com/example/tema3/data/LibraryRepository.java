package com.example.tema3.data;

import com.example.tema3.ApplicationController;
import com.example.tema3.data.tasks.DeleteBookTask;
import com.example.tema3.data.tasks.GetAllBooksTask;
import com.example.tema3.data.tasks.InsertBookTask;
import com.example.tema3.data.tasks.UpdateBookTask;
import com.example.tema3.models.dbEntities.BookItem;
import java.util.List;

public class LibraryRepository {
    private LibraryDatabase libraryDatabase;

    public LibraryRepository() {
        libraryDatabase = ApplicationController.getLibraryDataBase();
    }

    public interface OnSuccessListener {
        void onSuccess();
    }

    public interface OnGetAllBooksListener {
        void onSuccess(List<BookItem> items);
    }

    public void insertBook(BookItem bookItem, OnSuccessListener listener) {
        new InsertBookTask(libraryDatabase, listener).execute(bookItem);
    }

    public void getAllBooks(OnGetAllBooksListener listener) {
        new GetAllBooksTask(libraryDatabase, listener).execute();
    }

    public void updateBook(BookItem bookItem, OnSuccessListener listener) {
        new UpdateBookTask(libraryDatabase, listener).execute(bookItem);
    }

    public void deleteBook(BookItem bookItem, OnSuccessListener listener) {
        new DeleteBookTask(libraryDatabase, listener).execute(bookItem);
    }
}

