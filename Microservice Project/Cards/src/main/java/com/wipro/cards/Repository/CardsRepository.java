package com.wipro.cards.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.cards.entities.Cards;

public interface CardsRepository extends JpaRepository<Cards, Integer> {

	public Optional<Cards> findByMobileNumber(String MobileNumber);
}
