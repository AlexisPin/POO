package launchers;

import java.awt.Container;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import tp.model.agents.PointPositif;
import tp.model.world.Monde;
import ui.guiSimple.WorldFrame;

public class LauncherGui {
	public static void main(String[] args) {
		//new WorldFrame(new Monde(50));
		new WorldFrame(new Monde(getNbAgentDialog()));
	}

	public static int getNbAgentDialog() {
		SpinnerNumberModel sModel = new SpinnerNumberModel(100, 10, 500, 1);
		JSpinner spinner = new JSpinner(sModel);
		

		JLabel labelSpinner = new JLabel("Nombre d'agents à générer");
		labelSpinner.setLabelFor(spinner);
		
		Container panneauDialog = new JPanel();
		panneauDialog.add(labelSpinner);
		panneauDialog.add(spinner);
		
		JOptionPane.showMessageDialog(null, panneauDialog, "Créer Monde",JOptionPane.QUESTION_MESSAGE);
		
		return (int) spinner.getValue();
	}
}
