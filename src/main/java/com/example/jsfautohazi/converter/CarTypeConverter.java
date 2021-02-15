package com.example.jsfautohazi.converter;

import com.example.jsfautohazi.data.CarType;
import com.example.jsfautohazi.repository.CarTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

@ApplicationScoped
@Named
public class CarTypeConverter implements Converter {

    @Autowired
    private CarTypeRepository carTypeRepository;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if (s == null) {
            return null;
        }
        try {
            return carTypeRepository.findByName(s);
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
        return ((CarType) o).getName();
    }
}
