package com.example.jsfautohazi.data;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "car")
public class Car extends AbstractEntity {

    @NotNull(message = "{carType.not.null}")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private CarType carType;

    @NotNull(message = "{carManufacturer.not.null}")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacturer_id")
    private CarManufacturer carManufacturer;

    @NotNull(message = "{numberOfDoors.not.null}")
    @Column(name = "number_of_doors")
    private Integer numberOfDoors;

    @NotNull(message = "{yearOfManufacture.not.null}")
    @Column(name = "year_of_manufacture")
    private Integer yearOfManufacture;

    public Car() {
    }

    public Car(Long id, Date createdDate, Date lastModifiedDate, CarType carType, CarManufacturer carManufacturer, Integer numberOfDoors, Integer yearOfManufacture) {
        super(id, createdDate, lastModifiedDate);
        this.carType = carType;
        this.carManufacturer = carManufacturer;
        this.numberOfDoors = numberOfDoors;
        this.yearOfManufacture = yearOfManufacture;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public CarManufacturer getCarManufacturer() {
        return carManufacturer;
    }

    public void setCarManufacturer(CarManufacturer carManufacturer) {
        this.carManufacturer = carManufacturer;
    }

    public Integer getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(Integer numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    public Integer getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(Integer yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }
}
