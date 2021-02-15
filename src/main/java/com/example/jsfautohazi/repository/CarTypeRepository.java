package com.example.jsfautohazi.repository;

import com.example.jsfautohazi.data.CarType;

public interface CarTypeRepository extends CoreCrudRepository<CarType> {
    CarType findByName(String name) throws Exception;
}
