/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modeloLRACB;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author jose
 */
class ResultadoVisitante {
    private int puntos;
    private Club club;

    void modificarResultado(int puntosVisi){
        this.puntos = puntosVisi;
    }

    List obtenerResultadoNomClub(){
        List resultado = new ArrayList();
        resultado.add(this.club.obtenerNombre());
        resultado.add(this.puntos);

        return resultado;
    }    

    String obtenerNomClubVisi(){
        return this.club.obtenerNombre();
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

    ResultadoVisitante(Club cv) {
        this.club = cv;
    }



}
