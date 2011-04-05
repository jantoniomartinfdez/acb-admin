/*
Copyright 2011 Jose Antonio Martín Fernández


This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>

*/
package modeloLRACB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jose
 */
class Club {
    private String nombre;
    private String entrenador;
    private String pabellon;
    private Map<String, Jugador> jugadores = new HashMap<String, Jugador>();

    void ficharJugador(Jugador ju){
        this.jugadores.put(ju.obtenerDNI(), ju);
    }

    String obtenerNombre() {
        return nombre;
    }

    String obtenerPabellon() {
        return pabellon;
    }

    List jugadoresDeClub(){
        List resultado = new ArrayList();
        resultado.add(this.nombre);
        if(this.jugadores.isEmpty())
            resultado.add("El club no tiene definido jugadores.");
        else{
            Iterator it = this.jugadores.entrySet().iterator();
            Jugador jugador = null;
            while(it.hasNext()){
                Map.Entry e = (Map.Entry)it.next();
                jugador = (Jugador)e.getValue();
                resultado.add(jugador.obtenerDatosJugador());
            }
        }

        return resultado;
    }

    Club(String nombre, String entrenador, String pabellon) {
        this.nombre = nombre;
        this.entrenador = entrenador;
        this.pabellon = pabellon;
    }
}
