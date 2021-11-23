package RetoUno_C4.Web;

import RetoUno_C4.Modelo.User;
import RetoUno_C4.Servicios.serviciosUser;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/api/user")
public class UserWeb {

    @Autowired
    private serviciosUser servicios;

    @GetMapping("/all")
    public List<User> getUser() {
        return servicios.getAll();
    }

    @GetMapping("/{email}")
    public boolean getEmail(@PathVariable("email") String email) {

        return servicios.getExistsEmail(email);
    }

    @GetMapping("/{email}/{password}")
    public User getEmailAndPassword(@PathVariable("email") String email, @PathVariable("password") String password) {

        User user = servicios.getExistsEmailAndPassword(email, password);
        if (user == null) {

            User noUser = new User(null, email, "NO DEFINIDO", password);

            return noUser;
        }
        return user;
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody User user) throws Exception {

        return servicios.save(user);

    }

    //No se va a utilizar por ahora
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public User update(@RequestBody User user) {
        return servicios.update(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int carId) {
        return servicios.deleteUser(carId);
    }

    private Optional<User> User(String email, String password, String anull) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
