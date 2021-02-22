package edu.eci.arsw.blueprints.filters.impl;

import edu.eci.arsw.blueprints.filters.BluePrintFilter;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("SubmuestreoFiltro")
public class FiltroSubmuestreo implements BluePrintFilter {

    /**
     * Elimina uno de cada dos puntos de manera intercalada.
     *
     * @param bluePrint Plano al que se quiere aplicar el filtro.
     * @return Un nuevo plano con el filtro aplicado.
     */
    @Override
    public Blueprint filter(Blueprint bluePrint) {
        System.out.println("Filtro de sub-muestreo aplicado: ");
        ArrayList<Point> blueprintPoints = bluePrint.getPoints();
        for (int i = blueprintPoints.size() - 1; i >= 0; i--) {
            if (i % 2 == 0) {
                blueprintPoints.remove(i);
            }
        }
        return new Blueprint(bluePrint.getAuthor(), bluePrint.getName(), blueprintPoints);
    }
}
