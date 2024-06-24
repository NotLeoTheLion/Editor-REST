package lt.viko.eif.l.jurgutis.editor_REST.model;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;
import org.springframework.data.annotation.Id;

/**
 * Public TeamLead model class
 */
@XmlRootElement(name = "TeamLead")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {"id", "firstname", "lastname", "email"})
@Entity
@Table(name = "teamlead")
public class TeamLead {

    @jakarta.persistence.Id

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    private String firstName;
    private String lastName;

    private String email;


    public TeamLead(String firstname, String lastname, String phoneNumber, String licensePlate) {
        this.firstName =firstname;
        this.lastName = lastname;
        this.email = phoneNumber;

    }


    public TeamLead() {

    }

    @Override
    public String toString() {
        return String.format("\t\tTeamLead: \n" +
                        "\t\t\tFirst Name:   %s \n" +
                        "\t\t\tLast Name:    %s \n" +
                        "\t\t\temail: %s  \n",
                this.firstName, this.lastName, this.email);
    }
    @XmlElement
    public void setId(int id) {
        this.id = id;
    }
    @XmlElement(name = "firstName")
    public void setFirstname(String firstname) {
        this.firstName = firstname;
    }

    @XmlElement(name = "lastName")
    public void setLastname(String lastname) {
        this.lastName = lastname;
    }

    public String getFirstname() {
        return firstName;
    }

    public String getLastname() {
        return lastName;
    }


    public String getEmail() {
        return email;
    }

    @XmlElement(name = "email")
    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }


}
