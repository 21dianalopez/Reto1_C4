
package RetoUno_C4.Modelo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;


/**
 *
 * @author Diana L
 */
@Entity
@Table(name = "user")
public class User implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name="user_email", unique = true)
    private String email;
    private String password;
    private String name;
    

    public User() {
    }

    public User(Integer id, String email, String name, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        
    }
    
    public User(String email, String name, String password) {
        this.email = email;
        this.password = password;
        this.name = name;
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    
}
