package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service("inMemoryBlueprintPersistence")
public class InMemoryBlueprintPersistence implements BlueprintsPersistence {

    private final Map<Tuple<String, String>, Blueprint> blueprints = new ConcurrentHashMap<>();

    public InMemoryBlueprintPersistence() {
        //Creando los puntos
        ArrayList<Point> pts = new ArrayList<>(Arrays.asList(new Point(140, 140), new Point(115, 115)));
        ArrayList<Point> pts1 = new ArrayList<>(Arrays.asList(new Point(20, 0)));
        ArrayList<Point> pts2 = new ArrayList<>(Arrays.asList(new Point(58, 24), new Point(5, 25)));
        //Creando los blruepints
        Blueprint bp = new Blueprint("_authorname_", "_bpname_ ", pts);
        Blueprint bp1 = new Blueprint("Federico", "Plano casa", pts1);
        Blueprint bp2 = new Blueprint("Federico", "Plano apto", pts2);
        Blueprint bp3 = new Blueprint("Guillermo", "Plano 1", pts);
        Blueprint bp4 = new Blueprint("Ramona", "Plano 7", pts1);
        Blueprint bp5 = new Blueprint("Messi", "Plano 3", pts2);
        //Agregando los blueprints al contenedor
        blueprints.put(new Tuple<>(bp.getAuthor(), bp.getName()), bp);
        blueprints.put(new Tuple<>(bp1.getAuthor(), bp1.getName()), bp1);
        blueprints.put(new Tuple<>(bp2.getAuthor(), bp2.getName()), bp2);
        blueprints.put(new Tuple<>(bp3.getAuthor(), bp3.getName()), bp3);
        blueprints.put(new Tuple<>(bp4.getAuthor(), bp4.getName()), bp4);
        blueprints.put(new Tuple<>(bp5.getAuthor(), bp5.getName()), bp5);
    }

    @Override
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        if (blueprints.containsKey(new Tuple<>(bp.getAuthor(), bp.getName()))) {
            throw new BlueprintPersistenceException("El plano ingresado ya existe: " + bp);
        } else {
            blueprints.put(new Tuple<>(bp.getAuthor(), bp.getName()), bp);
        }
    }

    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {
        if(blueprints.containsKey(new Tuple<>(author, bprintname))){
            return blueprints.get(new Tuple<>(author, bprintname));
        }
        throw new BlueprintNotFoundException("No existe un plano con dichas especificaciones.");
    }

    @Override
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException {
        Set<Blueprint> planosAutor = new HashSet<>();
        for (Tuple<String, String> tupla : blueprints.keySet()) {
            if (tupla.getElem1().equals(author)) {
                planosAutor.add(blueprints.get(tupla));
            }
        }
        if (planosAutor.isEmpty()) {
            throw new BlueprintNotFoundException("No existen planos del autor: " + author);
        }
        return planosAutor;
    }

    @Override
    public Set<Blueprint> getBlueprintByAll() throws BlueprintNotFoundException {
        Set<Blueprint> Author = new HashSet<>();
        for (Tuple<String, String> tupla : blueprints.keySet()) {
            Author.add(blueprints.get(tupla));
        }
        if (Author.isEmpty()) {
            throw new BlueprintNotFoundException("No existen planos registrados.");
        }
        return Author;
    }

    @Override
    public void updateBlueprint(String autor, String nombrebp, Blueprint bp) throws BlueprintNotFoundException {
        if(blueprints.containsKey(new Tuple<>(autor, nombrebp))){
            blueprints.remove(new Tuple<>(autor, nombrebp));
            blueprints.put(new Tuple<>(bp.getAuthor(), bp.getName()),bp);
            
        }else{
            throw new BlueprintNotFoundException("No se puede actualizar el plano porque no existe.");
        } 
    }
}
