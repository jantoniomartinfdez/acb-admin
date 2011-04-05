/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modeloLRACB;

import java.util.Comparator;

/**
 *
 * @author jose
 */
class CompararClasifJug implements Comparator{

    public int compare(Object t, Object t1) {
        ClasificacionJugador cc1, cc2;
        cc1 = (ClasificacionJugador)t;
        cc2 = (ClasificacionJugador)t1;

        if(cc1.obtenerRendimiento() > cc2.obtenerRendimiento()) return 1;
        if(cc1.obtenerRendimiento() < cc2.obtenerRendimiento()) return -1;

        return 0;
    }

    

}
