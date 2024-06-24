package lt.viko.eif.l.jurgutis.editor_REST.assembler;

import lt.viko.eif.l.jurgutis.editor_REST.controller.TeamLeadController;
import lt.viko.eif.l.jurgutis.editor_REST.model.TeamLead;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TeamLeadAssembler implements RepresentationModelAssembler<TeamLead, EntityModel<TeamLead>> {
    public String Ref;

    public List<EntityModel<TeamLead>> toModelList(List<TeamLead> teamLeads) {
        return teamLeads.stream().map(this::toModel).collect(Collectors.toList());
    }

    @Override
    public EntityModel<TeamLead> toModel(TeamLead teamLead) {
        return EntityModel.of(teamLead,
                !Objects.equals(Ref, "All") ? linkTo(methodOn(TeamLeadController.class).getAllTeamLeads()).withRel("Get all team leads")
                        : linkTo(methodOn(TeamLeadController.class).getAllTeamLeads()).withSelfRel(),
                !Objects.equals(Ref, "findID") ? linkTo(methodOn(TeamLeadController.class).getTeamLeadById(teamLead.getId())).withRel("Find team lead")
                        : linkTo(methodOn(TeamLeadController.class).getTeamLeadById(teamLead.getId())).withSelfRel(),
                !Objects.equals(Ref, "New") ? linkTo(methodOn(TeamLeadController.class).createTeamLead(teamLead)).withRel("New team lead")
                        : linkTo(methodOn(TeamLeadController.class).createTeamLead(teamLead)).withSelfRel(),
                !Objects.equals(Ref, "Delete") ? linkTo(methodOn(TeamLeadController.class).deleteTeamLead(teamLead.getId())).withRel("Delete team lead")
                        : linkTo(methodOn(TeamLeadController.class).deleteTeamLead(teamLead.getId())).withSelfRel(),
                !Objects.equals(Ref, "Update") ? linkTo(methodOn(TeamLeadController.class).updateTeamLead(teamLead.getId(), teamLead)).withRel("Update team lead")
                        : linkTo(methodOn(TeamLeadController.class).updateTeamLead(teamLead.getId(), teamLead)).withSelfRel());
    }
}
