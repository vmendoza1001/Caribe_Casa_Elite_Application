package org.java.caribecasa_elite.repository;

import org.java.caribecasa_elite.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
