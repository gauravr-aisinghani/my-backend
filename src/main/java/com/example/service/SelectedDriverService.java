package com.example.service;

import com.example.dto.SelectedDriverDTO;
import com.example.dto.VisitorDriverDTO;

import java.util.List;

public interface SelectedDriverService {

    SelectedDriverDTO addSelectedDriver(VisitorDriverDTO visitor);

    List<SelectedDriverDTO> getAllSelectedDrivers();
}
