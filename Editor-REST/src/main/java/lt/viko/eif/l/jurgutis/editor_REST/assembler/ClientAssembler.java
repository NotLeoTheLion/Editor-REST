package lt.viko.eif.l.jurgutis.editor_REST.assembler;

import lt.viko.eif.l.jurgutis.editor_REST.controller.ClientController;
import lt.viko.eif.l.jurgutis.editor_REST.model.Client;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ClientAssembler implements RepresentationModelAssembler<Client, EntityModel<Client>> {
    public String Ref;

    public List<EntityModel<Client>> toModelList(List<Client> clients) {
        return clients.stream().map(this::toModel).collect(Collectors.toList());
    }

    @Override
    public EntityModel<Client> toModel(Client client) {
        return EntityModel.of(client,
                !Objects.equals(Ref, "All") ? linkTo(methodOn(ClientController.class).getAllClients()).withRel("Get all clients")
                        : linkTo(methodOn(ClientController.class).getAllClients()).withSelfRel(),
                !Objects.equals(Ref, "findID") ? linkTo(methodOn(ClientController.class).getClientById(client.getId())).withRel("Find client")
                        : linkTo(methodOn(ClientController.class).getClientById(client.getId())).withSelfRel(),
                !Objects.equals(Ref, "New") ? linkTo(methodOn(ClientController.class).createClient(client)).withRel("New client")
                        : linkTo(methodOn(ClientController.class).createClient(client)).withSelfRel(),
                !Objects.equals(Ref, "Delete") ? linkTo(methodOn(ClientController.class).deleteClient(client.getId())).withRel("Delete client")
                        : linkTo(methodOn(ClientController.class).deleteClient(client.getId())).withSelfRel(),
                !Objects.equals(Ref, "Update") ? linkTo(methodOn(ClientController.class).updateClient(client.getId(), client)).withRel("Update client")
                        : linkTo(methodOn(ClientController.class).updateClient(client.getId(), client)).withSelfRel());
    }
}
