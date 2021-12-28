package ui.guiSimple;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tp.model.agents.PointPositif;
import tp.model.agents.vegetaux.Arbre;
import tp.model.decor.Ruche;
import tp.model.world.Dessinable;
import tp.model.world.Monde;
import tp.model.world.MondeAnimable;

public class PanneauPrincipal extends JPanel {
	

	private JLabel labelNbrAgent = new JLabel();
	private JLabel labelNbrRuche = new JLabel();
	private int nbrRuche;
	private int nbrAgent;
	private int nbrArbre;
	private JLabel labelNbrArbre = new JLabel();
	
	private static final long serialVersionUID = 1L;
	
	private MondeAnimable monde;

	public PanneauPrincipal(MondeAnimable monde) {
		this.monde=monde;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(Dessinable element :monde.getElementsMonde()) {
			if(element instanceof Ruche)
			{
				nbrRuche += 1;
			}
			if(element instanceof Arbre)
			{
				nbrArbre += 1;
			}
			
			PointPositif coord = convertir(element.getCoord());
			try {
				
				  /*ImageIcon img = new ImageIcon(ImageIO.read(new File(element.getImage())));
				  JLabel imgAnime = new JLabel(img);
				  imgAnime.setLocation(coord.getX(),coord.getY());
				  imgAnime.setSize(element.getWidth(), element.getHeight());*/
				 
				Image img = ImageIO.read(new File(element.getImage()));
				g.drawImage(img, coord.getX(), coord.getY(), element.getWidth(), element.getHeight(), this);
				  //add(imgAnime);
			} catch (IOException e) {
				g.drawString(element.toString(), coord.getX(), coord.getY());
			}
		nbrAgent += 1;
		}
		labelNbrRuche.setText("Il y a " + nbrRuche + " ruche");
		labelNbrAgent.setText("Il y a " + nbrAgent + " agent");
		labelNbrArbre.setText("Il y a " + nbrArbre + " arbre");
		this.add(labelNbrRuche,-1);
		this.add(labelNbrAgent);
		this.add(labelNbrArbre);
		nbrRuche = 0;
		nbrAgent = 0;
		nbrArbre = 0;
		
	}
	
	public PointPositif convertir(PointPositif p) {
		int x = p.getX()*this.getWidth()/Monde.getLONGUEUR();
		int y = p.getY()*this.getHeight()/Monde.getLARGEUR();
		return new PointPositif(new Point(x,y));
	}

	
	
	

}
