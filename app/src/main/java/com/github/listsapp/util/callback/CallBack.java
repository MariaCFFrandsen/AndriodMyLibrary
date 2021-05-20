package com.github.listsapp.util.callback;

//this interface to return in an onCompleteListener
//method takes a string because the result is used in a toast to inform user of success

public interface CallBack {
    void makeToast(String message);
}
