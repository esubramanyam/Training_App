package com.fisemployeeservice.model;


import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PlanTypeConverter implements AttributeConverter<PlanType, String> {

    @Override
    public String convertToDatabaseColumn(PlanType attribute) {
        return attribute == null ? null : attribute.getPlan();
    }

    @Override
    public PlanType convertToEntityAttribute(String dbData) {
        return dbData == null ? null : PlanType.fromPlan(dbData);
    }
}
