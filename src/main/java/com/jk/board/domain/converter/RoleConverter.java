package com.jk.board.domain.converter;

import com.jk.board.domain.type.Role;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;


@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Role, String> {

    @Override
    public String convertToDatabaseColumn(Role attribute) {
        return attribute.getName();
    }

    @Override
    public Role convertToEntityAttribute(String dbData) {
        return Role.from(dbData);
    }
}
