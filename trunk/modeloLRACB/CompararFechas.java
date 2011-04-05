/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modeloLRACB;

import java.util.Comparator;

/**
 *
 * @author jose
 */
public class CompararFechas implements Comparator{

    public int compare(Object t, Object t1) {
        Fecha f1 = (Fecha)t;
        Fecha f2 = (Fecha)t1;

        return f1.obtenerFecha().compareTo(f2.obtenerFecha());
    }
}
