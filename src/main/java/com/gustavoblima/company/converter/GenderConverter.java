package com.gustavoblima.company.converter;

import com.gustavoblima.company.entity.Gender;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class GenderConverter implements AttributeConverter<Gender, String> {

    @Override
    public String convertToDatabaseColumn(Gender attribute) {
        if (attribute == Gender.MALE){
            return "male";
        } else if (attribute == Gender.FEMALE){
            return "female";
        }
        throw new IllegalArgumentException("Unknown" + attribute);

    }

    @Override
    public Gender convertToEntityAttribute(String dbData) {
        if (dbData.equals("male")) {
            return Gender.MALE;
        } else if (dbData.equals("female")){
            return Gender.FEMALE;
        }
        throw new IllegalArgumentException("Unknown" + dbData);
    }
}
