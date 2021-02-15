package com.example.jsfautohazi.mbean;

import com.example.jsfautohazi.data.CarManufacturer;
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
public class CarManufacturerManagerMBean {

    private List<CarManufacturer> carManufacturerList;

    @Autowired
    private CarManufacturerRepository carManufacturerRepository;

    private CarManufacturer selectedCarManufacturer;

    @PostConstruct
    private void init() {
        loadAll();
        selectedCarManufacturer = new CarManufacturer();
    }

    public void save() {
        try {
            CarManufacturer oldCarManufacturer = carManufacturerRepository.findByName(selectedCarManufacturer.getName());
            if(oldCarManufacturer!=null && selectedCarManufacturer.getId() == null){
                throw new RuntimeException("Van már ilyen elem");
            }else if(oldCarManufacturer!=null  && selectedCarManufacturer.getId() != null && !oldCarManufacturer.getId().equals(selectedCarManufacturer.getId())){
                throw new RuntimeException("Van már ilyen elem");
            }
            if (selectedCarManufacturer.getId() == null) {

                carManufacturerRepository.save(selectedCarManufacturer);

            } else {
                carManufacturerRepository.update(selectedCarManufacturer);
            }
            loadAll();
            selectedCarManufacturer = new CarManufacturer();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sikeres adat mentés!", null));
        }catch (RuntimeException e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validációs hiba", null));

        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Rendszerhiba", null));

        }
    }

    public void delete(Long id) {
        try {
            carManufacturerRepository.delete(id);
            loadAll();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Rendszerhiba", null));
        }

    }

    public void selectOne(CarManufacturer carManufacturer) {
        selectedCarManufacturer = carManufacturer;

    }

    private void loadAll() {
        try {
            carManufacturerList = carManufacturerRepository.findAll();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Rendszerhiba", null));
        }
    }

    public List<CarManufacturer> getCarManufacturerList() {
        return carManufacturerList;
    }

    public void setCarManufacturerList(List<CarManufacturer> carManufacturerList) {
        this.carManufacturerList = carManufacturerList;
    }

    public CarManufacturer getSelectedCarManufacturer() {
        return selectedCarManufacturer;
    }

    public void setSelectedCarManufacturer(CarManufacturer selectedCarManufacturer) {
        this.selectedCarManufacturer = selectedCarManufacturer;
    }
}
