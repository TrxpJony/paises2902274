package group.paises.paises2902274.Controllers;

import org.springframework.web.bind.annotation.RestController;

import group.paises.paises2902274.entities.Pais;
import group.paises.paises2902274.services.PaisServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;






@RestController
@RequestMapping("/api/paises")
public class PaisController {

    @Autowired
    private PaisServiceImpl service;
    
    @GetMapping
    public List<Pais> mostrarPais(){
        //buscar todos los paises en bases de datos 
        //utilizando el service
        return service.findAll();

    }

    //Traer un unico país por id 
    @GetMapping("/{id}")
    public  Pais mostrarPaisPorId(@PathVariable Long id) {
        //Buscar todos los paises en bd que 
        // que coincidan con el id
        Optional<Pais> optPais = service.findByid(id);
        return optPais.get();

    }

    //Ruta para grabar un pais en bd
    @PostMapping
    public Pais crearPais(@RequestBody Pais paisGrabar) {
        Pais paisNew = service.save(paisGrabar);
        return paisNew;
    }

    //Ruta para actualizar un pais en bd
    @PutMapping("/{id}")
    public Pais actualizarPais (@PathVariable Long id, 
                                @RequestBody Pais paisUpd) {
        //buscar pais por id 
        Pais p = service.findByid(id).get();
        //actualizamos atributos del pais a guardar;
        p.setCapital(paisUpd.getCapital());
        p.setNombre(paisUpd.getNombre());
        //grabar cambios en bd 
        return service.save(p);
    }

    //Ruta para borrar pais
    @DeleteMapping("/{id}")
    public Pais deletePais(@PathVariable Long id){
        //buscar el pais por id
        return service.delete(id).get();
        
    }

    
    
    
    

}
