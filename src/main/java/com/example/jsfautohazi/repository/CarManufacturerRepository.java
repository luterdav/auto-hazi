package com.example.jsfautohazi.repository;

import com.example.jsfautohazi.data.CarManufacturer;

public interface CarManufacturerRepository extends CoreCrudRepository<CarManufacturer> {
    CarManufacturer findByName(String name) throws Exception;
}
