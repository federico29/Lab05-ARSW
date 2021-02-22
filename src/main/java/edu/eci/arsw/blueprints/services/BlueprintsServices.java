package edu.eci.arsw.blueprints.services;

import edu.eci.arsw.blueprints.filters.BluePrintFilter;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("BlueprintsServices")
public class BlueprintsServices {

    @Autowired
    @Qualifier("inMemoryBlueprintPersistence")
    BlueprintsPersistence bpp = null;

    @Autowired
    @Qualifier("RedundanciaFiltro")
    BluePrintFilter filter;

    /**
     * Agrega el plano dado a la base de datos
     *
     * @param bp plano
     * @throws BlueprintPersistenceException
     */
    public void addNewBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        bpp.saveBlueprint(bp);
    }

    public void updateBlueprint(String autor, String nombrebp, Blueprint bp) throws BlueprintNotFoundException {
        bpp.updateBlueprint(autor, nombrebp, bp);
    }
    
    /**
     * Retorna todos los planos almacenados
     * 
     * @return todos los planos 
     * @throws BlueprintNotFoundException si no hay ningún plano
     */
    public Set<Blueprint> getAllBlueprints() throws BlueprintNotFoundException {
        return bpp.getBlueprintByAll();
    }

    /**
     * Retorna el plano según su autor y su nombre
     * 
     * @param author autor del plano
     * @param name nombre del plano
     * @return el plano del nombre dado creado por el autor dado
     * @throws BlueprintNotFoundException si no está dicho blueprint
     */
    public Blueprint getBlueprint(String author, String name) throws BlueprintNotFoundException {
        return bpp.getBlueprint(author, name);
    }

    /**
     *
     * @param author blueprint's author
     * @return all the blueprints of the given author
     * @throws BlueprintNotFoundException if the given author doesn't exist
     */
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException {
        return bpp.getBlueprintsByAuthor(author);
    }

    /**
     * Aplica el filtro que esté definido al blueprint que se envía.
     *
     * @param bluePrint Plano al que se quiere aplicar el filtro.
     * @return Un nuevo blueprint con el filtro aplicado.
     * @throws BlueprintPersistenceException
     */
    public Blueprint getFilteredBlueprints(Blueprint bluePrint) throws BlueprintPersistenceException {
        return filter.filter(bluePrint);
    }
}
