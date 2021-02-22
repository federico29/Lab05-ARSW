package edu.eci.arsw.blueprints.filters.impl;

import edu.eci.arsw.blueprints.filters.BluePrintFilter;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("RedundanciaFiltro")
public class FiltroRedundancia implements BluePrintFilter {

    /**
     * Elimina los puntos repetidos de un plano.
     *
     * @param blueprint El plano que se revisará.
     * @return Un nuevo plano con el filtro aplicado.
     */
    @Override
    public Blueprint filter(Blueprint blueprint) {
        System.out.println("Filtro de redundancia aplicado: ");
        ArrayList<Point> puntos = blueprint.getPoints();
        ArrayList<Point> puntosRepetidos = new ArrayList<>();
        Point lastPoint = puntos.get(0);
        for (int i = 1; i < puntos.size(); i++) {
            Point p = puntos.get(i);
            if (lastPoint.compare(p)) {
                puntosRepetidos.add(p);
            } else {
                lastPoint = p;
            }
        }
        for (Point p : puntosRepetidos) {
            puntos.remove(p);
        }
        return new Blueprint(blueprint.getAuthor(), blueprint.getName(), puntos);
    }
}
