package com.irem.demo.dto;

import java.time.LocalDate;

public class WorkdayRequest {
    private LocalDate startDate;
    private LocalDate endDate;
    private Long regionId;
    private Long personTypeId;

    // Getters and Setters
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public Long getRegionId() { return regionId; }
    public void setRegionId(Long regionId) { this.regionId = regionId; }

    public Long getPersonTypeId() { return personTypeId; }
    public void setPersonTypeId(Long personTypeId) { this.personTypeId = personTypeId; }
}
