package lt.viko.eif.l.jurgutis.editor_REST.controller;

import io.swagger.v3.oas.annotations.Operation;
import lt.viko.eif.l.jurgutis.editor_REST.assembler.OrderAssembler;
import lt.viko.eif.l.jurgutis.editor_REST.model.Order;
import lt.viko.eif.l.jurgutis.editor_REST.repos.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderAssembler orderAssembler;

    @Operation(summary = "Get all orders")
    @GetMapping
    public CollectionModel<EntityModel<Order>> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return CollectionModel.of(orderAssembler.toModelList(orders));
    }

    @Operation(summary = "Get order by ID")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Order>> getOrderById(@PathVariable(value = "id") int orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            return ResponseEntity.ok().body(orderAssembler.toModel(order.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Create new order")
    @PostMapping
    public ResponseEntity<EntityModel<Order>> createOrder(@RequestBody Order order) {
        Order savedOrder = orderRepository.save(order);
        return ResponseEntity.ok().body(orderAssembler.toModel(savedOrder));
    }

    @Operation(summary = "Update order")
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Order>> updateOrder(@PathVariable(value = "id") int orderId, @RequestBody Order orderDetails) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            Order updatedOrder = order.get();
            updatedOrder.setStatus(orderDetails.getStatus());
            updatedOrder.setClient(orderDetails.getClient());
            updatedOrder.setTeamLead(orderDetails.getTeamLead());
            updatedOrder.setEditor(orderDetails.getEditor());
            orderRepository.save(updatedOrder);
            return ResponseEntity.ok().body(orderAssembler.toModel(updatedOrder));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete order")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable(value = "id") int orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            orderRepository.delete(order.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
