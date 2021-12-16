package ui.guiSimple;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import tp.model.world.MondeAnimable;


public class WorldFrame extends JFrame implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * une instance du Monde animable
	 */
	private MondeAnimable monde;
	
    private int durationOfCycle;
	/**
	 * Thread d'éxécution
	 */
	private Thread thread;
	private JLabel labelInfo = new JLabel();

	
		/**
	 * Constructeur
	 * Vous verrez plus tard comment limiter le couplage fort entre une UI et un model: le MVC
	 * @param m
	 */
	public WorldFrame(MondeAnimable m) {
		super("Le fabuleux monde des abeilles");
		/*
		 * initialisation des attributs
		 */
		this.monde = m;
		thread = new Thread(this);
		/*
		 * positionnement de la fenètre
		 */
		dimensionnerFenetre();
		/*
		 * construction des composants de la fenetre
		 */
		fabriqueFenetre();
		/*
		 * opération par défaut en cas de fermeture
		 */
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/*
		 * affichage
		 */
		this.setVisible(true);
	}

	private void fabriqueFenetre() {
		Container panneau = new JPanel();
		panneau.setLayout(new BorderLayout());
		
		JPanel panStats = new JPanel();
		panStats.setBackground(Color.red);
		
		JPanel panInfo = new JPanel();
		panInfo.setBackground(Color.blue);
		panInfo.add(labelInfo);
		labelInfo.setText("Animation non démarré");
		JPanel panCommandes = new JPanel();
		panCommandes.setBackground(Color.green);
		panCommandes.setLayout(new GridLayout(10,1));
		JButton btStart = new JButton("Start");
		JButton btStop = new JButton("Stop");
		panCommandes.add(btStart);
		panCommandes.add(btStop);
		
		btStart.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				start();
			}
		});
		
		btStop.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				stop();
			}
		});
				
		//JPanel panMonde = new JPanel();
		JPanel panMonde = new PanneauPrincipal(monde);
		panMonde.setBackground(Color.orange);	
		
		/*
		 * net pas utiliser setSize mais setPreferredSize
		 */
		//panStats.setSize(new Dimension(200,0));
		panStats.setPreferredSize(new Dimension(200,0));
		panCommandes.setPreferredSize(new Dimension(200,50));
		panInfo.setPreferredSize(new Dimension(0,100));
		
		panneau.add(panStats,BorderLayout.WEST);
		panneau.add(panInfo,BorderLayout.SOUTH);
		panneau.add(panCommandes,BorderLayout.EAST);
		panneau.add(panMonde,BorderLayout.CENTER);
		
		setContentPane(panneau);
		
        JLabel status = new JLabel("Temps du cycle", JLabel.CENTER);
         
		JSlider slider = new JSlider(); 
        slider.setMajorTickSpacing(10);
        slider.setPaintTicks(true);
         
     
        slider.setPaintLabels(true);
         

        Hashtable<Integer, JLabel> position = new Hashtable<Integer, JLabel>();
        position.put(0, new JLabel("20"));
        position.put(60, new JLabel("60"));
        position.put(100, new JLabel("100"));
         

        slider.setLabelTable(position);
         

        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                status.setText("Temps de cycle : " + ((JSlider)e.getSource()).getValue());
                int cycleTime = slider.getValue();
                durationOfCycle = cycleTime;
            }
        });
        
        panCommandes.add(slider);
        panCommandes.add(status);
	}

	/**
	 * lance l'animation
	 */
	private void stop() {
		/*
		 * ne pas modifier la ligne suivante
		 */
		monde.stopperAnimation();
		labelInfo.setText("Animation en pause");
	}

	private void start() {
		/*
		 * ne pas modifier les 4 lignes suivantes
		 */
		monde.lancerAnimation();
		if(!thread.isAlive()) {
			thread.start();
		}
		labelInfo.setText("Animation en cours");
		
	}

	private void dimensionnerFenetre() {
		/*
		 * TODO placer la fenetre au centre de l'écran
		 */
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		dim.height = dim.height/3*2;
		dim.width = dim.width/3*2;
		this.setPreferredSize(dim);
		this.pack();
		this.setLocationRelativeTo(null);
	}

	@Override
	public void run() {
		while(true) {
			repaint();
			try {
		        Thread.sleep(durationOfCycle);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
	}
	
	

}
