package com.cursos.rocket.api.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.lang.NonNull;

public class Utils {

    public static void copyNonNullProperties(@NonNull Object source, @NonNull Object target){
        BeanUtils.copyProperties(source, target, getNullPropertiesNames(source));
    }
    
    @NonNull
    public static String[] getNullPropertiesNames(@NonNull Object source){
        final BeanWrapper src = new BeanWrapperImpl(source);

        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();

        for(PropertyDescriptor pd: pds){
            var propertyName = pd.getName();
            if(propertyName != null){
                Object srcValue = src.getPropertyValue(propertyName);
                if(srcValue == null){
                    emptyNames.add(propertyName);
                }
            }
        }

        String [] result = emptyNames.toArray(new String[emptyNames.size()]);
        return result != null ? result : new String [0];
    }
}