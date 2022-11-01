
package com.portfolio.nicolas2.Controller;


import com.portfolio.nicolas2.Dto.dtoHys;

import com.portfolio.nicolas2.Entity.hys;
import com.portfolio.nicolas2.Security.Controller.Mensaje;
import com.portfolio.nicolas2.Service.Shys;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "https://frontendproyecto-ff35c.web.app")
@RequestMapping("/skill")
public class CHys {
    @Autowired
    Shys shys;
    
     @GetMapping ("/lista")
    public ResponseEntity <List<hys>> list(){
        List<hys> list = shys.list();
        return new ResponseEntity (list, HttpStatus.OK);
    }
     
    @GetMapping("/detail/{id}")
    public ResponseEntity<hys> getById(@PathVariable("id") int id){
        if(!shys.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        hys hYs = shys.getOne(id).get();
        return new ResponseEntity(hYs, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!shys.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        shys.delete(id);
        return new ResponseEntity(new Mensaje("Skill eliminada"), HttpStatus.OK);
    }
    
      
    
    @PostMapping("/create")
    public ResponseEntity <?> create(@RequestBody dtoHys dtohys){
        if (StringUtils.isBlank(dtohys.getNombre()))
            return new ResponseEntity (new Mensaje ("El nombre es un campo obligatorio"), HttpStatus.BAD_REQUEST);
        if(shys.existsByNombre(dtohys.getNombre()))
            return new ResponseEntity(new Mensaje("Esa skill ya existe"), HttpStatus.BAD_REQUEST);
        
        hys hYs = new hys (dtohys.getNombre(), dtohys.getPorcentaje());
        shys.save(hYs);
        return new ResponseEntity(new Mensaje ("Skill agregada correctamente"),HttpStatus.OK);
    }   
    
    @PutMapping ("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody dtoHys dtohys){
        if(!shys.existsById(id))
            return new ResponseEntity (new Mensaje ("el ID no existe"), HttpStatus.BAD_REQUEST);
        if(shys.existsByNombre(dtohys.getNombre())&& shys.getByNombre(dtohys.getNombre()).get().getId() != id)
            return new ResponseEntity(new Mensaje("Esa skill ya existe"),HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(dtohys.getNombre()))
            return new ResponseEntity (new Mensaje ("Este campo es obligatorio"), HttpStatus.BAD_REQUEST);
        
        hys hYs= shys.getOne(id).get();
        hYs.setNombre(dtohys.getNombre());
        hYs.setPorcentaje((dtohys.getPorcentaje()));
        
        shys.save(hYs);
        return new ResponseEntity (new Mensaje("Skill actualizada"), HttpStatus.OK);
        
    }
    
   
}
