package com.example.jsfautohazi.converter;

import com.example.jsfautohazi.data.CarManufacturer;
import com.example.jsfautohazi.repository.CarManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

@ApplicationScoped
@Named
public class CarManufacturerConverter implements Converter {

    @Autowired
    private CarManufacturerRepository carManufacturerRepository;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if (s == null) {
            return null;
        }
        try {
            return carManufacturerRepository.findByName(s);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {

        if (o == null) {
            return "";
        }
        return ((CarManufacturer) o).getName();
    }
}
