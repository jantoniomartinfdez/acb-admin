/*
Copyright 2011 Jose Antonio Martín Fernández


This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>

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
