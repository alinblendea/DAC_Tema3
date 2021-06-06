package com.example.tema3.helpers;

import android.text.TextUtils;

public class UtilsValidators {
    public static boolean isValidTitle(String title) {
        return !TextUtils.isEmpty(title);
    }

    public static boolean isValidAuthor(String author) {
        return !TextUtils.isEmpty(author);
    }

    public static boolean isValidDescription(String description) {
        return !TextUtils.isEmpty(description);
    }
}
