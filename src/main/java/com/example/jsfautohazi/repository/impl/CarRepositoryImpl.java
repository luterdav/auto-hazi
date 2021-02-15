package com.example.jsfautohazi.repository.impl;

import com.example.jsfautohazi.data.Car;
import com.example.jsfautohazi.repository.CarRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CarRepositoryImpl extends CoreCrudRepositoryImpl<Car> implements CarRepository {
    @Override
    public Class getManagedClass() {
        return Car.class;
    }

}