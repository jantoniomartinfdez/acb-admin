/*
Copyright 2011 Jose Antonio Martín Fernández


This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>

*/
package modeloLRACB;

import java.sql.Time;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jose
 */
public class LRACB {
    private static LRACB miLRACB;

    private Map<Integer, Jornada> jornadas = new HashMap<Integer, Jornada>();
    private Map<String, Club> clubes = new HashMap<String, Club>();
    private Map<String, Jugador> jugadores = new HashMap<String, Jugador>();

    private LRACB() {        
    }

    public static LRACB getLRACB(){
        if(LRACB.miLRACB == null){
            LRACB. miLRACB = new LRACB();
        }

        return LRACB.miLRACB;
    }

    public void definirJornada(List<GregorianCalendar> listaDeFechas) throws LRACBException{
        Jornada jAnt = this.obtenerUltimaJornada();
        if(jAnt != null){
            boolean anterior = jAnt.esAnterior(listaDeFechas);
            if(anterior)
                throw new LRACBException("Las fehcas son anteriores a las de la jornada anterior.");
            else{
                int numJornada = jAnt.obtenerNumero();
                if(numJornada == 34)
                    throw new LRACBException("Ya están definidas las 34 jornadas.");
                else{
                    Jornada j = new Jornada(numJornada + 1, listaDeFechas);
                    this.jornadas.put(numJornada + 1, j);
                }
            }
        }
        else{
            Jornada j = new Jornada(1, listaDeFechas);
            this.jornadas.put(1, j);
        }
    }

    public void definirPartido(int numJornada, GregorianCalendar dia, String nomClubLocal, String nomClubVisi, Time hora, String TVemite) throws LRACBException{
        Club cl = this.buscarClub(nomClubLocal);
        Club cv = this.buscarClub(nomClubVisi);
        Jornada jor = this.buscarJornada(numJornada);
        jor.definirPartido(dia, cl, cv, hora, TVemite);
    }

    public List evolucionJugador(String dniPass) throws LRACBException{
        Jugador ju = this.buscarJugador(dniPass);
        return ju.evolucionJugador();
    }

    public void incluirClub(String nombre, String nombreEntrenador, String pabellon) throws LRACBException{
        if(this.existeClub(nombre))
            throw new LRACBException("Ya existe un Club con ese nombre.");
        else{
            Club c = new Club(nombre, nombreEntrenador, pabellon);
            this.clubes.put(nombre, c);
        }
    }

    public void incluirJugador(String nomClub, String dniPass, String nombreJugador, GregorianCalendar fechaNac, double altura, double peso, String posicion, String nacionalidad, int numero) throws LRACBException{
        if(this.existeJugador(dniPass))
           throw new LRACBException("Ya existe el jugador con ese dni o pasaporte.");
        else{
            Club club = this.buscarClub(nomClub);
            Jugador ju = new Jugador(nombreJugador, dniPass, fechaNac, altura, posicion, peso, nacionalidad, numero, club);
            club.ficharJugador(ju);
            this.jugadores.put(dniPass, ju);
        }
    }

    public List rankingJugadores(int numJornada) throws LRACBException{
        Jornada jor = this.buscarJornada(numJornada);
        return jor.rankingAnotadores();
    }

    public List resultadoJugadores(int numJornada, GregorianCalendar dia, String nomClubLocal) throws LRACBException{
        Jornada jor = this.buscarJornada(numJornada);
        return jor.resultadoJugadores(dia, nomClubLocal);
    }

    public List verJugadoresDeClub(String nomClub) throws LRACBException{
        Club club = this.buscarClub(nomClub);
        return club.jugadoresDeClub();
    }

    public List verPartidosDeJornada(int numJornada) throws LRACBException{
        Jornada jor = this.buscarJornada(numJornada);
        return jor.verPartidosDeJornada();
    }

