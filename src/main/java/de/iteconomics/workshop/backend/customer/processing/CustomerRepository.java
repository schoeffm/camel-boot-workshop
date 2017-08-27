package de.iteconomics.workshop.backend.customer.processing;


import de.iteconomics.workshop.backend.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
