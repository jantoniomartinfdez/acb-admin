/*
Copyright 2011 Jose Antonio Martín Fernández


This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>

*/
package modeloLRACB;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author jose
 */
class Jugador {
    private String nombre;
    private String dniPass;
    private GregorianCalendar fechaNac;
    private double altura;
    private String posicion;
    private double peso;
    private String nacionalidad;
    private int numero;
    private Club club;
    private Map<Integer, ClasificacionJugador> cj = new TreeMap<Integer, ClasificacionJugador>();

    Jugador(String nombre, String dniPass, GregorianCalendar fechaNac, double altura, String posicion, double peso, String nacionalidad, int numero, Club club) {
        this.nombre = nombre;
        this.dniPass = dniPass;
        this.fechaNac = fechaNac;
        this.altura = altura;
        this.posicion = posicion;
        this.peso = peso;
        this.nacionalidad = nacionalidad;
        this.numero = numero;
        this.club = club;
    }

    String obtenerNombre() {
        return nombre;
    }

    int obtenerNumero() {
        return numero;
    }

    String obtenerPosicion() {
        return posicion;
    }

    List obtenerDatosJugador(){
        List datosJugador = new ArrayList();
        datosJugador.add(this.nombre);
        datosJugador.add(GregorianCalendar.YEAR - this.fechaNac.YEAR) ;
        datosJugador.add(this.altura);
        datosJugador.add(this.peso);
        datosJugador.add(this.posicion);
        datosJugador.add(this.nacionalidad);
        datosJugador.add(this.numero);

        return datosJugador;

    }

    String obtenerNomClub(){
        return this.club.obtenerNombre();
    }    

    int[] obtenerClasificacion(){
        int[] ultimaClasificacion;
        if(!this.cj.isEmpty()){
            ClasificacionJugador ultimaCJ = this.obtenerUltima();
            ultimaClasificacion = ultimaCJ.obtenerResultados();
        }
        else{
            ultimaClasificacion = new int[3];
            //ultimaClasificacion.add(4, (Integer)0);//Forma una array de 4 ceros.
            ultimaClasificacion[0] = 0;
            ultimaClasificacion[1] = 0;
            ultimaClasificacion[2] = 0;
            
        }
        return ultimaClasificacion;
    }

    ClasificacionJugador obtenerUltima(){
        Iterator it = this.cj.entrySet().iterator();
        int i=0;
        ClasificacionJugador cjug = null;
        while(i != this.cj.size()){
            Map.Entry e = (Map.Entry)it.next();
            cjug = (ClasificacionJugador)e.getValue();
            i++;
        }

        return cjug;
    }

    void incluir(ClasificacionJugador cc){
        this.cj.put(cc.obtenerJornada()/*Este método no estaba en el diseño.*/, cc);
    }

    String obtenerDNI() {/*Este método no estaba en el diseño.*/
        return this.dniPass;
    }

    List evolucionJugador() {
        List resultado = new ArrayList();
        resultado.add(this.nombre);
        resultado.add(this.club.obtenerNombre());
        resultado.add(this.posicion);
        if(this.cj.isEmpty())
            resultado.add("El jugador no ha participado en ninguna jornada.");
        else{
            Iterator it = this.cj.entrySet().iterator();
            ClasificacionJugador c = null;
            while(it.hasNext()){
                Map.Entry e = (Map.Entry)it.next();
                c = (ClasificacionJugador)e.getValue();
                resultado.add(c.obtenerDatosJornada());
            }
        }

        return resultado;
    }
}
