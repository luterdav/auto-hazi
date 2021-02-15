package com.example.jsfautohazi.data;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "car_type")
public class CarType extends AbstractEntity {

    @NotEmpty (message = "{name.not.null}")
    @Column(unique = true)
    private String name;

    public CarType() {
    }

    public CarType(Long id, Date createdDate, Date lastModifiedDate, String name) {
        super(id, createdDate, lastModifiedDate);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        return name.equals(((CarType) obj).getName());
    }
}
