package lt.viko.eif.l.jurgutis.editor_REST.controller;

import io.swagger.v3.oas.annotations.Operation;
import lt.viko.eif.l.jurgutis.editor_REST.assembler.TeamLeadAssembler;
import lt.viko.eif.l.jurgutis.editor_REST.model.TeamLead;
import lt.viko.eif.l.jurgutis.editor_REST.repos.TeamLeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teamleads")
public class TeamLeadController {

    @Autowired
    private TeamLeadRepository teamLeadRepository;

    @Autowired
    private TeamLeadAssembler teamLeadAssembler;

    @Operation(summary = "Get all team leads")
    @GetMapping
    public CollectionModel<EntityModel<TeamLead>> getAllTeamLeads() {
        List<TeamLead> teamLeads = teamLeadRepository.findAll();
        return CollectionModel.of(teamLeadAssembler.toModelList(teamLeads));
    }

    @Operation(summary = "Get team lead by ID")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<TeamLead>> getTeamLeadById(@PathVariable(value = "id") int teamLeadId) {
        Optional<TeamLead> teamLead = teamLeadRepository.findById(teamLeadId);
        if (teamLead.isPresent()) {
            return ResponseEntity.ok().body(teamLeadAssembler.toModel(teamLead.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Create new team lead")
    @PostMapping
    public ResponseEntity<EntityModel<TeamLead>> createTeamLead(@RequestBody TeamLead teamLead) {
        TeamLead savedTeamLead = teamLeadRepository.save(teamLead);
        return ResponseEntity.ok().body(teamLeadAssembler.toModel(savedTeamLead));
    }

    @Operation(summary = "Update team lead")
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<TeamLead>> updateTeamLead(@PathVariable(value = "id") int teamLeadId, @RequestBody TeamLead teamLeadDetails) {
        Optional<TeamLead> teamLead = teamLeadRepository.findById(teamLeadId);
        if (teamLead.isPresent()) {
            TeamLead updatedTeamLead = teamLead.get();
            updatedTeamLead.setFirstname(teamLeadDetails.getFirstname());
            updatedTeamLead.setLastname(teamLeadDetails.getLastname());
            updatedTeamLead.setEmail(teamLeadDetails.getEmail());
            teamLeadRepository.save(updatedTeamLead);
            return ResponseEntity.ok().body(teamLeadAssembler.toModel(updatedTeamLead));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete team lead")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeamLead(@PathVariable(value = "id") int teamLeadId) {
        Optional<TeamLead> teamLead = teamLeadRepository.findById(teamLeadId);
        if (teamLead.isPresent()) {
            teamLeadRepository.delete(teamLead.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
