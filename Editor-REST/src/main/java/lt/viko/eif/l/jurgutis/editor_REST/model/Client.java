package lt.viko.eif.l.jurgutis.editor_REST.model;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlTransient;
import org.springframework.data.annotation.Id;


/**
 * Public Client Model class
 */
@XmlRootElement(name = "Client")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {"id", "firstname", "lastname", "email","phonenumber"})
@Entity
@Table(name = "client")
public class Client {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    private String email;


    public Client(String firstname, String lastname, String phoneNumber, String email) {
       this.firstName = firstname;
       this.lastName = lastname;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }


    public Client() {

    }

    @Override
    public String toString() {
        return String.format("\t\tClient: \n" +
                        "\t\t\tFirst Name:   %s \n" +
                        "\t\t\tLast Name:    %s \n" +
                        "\t\t\tPhone number:    %s \n" +
                        "\t\t\tEmail: %s  \n",
                this.firstName,this.lastName, this.phoneNumber,this.email);
    }

    public String getEmail() {
        return email;
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
    @XmlElement(name = "PhoneNumber")
    public void setPhonenumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    @XmlElement(name = "email")
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phoneNumber;
    }


    public String getFirstname() {
        return firstName;
    }

    public String getLastname() {
        return lastName;
    }
    public int getId() {
        return id;
    }
}
