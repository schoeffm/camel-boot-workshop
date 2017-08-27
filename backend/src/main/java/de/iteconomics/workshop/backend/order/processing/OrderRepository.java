package de.iteconomics.workshop.backend.order.processing;

import de.iteconomics.workshop.backend.order.entity.Ordering;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Ordering, Long> {
}
