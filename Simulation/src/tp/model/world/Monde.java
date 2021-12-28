package tp.model.world;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map.Entry;

import javax.swing.Timer;

import tp.model.agents.Agent;
import tp.model.agents.PointPositif;
import tp.model.agents.Sexe;
import tp.model.agents.Zone;
import tp.model.agents.Etat;
import tp.model.agents.Animal;
import tp.model.agents.animaux.Abeille;
import tp.model.agents.animaux.AbeilleDomestique;
import tp.model.agents.animaux.AbeilleSolitaire;
import tp.model.agents.animaux.FrelonAsiatique;
import tp.model.agents.animaux.FrelonEuropeen;
import tp.model.agents.animaux.Varroa;
import tp.model.agents.vegetaux.Arbre;
import tp.model.agents.vegetaux.Fleur;
import tp.model.decor.Decor;
import tp.model.decor.Ruche;

public class Monde implements MondeAnimable{
	/**
	 * population d'agents dans le monde
	 */
	private Set<Agent>agents;
	/**
	 * Decor
	 */
	private Set<Decor>decors;

	/**
	 * map de probabilité pour trouver un agent
	 */
	/*
	 * par commodité: la map n'est plus statique pour permettre le paramétrage
	 * par l'interface graphique des probabilités d'apparition d'agents.
	 */
	private Map<Integer,Agent> proba;
	/**
	 * constante: largeur du monde
	 */
	private static int LARGEUR = 200;
	/**
	 * constante: longueur du monde
	 */
	private static int LONGUEUR = 300;
	/**
	 * rayon maximal pour la rencontre
	 */
	private static int RAYON = 10;
	/**
	 * Timer pour déclencher un évènement toutes les x ms
	 */
	private Timer timer;


	
	public Set<Agent> getAgents() {
		return agents;
	}

	/**
	 * 
	 * @param nbAgents
	 */
	
	public Monde(int nbAgents) {
		this(nbAgents,10);
		
	}
	public Monde(int nbAgents, int delai) {
		proba = probaAgent();
		agents=generateAgents(nbAgents);
		timer=new Timer(delai, this);
	}
	
	/**
	 * @return the lARGEUR
	 */
	public static int getLARGEUR() {
		return LARGEUR;
	}

	/**
	 * @return the lONGUEUR
	 */
	public static int getLONGUEUR() {
		return LONGUEUR;
	}


	/**
	 * M�thode utilitaire statistique pour produire la table de proba
	 * d'apparition d'un agent
	 * @return
	 */
	private Map<Integer, Agent> probaAgent() {
		/*
		 * par commodit�: la map n'est plus statique pour permettre le param�trage
		 * par l'interface graphique des probabilit�s d'apparition d'agents.
		 */
		decors = new HashSet<Decor>();
		Ruche r1 =new Ruche(new Point(10,50));
		Ruche r2 =new Ruche(new Point(100,20));
		decors.add(r1);
		decors.add(r2);		
		Map<Integer,Agent> myMap = new LinkedHashMap<Integer,Agent>();
	    myMap.put(20,new AbeilleDomestique(Sexe.Assexue,new Point(0,0),r1));
	    myMap.put(40,new AbeilleDomestique(Sexe.Assexue,new Point(0,0),r2));
	    myMap.put(50,new AbeilleSolitaire(Sexe.Assexue,new Point(0,0)));
	    myMap.put(55,new FrelonEuropeen(Sexe.Assexue,new Point(0,0)));
	    myMap.put(60,new FrelonAsiatique(Sexe.Assexue,new Point(0,0)));
	    myMap.put(70,new Varroa(Sexe.Assexue,new Point(0,0)));
	    myMap.put(80,new Arbre(new Point(0,0),1.0));
	    myMap.put(85,new Arbre(new Point(0,0),2.0));
	    myMap.put(100,new Fleur(new Point(0,0)));
	    return myMap;
	}
	
	/**
	 * fabrication al�atoire d'un Agent par tirage dans la Map
	 * et positionnement al�atoire
	 * @param alea
	 * @return
	 */
	private Agent tirage(int alea) {
		Agent agent=null;
		if(alea<100 && alea>=0) {
			boolean trouve = false;
			Iterator<Integer> it = proba.keySet().iterator();
			while(!trouve && it.hasNext()) {
				Integer clef = it.next();
				if(clef>=alea) {
					agent = proba.get(clef);
					trouve=true;
				}
			}
		}
		else {
			agent = new Fleur(new Point(0,0));
		}
		//positionnement al�atoire entre Longueur et Largeur
		int aleaX = (int)(Math.random()*LONGUEUR);
		int aleaY = (int)(Math.random()*LARGEUR);
		agent.setCoord(aleaX, aleaY);
		return agent;
	}


	private TreeSet<Agent> generateAgents(int nbAgents) {
		TreeSet<Agent> ts = new TreeSet<Agent>();
		for(int i=0;i<nbAgents;i++) {
			int alea = (int)(Math.random()*100);
			ts.add((Agent)tirage(alea).clone());
		}
		return ts;
	}
	

	private Set<Agent> getAgentByCoord(){
		Set<Agent> coordSet = new TreeSet<Agent>(new CoordComparator());
		coordSet.addAll(agents);
		
		return coordSet;
	}

