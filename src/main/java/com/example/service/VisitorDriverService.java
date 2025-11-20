package com.example.service;

import com.example.dto.VisitorDriverDTO;
import java.util.List;

public interface VisitorDriverService {

    VisitorDriverDTO saveVisitorDriver(VisitorDriverDTO dto);

    VisitorDriverDTO getVisitorDriver(Long id);

    List<VisitorDriverDTO> getAllVisitorDrivers();

    VisitorDriverDTO updateVisitorDriver(Long id, VisitorDriverDTO dto);

    void deleteVisitorDriver(Long id);
}
