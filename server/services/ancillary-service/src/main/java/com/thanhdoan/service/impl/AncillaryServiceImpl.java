package com.thanhdoan.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.thanhdoan.mapper.AncillaryMapper;
import com.thanhdoan.model.Ancillary;
import com.thanhdoan.payload.request.AncillaryRequest;
import com.thanhdoan.payload.response.AncillaryResponse;
import com.thanhdoan.repository.AncillaryRepository;
import com.thanhdoan.service.AncillaryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AncillaryServiceImpl implements AncillaryService {

  private final AncillaryRepository ancillaryRepository;

  @Override
  public AncillaryResponse createAncillary(Long airlineId, AncillaryRequest ancillaryRequest) {
    Ancillary ancillary = Ancillary.builder()
        .type(ancillaryRequest.getType())
        .subType(ancillaryRequest.getSubType())
        .rfisc(ancillaryRequest.getRfisc())
        .name(ancillaryRequest.getName())
        .description(ancillaryRequest.getDescription())
        .metadata(ancillaryRequest.getMetadata())
        .displayOrder(ancillaryRequest.getDisplayOrder())
        .airlineId(airlineId)
        .build();
    Ancillary savedAncillary = ancillaryRepository.save(ancillary);
    return AncillaryMapper.toResponse(savedAncillary, null);
  }

  @Override
  public AncillaryResponse getAncillaryById(Long id) throws Exception {
    Ancillary ancillary = ancillaryRepository.findById(id)
        .orElseThrow(() -> new Exception("Ancillary not found with id: " + id));
    // TODO: fetch insurance coverages by ancillary
    return AncillaryMapper.toResponse(ancillary, null);
  }

  @Override
  public List<AncillaryResponse> getAncillariesByAirlineId(Long airlineId) {
    List<Ancillary> ancillaries = ancillaryRepository.findByAirlineId(airlineId);
    return ancillaries.stream()
        .map(ancillary -> AncillaryMapper.toResponse(ancillary, null))
        .collect(Collectors.toList());
  }

  @Override
  public AncillaryResponse updateAncillary(Long id, AncillaryRequest ancillaryRequest) throws Exception {
    Ancillary ancillary = ancillaryRepository.findById(id)
        .orElseThrow(() -> new Exception("Ancillary not found with id: " + id));

    ancillary.setType(ancillaryRequest.getType());
    ancillary.setSubType(ancillaryRequest.getSubType());
    ancillary.setRfisc(ancillaryRequest.getRfisc());
    ancillary.setName(ancillaryRequest.getName());
    ancillary.setDescription(ancillaryRequest.getDescription());
    ancillary.setMetadata(ancillaryRequest.getMetadata());
    ancillary.setDisplayOrder(ancillaryRequest.getDisplayOrder());
    Ancillary updatedAncillary = ancillaryRepository.save(ancillary);

    return AncillaryMapper.toResponse(updatedAncillary, null);
  }

  @Override
  public void deleteAncillary(Long id) throws Exception {
    Ancillary ancillary = ancillaryRepository.findById(id)
        .orElseThrow(() -> new Exception("Ancillary not found with id: " + id));
    ancillaryRepository.delete(ancillary);
  }

}
