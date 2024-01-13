package com.example.service;

public interface IService<T> {

    T findById(Long id);
    T save(T t);
}
