package tp.model.agents;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
//pour l'exemple d�taill� de aggraverEtat
import java.util.ListIterator;

import tp.model.comportements.Deplacable;
import tp.model.comportements.Hebergeur;
import tp.model.decor.Decor;
import tp.model.world.Monde;
/**
 * Cette classe mod�lise un Animal dans la simulation
 * @author bruno
 *
 */
/* 
 * abstract � partir du TP2 + d�placement des m�thodes/attributs du TP1 concernant les agents dans la classe agent:
 * attributs de classe 
	private static int currentId = 0;
	
	attributs d'instance:
	private int id;
	protected int age;
	protected PointPositif coord; //question subsdiaire du tp2 + solution pr�sent�e au cours 4
	//protected Point coord;
	 
	m�thodes:
	public Agent(Point coord)
	public Agent()
	
	equals, hascode,tostring (sans le sexe)
	getCoord, setAge, vieillir
	
	getUniqueId
	
	Attention: rencontrer(Agent a) devient abstrait 
 */
public abstract class Animal extends Agent implements Deplacable {
	
	/** �tat de sant� de l'animal */
	private Etat etat=Etat.Normal;
	/** sexe de l'animal */
	private Sexe sexe;
	/** hebergeur de l'animal */
	protected Hebergeur hebergeur;
	
	protected int initQteNourriture = 200;
	
	protected int qteNourriture = initQteNourriture;
	
	/* 
	 * constructeurs 
	 */
	public Animal(Sexe sexe, Point p) {
		super(p);
		this.sexe=sexe;
	}

	public Animal(Sexe sexe) {
		this(sexe,new Point(0,0));
	}
	
	public Animal() {
		this(Sexe.Femelle);
	}
	
	/*
	 *  Accesseurs et mutateurs
	 */
	public Sexe getSexe() {
		return sexe;
	}
	
	public Etat getNiveauSante() {
		return etat;
	}
	
	public Hebergeur getHebergeur() {
		return hebergeur;
	}

	public void setHebergeur(Hebergeur hebergeur) {
		this.hebergeur = hebergeur;
	}
	
	/**
	 * protected, car seul l'animal doit pouvoir changer son niveau de sant�
	 * @return
	 */
	protected void setNiveauSante(Etat e) {
		etat = e;
	}
	/*
	 * (non-Javadoc)
	 * @see complet.model.agents.Agent#toString()
	 */
	public String toString() {
		return super.toString()+", "+sexe;
	}
	

	/* 
	 * comportements d'instance
	 */
	public void rentrerHebergeur(){
		if(this.getCoord().getX() != hebergeur.getCoord().getX())
		{
			if(this.getCoord().getX() > hebergeur.getCoord().getX())
			{
				this.setCoord((int)(coord.getX()-1),(int)(coord.getY()));
			}else
			{
				this.setCoord((int)(coord.getX()+1),(int)(coord.getY()));
			}
		}
		if(this.getCoord().getY() != hebergeur.getCoord().getY())
		{
			if(this.getCoord().getY() > hebergeur.getCoord().getY())
			{
				this.setCoord((int)(coord.getX()),(int)(coord.getY()-1));
			}else
			{
				this.setCoord((int)(coord.getX()),(int)(coord.getY()+1));
			}
		}
	}
	/*
	 * (non-Javadoc)
	 * @see complet.model.comportements.Deplacable#seDeplacer()
	 */
	/**
	 * d�placement al�atoire avec -1<=dx<=1 et  -1<=dy<=1
	 * @see model.comportements.Deplacable#seDeplacer()
	 */
	public void seDeplacer() {
			int aleaX = (int)(Math.random()*3)-1;
			int aleaY = (int)(Math.random()*3)-1;
			this.setCoord((int)(coord.getX()+aleaX),(int)(coord.getY()+aleaY));
	}
	
	
	/**
	 * condition d'installation d'un animal dans un h�bergeur
	 * @param h
	 * @return
	 */
	protected final boolean sInstaller(Hebergeur h) {
		boolean ret=false;
		if(h!= null && h.accueillir(this)) {
			hebergeur = h;
			ret=true;
		}
		return ret;
	}
	
	protected final void aggraverEtat() {
		LinkedList<Etat> liste = new LinkedList<Etat>(Arrays.asList(Etat.values()));
		ListIterator<Etat> it = liste.listIterator(liste.indexOf(etat));
		if(it.hasNext()) {etat = it.next();}	
	}
	
	protected final void ameliorerEtat() {
		if(etat != Etat.Mourant) {
			LinkedList<Etat> liste = new LinkedList<Etat>(Arrays.asList(Etat.values()));
			ListIterator<Etat> it = liste.listIterator(liste.indexOf(etat));
			if(it.hasPrevious()) {etat = it.previous();}
		}
	}

	public int getQteNourriture() {
		return qteNourriture;
	}
	
	public int getInitQteNourriture() {
		return initQteNourriture;
	}

	public void setQteNourriture(int qteNourriture) {
		this.qteNourriture = qteNourriture;
	}

	/**
	 * Supprimer les animaux
	 */
	public void mourrir() {
		if(hebergeur != null) {
			hebergeur.supprimer(this);
		}
	}
	
	@Override
	public int getWidth() {
		return 20;
	}

	@Override
	public int getHeight() {
		return 20;
	}
}
