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
class ClasificacionJugador implements Comparable{
    private int puntosTotales;
    private int intentosTotales;
    private double  minutosTotales;
    private double rendimiento;
    private Jugador jugador;
    private Jornada jornada;

    List obtenerDatosClasificacion(){
        List lista = new ArrayList();
        lista.add(this.jugador.obtenerNombre());
        lista.add(this.jugador.obtenerNomClub());
        lista.add(this.jugador.obtenerPosicion());
        lista.add(this.puntosTotales);
        lista.add(this.intentosTotales);
        lista.add(this.minutosTotales);
        lista.add(this.rendimiento);
        
        return lista;
    }

    int[] obtenerResultados(){
        int[] resultados = new int[3];

        resultados[0] = (int) this.minutosTotales;
        resultados[1] = this.intentosTotales;
        resultados[2] = this.puntosTotales;

        return resultados;
    }

    List obtenerDatosJornada(){
        List datosJornada = new ArrayList();
        datosJornada.add(this.jornada.obtenerNumero());
        datosJornada.add(this.puntosTotales);
        datosJornada.add(this.intentosTotales);
        datosJornada.add(this.minutosTotales);
        datosJornada.add(this.rendimiento);

        return datosJornada;
    }

    public ClasificacionJugador(int puntosTotales, int intentosTotales, double minutosTotales, Jugador jugador, Jornada jornada) {
        this.puntosTotales = puntosTotales;
        this.intentosTotales = intentosTotales;
        this.minutosTotales = minutosTotales;
        this.rendimiento = (puntosTotales-intentosTotales)/minutosTotales;
        this.jugador = jugador;
        this.jugador.incluir(this);
        this.jornada = jornada;
    }

    int obtenerJornada(){
       return this.jornada.obtenerNumero();
    }

    public int compareTo(Object t) {//Hacer para subir nota--->

        ClasificacionJugador cj = (ClasificacionJugador) t;
        if(this.rendimiento == cj.obtenerRendimiento())
            return 0;
        if(this.rendimiento > cj.obtenerRendimiento())
            return 1;

        return -1;
    }

    double obtenerRendimiento() {
        return this.rendimiento;
    }


}
