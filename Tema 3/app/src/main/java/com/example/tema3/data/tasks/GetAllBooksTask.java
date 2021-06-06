package com.example.tema3.data.tasks;

import android.os.AsyncTask;
import com.example.tema3.data.LibraryDatabase;
import com.example.tema3.data.LibraryRepository;
import com.example.tema3.models.dbEntities.BookItem;
import java.util.ArrayList;
import java.util.List;

public class GetAllBooksTask extends AsyncTask<Void, Void, List<BookItem>> {
    private LibraryDatabase libraryDatabase;
    private LibraryRepository.OnGetAllBooksListener listener;

    public GetAllBooksTask(LibraryDatabase libraryDatabase, LibraryRepository.OnGetAllBooksListener listener) {
        this.libraryDatabase = libraryDatabase;
        this.listener = listener;
    }

    @Override
    protected ArrayList<BookItem> doInBackground(Void... voids) {
        return (ArrayList) libraryDatabase.libraryDAO().getAllBooks();
    }

    @Override
    protected void onPostExecute(List<BookItem> items) {
        super.onPostExecute(items);
        listener.onSuccess(items);
    }
}

