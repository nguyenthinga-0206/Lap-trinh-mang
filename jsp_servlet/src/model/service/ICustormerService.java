package model.service;

import model.bean.Custormer;

import java.util.List;

public interface ICustormerService {
    List<Custormer> findAll();

    int size();

    void save(Custormer custormer);

    Custormer findById(int id);

    void update(int id, Custormer custormer);

    List<Custormer> search(String name);

    void remove(int id);
}
