/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package Clases;

import Vistas.Login;
import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.UIManager;


/**
 *
 * @author hawri
 */
public class Miscelanearr {

    public static void main(String[]args){
        FlatDarkLaf.setup();
        try {
            UIManager.setLookAndFeel( new FlatDarkLaf() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        Conexion dt = new Conexion();
        try {
            dt.conectar();
        } catch (Exception e) {
            System.err.println( "Failed to conect DB" );
            System.err.println(e);
        }
        new Login().setVisible(true);
    }

}
