package lt.viko.eif.l.jurgutis.editor_REST.repos;

import lt.viko.eif.l.jurgutis.editor_REST.model.TeamLead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamLeadRepository extends JpaRepository<TeamLead, Integer> {
}
