
package RetoUno_C4.Servicios;

import RetoUno_C4.Modelo.User;
import RetoUno_C4.Repositorio.UserRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class serviciosUser {
    
    @Autowired
    private UserRepositorio metodosCrud;
    

    
    public List<User> getAll(){
        return metodosCrud.getAll();
    }
    
    public Optional<User> getUser(int id){
        return metodosCrud.getUser(id);
    }

    public boolean getExistsEmail(String email){
        return metodosCrud.getExistsEmail(email);
    }
    
    public User getExistsEmailAndPassword(String email, String password){
        
        return metodosCrud.getExistsEmailAndPassword(email,password);
    }

    
    public User save(User user) throws Exception{
        
        /*
        Optional<User> userFound = metodosCrud.getFindByEmail(user.getEmail());
        if(userFound.isPresent()){
            throw new Exception("El correo ya existe");
        }else{
        */
        try {
            if(user.getId()==null){
                return metodosCrud.save(user);
            }else{
                Optional<User> evt=metodosCrud.getUser(user.getId());
                if(evt.isEmpty()){
                    return metodosCrud.save(user);
                }else{
                    return  user;
                }
            }
        } catch (Exception e) {
            
           throw new Exception("El correo ya existe");
            
        }
        
            
        //}
        
    }
    
    public User update(User user){
        if (user.getId()!=null){
            Optional<User> evt = metodosCrud.getUser(user.getId());
            if (!evt.isEmpty()){
                if (user.getEmail()!=null){
                    evt.get().setEmail(user.getEmail());
                }
                if (user.getPassword()!=null){
                    evt.get().setPassword(user.getPassword());
                }
                if (user.getName()!=null){
                    evt.get().setName(user.getName());
                }
                metodosCrud.save(evt.get());
                return evt.get();
            }else {
                return user;
            }
        }else {
            return user;
        }
    }
    
    
    public boolean deleteUser(int id){
        Boolean del = getUser(id).map(user -> {
            metodosCrud.delete(user);
            return true;
        }).orElse(false);
        return del;
    }

    

}
