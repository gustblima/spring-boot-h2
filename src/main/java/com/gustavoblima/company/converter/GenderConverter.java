package com.gustavoblima.company.converter;

import com.gustavoblima.company.entity.Gender;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class GenderConverter implements AttributeConverter<Gender, String> {

    @Override
    public String convertToDatabaseColumn(Gender attribute) {
        if (attribute == Gender.male){
            return "male";
        } else if (attribute == Gender.female){
            return "female";
        }
        throw new IllegalArgumentException("Unknown" + attribute);

    }

    @Override
    public Gender convertToEntityAttribute(String dbData) {
        if (dbData.equals("male")) {
            return Gender.male;
        } else if (dbData.equals("female")){
            return Gender.female;
        }
        throw new IllegalArgumentException("Unknown" + dbData);
    }
}
