/*
Copyright 2011 Jose Antonio Martín Fernández


This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>

*/

package modeloLRACB;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jose
 */
class Partido {
    private Time hora;
    private String TVemite;
    private ResultadoLocal rl;
    private ResultadoVisitante rv;
    private Map<String, ResultadoJugador> resultadosJugadores = new HashMap<String, ResultadoJugador>();

    void anotarResultado(int puntosLocal, int puntosVisi){
        this.rl.modificarResultado(puntosLocal);
        this.rv.modificarResultado(puntosVisi);
    }

    String obtenerNomClubVisi(){
        return this.rv.obtenerNomClubVisi();
    }

    List resultadoJugadores(){
        List resultado = new ArrayList();
        resultado.add(this.rl.obtenerResultadoNomClub());
        resultado.add(this.rv.obtenerResultadoNomClub());
        if(!this.resultadosJugadores.isEmpty()){
            resultado.add(this.rl.obtenerNomClubLocal());
            resultado.add(this.rv.obtenerNomClubVisi());
            Iterator it = this.resultadosJugadores.entrySet().iterator();
            ResultadoJugador rj = null;
            while(it.hasNext()){
                Map.Entry e = (Map.Entry)it.next();
                rj = (ResultadoJugador)e.getValue();
                resultado.add(rj.obtenerResultadoJugador(this.rl.obtenerNomClubLocal()));
                resultado.add(rj.obtenerResultadoJugador(this.rv.obtenerNomClubVisi()));
            }
        }

        return resultado;
    }

    List datosDelPartido(){
        List resultado = new ArrayList();
        resultado.add(this.rl.obtenerNomClubLocal());
        resultado.add(this.rv.obtenerNomClubVisi());
        resultado.add(this.hora);
        resultado.add(this.rl.obtenerPabellon());
        resultado.add(this.TVemite);

        return resultado;
    }

    void anotarResultadoJugador(String dniPass, double minutosJugados, int intentos, int puntosConseguidos)throws LRACBException{
        Jugador ju;
        if(this.rl.perteneceJugador(dniPass))
            ju = this.rl.obtenerJugador(dniPass);
        else{
            if(this.rv.perteneceJugador(dniPass))
                ju = this.rv.obtenerJugador(dniPass);
            else
                throw new LRACBException("Este jugador no pertenece a ninguno de los dos clubes de ese partido.");
        }
        ResultadoJugador rj = new ResultadoJugador(minutosJugados, puntosConseguidos, intentos, ju);
        this.resultadosJugadores.put(dniPass, rj);
    }

    boolean participaClub(Club cl, Club cv){
        return (this.rl.participaClub(cl, cv) || this.rv.participaClub(cl, cv));
    }

    Partido(Club cl, Club cv, Time hora, String TVemite) {
        this.hora = hora;
        this.TVemite = TVemite;
        rl = new ResultadoLocal(cl);
        rv = new ResultadoVisitante(cv);
    }



}
