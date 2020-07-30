package com.client.service;

import org.springframework.stereotype.Service;
import com.client.model.PostRequest;
import com.client.model.Response;

@Service
public interface ClientService {

	Response addClient(PostRequest request);

	Response updateClient(PostRequest client);

	Object getClientDetail(String clientName);

}
