package com.example.jsfautohazi.repository.impl;

import com.example.jsfautohazi.data.CarType;
import com.example.jsfautohazi.repository.CarTypeRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class CarTypeRepositoryImpl extends CoreCrudRepositoryImpl<CarType> implements CarTypeRepository {
    @Override
    public Class getManagedClass() {
        return CarType.class;
    }

    @Override
    public CarType findByName(String name) throws Exception {
        Query query = em.createQuery("select n from " + getManagedClass().getSimpleName() + " n where n.name=:name");
        query.setParameter("name", name);
        List<CarType> list = query.getResultList();
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

}
