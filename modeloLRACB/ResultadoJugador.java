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
