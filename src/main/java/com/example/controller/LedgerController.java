package com.example.controller;

import com.example.dto.LedgerRowDto;
import com.example.dto.LedgerSummaryView;
import com.example.service.LedgerReadService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ledger")
@CrossOrigin
public class LedgerController {

    private final LedgerReadService ledgerReadService;

    public LedgerController(LedgerReadService ledgerReadService) {
        this.ledgerReadService = ledgerReadService;
    }

    // 🔥 TRANSPORTER LEDGER (DETAIL)
    @GetMapping("/transporter/{gdcNumber}")
    public List<LedgerRowDto> transporterLedger(
            @PathVariable String gdcNumber
    ) {
        return ledgerReadService.transporterLedger(gdcNumber);
    }

    // 🔥 DRIVER LEDGER (DETAIL)
    @GetMapping("/driver/{gdcNumber}/{driverRegistrationId}")
    public List<LedgerRowDto> driverLedger(
            @PathVariable String gdcNumber,
            @PathVariable Long driverRegistrationId
    ) {
        return ledgerReadService.driverLedger(
                gdcNumber,
                driverRegistrationId
        );
    }

    // 🔍 ALL TRANSPORTERS (SEARCHABLE – NATIVE)
    @GetMapping("/transporters")
    public List<LedgerSummaryView> allTransporters(
            @RequestParam(required = false) String search
    ) {
        return ledgerReadService.allTransportersNative(search);
    }

    // 🔍 ALL DRIVERS (SEARCHABLE – NATIVE)
    @GetMapping("/drivers")
    public List<LedgerSummaryView> allDrivers(
            @RequestParam(required = false) String search
    ) {
        return ledgerReadService.allDriversNative(search);
    }
}
