package tp.model.comportements;

import tp.model.agents.Agent;
import tp.model.agents.Animal;

/**
 * un hébergeur peut accueillir un animal
 * @author bruno
 *
 */
public interface Hebergeur {
	/**
	 * Renvoie vrai si a peut être acceuilli
	 * @param a 
	 * @return
	 */
	public boolean peutAccueillir(Agent a);
	/**
	 * Renvoie vrai si l'animal a est acceuilli par l'hébergeur 
	 * @param a
	 * @return
	 */
	public boolean accueillir(Agent a);

	public void supprimer(Animal a);
}
