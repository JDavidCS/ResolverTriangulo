package ResolverTriangulo.src.Components;

import javax.swing.JTextField;
import java.awt.event.*;

public class CajaDatos extends JTextField {

    KeyListener input;

    public CajaDatos(){
        setColumns(8);
        only_num();
    }
    
    private void only_num(){
        input = new KeyListener(){
            @Override 
            public void keyTyped(KeyEvent ke){
                char c = ke.getKeyChar();
                if(!Character.isDigit(c) && c != '.'){
                    ke.consume();
                }
                if(c == '.' &&  getText().contains(".")){
                    ke.consume();
                }
            }
            @Override
            public void keyPressed(KeyEvent ke){

            }
            @Override
            public void keyReleased(KeyEvent ke){

            }
        };
        addKeyListener(input);
    }
    
}
