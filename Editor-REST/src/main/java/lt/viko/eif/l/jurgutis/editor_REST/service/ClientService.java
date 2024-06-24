package lt.viko.eif.l.jurgutis.editor_REST.service;

import lt.viko.eif.l.jurgutis.editor_REST.exception.ResourceNotFoundException;
import lt.viko.eif.l.jurgutis.editor_REST.model.Client;
import lt.viko.eif.l.jurgutis.editor_REST.repos.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(int clientId) {
        return Optional.ofNullable(clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found for this id :: " + clientId)));
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public Optional<Client> updateClient(int clientId, Client clientDetails) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found for this id :: " + clientId));

        client.setFirstname(clientDetails.getFirstname());
        client.setLastname(clientDetails.getLastname());
        client.setPhonenumber(clientDetails.getPhonenumber());
        client.setEmail(clientDetails.getEmail());
        return Optional.of(clientRepository.save(client));
    }

    public void deleteClient(int clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found for this id :: " + clientId));
        clientRepository.delete(client);
    }
}
