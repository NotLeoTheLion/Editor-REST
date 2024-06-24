package lt.viko.eif.l.jurgutis.editor_REST.service;

import lt.viko.eif.l.jurgutis.editor_REST.exception.ResourceNotFoundException;
import lt.viko.eif.l.jurgutis.editor_REST.model.TeamLead;
import lt.viko.eif.l.jurgutis.editor_REST.repos.TeamLeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamLeadService {
    @Autowired
    private TeamLeadRepository teamLeadRepository;

    public List<TeamLead> getAllTeamLeads() {
        return teamLeadRepository.findAll();
    }

    public Optional<TeamLead> getTeamLeadById(int teamLeadId) {
        return Optional.ofNullable(teamLeadRepository.findById(teamLeadId)
                .orElseThrow(() -> new ResourceNotFoundException("TeamLead not found for this id :: " + teamLeadId)));
    }

    public TeamLead createTeamLead(TeamLead teamLead) {
        return teamLeadRepository.save(teamLead);
    }

    public Optional<TeamLead> updateTeamLead(int teamLeadId, TeamLead teamLeadDetails) {
        TeamLead teamLead = teamLeadRepository.findById(teamLeadId)
                .orElseThrow(() -> new ResourceNotFoundException("TeamLead not found for this id :: " + teamLeadId));

        teamLead.setFirstname(teamLeadDetails.getFirstname());
        teamLead.setLastname(teamLeadDetails.getLastname());
        teamLead.setEmail(teamLeadDetails.getEmail());
        return Optional.of(teamLeadRepository.save(teamLead));
    }

    public void deleteTeamLead(int teamLeadId) {
        TeamLead teamLead = teamLeadRepository.findById(teamLeadId)
                .orElseThrow(() -> new ResourceNotFoundException("TeamLead not found for this id :: " + teamLeadId));
        teamLeadRepository.delete(teamLead);
    }
}
