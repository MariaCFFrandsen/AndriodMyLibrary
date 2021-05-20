package com.github.listsapp.util.callback;

//this interface to return in an onCompleteListener
//method takes a boolean whether or not a title is already taken

public interface CallBackCheckTitle {
    void checkTitle(boolean check);
}
