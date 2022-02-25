package com.example.backendmicroservicoconsumer.repository;

import com.example.backendmicroservicoconsumer.domain.SubscriptionCanceled;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SubscriptionCanceledRepository extends JpaRepository<SubscriptionCanceled, Integer> {

}
