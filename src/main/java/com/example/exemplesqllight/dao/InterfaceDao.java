package com.example.exemplesqllight.dao;

import java.util.ArrayList;

public interface InterfaceDao<T> {

    void insert(T object);
    int update(T object);
    int delete(int id);
    T get(int id);
    ArrayList<T> getAll();

}
