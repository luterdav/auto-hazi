package com.example.jsfautohazi.mbean;

import com.example.jsfautohazi.data.Car;
import com.example.jsfautohazi.data.CarType;
import com.example.jsfautohazi.data.CarManufacturer;
import com.example.jsfautohazi.repository.CarRepository;
import com.example.jsfautohazi.repository.CarTypeRepository;
import com.example.jsfautohazi.repository.CarManufacturerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.List;

@Transactional
@Named
@ViewScoped
public class CarManagerMBean {
    private List<Car> carList;
    private List<CarType> carTypeList;
    private List<CarManufacturer> carManufacturerList;

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CarTypeRepository carTypeRepository;
    @Autowired
    private CarManufacturerRepository carManufacturerRepository;

    private Car selectedCar;

    @PostConstruct
    private void init() {
        loadAll();
        selectedCar = new Car();
        try {
            carTypeList = carTypeRepository.findAll();
            carManufacturerList = carManufacturerRepository.findAll();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Rendszerhiba", null));
        }
    }

    public void save() {
        try {
            if (selectedCar.getId() == null) {
                carRepository.save(selectedCar);
            } else {
                carRepository.update(selectedCar);
            }
            loadAll();
            selectedCar = new Car();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sikeres adat mentés!", null));
        }catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Rendszerhiba", null));
        }

    }

    public void delete(Long id) {
        try {
            carRepository.delete(id);
            loadAll();

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Rendszerhiba", null));
        }
    }

    public void selectOne(Car car) {
        selectedCar = car;

    }

    private void loadAll() {
        try {
            carList = carRepository.findAll();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Rendszerhiba", null));
        }
    }

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }

    public Car getSelectedCar() {
        return selectedCar;
    }

    public void setSelectedCar(Car selectedCar) {
        this.selectedCar = selectedCar;
    }

    public List<CarType> getCarTypeList() {
        return carTypeList;
    }

    public List<CarManufacturer> getCarManufacturerList() {
        return carManufacturerList;
    }
}
