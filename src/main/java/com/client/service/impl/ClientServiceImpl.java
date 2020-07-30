package com.client.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.client.model.CampaignDetail;
import com.client.model.Client;
import com.client.model.ClientDetail;
import com.client.model.PostRequest;
import com.client.model.Response;
import com.client.repository.ClientRepository;
import com.client.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {
	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	Validator validator;

	@Autowired
	ExceptionHandler exceptionHandler;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public Response addClient(PostRequest request) {

		Map<String,String> error = validator.validateAddClientFieldValues(request);
		
		if(!error.isEmpty()) {
			return exceptionHandler.throwBadRequest(error);
		}
		Response response = new Response();
		Client client = clientRepository.findByClientName(request.getClientName());
		if(client == null) {
			client = new Client();
			client.setClientName(request.getClientName());
			client.setCarrier(request.getCarrier());
			client.setContractId(request.getContractId());
			client.setEffectiveDate(LocalDate.parse(request.getEffectiveDate(), Constants.DATE_FORMAT));
			client.setEndDate(LocalDate.parse(request.getEndDate(), Constants.DATE_FORMAT));
			clientRepository.save(client);
			
			response.setStatusCode(HttpStatus.OK.value());
			response.setStatusMessage(Constants.CLIENT_CREATED);
		}else {
			error.put(Constants.CLIENT, Constants.CLIENT_EXISTS);
			return exceptionHandler.throwBadRequest(error);
		}
		
		return response;
	}

	@Override
	public Response updateClient(PostRequest request) {


		Map<String,String> error = validator.validateUpdateClientFieldValues(request);
		
		if(!error.isEmpty()) {
			return exceptionHandler.throwBadRequest(error);
		}
		Response response = new Response();
		Client client = clientRepository.findByClientName(request.getClientName());
		if(client != null) {
			if(!StringUtils.isEmpty(request.getEffectiveDate()))
				client.setEffectiveDate(LocalDate.parse(request.getEffectiveDate(), Constants.DATE_FORMAT));
			
			if(!StringUtils.isEmpty(request.getEndDate()))
			client.setEndDate(LocalDate.parse(request.getEndDate(), Constants.DATE_FORMAT));
			clientRepository.save(client);
			
			response.setStatusCode(HttpStatus.OK.value());
			response.setStatusMessage(Constants.CLIENT_UPDATED);
		}else {
			error.put(Constants.CLIENT, Constants.CLIENT_NOT_EXISTS);
			return exceptionHandler.throwBadRequest(error);
		}
		
		return response;
	}

	@Override
	public Object getClientDetail(String clientName) {
		Map<String,String> error = new HashMap<String, String>();
		Client client = clientRepository.findByClientName(clientName);
		if(client != null) {
			ClientDetail clientDetail = new ClientDetail();
			clientDetail.setCarrier(client.getCarrier());
			clientDetail.setClientName(client.getClientName());
			clientDetail.setContractId(client.getContractId());
			clientDetail.setEffectiveDate(client.getEffectiveDate());
			clientDetail.setEndDate(client.getEndDate());
			
			clientDetail.setCampaignList(getCampaignList(clientName));
			
			return clientDetail;
		}else {
			error.put(Constants.CLIENT, Constants.CLIENT_NOT_EXISTS);
			return exceptionHandler.throwBadRequest(error);
		}
	}

	private List<CampaignDetail> getCampaignList(String clientName) {
		List<CampaignDetail> campaignDetailList = new ArrayList<CampaignDetail>();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(Constants.CAMPAIGN_URL)
		        .queryParam(Constants.CLIENT_NAME, clientName);

		HttpEntity<?> entity = new HttpEntity<>(headers);

		ResponseEntity<CampaignDetail[]> response = restTemplate.exchange(
		        builder.toUriString(), 
		        HttpMethod.GET, 
		        entity, 
		        CampaignDetail[].class);
		CampaignDetail[] list = response.getBody();
		
		for(CampaignDetail c:list) {
			campaignDetailList.add(c);
		}
		
		
		return campaignDetailList;
	}

}
