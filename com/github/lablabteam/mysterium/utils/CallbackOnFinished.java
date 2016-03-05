package com.github.lablabteam.mysterium.utils;

public interface CallbackOnFinished<T> {
    void callbackOnFinished(T obj);
    void callbackOnError(T obj, Exception ex);
}
