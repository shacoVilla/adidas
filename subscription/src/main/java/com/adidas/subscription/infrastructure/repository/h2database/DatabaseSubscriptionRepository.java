package com.adidas.subscription.infrastructure.repository.h2database;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adidas.subscription.domain.Subscription;
import com.adidas.subscription.domain.repository.SubscriptionRepository;

@Component
public class DatabaseSubscriptionRepository implements SubscriptionRepository {

	private H2DbSubscriptionRepository repo;

	@Autowired
	public DatabaseSubscriptionRepository(H2DbSubscriptionRepository subscriptionRepository) {
		this.repo = subscriptionRepository;
	}

	@Override
	public Optional<Subscription> findById(Long id) {
		Optional<SubscriptionEntity> subsEntity = repo.findById(id);
		if (subsEntity.isPresent()) {
			return Optional.of(subsEntity.get().toSubscription());
		} else {
			return Optional.empty();
		}
	}

	@Override
	public Long save(Subscription subscription) {
		SubscriptionEntity entity = new SubscriptionEntity(subscription);
		repo.save(entity);
		
		Long id = entity.getId();
		return id;

	}

	@Override
	public void remove(Subscription subscription) {
		repo.delete(new SubscriptionEntity(subscription));
	}

	@Override
	public List<Subscription> findAll() {

		List<Subscription> actualSubscriptions = new ArrayList<Subscription>();
		Iterable<SubscriptionEntity> subscriptions = repo.findAll();

		subscriptions.forEach(o -> actualSubscriptions.add(o.toSubscription()));
		return actualSubscriptions;
	}
}