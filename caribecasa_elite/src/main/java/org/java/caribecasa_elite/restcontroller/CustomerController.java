package org.java.caribecasa_elite.restcontroller;

import org.java.caribecasa_elite.model.Customer;
import org.java.caribecasa_elite.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/customers")

public class CustomerController {



        @Autowired
        private CustomerService customerService;

        // Get all customers
        @GetMapping
        public List<Customer> getAllCustomers() {
            return customerService.findAll();
        }

        // Get a single customer by ID
        @GetMapping("/{id}")
        public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
            Optional<Customer> customer = customerService.findById(id);
            if (customer.isPresent()) {
                return ResponseEntity.ok(customer.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        // Create a new customer
        @PostMapping
        public Customer createCustomer(@RequestBody Customer customer) {
            return customerService.createOrUpdateCustomer(customer);
        }

        // Update an existing customer
        @PutMapping("/{id}")
        public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
            Optional<Customer> customer = customerService.findById(id);
            if (customer.isPresent()) {
                updatedCustomer.setId(id);
                return ResponseEntity.ok(customerService.createOrUpdateCustomer(updatedCustomer));
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        // Delete a customer
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
            Optional<Customer> customer = customerService.findById(id);
            if (customer.isPresent()) {
                customerService.deleteById(id);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        }
}
