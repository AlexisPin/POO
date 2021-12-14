package tp.model.world;

import java.awt.event.ActionListener;
import java.util.List;

public interface MondeAnimable extends ActionListener{
	
	public void lancerAnimation();
	public void stopperAnimation();
	public List<Dessinable> getElementsMonde();

}