	public String toString() {
		String ret="";
		ret+="******************************\n";
		ret+="Le monde contient "+agents.size()+" agents:\n";
		Set<Agent> coordSet = getAgentByCoord();
		for(Agent a:coordSet) {
			ret+="\t"+a+"\n";
		}
		return ret;
	}
	
	public static String remplir(Integer indice, PointPositif coord) {
		int cible = coord.getX()*LARGEUR + coord.getY()-indice;
		indice=cible+1;
		return String.format("%" + cible+"s","");		
	}

	public Map<Agent,List<Agent>> gererRencontre() {
		Set<Agent> coordSet = getAgentByCoord();
		Map<Agent,List<Agent>> agentsVoisin = new HashMap<Agent,List<Agent>>();
		for(Agent agent1:coordSet) {
			agentsVoisin.put(agent1,new ArrayList<Agent>());
			for(Agent agent2:coordSet) {
				if(agent1.getCoord().getRayon(agent2.getCoord()) < RAYON && !agent1.equals(agent2))
				{
					agentsVoisin.get(agent1).add(agent2);
				}
			}
		}
		return agentsVoisin;
	}
	
	public Set<Agent> copie(Set<Agent> agents) {
		Iterator<Agent> it = agents.iterator();
		Set<Agent> copieAgent=new TreeSet<Agent>();
		while(it.hasNext())
		{
			Agent agent = (Agent)it.next();
			copieAgent.add(agent);
		}
		return copieAgent;
	}
	/**
	 * génère un cycle de vie dans le monde
	 */
	public void cycle() {
		for(Agent agent : agents)
		{
			agent.cycle();
			
			if(agent instanceof Animal) 
			{
				if(((Animal) agent).getNiveauSante() == Etat.Mourant)
				{
					((Animal) agent).mourrir();
					Set<Agent> copieAgent = copie(agents);
					copieAgent.remove(agent);
					agents = copie(copieAgent);
				}
			}
			
		}
		
		 Map<Agent, List<Agent>> agentsVoisin = gererRencontreOpti(); 
		 for(Entry<Agent,List<Agent>> entry : agentsVoisin.entrySet()) 
		 { 
			 Agent key = entry.getKey();
			 List<Agent> value = entry.getValue();
			 ListIterator<Agent> agentAProximite = value.listIterator();
			 if(agentAProximite.hasNext()) 
			 { 
				 key.rencontrer(agentAProximite.next()); 
			 } 
		 }
	}

	public List<List<PointPositif>> creationZone() {
	List<List<PointPositif>>  zone = new ArrayList<List<PointPositif>>();

		for(int i = 0; i < 30; i++) 
		{
			ArrayList<PointPositif> eachZone = new ArrayList<PointPositif>();
			for(int j = 0; j < 20; j++) 
			{

				PointPositif coinSuperieur = new PointPositif(new Point(10*i, 10*j));
				PointPositif coinInferieur = new PointPositif(new Point(10+10*i, 10+10*j));
				eachZone.add(coinSuperieur);
				eachZone.add(coinInferieur); 
				zone.add(eachZone);
				System.out.println(eachZone);	
			}
			zone.add(eachZone);
		}
		System.out.println(zone);
		return zone;
	}
	

		List<List<PointPositif>> zone = creationZone();
	
	public Map<Zone, List<Agent>> distrubitionZoneAgent() {
		Set<Agent> coordSet = getAgentByCoord();
		Map<Zone,List<Agent>> agentsVoisin = new HashMap<Zone,List<Agent>>();
		for(Agent agent:coordSet) {
			Zone zone = new Zone(
					new PointPositif(agent.getCoord().getX() - agent.getCoord().getX() % 10, agent.getCoord().getY() -agent.getCoord().getY() % 10),
					new PointPositif(agent.getCoord().getX() - agent.getCoord().getX() % 10 + 10 ,agent.getCoord().getY() -  agent.getCoord().getY() % 10 + 10)
					);
			
			List<Agent> agents = agentsVoisin.get(zone);
			if(agents == null) agentsVoisin.put(zone, new ArrayList<Agent>());
			agentsVoisin.get(zone).add(agent);
		}
		return agentsVoisin;
	}
	
	public Map<Agent,List<Agent>> gererRencontreOpti() {
		Map<Zone, List<Agent>> linkedAgentsZone =  distrubitionZoneAgent();
		Map<Agent,List<Agent>> agentsVoisin = new HashMap<Agent,List<Agent>>();
		
		for(Entry<Zone, List<Agent>> entry : linkedAgentsZone.entrySet()) 
		{ 
			Zone key = entry.getKey();
			List<Agent> value = entry.getValue();
			for(Agent agent1: value) 
			{
				agentsVoisin.put(agent1,new ArrayList<Agent>());
				for(Agent agent2:value) {
					if(agent1.getCoord().getRayon(agent2.getCoord()) < RAYON && !agent1.equals(agent2))
					{
						agentsVoisin.get(agent1).add(agent2);
					}
				}
			}
		}
		return agentsVoisin;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		cycle();
		System.out.println("cycle");
	}

	@Override
	public void lancerAnimation() {
		timer.start();
	}

	@Override
	public void stopperAnimation() {
		timer.stop();	
	}

	@Override
	public List<Dessinable> getElementsMonde() {
		List<Dessinable> ret = new ArrayList<Dessinable>();
		ret.addAll((Collection<? extends Dessinable>) decors);
		ret.addAll((Collection<? extends Dessinable>) agents);
		return ret;
	}



}
