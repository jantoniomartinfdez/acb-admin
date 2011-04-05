/*
Copyright 2011 Jose Antonio Martín Fernández


This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>

*/
package modeloLRACB;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jose
 */
class ClasificacionClub {
    private int partidosGanados;
    private int partidosPerdidos;
    private int puntosAFavor;
    private int puntosEnContra;
    private Club club;

    public ClasificacionClub(int partidosGanados, int partidosPerdidos, int puntosAFavor, int puntosEnContra, Club club) {
        this.partidosGanados = partidosGanados;
        this.partidosPerdidos = partidosPerdidos;
        this.puntosAFavor = puntosAFavor;
        this.puntosEnContra = puntosEnContra;
        this.club = club;
    }

    int[] obtenerResultados(){
        int[] resultado = new int[4];
        resultado[0] = this.partidosGanados;
        resultado[1] = this.partidosPerdidos;
        resultado[2] = this.puntosAFavor;
        resultado[3] = this.puntosEnContra;

        return resultado;
    }

    List obtenerDatosClasificacion(){
        List datosClasificacion = new ArrayList();
        datosClasificacion.add(this.club.obtenerNombre());
        datosClasificacion.add(this.partidosGanados);
        datosClasificacion.add(this.partidosPerdidos);
        datosClasificacion.add(this.puntosAFavor);
        datosClasificacion.add(this.puntosEnContra);

        return datosClasificacion;
    }

    int obtenerPartidosGanados() {
       return this.partidosGanados;
    }

    String obtenerNombreClub(){
        return this.club.obtenerNombre();
    }
}
