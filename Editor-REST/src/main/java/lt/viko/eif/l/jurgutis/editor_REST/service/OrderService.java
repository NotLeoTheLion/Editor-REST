package lt.viko.eif.l.jurgutis.editor_REST.service;

import lt.viko.eif.l.jurgutis.editor_REST.exception.ResourceNotFoundException;
import lt.viko.eif.l.jurgutis.editor_REST.model.Order;
import lt.viko.eif.l.jurgutis.editor_REST.repos.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(int orderId) {
        return Optional.ofNullable(orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for this id :: " + orderId)));
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Optional<Order> updateOrder(int orderId, Order orderDetails) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for this id :: " + orderId));

        order.setStatus(orderDetails.getStatus());
        order.setClient(orderDetails.getClient());
        order.setTeamLead(orderDetails.getTeamLead());
        order.setEditor(orderDetails.getEditor());
        return Optional.of(orderRepository.save(order));
    }

    public void deleteOrder(int orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for this id :: " + orderId));
        orderRepository.delete(order);
    }
}
