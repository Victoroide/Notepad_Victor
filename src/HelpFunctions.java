import javax.swing.*;

public class HelpFunctions {
    Events gui;
    public HelpFunctions(Events gui){
        this.gui = gui;
    }
    public void Shortcuts(){
        StringBuilder sb = new StringBuilder();
        sb.append("Nuevo texto:                    CTRL + N");
        sb.append("\nAbrir un texto:                 CTRL + W");
        sb.append("\nGuardar:                           CTRL + G");
        sb.append("\nGuardar como:                CTRL + SHIFT + S");
        sb.append("\nDeshacer:                        CTRL + Z");
        sb.append("\nRehacer:                          CTRL + Y");
        sb.append("\nBuscar:                             CTRL + F");
        sb.append("\nReemplazar:                    CTRL + R");
        sb.append("\nEncriptar:                         CTRL + E");
        sb.append("\nDesencriptar:                   CTRL + D");
        sb.append("\nCambiar modo oscuro:   CTRL + SPACE");
        JOptionPane.showMessageDialog(null, sb.toString());
    }
}
