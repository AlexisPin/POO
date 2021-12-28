package tp.model.agents;

import java.awt.Point;

public class Zone {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coinInferieur == null) ? 0 : coinInferieur.hashCode());
		result = prime * result + ((coinSuperieur == null) ? 0 : coinSuperieur.hashCode());
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
		Zone other = (Zone) obj;
		if (coinInferieur == null) {
			if (other.coinInferieur != null)
				return false;
		} else if (!coinInferieur.equals(other.coinInferieur))
			return false;
		if (coinSuperieur == null) {
			if (other.coinSuperieur != null)
				return false;
		} else if (!coinSuperieur.equals(other.coinSuperieur))
			return false;
		return true;
	}
	public PointPositif getCoinSuperieur() {
		return coinSuperieur;
	}
	public PointPositif getCoinInferieur() {
		return coinInferieur;
	}
	public Zone(PointPositif coinSuperieur, PointPositif coinInferieur) {
		super();
		this.coinSuperieur = coinSuperieur;
		this.coinInferieur = coinInferieur;
	}
	private PointPositif coinSuperieur;
	private PointPositif coinInferieur;
	
	public static void main(String[] args) {
		Zone z1 = new Zone(new PointPositif(0,0),new PointPositif(10,10));
		Zone z2 = new Zone(new PointPositif(0,0),new PointPositif(10,10));
	}
}
