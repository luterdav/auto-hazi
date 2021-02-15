package com.example.jsfautohazi.mbean;

import com.example.jsfautohazi.data.CarType;
import com.example.jsfautohazi.repository.CarTypeRepository;
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
public class CarTypeManagerMBean {

    private List<CarType> carTypeList;

    @Autowired
    private CarTypeRepository carTypeRepository;

    private CarType selectedCarType;

    @PostConstruct
    private void init() {
        loadAll();
        selectedCarType = new CarType();
    }

    public void save() {
        try {
            CarType oldCarType = carTypeRepository.findByName(selectedCarType.getName());
            if(oldCarType!=null && selectedCarType.getId() == null){
                throw new RuntimeException("Van már ilyen elem");
            }else if(oldCarType!=null  && selectedCarType.getId() != null && !oldCarType.getId().equals(selectedCarType.getId())){
                throw new RuntimeException("Van már ilyen elem");
            }
            if (selectedCarType.getId() == null) {

                carTypeRepository.save(selectedCarType);

            } else {
                carTypeRepository.update(selectedCarType);
            }
            loadAll();
            selectedCarType = new CarType();
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
            carTypeRepository.delete(id);
            loadAll();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Rendszerhiba", null));
        }

    }

    public void selectOne(CarType carType) {
        selectedCarType = carType;

    }

    private void loadAll() {
        try {
            carTypeList = carTypeRepository.findAll();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Rendszerhiba", null));
        }
    }

    public List<CarType> getCarTypeList() {
        return carTypeList;
    }

    public void setCarTypeList(List<CarType> carTypeList) {
        this.carTypeList = carTypeList;
    }

    public CarType getSelectedCarType() {
        return selectedCarType;
    }

    public void setSelectedCarType(CarType selectedCarType) {
        this.selectedCarType = selectedCarType;
    }
}
