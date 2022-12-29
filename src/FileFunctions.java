import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.*;

public class FileFunctions {
    Events gui; //Se declara la variable gui para llamar a la clase Eventos
    String fileName;
    String fileAddress;
    public FileFunctions(Events gui){
        this.gui = gui; //Define gui como función actual de la clase
    }
    public void newFile(){
        gui.textArea.setText("");   //Llama a la clase Eventos para reutilizar el código
        gui.window.setTitle("Sin título: EasyPad");
        fileName = null;
        fileAddress = null;
    }
    public void openFile(){
        FileDialog fd = new FileDialog(gui.window, "Abrir",FileDialog.LOAD);    //Habilita la apertura de archivos
        fd.setVisible(true);
        if(fd.getFile()!=null){
            fileName = fd.getFile();    //Guarda el nombre del archivo
            fileAddress = fd.getDirectory();    //Guarda la dirección del archivo
            gui.window.setTitle(fileName);
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileAddress + fileName));
            // Se necesita la dirección para leer un archivo
            gui.textArea.setText("");
            String line = null;

            while((line = br.readLine())!=null){    //Si el fileName no está vacío (existe), escribe su contenido
                gui.textArea.append(line + "\n");
            }
            br.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"El archivo no se pudo abrir o no existe");
        }

    }
    public void save(){
        if(fileName==null){     //Pregunta si el archivo es nuevo o ya está creado
            saveAs();
        }else{
            try{
                FileWriter fw = new FileWriter(fileAddress + fileName); //Guarda el archivo como tipo texto
                fw.write(gui.textArea.getText());
                fw.close();
                gui.window.setTitle(fileName);

            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "Algo ha fallado al guardar el archivo");
            }
        }
    }
    public void saveAs(){
        FileDialog fd = new FileDialog(gui.window, "Guardar", FileDialog.SAVE); //Agrega la función Guardar al botón
        fd.setVisible(true);

        if(fd.getFile()!=null){
            fileName = fd.getFile();
            fileAddress = fd.getDirectory();
            gui.window.setTitle(fileName);
        }
        try{
            FileWriter fw = new FileWriter(fileAddress + fileName);
            fw.write(gui.textArea.getText());
            fw.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"El archivo no se pudo abrir o no existe");
        }
    }
    public void exit(){
        System.exit(0);
    }
}
