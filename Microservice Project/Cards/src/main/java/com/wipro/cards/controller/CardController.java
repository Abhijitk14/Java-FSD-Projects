package com.wipro.cards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.cards.constants.CardsConstants;
import com.wipro.cards.dto.CardsDto;
import com.wipro.cards.dto.ResponseDto;
import com.wipro.cards.service.CardService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("cards/api")
public class CardController {
	
	@Autowired
	CardService cardService;
	
	@PostMapping("/create")
	public ResponseEntity<ResponseDto> createCards(@Valid @RequestBody CardsDto cardsDto){
		cardService.createCard(cardsDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(CardsConstants.STATUS_201, CardsConstants.MESSAGE_201));
	}
	
	@GetMapping("/get")
	public ResponseEntity<CardsDto> getCards(@RequestParam("MobileNumber") String MobileNumber){
		CardsDto cardsDto=cardService.getCard(MobileNumber);
		return ResponseEntity.status(HttpStatus.OK).body(cardsDto);
	}
	
	@PutMapping("/update")
	public ResponseEntity<ResponseDto> updateCards(@Valid @RequestBody CardsDto cardsDto, @RequestParam("MobileNumber") String MobileNumber){
		Boolean isUpdated=cardService.updateCard(cardsDto, MobileNumber);
		if(isUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
        }else {
        	return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_UPDATE));
        }
	}

	@DeleteMapping("/remove")
	public ResponseEntity<ResponseDto> removeCards(@RequestParam("MobileNumber") String MobileNumber){
		Boolean isDelete=cardService.removeCard(MobileNumber);
		if(isDelete) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
		}else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_DELETE));
		}
	}
}
