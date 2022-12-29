import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Events implements ActionListener {
    JFrame window, eWindow;  //Declara la variable para crear la ventana del editor
    JTextArea textArea;
    JScrollPane scrollPane;
    JMenuBar menuBar;
    JMenu menuFile, menuEdit, menuFormat, menuHelp;
    JMenuItem itemNew, itemOpen, itemSave, itemSaveAs, itemExit, itemUndo, itemRedo,
            itemShortcuts, itemAboutUs, itemComments, itemFind, itemReplace, itemEncrypt, itemDecrypt;
    JMenu menuFont, menuFontSize;
    JMenuItem itemFontCalibri, itemFontCentury, itemFontArial, itemDarkMode,
            itemFontSize8, itemFontSize10, itemFontSize12, itemFontSize14, itemFontSize16;
    FileFunctions file = new FileFunctions(this);
    FormatFunctions format = new FormatFunctions(this);
    UndoManager um = new UndoManager();
    EditFunctions edit = new EditFunctions(this);
    Shortcuts sc = new Shortcuts(this);
    SpellChecker spc = new SpellChecker(this);
    HelpFunctions help = new HelpFunctions(this);
    boolean darkMode = false;
    public void createWindow(){     //Creación de la ventana y sus atributos
        window = new JFrame("Sin título: EasyPad");   //Añade el título a la ventana
        window.setSize(800,600);    //Define el tamaño de la ventana
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null); //Centra la ventana
        Image icon = new ImageIcon(this.getClass().getResource("/icon2.png")).getImage();
        window.setIconImage(icon);
    }
    public void createEWindow(String func){     //Creación de la ventana y sus atributos
        eWindow = new JFrame();
        eWindow.setTitle(func);
        eWindow.setSize(500,500);    //Define el tamaño de la ventana
        eWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        eWindow.setLocationRelativeTo(null); //Centra la ventana
        Image icon = new ImageIcon(this.getClass().getResource("/icon2.png")).getImage();
        eWindow.setIconImage(icon);
    }
    public void createTextArea(){
        textArea = new JTextArea();
        textArea.addKeyListener(sc);
        spc.spellCheck();
        textArea.getDocument().addUndoableEditListener(new UndoableEditListener() {
            @Override
            public void undoableEditHappened(UndoableEditEvent e) {
                um.addEdit(e.getEdit());
            }
        });

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        Border border = BorderFactory.createEmptyBorder();
        textArea.setBorder(BorderFactory.createCompoundBorder(border,   //Crea un borde al area de texto
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        scrollPane = new JScrollPane(textArea,  //Crea ambos scrollbars
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());    //Elimina los bordes creados por el scrollbar
        window.add(scrollPane);
    }
    public void createMenuBar(){

        menuBar = new JMenuBar();
        window.setJMenuBar(menuBar);

        menuFile = new JMenu("Archivo");
        menuBar.add(menuFile);

        menuEdit = new JMenu("Editar");
        menuBar.add(menuEdit);

        menuFormat = new JMenu("Formato");
        menuBar.add(menuFormat);

        menuHelp = new JMenu("Atajos");
        menuBar.add(menuHelp);

        menuBar.setBorder(BorderFactory.createEmptyBorder());
    }
    public void createFileMenu(){
        itemNew = new JMenuItem("Nuevo");
        itemNew.addActionListener(this);
        itemNew.setActionCommand("Nuevo");
        menuFile.add(itemNew);

        itemOpen = new JMenuItem("Abrir");
        itemOpen.addActionListener(this);
        itemOpen.setActionCommand("Abrir");
        menuFile.add(itemOpen);

        itemSave = new JMenuItem("Guardar");
        itemSave.addActionListener(this);
        itemSave.setActionCommand("Guardar");
        menuFile.add(itemSave);

        itemSaveAs = new JMenuItem("Guardar como");
        itemSaveAs.addActionListener(this);
        itemSaveAs.setActionCommand("Guardar como");
        menuFile.add(itemSaveAs);

        itemExit = new JMenuItem("Cerrar");
        itemExit.addActionListener(this);
        itemExit.setActionCommand("Cerrar");
        menuFile.add(itemExit);

        menuFile.setBorder(BorderFactory.createEmptyBorder());
    }
    public void createEditMenu(){
        itemUndo = new JMenuItem("Deshacer");
        itemUndo.addActionListener(this);
        itemUndo.setActionCommand("Deshacer");
        menuEdit.add(itemUndo);

        itemRedo = new JMenuItem("Rehacer");
        itemRedo.addActionListener(this);
        itemRedo.setActionCommand("Rehacer");
        menuEdit.add(itemRedo);

        itemFind = new JMenuItem("Buscar");
        itemFind.addActionListener(this);
        itemFind.setActionCommand("Buscar");
        menuEdit.add(itemFind);

        itemReplace = new JMenuItem("Reemplazar");
        itemReplace.addActionListener(this);
        itemReplace.setActionCommand("Reemplazar");
        menuEdit.add(itemReplace);

        itemEncrypt = new JMenuItem("Encriptar");
        itemEncrypt.addActionListener(this);
        itemEncrypt.setActionCommand("Encriptar");
        menuEdit.add(itemEncrypt);

        itemDecrypt = new JMenuItem("Desencriptar");
        itemDecrypt.addActionListener(this);
        itemDecrypt.setActionCommand("Desencriptar");
        menuEdit.add(itemDecrypt);
    }
    public void createFormatMenu(){
        menuFont = new JMenu("Fuente");
        menuFormat.add(menuFont);

        menuFontSize = new JMenu("Tamaño");
        menuFormat.add(menuFontSize);

        itemDarkMode = new JMenuItem("Modo oscuro");
        itemDarkMode.addActionListener(this);
        itemDarkMode.setActionCommand("Modo oscuro");
        menuFormat.add(itemDarkMode);

        itemFontArial = new JMenuItem("Arial");
        itemFontArial.addActionListener(this);
        itemFontArial.setActionCommand("Arial");
        menuFont.add(itemFontArial);

        itemFontCalibri = new JMenuItem("Calibri");
        itemFontCalibri.addActionListener(this);
        itemFontCalibri.setActionCommand("Calibri");
        menuFont.add(itemFontCalibri);

        itemFontCentury = new JMenuItem("Century");
        itemFontCentury.addActionListener(this);
        itemFontCentury.setActionCommand("Century");
        menuFont.add(itemFontCentury);

        itemFontSize8 = new JMenuItem("8");
        itemFontSize8.addActionListener(this);
        itemFontSize8.setActionCommand("size8");
        menuFontSize.add(itemFontSize8);

        itemFontSize10 = new JMenuItem("10");
        itemFontSize10.addActionListener(this);
        itemFontSize10.setActionCommand("size10");
        menuFontSize.add(itemFontSize10);

        itemFontSize12 = new JMenuItem("12");
        itemFontSize12.addActionListener(this);
        itemFontSize12.setActionCommand("size12");
        menuFontSize.add(itemFontSize12);

        itemFontSize14 = new JMenuItem("14");
        itemFontSize14.addActionListener(this);
        itemFontSize14.setActionCommand("size14");
        menuFontSize.add(itemFontSize14);

        itemFontSize16 = new JMenuItem("16");
        itemFontSize16.addActionListener(this);
        itemFontSize16.setActionCommand("size16");
        menuFontSize.add(itemFontSize16);
    }
    public void createHelpMenu(){
        itemShortcuts = new JMenuItem("Atajos");
        itemShortcuts.addActionListener(this);
        itemShortcuts.setActionCommand("Atajos");
        menuHelp.add(itemShortcuts);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command){
            case "Nuevo": file.newFile(); break;
            case "Abrir": file.openFile(); break;
            case "Guardar": file.save(); break;
            case "Guardar como": file.saveAs(); break;
            case "Cerrar": file.exit(); break;
            case "Arial", "Century", "Calibri": format.setFont(command); break;
            case "size8": format.createFont(8); break;
            case "size10": format.createFont(10); break;
            case "size12": format.createFont(12); break;
            case "size14": format.createFont(14); break;
            case "size16": format.createFont(16); break;
            case "Modo oscuro": format.darkMode(); break;
            case "Deshacer": edit.undo(); break;
            case "Rehacer": edit.redo(); break;
            case "Buscar": edit.find(); break;
            case "Reemplazar": edit.replace(); break;
            case "Encriptar":
                try {
                    edit.encrypt();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "Desencriptar": edit.decrypt(); break;
            case "Atajos": help.Shortcuts(); break;
        }
    }
}
