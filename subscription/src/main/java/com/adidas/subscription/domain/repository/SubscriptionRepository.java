package com.adidas.subscription.domain.repository;

import java.util.List;
import java.util.Optional;

import com.adidas.subscription.domain.Subscription;

public interface SubscriptionRepository {
	
	Optional<Subscription> findById(Long id);

    Long save(Subscription subscription);
    
    void remove(Subscription subscription);
    
    List<Subscription> findAll();
}
