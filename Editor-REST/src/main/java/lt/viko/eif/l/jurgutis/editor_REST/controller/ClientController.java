package lt.viko.eif.l.jurgutis.editor_REST.controller;

import io.swagger.v3.oas.annotations.Operation;
import lt.viko.eif.l.jurgutis.editor_REST.assembler.ClientAssembler;
import lt.viko.eif.l.jurgutis.editor_REST.model.Client;
import lt.viko.eif.l.jurgutis.editor_REST.repos.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientAssembler clientAssembler;

    @Operation(summary = "Get all clients")
    @GetMapping
    public CollectionModel<EntityModel<Client>> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        return CollectionModel.of(clientAssembler.toModelList(clients));
    }

    @Operation(summary = "Get client by ID")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Client>> getClientById(@PathVariable(value = "id") int clientId) {
        Optional<Client> client = clientRepository.findById(clientId);
        if (client.isPresent()) {
            return ResponseEntity.ok().body(clientAssembler.toModel(client.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Create new client")
    @PostMapping
    public ResponseEntity<EntityModel<Client>> createClient(@RequestBody Client client) {
        Client savedClient = clientRepository.save(client);
        return ResponseEntity.ok().body(clientAssembler.toModel(savedClient));
    }

    @Operation(summary = "Update client")
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Client>> updateClient(@PathVariable(value = "id") int clientId, @RequestBody Client clientDetails) {
        Optional<Client> client = clientRepository.findById(clientId);
        if (client.isPresent()) {
            Client updatedClient = client.get();
            updatedClient.setFirstname(clientDetails.getFirstname());
            updatedClient.setLastname(clientDetails.getLastname());
            updatedClient.setPhonenumber(clientDetails.getPhonenumber());
            updatedClient.setEmail(clientDetails.getEmail());
            clientRepository.save(updatedClient);
            return ResponseEntity.ok().body(clientAssembler.toModel(updatedClient));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete client")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable(value = "id") int clientId) {
        Optional<Client> client = clientRepository.findById(clientId);
        if (client.isPresent()) {
            clientRepository.delete(client.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
