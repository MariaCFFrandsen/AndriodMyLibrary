package com.github.listsapp.util;

public class Book {

        private String mName;
        private int mIconId;

        Book(String name, int iconId) {
            mName = name;
            mIconId = iconId;
        }

        public String getName() {
            return mName;
        }

        public int getIconId() {
            return mIconId;
        }

}
