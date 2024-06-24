package lt.viko.eif.l.jurgutis.editor_REST.model;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;
import org.springframework.data.annotation.Id;

/**
 * Order Model class
 */
@XmlRootElement(name = "Order")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {"id", "status", "editor","client", "teamLead"})
@Entity
@Table(name = "orders")
public class Order {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    private String status;
    @OneToOne(targetEntity = Client.class, cascade = CascadeType.ALL)
    private Client client;
    @OneToOne(targetEntity = TeamLead.class, cascade = CascadeType.ALL)
    private TeamLead teamLead;
    @OneToOne(targetEntity = Editor.class, cascade = CascadeType.ALL)
    private Editor editor;

    public Order() {

    }

    @Override
    public String toString() {
        return String.format("Order: \n" +
                        "\tstatus: %s \n" +
                        "%s " +
                        "%s " +
                        "%s ",
                this.status, this.client, this.teamLead, this.editor);
    }

    public int getId() {
        return id;
    }

    @XmlElement
    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }


    public void setClient(Client client) {
        this.client = client;
    }

    public TeamLead getTeamLead() {
        return teamLead;
    }


    public void setTeamLead(TeamLead teamLead) {
        this.teamLead = teamLead;
    }

    public Editor getEditor() {
        return editor;
    }


    public void setEditor(Editor editor) {
        this.editor = editor;
    }

    public String getStatus() {
        return status;
    }

    @XmlElement(name = "status")
    public void setStatus(String status) {
        this.status = status;
    }
}
