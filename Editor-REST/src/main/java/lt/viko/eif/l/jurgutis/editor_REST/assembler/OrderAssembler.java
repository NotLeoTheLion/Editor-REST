package lt.viko.eif.l.jurgutis.editor_REST.assembler;

import lt.viko.eif.l.jurgutis.editor_REST.controller.OrderController;
import lt.viko.eif.l.jurgutis.editor_REST.model.Order;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderAssembler implements RepresentationModelAssembler<Order, EntityModel<Order>> {
    public String Ref;

    public List<EntityModel<Order>> toModelList(List<Order> orders) {
        return orders.stream().map(this::toModel).collect(Collectors.toList());
    }

    @Override
    public EntityModel<Order> toModel(Order order) {
        return EntityModel.of(order,
                !Objects.equals(Ref, "All") ? linkTo(methodOn(OrderController.class).getAllOrders()).withRel("Get all orders")
                        : linkTo(methodOn(OrderController.class).getAllOrders()).withSelfRel(),
                !Objects.equals(Ref, "findID") ? linkTo(methodOn(OrderController.class).getOrderById(order.getId())).withRel("Find order")
                        : linkTo(methodOn(OrderController.class).getOrderById(order.getId())).withSelfRel(),
                !Objects.equals(Ref, "New") ? linkTo(methodOn(OrderController.class).createOrder(order)).withRel("New order")
                        : linkTo(methodOn(OrderController.class).createOrder(order)).withSelfRel(),
                !Objects.equals(Ref, "Delete") ? linkTo(methodOn(OrderController.class).deleteOrder(order.getId())).withRel("Delete order")
                        : linkTo(methodOn(OrderController.class).deleteOrder(order.getId())).withSelfRel(),
                !Objects.equals(Ref, "Update") ? linkTo(methodOn(OrderController.class).updateOrder(order.getId(), order)).withRel("Update order")
                        : linkTo(methodOn(OrderController.class).updateOrder(order.getId(), order)).withSelfRel());
    }
}
