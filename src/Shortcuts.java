import com.inet.jortho.CheckerListener;

import java.awt.event.*;

public class Shortcuts implements KeyListener {
    Events gui;
    EditFunctions ef;
    CheckerListener cl;
    public Shortcuts(Events gui){
        this.gui = gui;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.isControlDown() && e.getKeyCode()==KeyEvent.VK_G){
            gui.file.save();
        }
        if (e.isControlDown() && e.getKeyCode()==KeyEvent.VK_W){
            gui.file.openFile();
        }
        if (e.isControlDown() && e.isShiftDown() && e.getKeyCode()==KeyEvent.VK_S){
            gui.file.saveAs();
        }
        if (e.isControlDown() && e.getKeyCode()==KeyEvent.VK_Z){
            gui.edit.undo();
        }
        if (e.isControlDown() && e.getKeyCode()==KeyEvent.VK_Y){
            gui.edit.redo();
        }
        if (e.isControlDown() && e.getKeyCode()==KeyEvent.VK_SPACE){
            gui.format.darkMode();
        }
        if (e.isControlDown() && e.getKeyCode()==KeyEvent.VK_F){
            gui.edit.find();
        }
        if (e.isControlDown() && e.getKeyCode()==KeyEvent.VK_R){
            gui.edit.replace();
        }
        if (e.isControlDown() && e.getKeyCode()==KeyEvent.VK_E){
            try {
                gui.edit.encrypt();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        if (e.isControlDown() && e.getKeyCode()==KeyEvent.VK_D){
            gui.edit.decrypt();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
