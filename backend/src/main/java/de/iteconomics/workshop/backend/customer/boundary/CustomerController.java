package de.iteconomics.workshop.backend.customer.boundary;


import de.iteconomics.workshop.backend.customer.entity.Customer;
import de.iteconomics.workshop.backend.customer.processing.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/customers", produces = APPLICATION_JSON_VALUE)
public class CustomerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    private CustomerRepository repository;

    @Autowired
    public CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    void init() {
        LOGGER.info("Repo {} was injected - ready to be used", repository);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(OK)
    public Customer findCustomerById(@PathVariable("id") Long id) {
        return repository.findOne(id);
    }
}
