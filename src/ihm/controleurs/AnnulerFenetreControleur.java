package ihm.controleurs;

import javax.swing.*;
import java.awt.event.*;

public class AnnulerFenetreControleur implements ActionListener {

    private JDialog fenetre;

    public AnnulerFenetreControleur(JDialog fn) {
        fenetre = fn;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	fenetre.dispose();
    }
}
