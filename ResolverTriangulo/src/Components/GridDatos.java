package resolverTriangulo.src.Components;

import javax.swing.*;
import java.awt.*;

public class GridDatos extends JPanel{
    public GridDatos(String title){
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), title));
        setLayout(new GridLayout(7,2,30,8));
    }
}
