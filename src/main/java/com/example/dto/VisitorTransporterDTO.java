package com.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VisitorTransporterDTO {

    @JsonProperty("companyName")
    private String companyName;

    @JsonProperty("ownerName")
    private String ownerName;

    @JsonProperty("ownerMobileNo")
    private String ownerMobileNo;

    @JsonProperty("authorisedName")
    private String authorisedName;

    @JsonProperty("authorisedMobileNo")
    private String authorisedMobileNo;

    @JsonProperty("needDriver")
    private Integer needDriver;

    @JsonProperty("gaadiType")
    private String gaadiType;

    @JsonProperty("runningApplication")
    private String runningApplication;

    @JsonProperty("loadingPlace")
    private String loadingPlace;

    @JsonProperty("unloadPlace")
    private String unloadPlace;

    @JsonProperty("monthlySalary")
    private Double monthlySalary;

    @JsonProperty("otherBenefit")
    private String otherBenefit;

    @JsonProperty("needTiming")
    private String needTiming;

    @JsonProperty("notes")
    private String notes;

    // ✅ Getters and Setters
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    public String getOwnerMobileNo() { return ownerMobileNo; }
    public void setOwnerMobileNo(String ownerMobileNo) { this.ownerMobileNo = ownerMobileNo; }

    public String getAuthorisedName() { return authorisedName; }
    public void setAuthorisedName(String authorisedName) { this.authorisedName = authorisedName; }

    public String getAuthorisedMobileNo() { return authorisedMobileNo; }
    public void setAuthorisedMobileNo(String authorisedMobileNo) { this.authorisedMobileNo = authorisedMobileNo; }

    public Integer getNeedDriver() { return needDriver; }
    public void setNeedDriver(Integer needDriver) { this.needDriver = needDriver; }

    public String getGaadiType() { return gaadiType; }
    public void setGaadiType(String gaadiType) { this.gaadiType = gaadiType; }

    public String getRunningApplication() { return runningApplication; }
    public void setRunningApplication(String runningApplication) { this.runningApplication = runningApplication; }

    public String getLoadingPlace() { return loadingPlace; }
    public void setLoadingPlace(String loadingPlace) { this.loadingPlace = loadingPlace; }

    public String getUnloadPlace() { return unloadPlace; }
    public void setUnloadPlace(String unloadPlace) { this.unloadPlace = unloadPlace; }

    public Double getMonthlySalary() { return monthlySalary; }
    public void setMonthlySalary(Double monthlySalary) { this.monthlySalary = monthlySalary; }

    public String getOtherBenefit() { return otherBenefit; }
    public void setOtherBenefit(String otherBenefit) { this.otherBenefit = otherBenefit; }

    public String getNeedTiming() { return needTiming; }
    public void setNeedTiming(String needTiming) { this.needTiming = needTiming; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
