package de.iteconomics.workshop.backend.order.boundary;

import de.iteconomics.workshop.backend.order.entity.Ordering;
import de.iteconomics.workshop.backend.order.processing.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/orders", produces = APPLICATION_JSON_VALUE)
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public Ordering getOrderById(@PathVariable("id") Long id) {
        return orderRepository.findOne(id);
    }

    @GetMapping()
    @ResponseStatus(OK)
    public List<Ordering> getOrderById() {
        return orderRepository.findAll();
    }
}
