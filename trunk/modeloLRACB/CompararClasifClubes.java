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
class CompararClasifClubes implements Comparator{
    

    public int compare(Object t, Object t1) {
        ClasificacionClub c1, c2;

        c1 = (ClasificacionClub)t;
        c2 = (ClasificacionClub)t1;

        if(c1.obtenerPartidosGanados() > c2.obtenerPartidosGanados()) return 1;
        if(c1.obtenerPartidosGanados() < c2.obtenerPartidosGanados()) return -1;

        return 0;
    }

}
