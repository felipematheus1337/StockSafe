package com.stocksafe.repositories;


import com.stocksafe.model.Client;
import com.stocksafe.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
