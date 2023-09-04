package org.java.caribecasa_elite.service;

import org.java.caribecasa_elite.model.Customer;
import org.java.caribecasa_elite.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    public Customer createOrUpdateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
    public Customer findByUsername(String username) {
        return customerRepository.findByUser_Username(username);
    }


    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }
}
