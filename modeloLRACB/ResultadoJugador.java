/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modeloLRACB;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jose
 */
public class ResultadoJugador {
    private double minutosJugados;
    private int puntos;
    private int intentos;
    private Jugador jugador;

    List obtenerResultadoJugador(String nc){
        List resultado = new ArrayList();
        if(this.jugador.obtenerNomClub().equals(nc)){
            resultado.add(this.jugador.obtenerNumero());
            resultado.add(this.jugador.obtenerNombre());
            resultado.add(this.jugador.obtenerPosicion());
            resultado.add(this.minutosJugados);
            resultado.add(this.intentos);
            resultado.add(this.puntos);
            resultado.add((this.puntos-this.intentos)/this.minutosJugados);
        }

        return resultado;
    }

    ResultadoJugador(double minutosJugados, int puntos, int intentos, Jugador jugador) {
        this.minutosJugados = minutosJugados;
        this.puntos = puntos;
        this.intentos = intentos;
        this.jugador = jugador;
    }



}
