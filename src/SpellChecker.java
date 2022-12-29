
import com.inet.jortho.*;
import com.inet.jorthodictionaries.BookGenerator_es;

public class SpellChecker{
    Events gui;
    public SpellChecker(Events gui){
        this.gui = gui;
    }
    public void spellCheck(){
        com.inet.jortho.SpellChecker.setUserDictionaryProvider(new FileUserDictionary());
        com.inet.jortho.SpellChecker.registerDictionaries(null, null);
        com.inet.jortho.SpellChecker.register(gui.textArea);
    }
}
