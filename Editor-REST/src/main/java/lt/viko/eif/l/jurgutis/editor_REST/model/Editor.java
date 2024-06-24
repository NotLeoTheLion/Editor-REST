package lt.viko.eif.l.jurgutis.editor_REST.model;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;
import org.springframework.data.annotation.Id;

/**
 * Public Editor Model class
 */
@XmlRootElement(name = "Editor")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {"id", "firstname", "lastname", "email", "skillLevel"})
@Entity
@Table(name = "editor")
public class Editor  {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    private String firstName;
    private String lastName;
    private String email;
    private String skillLevel;

    public Editor(String firstname, String lastname, String email,String skilllevel) {
        this.firstName = firstname;
        this.lastName = lastname;

        this.email = email;
        this.skillLevel = skilllevel;

    }


    public Editor() {

    }

    @Override
    public String toString() {
        return String.format("\t\tEditor: \n" +
                        "\t\t\tFirst Name:   %s \n" +
                        "\t\t\tLast Name:    %s \n" +
                        "\t\t\temail: %s \n" +
                        "\t\t\tskilllevel:  %s \n",
                this.firstName, this.lastName, this.email,this.skillLevel);
    }

    @XmlElement
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    @XmlElement(name = "firstName")
    public void setFirstname(String firstname) {
        this.firstName = firstname;
    }

    @XmlElement(name = "lastName")
    public void setLastname(String lastname) {
        this.lastName = lastname;
    }
    @XmlElement(name = "email")
    public void setEmail(String email) {
        this.email = email;
    }


    @XmlElement(name = "skilllevel")
    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }
    public String getSkillLevel() {
        return skillLevel;
    }
    public String getEmail() {
        return email;
    }




    public String getFirstname() {
        return firstName;
    }

    public String getLastname() {
        return lastName;
    }
}
