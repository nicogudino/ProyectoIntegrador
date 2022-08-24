
package com.portfolio.nicolas2.Interface;

import com.portfolio.nicolas2.Entity.Persona;
import java.util.List;



public interface IPersonaService {
    public List<Persona> getPersona();
    
    public void savePersona(Persona persona);
    
    public void deletePersona(Long id);
    
    public Persona findPersona (Long id);
}
