package com.thanhdoan.service;

import com.thanhdoan.domain.AncillaryMetadata;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import tools.jackson.databind.ObjectMapper;

@Converter
public class AncillaryMetadataConverter implements AttributeConverter<AncillaryMetadata, String> {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public String convertToDatabaseColumn(AncillaryMetadata ancillaryMetadata) {
    if (ancillaryMetadata == null) {
      return null;
    }
    return objectMapper.writeValueAsString(ancillaryMetadata);
  }

  @Override
  public AncillaryMetadata convertToEntityAttribute(String dbData) {
    if (dbData == null) {
      return null;
    }
    return objectMapper.readValue(dbData, AncillaryMetadata.class);
  }

}
