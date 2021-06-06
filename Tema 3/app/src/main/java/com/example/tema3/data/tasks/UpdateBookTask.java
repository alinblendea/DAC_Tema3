package com.example.tema3.data.tasks;

import android.os.AsyncTask;

import com.example.tema3.data.LibraryDatabase;
import com.example.tema3.data.LibraryRepository;
import com.example.tema3.models.dbEntities.BookItem;

public class UpdateBookTask extends AsyncTask<BookItem, Void, Void> {
    private LibraryDatabase libraryDatabase;
    private LibraryRepository.OnSuccessListener listener;

    public UpdateBookTask(LibraryDatabase libraryDatabase, LibraryRepository.OnSuccessListener listener) {
        this.libraryDatabase = libraryDatabase;
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(BookItem... bookItems) {
        libraryDatabase.libraryDAO().updateBook(bookItems[0]);

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.onSuccess();
    }
}

