package tp.model.agents.animaux;

import java.awt.Point;
import java.util.ArrayList;

import tp.model.agents.Agent;
import tp.model.agents.Animal;
import tp.model.agents.Etat;
import tp.model.agents.Sexe;
import tp.model.agents.vegetaux.Arbre;

public abstract class Frelon extends Animal {
	/**
	 * list d'objets de type "Class"
	 * Ces types Class sont paramï¿½trï¿½s par <? extends Animal>
	 * Cela signifie que la classe reprï¿½sentï¿½e par Class doit hï¿½riter de la classe Animal
	 */
	protected ArrayList<Class<? extends Animal>> proies;
	
	/**
	 * nombre de cycle possible avant que le frelon ait de nouveau faim
	 */
	private int cycleSansManger = 20;
	
	/**
	 * cycle passer
	 */
	private int cyclePassed;
	
	public Frelon(Sexe s,Point p) {
		super(s,p);
		proies = new ArrayList<Class<? extends Animal>>();
		proies.add(Abeille.class);
	}
	
	@Override
	public void rencontrer(Agent a) {
		try {
			gestionProie((Animal)a);
		}
		catch (ClassCastException cce) {
			System.err.println(a+" n'est pas un Animal");
		}
		if(a instanceof Arbre) {
			Arbre candidatHebergement= (Arbre) a;
			this.sInstaller(candidatHebergement);
		}
		
	}
	
	protected void gestionProie(Animal a) {
		if(faim && proies.contains(a.getClass())) {
			faim=false;
			System.out.println(this + "à mangé " + a);
		}
	}
	
	
	@Override
	protected void maj() {
		if(faim == true)
		{
			setQteNourriture(getQteNourriture()-1);
		}
		if(cyclePassed > cycleSansManger)
		{
			faim = true;
			cyclePassed = 0;
		}
		++cyclePassed;
	}

	@Override
	protected void seNourrir() {
		if(getNiveauSante() != Etat.Mourant && faim == false)
		{
			setQteNourriture(50);
			ameliorerEtat();
		}
		if(getQteNourriture() == 0) 
		{
			setNiveauSante(Etat.Mourant);
		}
	}
	
}