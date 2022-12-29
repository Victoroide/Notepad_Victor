import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.*;
import java.util.ArrayList;
import java.util.*;
import java.util.List;

public class EditFunctions {
    Events gui;
    public EditFunctions(Events gui){
        this.gui=gui;
    }
    public void undo(){
        gui.um.undo();
    }
    public void redo (){
        gui.um.redo();
    }
    public void find(){
        final String inputValue = JOptionPane.showInputDialog("Buscar");
        final int l1 = gui.textArea.getText().indexOf(inputValue);
        final int l2 = inputValue.length();

        if (l1 == -1) {
            JOptionPane.showMessageDialog(null, "Valor de búsqueda no encontrado");
        } else {
            gui.textArea.select(l1, l2+l1);
        }
    }
    public void replace(){
        String txt = gui.textArea.getText();
        String txt2 = JOptionPane.showInputDialog("Palabra a buscar");
        String txt3 = JOptionPane.showInputDialog("Palabra a reemplazar");

        if (txt.contains(txt2)) {
            gui.textArea.setText(txt.replaceAll(txt2, txt3));
        }
    }
    public void encrypt() throws Exception {
        // Clave secreta utilizada para encriptar y desencriptar el texto
        byte[] keyBytes = "mi clave secreta".getBytes();
        SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");

        // Obtén el contenido del JTextArea como una cadena de texto
        String textoPlano = gui.textArea.getText();

        try {
            // Crea un objeto Cipher utilizando AES
            Cipher cipher = Cipher.getInstance("AES");

            // Inicializa el Cipher en modo de encriptación
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            // Encripta el texto y convierte el resultado a una cadena de caracteres hexadecimales
            String textoEncriptado = new BigInteger(cipher.doFinal(textoPlano.getBytes())).toString(16);

            // Asigna el texto encriptado al JTextArea
            gui.textArea.setText(textoEncriptado);
        } catch (Exception e) {
            // Maneja el error
        }
    }
    public void decrypt() {
        // Clave secreta utilizada para encriptar y desencriptar el texto
        byte[] keyBytes = "mi clave secreta".getBytes();
        SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");

        // Obtén el contenido del JTextArea como una cadena de caracteres hexadecimales
        String textoEncriptado = gui.textArea.getText();

        try {
            // Crea un objeto Cipher utilizando AES
            Cipher cipher = Cipher.getInstance("AES");

            // Inicializa el Cipher en modo de desencriptar
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            // Convierte la cadena de caracteres hexadecimales a una cadena de bytes
            byte[] bytesEncriptados = new BigInteger(textoEncriptado, 16).toByteArray();

            // Desencripta la cadena de bytes y convierte el resultado a una cadena de texto
            String textoPlano = new String(cipher.doFinal(bytesEncriptados));

            // Asigna el texto desencriptado al JTextArea
            gui.textArea.setText(textoPlano);
        } catch (Exception e) {
            // Maneja el error
        }
    }

}
