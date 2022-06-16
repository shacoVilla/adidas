package com.adidas.subscription.infrastructure.repository.h2database;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface H2DbSubscriptionRepository extends CrudRepository<SubscriptionEntity, Long>{
	

    
}
