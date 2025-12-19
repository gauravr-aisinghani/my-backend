package com.example.service;

import com.example.dto.VisitorTransporterDTO;
import java.util.List;

public interface VisitorTransporterService {

    VisitorTransporterDTO save(VisitorTransporterDTO dto);

    List<VisitorTransporterDTO> getAll();

    VisitorTransporterDTO getById(Long id);

    VisitorTransporterDTO update(Long id, VisitorTransporterDTO dto);

    void delete(Long id);
}
