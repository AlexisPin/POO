package tp.model.world;

import java.util.Comparator;

import tp.model.agents.Agent;

public class CoordComparator implements Comparator<Agent> {

	@Override
	public int compare(Agent o1, Agent o2) {
		int ret = 1;
		if(o1.getCoord().getX()<o2.getCoord().getX()) {ret = -1;}
		else if (o1.getCoord().getX()==o2.getCoord().getX()) {
			if(o1.getCoord().getY()<o2.getCoord().getY()) {
				ret = -1;
			}		
		}
		return ret;
	}


}
