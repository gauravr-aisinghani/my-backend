package com.example.controller;

import com.example.dto.TransporterDriverRequestDTO;

import com.example.entity.TransporterDriverRequest;
import com.example.service.TransporterDriverRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for Transporter Driver Requests
 */
@RestController
@RequestMapping("/api/driver-request")
public class TransporterDriverRequestController {

	private final TransporterDriverRequestService service;

	public TransporterDriverRequestController(TransporterDriverRequestService service) {
		this.service = service;
	}

	/**
	 * Create a new driver request
	 */
	@PostMapping("/create")
	public ResponseEntity<?> createRequest(@RequestBody TransporterDriverRequestDTO dto) {
		try {
			TransporterDriverRequest saved = service.createRequest(dto);
			return ResponseEntity.ok(saved);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error creating request: " + e.getMessage());
		}
	}

	
	@PostMapping("/accept/{requestId}")
	public ResponseEntity<?> acceptDriverRequest(@PathVariable Long requestId) {
	    try {
	        TransporterDriverRequest updated =
	                service.acceptDriverRequest(requestId);
	        return ResponseEntity.ok(updated);
	    } catch (Exception e) {
	        return ResponseEntity.badRequest().body(e.getMessage());
	    }
	}
	
	
	@GetMapping("/eligible-for-assignment")
	public ResponseEntity<List<TransporterDriverRequest>> getEligibleRequests() {
	    return ResponseEntity.ok(
	            service.getEligibleRequestsForAssignment()
	    );
	}


	/**
	 * Get all requests
	 */
	@GetMapping("/all")
	public ResponseEntity<List<TransporterDriverRequest>> getAllRequests() {
		return ResponseEntity.ok(service.getAllRequests());
	}

	/**
	 * Get request by ID
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> getRequestById(@PathVariable Long id) {
		try {
			TransporterDriverRequest req = service.getRequestById(id);
			return ResponseEntity.ok(req);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Request not found: " + e.getMessage());
		}
	}
}
