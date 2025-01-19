package com.wipro.cards.service;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.cards.Repository.CardsRepository;
import com.wipro.cards.dto.CardsDto;
import com.wipro.cards.entities.Cards;
import com.wipro.cards.exception.CardAlreadyExistsException;
import com.wipro.cards.exception.CardNotFoundException;
import com.wipro.cards.mapper.CardMapper;

@Service
public class CardService {
	
	@Autowired
	CardsRepository cardsRepository;
	
	public void createCard(CardsDto cardsDto) {
		Optional<Cards> optCards =cardsRepository.findByMobileNumber(cardsDto.getMobileNumber());
		if(optCards.isPresent()) {
			throw new CardAlreadyExistsException("Card already present");
		}else {
			Cards cards= CardMapper.mapToCardsDto(cardsDto, new Cards());
			Long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
			cards.setCardNumber(Long.toString(randomCardNumber));
			Long available_amount=cards.getTotalLimit()- cards.getAmountUsed();
			cards.setAvailable_amount(available_amount);
			
			Cards saveCards=cardsRepository.save(cards);
		}	
	}
	
	public CardsDto getCard(String MobileNumber) {
		Optional<Cards> optCards =cardsRepository.findByMobileNumber(MobileNumber);
		if(optCards.isPresent()) {
			return CardMapper.mapToCards(optCards.get(), new CardsDto());
		}else {
			throw new CardNotFoundException("Card Not Found With Mobile Number: "+ MobileNumber);
		}
	}
	
	public Boolean updateCard(CardsDto cardsDto, String MobileNumber) {
		Optional<Cards> optCards =cardsRepository.findByMobileNumber(cardsDto.getMobileNumber());
		if(optCards.isPresent()) {
			Cards cards= CardMapper.mapToCardsDto(cardsDto, new Cards());
			cards.setCardId(optCards.get().getCardId());
			cards.setCardNumber(optCards.get().getCardNumber());
			Long available_amount=cards.getTotalLimit()- cards.getAmountUsed();
			cards.setAvailable_amount(available_amount);
			
			Cards saveCards=cardsRepository.save(cards);
			return true;
		}else {
			throw new CardNotFoundException("Card Not Found With Mobile Number: "+ MobileNumber);
		}
		
	}

	public Boolean removeCard(String mobileNumber) {
		Optional<Cards> optCards =cardsRepository.findByMobileNumber(mobileNumber);
		if(optCards.isPresent()) {
			cardsRepository.deleteById(optCards.get().getCardId());
			return true;
		}else {
			throw new CardNotFoundException("Card Not Found With Mobile Number: "+ mobileNumber);
		}
	}
	
//	   
}
