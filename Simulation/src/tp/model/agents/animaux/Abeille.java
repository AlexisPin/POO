package tp.model.agents.animaux;

import java.awt.Point;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tp.model.agents.Agent;
import tp.model.agents.Animal;
import tp.model.agents.Etat;
import tp.model.agents.Sexe;
import tp.model.agents.vegetaux.Vegetal;
import tp.model.comportements.Hebergeur;
import tp.model.world.Monde;
/**
 * Abeille est un h�bergeur pour ses parasites (Varroa par exemple)
 * @author bruno
 *
 */
public abstract class Abeille extends Animal implements Hebergeur{
	/**
	 * parasite �ventuel de l'abeille
	 * si l'abeille est parasit�e, passe � true
	 */
	private boolean parasite=false;
	/**
	 * quantit� de miel transport� par l'abeille
	 */
	protected int qteMiel = 0;
	/**
	 * constante donnant la quantit� maximal de miel que l'abeille peut transporter
	 */
	private static final int qteMax = 10;
	
	private Map<Abeille,Varroa> dictionnaireAbeillesParasites = new HashMap<Abeille,Varroa>();
	
	public Abeille(Sexe s, Point p) {
		super(s,p);
		dictionnaireAbeillesParasites.put(this, null);
	}

	@Override
	public void rencontrer(Agent a) {
		if(a instanceof Vegetal && qteMiel<Abeille.qteMax) {
			Vegetal v = (Vegetal)a;
			qteMiel = qteMiel + v.getPortionNectar();
		}
		/* rencontre avec un pr�dateur */
		else if(a instanceof Frelon && getNiveauSante()!=Etat.Mourant) {
			setNiveauSante(Etat.EnDetresse);
			if (a.aFaim()) {setNiveauSante(Etat.Mourant);}
		}
	}

	@Override
	public boolean peutAccueillir(Agent a) {
		/*
		 * l'abeille n'a pas de parasite et l'animal est un Varroa
		 */
		return a instanceof Varroa && !parasite;
	}
	
	@Override
	public boolean accueillir(Agent a) {
		boolean ret = false;
		if(peutAccueillir(a)) {
			dictionnaireAbeillesParasites.replace(this, (Varroa) a);
			parasite = true;
			aggraverEtat();
			ret = true;
		}
		return ret;
	}
	@Override
	protected void maj() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void seNourrir() {
		if(getNiveauSante() != Etat.Mourant)
		{
			setQteNourriture(1);
			ameliorerEtat();
		}
		if(getQteNourriture() == 0) 
		{
			setNiveauSante(Etat.Mourant);
		}
	}
	
	@Override
	public void supprimer(Animal a) {
		if(dictionnaireAbeillesParasites.get(a) != null)
		{
			dictionnaireAbeillesParasites.get(a).setHebergeur(null);
		}
		dictionnaireAbeillesParasites.remove(a);
	}
}
	
