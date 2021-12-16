package tp.model.agents;

import java.awt.Point;
import java.io.File;

import tp.model.comportements.Deplacable;
import tp.model.world.Dessinable;
import tp.model.world.Monde;

/**
 * Cette classe mod�lise un Agent, c'est � dire un �l�ment du monde qui est vivant ET 
 * qui peut interagir avec d'autres �l�ments de mani�re r�ciproque
 * Par exemple, une Abeille (qui butine une fleur) est un Agent
 * Une Fleur (qui produit et donne du nectar ou du pollen � une Abeille) est un Agent.
 * Une ruche, en revanche, n'est pas un agent (elle n'est pas vivante, elle ne produit rien).
 * @author bruno
 *
 */
public abstract class Agent implements Cloneable, Comparable<Agent>, Dessinable{
	
	/* attributs de classe */
	private static int currentId = 0;
	
	/* attributs d'instance*/
	/** identifiant unique de l'animal*/
	private int id;
	/** age en unit� de temps*/
	protected int age;
	/** position sur la carte*/
	protected PointPositif coord;
	//protected Point coord;
	
	/**
	 * faim (bool�en pour le moment)
	 */
	protected boolean faim = false;
	
	/**
	 * mode nuit ou non
	 */
	private boolean modeNuit = false;
	
	/**
	 * cr�e un agent d'age 0, avec un id unique � la position coord
	 * @param coord position de l'agent
	 */
	public Agent(Point coord) {
		age = 0;
		id = Agent.getUniqueId();
		//� commenter partie 3 
		//this.coord=coord;
		//� d�commenter partie 3 
		this.coord=new PointPositif(coord);
	}
	/**
	 * age 0, id unique et position (0,0)
	 */
	public Agent() {
		this(new Point(0,0));
	}
	
	/* ***************************
	 * Accesseurs et mutateurs
	 */
	/**
	 * renvoie l'identifiant (unique) de l'agent
	 * @return
	 */
	public int getId() {
		return id;
	}
	/**
	 * renvoie vrai si l'agent a faim
	 * @return
	 */
	public boolean aFaim() {
		return faim;
	}
	
	/**
	 * renvoie un clone de la position (la position de l'agent ne sera pas modifiable par l'interm�diaire de 
	 * l'objet renvoy�
	 * @return un clone de {@link #coord}
	 */
	//partie 2 et 3
	public PointPositif getCoord() {
		
		/*
		 version 1
		return new PointPositif(new Point(coord.getX(),coord.getY()));
		 ou version 2
		 */
		return (PointPositif) coord.clone();
	}
	
	
	/**
	 * age doit �tre un entier positif
	 * @param a
	 * @return true si age positif
	 */
	protected boolean setAge(int a) {
		boolean ret = false;
		if(a>0) {
			age = a;
			ret = true;
		}
		return ret;
	}
	
	/**
	 * x et y doivent �tre positifs pour �tre pris en compte
	 * @param x
	 * @param y
	 */
	public void setCoord(int x, int y) {
		coord.setX(x);
		coord.setY(y);	
	}
	
	/**
	 * fait vieillir l'agent d'une unit� de temps
	 */
	public void vieillir() {
		setAge(age+1);
	}
	
	/**
	 * algo qui traite la rencontre de l'agent avec un autre agent
	 * d�pend du type des agents impliqu�s
	 * @param a
	 */
	public abstract void rencontrer(Agent a); 
	
	/**
	 * template method sur le cycle
	 */
	public final void cycle() {
		vieillir();
		if(this instanceof Deplacable) {
			if(modeNuit)
			{
				((Deplacable)this).rentrerHebergeur();
			}else
			{
				((Deplacable)this).seDeplacer();
			}
			
		}
		seNourrir();
		maj();
	}
	
	protected abstract void maj();
	protected abstract void seNourrir();
	public abstract Object clone();
	@Override
	public String toString() {
		//NomDeLaClasse n� id_agent (position x; position y)
		return getClass().getSimpleName() + " " + id + " (" + getCoord() + ")";
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agent other = (Agent) obj;
		if (age != other.age)
			return false;
		if (coord == null) {
			if (other.coord != null)
				return false;
		} else if (!coord.equals(other.coord))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	/* comportements de classe */ 
	/**
	 * Renvoie un identifiant unique non encore utilis�
	 * @return un identifiant entier unique d'animal
	 */
	private static int getUniqueId() {
		//TODO 
		Agent.currentId++;
		return currentId;
	}
	/**
	 * Compare l'id de deux agents 
	 * @return un entier positif, n�gatif ou null
	 */
	public int compareTo(Agent a) {
		return id - a.id;
	}
	
	public String getImage() {
		return "images"+File.separator+getClass().getSimpleName()+".gif";
	}
	
	public boolean isModeNuit() {
		return modeNuit;
	}
	public void setModeNuit(boolean modeNuit) {
		this.modeNuit = modeNuit;
	}
}