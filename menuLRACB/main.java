/*
Copyright 2011 Jose Antonio Martín Fernández


This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>

*/
package menuLRACB;

/**
 *
 * @author jose
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Time;
import modeloLRACB.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ListIterator;


public class main {

    public static int MenuPrincipal() throws Exception, NumberFormatException{
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("========== MODELO   L R A C B: ==========\n" +
                "Seleccione una opción:\n" +
                "   1. Zona Privada\n" +
                "   2. Zona Cliente\n" +
                "   3. Terminar\n" +
                "==========================================\n");

        return (Integer.parseInt(entrada.readLine()));

    }
    public static int menuAdministrador() throws Exception, NumberFormatException{
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("========MENU ADMINISTRADOR ========\n\n" +
                "========Seleccione una opción: ========\n" +
                "   1. Definir Jornada\n" +
                "   2. Incluir Club\n" +
                "   3. Incluir Jugador\n" +
                "   4. Definir Partido\n" +
                "   5. Anotar Resultado\n" +
                "   6. Anotar Resultado Jugador\n" +
                "   7. Abandonar Sesión\n"+
                "========================================\n");

        return (Integer.parseInt(entrada.readLine()));

    }
    public static int menuCliente() throws Exception, NumberFormatException{
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("========MENU CLIENTE ========\n\n" +
                "========Seleccione una opción: ========\n" +
                "   1. Ver partidos de una jornada\n" +
                "   2. Ver jugadores de un club\n"+
                "   3. Mostrar la clasificación general\n"+
                "   4. Resultado de jugadores de un partido\n"+
                "   5. Ranking de anotadores\n"+                
                "   6. Mostrar evolución de un jugador\n"+
                "   7. Abandonar Sesión\n"+
                "========================================\n");

        return (Integer.parseInt(entrada.readLine()));

    }


    public static void main(String[] args) throws IOException, LRACBException, Exception, NumberFormatException {
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
        String nombreUser, clave;

        LRACB lrACB = LRACB.getLRACB();

        
        boolean terminar=false;
        
        while(!terminar){
            int opcion=MenuPrincipal();
            try{
                switch(opcion){
                    case 1:
                        boolean terminar2 = false;
                        System.out.println("Introduzca su nombre de usuario: ");
                        nombreUser = entrada.readLine();
                        System.out.println("Introduzca su contraseña: ");
                        clave = entrada.readLine();
                        if(nombreUser.equals("admin") && clave.equals("admin")){
                            while(!terminar2){
                                int opcion2 = menuAdministrador();
                                switch(opcion2){
                                    case 1:
                                        List<GregorianCalendar> listaF = new ArrayList<GregorianCalendar>();
                                        int numDias, dia, mes, anio;
                                        System.out.println("Numero de dias de la jornada: ");
                                        numDias = Integer.parseInt(entrada.readLine());
                                        for(int i=0; i<numDias; i++){
                                            System.out.println("Introduce el dia: ");
                                            dia = Integer.parseInt(entrada.readLine());
                                            System.out.println("Introduce el mes: ");
                                            mes = Integer.parseInt(entrada.readLine());
                                            System.out.println("Introduce el año: ");
                                            anio = Integer.parseInt(entrada.readLine());
                                            GregorianCalendar fecha = new GregorianCalendar(anio, mes, dia);
                                            listaF.add(fecha);
                                        }
                                        
                                        lrACB.definirJornada(listaF);
                                        break;

                                    case 2:
                                        String nc, nomEntrenador, pabellon;
                                        System.out.println("Nombre del Club: ");
                                        nc = entrada.readLine();
                                        System.out.println("Nombre del entrenador: ");
                                        nomEntrenador = entrada.readLine();
                                        System.out.println("Nombre del pabellón: ");
                                        pabellon = entrada.readLine();
                                        
                                        lrACB.incluirClub(nc, nomEntrenador, pabellon);
                                        break;

                                    case 3:
                                        String nomClub, dniPass, nombre, posicion, nacionalidad;
                                        int dia2, mes2, anio2;
                                        GregorianCalendar fechaNac;
                                        double altura, peso;
                                        int numero;

                                        System.out.println("Introduce club del jugador: ");
                                        nomClub = entrada.readLine();
                                        System.out.println("DNI o Pasaporte:");
                                        dniPass = entrada.readLine();
                                        System.out.println("Nombre del Jugador:");
                                        nombre = entrada.readLine();
                                        System.out.println("Introduce el dia de su nacimiento: ");
                                        dia2 = Integer.parseInt(entrada.readLine());
                                        System.out.println("Introduce el mes de su nacimiento: ");
                                        mes2 = Integer.parseInt(entrada.readLine());
                                        System.out.println("Introduce el año de su nacimiento: ");
                                        anio2 = Integer.parseInt(entrada.readLine());
                                        fechaNac = new GregorianCalendar(anio2, mes2, dia2);
                                        System.out.println("Altura del jugador:");
                                        altura = Double.parseDouble(entrada.readLine());
                                        System.out.println("Posición de juego:");
                                        posicion = entrada.readLine();
                                        System.out.println("Peso del jugador:");
                                        peso = Double.parseDouble(entrada.readLine());
                                        System.out.println("Pais del jugador:");
                                        nacionalidad = entrada.readLine();
                                        System.out.println("Dorsal del jugador:");
                                        numero = Integer.parseInt(entrada.readLine());

                                        lrACB.incluirJugador(nomClub, dniPass, nombre, fechaNac, altura, peso, posicion, nacionalidad, numero);
                                        break;

                                    case 4:
                                        int idJornada, dia3, mes3, anio3, hora, minuto;
                                        String nomClubLocal, nomClubVisi, tv;
                                        Time horario;
                                        GregorianCalendar idFecha;

                                        System.out.println("Introduce la jornada del partido:");
                                        idJornada = Integer.parseInt(entrada.readLine());
                                        System.out.println("Introduce el dia: ");
                                        dia3 = Integer.parseInt(entrada.readLine());
                                        System.out.println("Introduce el mes: ");
                                        mes3 = Integer.parseInt(entrada.readLine());
                                        System.out.println("Introduce el año: ");
                                        anio3 = Integer.parseInt(entrada.readLine());
                                        idFecha = new GregorianCalendar(anio3, mes3, dia3);
                                        System.out.println("Club Local:");
                                        nomClubLocal = entrada.readLine();
                                        System.out.println("Club Visi:");
                                        nomClubVisi = entrada.readLine();
                                        System.out.println("Introduce la hora del partido:");
                                        hora = Integer.parseInt(entrada.readLine());
                                        System.out.println("Introduce ahora los minutos:");
                                        minuto = Integer.parseInt(entrada.readLine());
                                        long milisegundos = hora*3600000 + minuto*60000;
                                        horario = new Time(milisegundos);
                                        System.out.println("Cadena de TV que emite el partido:");
                                        tv = entrada.readLine();

                                        lrACB.definirPartido(idJornada, idFecha, nomClubLocal, nomClubVisi, horario, tv);
                                        break;

                                    case 5:
                                        int idJornada2, puntosLocal, puntosVisi, dia4, mes4, anio4;
                                        String nomClubLocal2;
                                        GregorianCalendar idFecha2;

                                        System.out.println("Introduce la jornada del partido:");
                                        idJornada2 = Integer.parseInt(entrada.readLine());
                                        System.out.println("Introduce el dia: ");
                                        dia4 = Integer.parseInt(entrada.readLine());
                                        System.out.println("Introduce el mes: ");
                                        mes4 = Integer.parseInt(entrada.readLine());
                                        System.out.println("Introduce el año: ");
                                        anio4 = Integer.parseInt(entrada.readLine());
                                        idFecha2 = new GregorianCalendar(anio4, mes4, dia4);
                                        System.out.println("Introduce el nombre del club local para identificar el partido:");
                                        nomClubLocal2 = entrada.readLine();
                                        System.out.println("Puntos del equipo local:");
                                        puntosLocal = Integer.parseInt(entrada.readLine());
                                        System.out.println("Puntos del equipo visitante:");
                                        puntosVisi = Integer.parseInt(entrada.readLine());

                                        lrACB.anotarResultado(idJornada2, idFecha2, nomClubLocal2, puntosLocal, puntosVisi);
                                        break;

                                    case 6:
                                        int idJornada3, intentos, puntos, dia5, mes5, anio5;
                                        double minutos;
                                        String nomClubLocal3, dniPass2;
                                        GregorianCalendar idFecha3;

                                        System.out.println("Introduce la jornada del partido:");
                                        idJornada3 = Integer.parseInt(entrada.readLine());
                                        System.out.println("Introduce el dia: ");
                                        dia5 = Integer.parseInt(entrada.readLine());
                                        System.out.println("Introduce el mes: ");
                                        mes5 = Integer.parseInt(entrada.readLine());
                                        System.out.println("Introduce el año: ");
                                        anio5 = Integer.parseInt(entrada.readLine());
                                        idFecha3 = new GregorianCalendar(anio5, mes5, dia5);
                                        System.out.println("Introduce el nombre del club local para identificar el partido:");
                                        nomClubLocal3 = entrada.readLine();
                                        System.out.println("DNI o Pasaporte del jugador:");
                                        dniPass2 = entrada.readLine();
                                        System.out.println("Introduce sus minutos jugados:");
                                        minutos = Integer.parseInt(entrada.readLine());
                                        System.out.println("Introduce sus intentos:");
                                        intentos = Integer.parseInt(entrada.readLine());
                                        System.out.println("Introduce los puntos conseguidos por el jugador:");
                                        puntos = Integer.parseInt(entrada.readLine());

                                        lrACB.anotarResultadoJugador(idJornada3, idFecha3, nomClubLocal3, dniPass2, minutos, intentos, puntos);
                                        break;

                                    case 7:
                                        terminar2 = true;
                                        break;
                                }
                            }
                        }
                        else{
                            System.out.println("Login o Pass     INCORRECTOS\n");
                            terminar2 = true;
                       }                        

                       break;

                    case 2:
                        boolean terminar3 = false;
                        while(!terminar3){
                            int opcion3 = menuCliente();
                            switch(opcion3){
                                case 1:
                                    int idJornada;
                                    List lista;

                                    System.out.println("Introduzca la jornada concreta:");
                                    idJornada = Integer.parseInt(entrada.readLine());
                                    lista = lrACB.verPartidosDeJornada(idJornada);
                                    System.out.println("Jornada número " + lista.get(0) + ":\n");
                                    ListIterator it1 = lista.listIterator(1);
                                    while(it1.hasNext()){
                                        if(lista.size() > 2){
                                            ListIterator it1Aux = ((List)it1.next()).listIterator();
                                            while(it1Aux.hasNext()){
                                                System.out.println(it1Aux.next());
                                            }
                                        }
                                        else//Si no hay partidos en la jornada.
                                            System.out.println(it1.next());
                                    }
                                    break;

                                case 2:
                                    String nomClub;
                                    List lista2;

                                    System.out.println("Escriba el nombre del club:");
                                    nomClub = entrada.readLine();                                    
                                    lista2 = lrACB.verJugadoresDeClub(nomClub);                                    
                                    ListIterator it2 = lista2.listIterator();                                    
                                    while(it2.hasNext()){
                                        System.out.println(it2.next());
                                    }

                                    break;

                                case 3:
                                    int numJor;
                                    List lista3;

                                    System.out.println("Introduza la jornada en la que desea ver la clasificacion:");
                                    numJor = Integer.parseInt(entrada.readLine());
                                    lista3 = lrACB.clasificacionGeneral(numJor);
                                    ListIterator it3 = lista3.listIterator();
                                    while(it3.hasNext()){
                                        System.out.println(it3.next());
                                    }

                                    break;

                                case 4:
                                    int idJ, dia5, mes5, anio5;
                                    String idPar;
                                    GregorianCalendar f;
                                    List lista4;

                                    System.out.println("Introduzca una jornada concreta:");
                                    idJ = Integer.parseInt(entrada.readLine());
                                    System.out.println("Introduza el nombre del club local para identificar el partido:");
                                    idPar = entrada.readLine();
                                    System.out.println("Introduce el dia: ");
                                    dia5 = Integer.parseInt(entrada.readLine());
                                    System.out.println("Introduce el mes: ");
                                    mes5 = Integer.parseInt(entrada.readLine());
                                    System.out.println("Introduce el año: ");
                                    anio5 = Integer.parseInt(entrada.readLine());
                                    f = new GregorianCalendar(anio5, mes5, dia5);
                                    lista4 = lrACB.resultadoJugadores(idJ, f, idPar);
                                    ListIterator it4 = lista4.listIterator();
                                    while(it4.hasNext()){
                                        System.out.println(it4.next());
                                    }

                                    break;

                                case 5:
                                    int idJor;
                                    List lista5;

                                    System.out.println("Introduzaca la jornada para ver el ranking:");
                                    idJor = Integer.parseInt(entrada.readLine());
                                    lista5 = lrACB.rankingJugadores(idJor);
                                    ListIterator it5 = lista5.listIterator();
                                    while(it5.hasNext()){
                                        System.out.println(it5.next());
                                    }

                                    break;

                                case 6:
                                    String idJugador;
                                    List lista6;

                                    System.out.println("Introduzca el DNI o Pasaporte para encontrarlo:");
                                    idJugador = entrada.readLine();
                                    lista6 = lrACB.evolucionJugador(idJugador);
                                    ListIterator it6 = lista6.listIterator();
                                    while(it6.hasNext()){
                                        System.out.println(it6.next());
                                    }

                                    break;

                                case 7:
                                    terminar3 = true;
                                    break;
                            }
                        }
                        
                        break;

                    case 3:
                        terminar = true;
                        break;
                }
            }
            catch(Exception ex/*LRACBException ex*/){
                System.out.println(ex);
            }
        }
      }    
}
