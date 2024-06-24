package lt.viko.eif.l.jurgutis.editor_REST.repos;

import lt.viko.eif.l.jurgutis.editor_REST.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