    public void anotarResultado(int numJornada, GregorianCalendar dia, String nomClubLocal, int puntosLocal, int puntosVisi) throws LRACBException{
        Jornada jor1 = this.buscarJornada(numJornada);
        jor1.anotarResultado(dia, nomClubLocal, puntosLocal, puntosVisi);
        String nomClubVisi = jor1.obtenerNomCLubVisi(dia, nomClubLocal);
        if(numJornada != 1){
            Jornada jorAnterior = this.buscarJornada(numJornada - 1);
            String[] nc = new String[2];
            nc[0] = nomClubLocal;
            nc[1] = nomClubVisi;
            for(int i=0; i<2; i++){
                int[] resultJorAnterior = jorAnterior.obtenerResultados(nc[i]);
                Club club = this.buscarClub(nc[i]);
                if(i == 0){/*Es local*/
                    if(puntosLocal > puntosVisi)//Ha ganado.
                        jor1.definirClasificacionClub(club, (Integer)resultJorAnterior[0]+1, (Integer)resultJorAnterior[1], (Integer)resultJorAnterior[2]+puntosLocal, (Integer)resultJorAnterior[3]+puntosVisi);
                    else
                        jor1.definirClasificacionClub(club, (Integer)resultJorAnterior[0], (Integer)resultJorAnterior[1]+1, (Integer)resultJorAnterior[2]+puntosLocal, (Integer)resultJorAnterior[3]+puntosVisi);
                }
                else{//Es visitante
                    if(puntosLocal > puntosVisi)//Ha ganado.
                        jor1.definirClasificacionClub(club, (Integer)resultJorAnterior[0]+1, (Integer)resultJorAnterior[1], (Integer)resultJorAnterior[2]+puntosVisi, (Integer)resultJorAnterior[3]+puntosLocal);
                    else
                        jor1.definirClasificacionClub(club, (Integer)resultJorAnterior[0], (Integer)resultJorAnterior[1]+1, (Integer)resultJorAnterior[2]+puntosVisi, (Integer)resultJorAnterior[3]+puntosLocal);
                }
            }
        }
    }

    public void anotarResultadoJugador(int numJornada, GregorianCalendar dia, String nomClubLocal, String dniPass, double minutosJugados, int intentos, int puntosConseguidos) throws LRACBException{
        Jornada jor = this.buscarJornada(numJornada);
        jor.anotarResultadoJugador(dia, nomClubLocal, dniPass, minutosJugados, intentos, puntosConseguidos);
        Jugador jug = this.buscarJugador(dniPass);
        int[] resulUltimaClasi = jug.obtenerClasificacion();
        jor.incluirClasificacion(jug, (Integer)resulUltimaClasi[0]+minutosJugados, (Integer)resulUltimaClasi[1]+intentos, (Integer)resulUltimaClasi[2]+puntosConseguidos);
    }

    public List clasificacionGeneral(int numJornada) throws LRACBException{
        Jornada jor = this.buscarJornada(numJornada);
        return jor.clasificacionGeneral();
    }





    private Jornada obtenerUltimaJornada(){
        boolean existeJ = false;
        int i;
        for(i=1; i<=34 && !existeJ; i++){
            existeJ = this.jornadas.containsKey(i);
        }//Puede que al salir del bucle se me incremente en 1 la i, tener en cuenta.

        if(!existeJ)
            return null;
        else
            return this.jornadas.get(i);
    }

    private Club buscarClub(String nc) throws LRACBException{
        if(this.existeClub(nc))
            return this.clubes.get(nc);
        else
            throw new LRACBException("No existe un club llamado " + nc);
    }

    private Jornada buscarJornada(int numJornada) throws LRACBException{
        if(this.jornadas.containsKey(numJornada))
            return this.jornadas.get(numJornada);
        else
            throw new LRACBException("No existe la jornada numero " + numJornada + " en el sistema.");
    }

    private Jugador buscarJugador(String dniPass) throws LRACBException{
        if(!this.existeJugador(dniPass))
            throw new LRACBException("No existe un jugador con ese DNI o Pasaporte.");
        else
           return this.jugadores.get(dniPass);
    }

    private boolean existeClub(String nc){
        return this.clubes.containsKey(nc);
    }

    private boolean existeJugador(String dniPass){
        return this.jugadores.containsKey(dniPass);
    }
}
