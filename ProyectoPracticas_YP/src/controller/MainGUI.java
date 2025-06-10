package controller;

import java.awt.EventQueue;

import view.VentanaGrafica;

/**
 * Clase Main (interfaz gráfica) del sistema Akihabara Market
 * ----------------------------------------------------------
 * Punto de entrada para iniciar la aplicación con interfaz gráfica (GUI).
 * Lanza la ventana principal `VentanaGrafica` de forma segura en el hilo de eventos de AWT/Swing.
 */

public class MainGUI {
	public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
            	// Crea la ventana gráfica y la hace visible
                VentanaGrafica frame = new VentanaGrafica();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
