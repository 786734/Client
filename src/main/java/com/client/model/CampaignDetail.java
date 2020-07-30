package com.client.model;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class CampaignDetail implements Serializable{

	private static final long serialVersionUID = 6072343415160970011L;
	private String campaignName;
	private String campaignDescription;
	private LocalDate effectiveDate;
	private LocalDate endDate;
	
	public String getCampaignName() {
		return campaignName;
	}
	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}
	public String getCampaignDescription() {
		return campaignDescription;
	}
	public void setCampaignDescription(String campaignDescription) {
		this.campaignDescription = campaignDescription;
	}
	public LocalDate getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(LocalDate effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	
}
