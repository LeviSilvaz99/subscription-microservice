package com.example.backendmicroservicoconsumer.repository;

import com.example.backendmicroservicoconsumer.domain.SubscriptionPurchased;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SubscriptionPurchasedRepository extends JpaRepository<SubscriptionPurchased, Integer> {

}
