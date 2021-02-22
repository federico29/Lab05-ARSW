package edu.eci.arsw.blueprints.controllers;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/blueprints")
public class BlueprintAPIController {

    @Autowired
    BlueprintsServices bps;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> consultarTodosLosPlanos() {
        try {
            return new ResponseEntity<>(bps.getAllBlueprints(), HttpStatus.ACCEPTED);
        } catch (BlueprintNotFoundException ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("BLUEPRINT ERROR 404: " + ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{autor}", method = RequestMethod.GET)
    public ResponseEntity<?> consultarPlanosPorAutor(@PathVariable("autor") String autor) {
        try {
            return new ResponseEntity<>(bps.getBlueprintsByAuthor(autor), HttpStatus.ACCEPTED);
        } catch (BlueprintNotFoundException ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("BLUEPRINT ERROR 404: " + ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{autor}/{nombrebp}", method = RequestMethod.GET)
    public ResponseEntity<?> consultarPlanosPorAutorYNombre(@PathVariable("autor") String autor, @PathVariable("nombrebp") String nombrebp) {
        try {
            return new ResponseEntity<>(bps.getBlueprint(autor, nombrebp), HttpStatus.ACCEPTED);
        } catch (BlueprintNotFoundException ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("BLUEPRINT ERROR 404: " + ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> registrarPlano(@RequestBody Blueprint bp) {
        try {
            //registrar dato
            bps.addNewBlueprint(bp);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (BlueprintPersistenceException ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("BLUEPRINT ERROR 409: " + ex.getMessage(), HttpStatus.CONFLICT);
        }
    }
    
    @RequestMapping(value = "/{autor}/{nombrebp}", method = RequestMethod.PUT)
    public ResponseEntity<?> actualizarPlano(@PathVariable("autor") String autor, @PathVariable("nombrebp") String nombrebp, @RequestBody Blueprint bp) {
        try {
            bps.updateBlueprint(autor, nombrebp, bp);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (BlueprintNotFoundException ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("BLUEPRINT ERROR 409: " + ex.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
