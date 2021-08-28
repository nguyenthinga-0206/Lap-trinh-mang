package model.service;

import model.bean.Custormer;
import model.repository.CustormerRepositoryImpl;
import model.repository.ICustormerRepository;

import java.util.List;

public class CustomerServiceImpl implements ICustormerService {
    private ICustormerRepository custormerRepository = new CustormerRepositoryImpl();

    @Override
    public List<Custormer> findAll() {
        return custormerRepository.findAll();
    }

    @Override
    public int size() {
        return custormerRepository.size();
    }

    @Override
    public void save(Custormer custormer) {
        custormerRepository.save(custormer);
    }

    @Override
    public Custormer findById(int id) {
        return custormerRepository.findById(id);
    }

    @Override
    public void update(int id, Custormer custormer) {
        custormerRepository.update(id, custormer);
    }

    @Override
    public List<Custormer> search(String name) {
        return custormerRepository.search(name);
    }

    @Override
    public void remove(int id) {
        custormerRepository.remove(id);
    }
}
