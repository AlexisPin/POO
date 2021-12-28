package tp.model.agents.animaux;

import java.awt.Point;

import tp.model.decor.Decor;
import tp.model.decor.Ruche;
import tp.model.agents.Agent;
import tp.model.agents.Etat;
import tp.model.agents.Sexe;
import tp.model.comportements.Hebergeur;

public class AbeilleDomestique extends Abeille {

	public AbeilleDomestique(Sexe s, Point p, Ruche r) {
		super(s,p);
		hebergeur= r;
		r.accueillir(this);
	}

	public void rapporterMiel(Hebergeur hebergeur) {
		rentrerHebergeur();
		((Ruche) hebergeur).ajoutAbeillePresente(this);
		((Ruche) hebergeur).setStockMiel(qteMiel);
	}
	
	public void abeilleEstRentreDormir(Hebergeur hebergeur) {
		if(getCoord().getX() == hebergeur.getCoord().getX()) 
		{
			((Ruche) hebergeur).ajoutAbeillePresente(this);
		}
	}
	@Override
	protected void maj() {
		abeilleEstRentreDormir(hebergeur);
		if(qteMiel == getQtemax())
		{
			rapporterMiel(hebergeur);
		}
		setQteNourriture(getQteNourriture()-1);
	}
	
	
	@Override
	public Object clone() {
		AbeilleDomestique a = new AbeilleDomestique(getSexe(), new Point(getCoord().getX(),getCoord().getY()), (Ruche)hebergeur);
		return a;
	}
}
