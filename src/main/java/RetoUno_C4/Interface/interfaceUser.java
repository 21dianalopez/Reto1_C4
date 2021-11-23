package RetoUno_C4.Interface;

import RetoUno_C4.Modelo.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface interfaceUser extends CrudRepository<User, Integer> {

   public User findAllByEmailAndPassword(String email,String password);
   
   boolean existsByEmail(String email);
}
