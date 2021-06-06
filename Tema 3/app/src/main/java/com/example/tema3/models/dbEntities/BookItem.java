package com.example.tema3.models.dbEntities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.tema3.models.BookItemElement;

@Entity
public class BookItem {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name="title")
    public String title;
    @ColumnInfo(name="author")
    public String author;
    @ColumnInfo(name="description")
    public String description;

    @Ignore
    public BookItem(int id, String title, String author, String description){
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
    }

    public BookItem(String title, String author, String description){
        this.title = title;
        this.author = author;
        this.description = description;
    }

    public BookItemElement convert(){
        return new BookItemElement(id, title, author, description);
    }
}

