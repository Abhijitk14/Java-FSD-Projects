package com.wipro.cards.mapper;

import com.wipro.cards.dto.CardsDto;
import com.wipro.cards.entities.Cards;

public class CardMapper {

	
	public static CardsDto mapToCards(Cards cards, CardsDto cardsDto) {
		cardsDto.setMobileNumber(cards.getMobileNumber());
		cardsDto.setCardType(cards.getCardType());
		cardsDto.setCardNumber(cards.getCardNumber());
		cardsDto.setTotalLimit(cards.getTotalLimit());
		cardsDto.setAmountUsed(cards.getAmountUsed());
		cardsDto.setAvailable_amount(cards.getAvailable_amount());
		
		return cardsDto;
	}
	
	public static Cards mapToCardsDto(CardsDto cardsDto, Cards cards) {
		cards.setMobileNumber(cardsDto.getMobileNumber());
		cards.setCardType(cardsDto.getCardType());
		cards.setCardNumber(cardsDto.getCardNumber());
		cards.setTotalLimit(cardsDto.getTotalLimit());
		cards.setAmountUsed(cardsDto.getAmountUsed());
		cards.setAvailable_amount(cardsDto.getAvailable_amount());
		return cards;
	}
}
