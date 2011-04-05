/*
Copyright 2011 Jose Antonio Martín Fernández


This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>

*/
package modeloLRACB;

import java.sql.Time;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author jose
 */
class Jornada {
    private int numJornada;
    private SortedSet<ClasificacionClub> clasificacionesDeClubes = new TreeSet<ClasificacionClub>(new CompararClasifClubes());
    private SortedSet<Fecha> fechas = new TreeSet<Fecha>(new CompararFechas());
    private SortedSet<ClasificacionJugador> clasificacionesDeJugadores = new TreeSet<ClasificacionJugador>(new CompararClasifJug());

    boolean esAnterior(List<GregorianCalendar> listaDeFechas){
        return this.fechas.first().esAnterior(listaDeFechas);
    }

    int obtenerNumero(){
        return this.numJornada;
    }

    Jornada(int numJornada, List<GregorianCalendar> listaDeFechas) {
        this.numJornada = numJornada;

        ListIterator it = listaDeFechas.listIterator();
        GregorianCalendar dia;

        while(it.hasNext()){
            dia = (GregorianCalendar) it.next();
            Fecha f = new Fecha(dia);
            this.fechas.add(f);
        }
    }

    int[] obtenerResultados(String nomClub) throws LRACBException{
        ClasificacionClub cc = this.buscarCClub(nomClub);
        return cc.obtenerResultados();
    }

    void anotarResultado(GregorianCalendar dia, String nomClubLocal, int puntosLocal, int puntosVisi) throws LRACBException{
        Fecha fech = this.buscarFecha(dia);
        fech.anotarResultado(nomClubLocal, puntosLocal, puntosVisi);
    }

    String obtenerNomCLubVisi(GregorianCalendar dia, String nomClubLocal) throws LRACBException{
        Fecha fech = this.buscarFecha(dia);
        return fech.obtenerNomClubVisi(nomClubLocal);
    }

    void definirClasificacionClub(Club cl, int ganados, int perdidos, int puntosAFavor, int puntosEnContra){
        ClasificacionClub cc = new ClasificacionClub(puntosAFavor, perdidos, puntosAFavor, puntosEnContra, cl);
        this.clasificacionesDeClubes.add(cc);
    }

    List resultadoJugadores(GregorianCalendar dia, String nomClubLocal) throws LRACBException{
        List resultado = new ArrayList();
        resultado.add(this.numJornada);
        Fecha fech = this.buscarFecha(dia);
        resultado.add(fech.resultadoJugadores(nomClubLocal));

        return resultado;
    }

    private Fecha buscarFecha(GregorianCalendar dia) throws LRACBException{
        if(this.fechas.isEmpty())
            throw new LRACBException("No existen fechas definidas para esta jornada.");
        else{
            Iterator it = this.fechas.iterator();
            Fecha f = null;
            boolean parar = false;
            while(it.hasNext() && !parar){
                f = (Fecha)it.next();
                if(f.obtenerFecha().equals(dia))
                    parar = true;
            }

            if(!parar)
                throw new LRACBException("No existe el dia solicitado en esta jornada.");

            return f;
        }
    }

    private ClasificacionClub buscarCClub(String nomClub) throws LRACBException{
        //return this.clasificacionesDeClubes.get(nomClub);
        if(this.clasificacionesDeClubes.isEmpty())
            throw new LRACBException("Esta jornada no tiene asociada ninguna clasificación de club.");
        else{
            Iterator it = this.clasificacionesDeClubes.iterator();
            ClasificacionClub cc = null;
            boolean parar = false;
            while(it.hasNext() && !parar){
                cc = (ClasificacionClub)it.next();
                if(cc.obtenerNombreClub().equals(nomClub))
                    parar = true;
            }

            if(!parar)
                throw new LRACBException("No existe una clasificacion de club para el equipo llamado " + nomClub);

            return cc;
        }
    }

    List verPartidosDeJornada(){
        List resultado = new ArrayList();
        resultado.add(this.numJornada);        
        Iterator it = this.fechas.iterator();        
        Fecha fech = null;
        while(it.hasNext()){            
            fech = (Fecha)it.next();            
            resultado.add(fech.partidosDeUnDia());            
        }

        return resultado;
    }

    List clasificacionGeneral(){
        List resultado = new ArrayList();
        resultado.add(this.numJornada);
        if(this.clasificacionesDeClubes.isEmpty())
            resultado.add("No hay clasificación para esa jornada.");
        else{
            //this.ordenarPorPartidosGanados(); ------> No hace falta.
            Iterator it = this.clasificacionesDeClubes.iterator();
            while(it.hasNext()){
               ClasificacionClub cl = (ClasificacionClub)it.next();
               List datosClasificacion = cl.obtenerDatosClasificacion();
               resultado.add(datosClasificacion);
            }
        }

        return resultado;
    }    

    List rankingAnotadores(){
        List resultado = new ArrayList();
        resultado.add(this.numJornada);
        if(this.clasificacionesDeJugadores.isEmpty())
            resultado.add("No existe la clasificacion para esa jornada.");
        else{
            //this.ordenarPorRendimiento(); -----> No hace falta.
            Iterator it = this.clasificacionesDeJugadores.iterator();
            ClasificacionJugador cj = null;
            while(it.hasNext()){
                cj = (ClasificacionJugador)it.next();
                resultado.add(cj.obtenerDatosClasificacion());
            }
        }                

        return resultado;
    }   

    void anotarResultadoJugador(GregorianCalendar dia, String nomClubLocal, String dniPass, double minutosJugados, int intentos, int punosConseguidos) throws LRACBException{
        Fecha fech = this.buscarFecha(dia);
        fech.anotarResultadoJugador(nomClubLocal, dniPass, minutosJugados, intentos, punosConseguidos);

    }

    void incluirClasificacion(Jugador jug, double minutosTotales, int intentosTotales, int puntosTotales){
        ClasificacionJugador cj = new ClasificacionJugador(puntosTotales, intentosTotales, minutosTotales, jug, this);
        this.clasificacionesDeJugadores.add(cj);
    }

    void definirPartido(GregorianCalendar dia, Club cl, Club cv, Time hora, String TVemite) throws LRACBException{
        boolean participa = false;
        Iterator it = this.fechas.iterator();
        Fecha f1;
        while(it.hasNext() && !participa){
            f1 = (Fecha) it.next();
            participa = f1.participaPartido(cl, cv);
        }

        if(participa)
            throw new LRACBException("Alguno de los clubes ya participa en otro partido de esa jornada.");

        Fecha f2 = this.buscarFecha(dia);
        f2.definirPartido(cl, cv, hora, TVemite);
    }
}
