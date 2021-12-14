package tp.model.agents;

import java.awt.Point;
import java.io.File;

import tp.model.comportements.Deplacable;
import tp.model.world.Dessinable;

/**
 * Cette classe modélise un Agent, c'est à dire un élément du monde qui est vivant ET 
 * qui peut interagir avec d'autres éléments de manière réciproque
 * Par exemple, une Abeille (qui butine une fleur) est un Agent
 * Une Fleur (qui produit et donne du nectar ou du pollen à une Abeille) est un Agent.
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
	/** age en unité de temps*/
	protected int age;
	/** position sur la carte*/
	protected PointPositif coord;
	//protected Point coord;
	
	/**
	 * faim (booléen pour le moment)
	 */
	protected boolean faim = false;
	
	/**
	 * crée un agent d'age 0, avec un id unique à la position coord
	 * @param coord position de l'agent
	 */
	public Agent(Point coord) {
		age = 0;
		id = Agent.getUniqueId();
		//à commenter partie 3 
		//this.coord=coord;
		//à décommenter partie 3 
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
	public boolean aFaim() {return faim;}
	
	/**
	 * renvoie un clone de la position (la position de l'agent ne sera pas modifiable par l'intermédiaire de 
	 * l'objet renvoyé
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
	 * age doit être un entier positif
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
	 * x et y doivent être positifs pour être pris en compte
	 * @param x
	 * @param y
	 */
	public void setCoord(int x, int y) {
		coord.setX(x);
		coord.setY(y);	
	}
	
	/**
	 * fait vieillir l'agent d'une unité de temps
	 */
	public void vieillir() {
		setAge(age+1);
	}
	
	/**
	 * algo qui traite la rencontre de l'agent avec un autre agent
	 * dépend du type des agents impliqués
	 * @param a
	 */
	public abstract void rencontrer(Agent a); 
	
	/**
	 * template method sur le cycle
	 */
	public final void cycle() {
		vieillir();
		if(this instanceof Deplacable) {
			((Deplacable)this).seDeplacer();
		}
		seNourrir();
		maj();
	}
	
	protected abstract void maj();
	protected abstract void seNourrir();
	public abstract Object clone();
	@Override
	public String toString() {
		//NomDeLaClasse n° id_agent (position x; position y)
		return getClass().getSimpleName() + " " + id + " (" + getCoord() + ")";
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((coord == null) ? 0 : coord.hashCode());
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
	 * Renvoie un identifiant unique non encore utilisé
	 * @return un identifiant entier unique d'animal
	 */
	private static int getUniqueId() {
		//TODO 
		Agent.currentId++;
		return currentId;
	}
	/**
	 * Compare l'id de deux agents 
	 * @return un entier positif, négatif ou null
	 */
	public int compareTo(Agent a) {
		return id - a.id;
	}
	
	public String getImage() {
		return "images"+File.separator+getClass().getSimpleName()+".gif";
	}
	

}