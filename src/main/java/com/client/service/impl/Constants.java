package com.client.service.impl;

import java.time.format.DateTimeFormatter;

public class Constants {

	public static final String CLIENT_CREATED = "Client added successfully";
	public static final String CLIENT_UPDATED = "Client updated successfully";
	public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	public static final String CLIENT = "Client";
	public static final String CLIENT_EXISTS = "Client already exists";
	public static final String CLIENT_NOT_EXISTS = "Client not exists";
	
	public static final String CLIENT_NAME = "clientName";
	public static final String CARRIER = "carrier";
	public static final String CONTRACT_ID = "contractId";
	public static final String EFFECTIVE_DATE = "effectiveDate";
	public static final String END_DATE = "endDate";
	
	public static final String DATE_RANGE_ERROR = "Effective Date should be always earlier than EndDate";
	public static final String DATE_FORMAT_ERROR = "Invalid date format. Please provide date in dd-MM-yyyy format";
	public static final String CAMPAIGN_URL = "http://localhost:7003/campaign";
	
}
