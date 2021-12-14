package ui.guiSimple;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import tp.model.agents.PointPositif;
import tp.model.world.Dessinable;
import tp.model.world.Monde;
import tp.model.world.MondeAnimable;

public class PanneauPrincipal extends JPanel {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MondeAnimable monde;

	public PanneauPrincipal(MondeAnimable monde) {
		this.monde=monde;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(Dessinable element :monde.getElementsMonde()) {
			PointPositif coord = convertir(element.getCoord());
			try {
				Image img = ImageIO.read(new File(element.getImage()));
				g.drawImage(img, coord.getX(), coord.getY(), element.getWidth(), element.getHeight(), this);
			} catch (IOException e) {
				g.drawString(element.toString(), coord.getX(), coord.getY());
			}
			
		}
	}
	
	public PointPositif convertir(PointPositif p) {
		int x = p.getX()*this.getWidth()/Monde.getLONGUEUR();
		int y = p.getY()*this.getHeight()/Monde.getLARGEUR();
		return new PointPositif(new Point(x,y));
	}

	
	
	

}
