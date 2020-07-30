package com.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.client.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

	Client findByCarrierAndContractId(String carrier, String contractId);

	Client findByClientName(String clientName);


}
