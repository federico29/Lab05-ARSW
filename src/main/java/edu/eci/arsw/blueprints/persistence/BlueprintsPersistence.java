package edu.eci.arsw.blueprints.persistence;

import edu.eci.arsw.blueprints.model.Blueprint;

import java.util.Set;

public interface BlueprintsPersistence {

    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException;
    
    public void updateBlueprint(String autor, String nombrebp, Blueprint bp) throws BlueprintNotFoundException;

    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException;

    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException;

    public Set<Blueprint> getBlueprintByAll() throws BlueprintNotFoundException;

}
