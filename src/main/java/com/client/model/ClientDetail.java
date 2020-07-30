package com.client.model;

import java.time.LocalDate;
import java.util.List;

public class ClientDetail {

	private String clientName;
	private String carrier;
	private String contractId;
	private LocalDate effectiveDate;
	private LocalDate endDate;
	private List<CampaignDetail> CampaignList;
	
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
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
	public List<CampaignDetail> getCampaignList() {
		return CampaignList;
	}
	public void setCampaignList(List<CampaignDetail> campaignList) {
		CampaignList = campaignList;
	}
	
}
