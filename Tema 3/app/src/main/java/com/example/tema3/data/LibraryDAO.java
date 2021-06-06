package com.example.tema3.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.tema3.models.dbEntities.BookItem;
import java.util.List;

@Dao
public interface LibraryDAO {
    @Query("SELECT * FROM bookItem")
    List<BookItem> getAllBooks();

    @Insert
    void insertBook(BookItem bookItem);

    @Delete
    void deleteBook(BookItem bookItem);

    @Update
    void updateBook(BookItem bookItem);
}
