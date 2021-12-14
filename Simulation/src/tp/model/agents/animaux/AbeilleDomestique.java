package tp.model.agents.animaux;

import java.awt.Point;

import tp.model.decor.Decor;
import tp.model.decor.Ruche;
import tp.model.agents.Agent;
import tp.model.agents.Sexe;

public class AbeilleDomestique extends Abeille {

	public AbeilleDomestique(Sexe s, Point p, Ruche r) {
		super(s,p);
		hebergeur= r;
		r.accueillir(this);
	}

	@Override
	public Object clone() {
		AbeilleDomestique a = new AbeilleDomestique(getSexe(), new Point(getCoord().getX(),getCoord().getY()), (Ruche)hebergeur);
		return a;
	}

	public void rapporterMiel(Ruche r) {
		rentrerHebergeur();
		r.ajoutAbeillePresente(this);
		r.setStockMiel(qteMiel);
	}
	
	public boolean abeilleEstRentreDormir(Ruche r) {
		boolean ret = false;
		if(getCoord().getX() == ((Decor) hebergeur).getCoord().getX()) 
		{
			r.ajoutAbeillePresente(this);
			ret = true;
		}
		return ret;
	}
}
