import javax.swing.*;
import java.awt.*;

public class FormatFunctions {
    Events gui;
    Font calibri, century, arial;
    String selectedFont;
    public FormatFunctions(Events gui){
        this.gui = gui;
    }
    public void createFont(int fontSize){
        arial = new Font("Arial", Font.PLAIN,fontSize);
        calibri = new Font("Calibri", Font.PLAIN,fontSize);
        century = new Font("Century", Font.PLAIN,fontSize);
        setFont(selectedFont);
    }
    public void setFont(String font){
        selectedFont = font;
        switch (selectedFont){
            case "Arial":
                gui.textArea.setFont(arial);
                break;
            case "Calibri":
                gui.textArea.setFont(calibri);
                break;
            case "Century":
                gui.textArea.setFont(century);
                break;
        }
    }
    public void darkMode(){
        if(gui.darkMode==false){
            gui.darkMode = true;
            gui.window.getContentPane().setBackground(new Color(32,32,32));
            gui.textArea.setBackground(new Color(32,32,32));
            gui.textArea.setForeground(Color.white);
        }else{
            gui.darkMode = false;
            gui.window.getContentPane().setBackground(Color.white);
            gui.textArea.setBackground(Color.white);
            gui.textArea.setForeground(Color.black);
        }
    }
}
