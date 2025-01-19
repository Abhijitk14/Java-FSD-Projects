package com.wipro.ms.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wipro.ms.dto.CardsDto;

@FeignClient("Cards")
public interface CardsFeignClient {

	@GetMapping(value= "/cards/api/get", consumes="application/json")
	public ResponseEntity<CardsDto> getCards(@RequestParam() String MobileNumber);
}
