package com.client.controller;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.client.model.PostRequest;
import com.client.model.Response;
import com.client.service.ClientService;

@RestController
@RequestMapping("client")
public class Controller {
	
	@Autowired
	ClientService clientService;

	@PostMapping
	@RolesAllowed({"ADMIN","USER"})
	public Response addClient(@RequestBody PostRequest client) {
	    return clientService.addClient(client);
	}   
	
	@PutMapping
	@RolesAllowed({"ADMIN","USER"})
	public Response updateClient(@RequestBody PostRequest client) {
	    return clientService.updateClient(client);
	}   
	
	@GetMapping
	@RolesAllowed({"ADMIN","USER"})
	public Object getClientDetail(@RequestParam("clientName") String clientName) {
	    return clientService.getClientDetail(clientName);
	}
}
