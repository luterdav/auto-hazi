package com.example.jsfautohazi.repository.impl;

import com.example.jsfautohazi.data.CarManufacturer;
import com.example.jsfautohazi.repository.CarManufacturerRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class CarManufacturerRepositoryImpl extends CoreCrudRepositoryImpl<CarManufacturer> implements CarManufacturerRepository {
    @Override
    public Class getManagedClass() {
        return CarManufacturer.class;
    }

    @Override
    public CarManufacturer findByName(String name) throws Exception {
        Query query = em.createQuery("select n from " + getManagedClass().getSimpleName() + " n where n.name=:name");
        query.setParameter("name", name);
        List<CarManufacturer> list = query.getResultList();
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }
}