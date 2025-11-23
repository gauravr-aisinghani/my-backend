package com.example.service;

import com.example.dto.SelectedDriverDTO;
import java.util.List;

public interface SelectedDriverService {
    SelectedDriverDTO saveSelectedDriver(SelectedDriverDTO dto);
    SelectedDriverDTO getSelectedDriver(Long id);
    List<SelectedDriverDTO> getAllSelectedDrivers();
    SelectedDriverDTO updateSelectedDriver(Long id, SelectedDriverDTO dto);
    void deleteSelectedDriver(Long id);
}
