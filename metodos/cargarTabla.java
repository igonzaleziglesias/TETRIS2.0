
package metodos;

import baseDatos.Alumno;
import baseDatos.Quest;
import excepciones.excepcionSql;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class cargarTabla {

    public static void vaciarTabla(JTable tabla) {
        try {
            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
            int filas = tabla.getRowCount();
            for (int i = 0; i < filas; i++) {
                modelo.removeRow(0);
            }

        } catch (Exception e) {
            System.out.println("no se pudo resetear la tabla");
        }
    }

    public static void mostrarTablas(JTable tabla) {

        Quest quest = new Quest();

        ArrayList<Object[]> datos = new ArrayList<Object[]>();

        //ArrayList de alumnos
        try {

            ArrayList<Alumno> lista = quest.selectAll();

            int contador = lista.size();
//        System.out.println(contador);
//        System.out.println(lista.size());

            for (Alumno ele : lista) {
                Object[] dato = {"", "", 1};
//            System.out.println(ele.getName());
                String name, puntos;
                int id;
                name = ele.getName();
                puntos = ele.getPuntos();
                id = ele.getId();

                dato[0] = name;
                dato[1] = puntos;
                dato[2] = id;
//            System.out.println(dato[0]);
                datos.add(dato);
            }
//        System.out.println("");
//        for (Object[] ele : datos) {
//            System.out.println(ele[0]);
//        }

            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
            for (Object[] ele : datos) {
                modelo.addRow(ele);
            }

            tabla.setModel(modelo);
        } catch (excepcionSql e) {

        }
    }
}
