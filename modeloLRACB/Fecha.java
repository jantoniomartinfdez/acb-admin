/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modeloLRACB;

import java.sql.Time;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/**
 *
 * @author jose
 */
class Fecha {
    private GregorianCalendar dia;
    private Map<String, Partido> partidos = new HashMap<String, Partido>();

    Fecha(GregorianCalendar dia) {
        this.dia = dia;
    }

    GregorianCalendar obtenerFecha(){
        return this.dia;
    }

    boolean esAnterior(List<GregorianCalendar> listaDeFechas){
        ListIterator it = listaDeFechas.listIterator();
        GregorianCalendar diaAux;
        boolean anterior = false;

        while(it.hasNext() && !anterior){
            diaAux = (GregorianCalendar) it.next();
            anterior = diaAux.before(this.dia);
        }

        return anterior;
    }

    String obtenerNomClubVisi(String nomClubLocal){
        return this.partidos.get(nomClubLocal).obtenerNomClubVisi();
    }

    void anotarResultado(String nomClubLocal, int puntosLocal, int puntosVisi) throws LRACBException{
        Partido par = this.buscarPartido(nomClubLocal);
        par.anotarResultado(puntosLocal, puntosVisi);
    }

    List resultadoJugadores(String nomClubLocal) throws LRACBException{
        Partido par = this.buscarPartido(nomClubLocal);
        return par.resultadoJugadores();
    }

    private Partido buscarPartido(String nomClubLocal) throws LRACBException{
        if(!this.partidos.containsKey(nomClubLocal))
            throw new LRACBException("No existe un partido en el que juegue el club " + nomClubLocal + "\no en esta fecha no haya partidos definidos.");
        return this.partidos.get(nomClubLocal);
    }

    List partidosDeUnDia(){
        List resultado = new ArrayList();
        if(this.partidos.isEmpty()){
            resultado.add("No están definidos los partidos de ese día.");
        }
        else{
            Iterator it = this.partidos.entrySet().iterator();
            Partido par = null;
            while(it.hasNext()){
                Map.Entry e = (Map.Entry)it.next();
                par = (Partido)e.getValue();
                resultado.add(par.datosDelPartido());
            }
        }

        return resultado;
    }

    void anotarResultadoJugador(String nomClubLocal, String dniPass, double minutosJugados, int intentos, int puntosConseguidos) throws LRACBException{
        Partido par = this.buscarPartido(nomClubLocal);
        par.anotarResultadoJugador(dniPass, minutosJugados, intentos, puntosConseguidos);
    }

    boolean participaPartido(Club cl, Club cv){
        Iterator it = this.partidos.entrySet().iterator();
        Partido par;
        boolean participa = false;
        while(it.hasNext() && !participa){
            Map.Entry e = (Map.Entry)it.next();
            par = (Partido)e.getValue();
            participa = par.participaClub(cl, cv);
        }

        return participa;
    }

    void definirPartido(Club cl, Club cv, Time hora, String TVemite){
        Partido par = new Partido(cl, cv, hora, TVemite);
        this.partidos.put(cl.obtenerNombre(), par);
    }
}
