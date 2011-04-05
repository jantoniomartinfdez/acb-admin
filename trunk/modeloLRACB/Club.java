/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
