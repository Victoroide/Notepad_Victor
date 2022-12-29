import javax.swing.*;   //Importa todas las librerías para los Eventos
import java.awt.*;

public class GUI {
    Events x = new Events();    //Habilita la clase para usar los eventos
    public static void main(String[] args) {
        new GUI();
    }
    public GUI(){   //Uso de la interfaz
        x.createWindow();
        x.createTextArea();
        x.createMenuBar();
        x.createFileMenu();
        x.createEditMenu();
        x.createFormatMenu();
        x.createHelpMenu();
        x.format.selectedFont = "Arial";
        x.format.createFont(14);


        x.window.setVisible(true);    //Visibiliza la ventana emergente
    }

}