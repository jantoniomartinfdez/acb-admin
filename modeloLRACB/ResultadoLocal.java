/*
Copyright 2011 Jose Antonio Martín Fernández


This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>

*/
package modeloLRACB;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author jose
 */
class ResultadoLocal {//Hacer Generalizacion.---->Resultado---> (1)rl.....  -->(2)rv
    private int puntos;
    private Club club;

    void modificarResultado(int puntosLocal){
        this.puntos = puntosLocal;
    }

    List obtenerResultadoNomClub(){
        List resultado = new ArrayList();
        resultado.add(this.club.obtenerNombre());
        resultado.add(this.puntos);

        return resultado;
    }

    String obtenerNomClubLocal(){
        return this.club.obtenerNombre();
    }

    String obtenerPabellon(){
        return this.club.obtenerPabellon();
    }

    Jugador obtenerJugador(String dniPass){
        ListIterator it = this.club.jugadoresDeClub().listIterator();
        boolean existe = false;
        Jugador ju = null;
        while(it.hasNext() && !existe){
            ju = (Jugador)it.next();
            existe = ju.obtenerDNI().equals(dniPass);
        }

        return ju;
    }

    boolean perteneceJugador(String dniPass){
        ListIterator it = this.club.jugadoresDeClub().listIterator();
        boolean pertenece = false;
        Jugador ju;
        while(it.hasNext() && !pertenece){
            ju = (Jugador)it.next();
            pertenece = ju.obtenerDNI().equals(dniPass);
        }

        return pertenece;
    }

    boolean participaClub(Club cl, Club cv){
        return (this.club.obtenerNombre().equals(cl.obtenerNombre()) || this.club.obtenerNombre().equals(cv.obtenerNombre()));
    }

    ResultadoLocal(Club cl) {
        this.club = cl;
    }



}
