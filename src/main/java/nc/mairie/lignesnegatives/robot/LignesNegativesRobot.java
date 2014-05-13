/*
 * Created on 11 mai 2006
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
package nc.mairie.lignesnegatives.robot;

import java.util.Hashtable;

import nc.mairie.lignesnegatives.process.GestionLignesNegatives;
import nc.mairie.lignesnegatives.process.GestionLogLignesNegatives;
import nc.mairie.robot.Robot;
import nc.mairie.robot.Testeur;
import nc.mairie.technique.BasicProcess;

/**
 * @author boulu72
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LignesNegativesRobot extends Robot {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3259395076675356131L;

	/* (non-Javadoc)
	 * @see nc.mairie.robot.Robot#getDefaultProcess()
	 */
	public BasicProcess getDefaultProcess() throws Exception {
		return new GestionLignesNegatives();
	}

	/* (non-Javadoc)
	 * @see nc.mairie.robot.Robot#getFirstProcess(java.lang.String)
	 */
	public BasicProcess getFirstProcess(String activite) throws Exception {
		if (activite.equals("GestionLignesNegatives")) {
			return new GestionLignesNegatives();
		} else if (activite.equals("GestionLogLignesNegatives")) {
			return new GestionLogLignesNegatives();
		} else {
			BasicProcess p =  new GestionLignesNegatives();
			p.setActivite("GestionLignesNegatives");
			return p;
		}
	}

	/* (non-Javadoc)
	 * @see nc.mairie.robot.Robot#initialiseNavigation()
	 */
	protected Hashtable<String, String> initialiseNavigation() {
		java.util.Hashtable<String, String> navigation = new java.util.Hashtable<>();
		
		//Classe GestionLignesNegatives
		navigation.put(GestionLignesNegatives.class.getName()+GestionLignesNegatives.STATUT_RECETTE,GestionLogLignesNegatives.class.getName());
		
		//Classe GestionLogLignesNegatives
		navigation.put(GestionLogLignesNegatives.class.getName()+GestionLogLignesNegatives.STATUT_LIGNES_NEGATIVES,GestionLignesNegatives.class.getName());
		
		return navigation;
	}

	/* (non-Javadoc)
	 * @see nc.mairie.robot.Robot#initialiseTesteur()
	 */
	protected Testeur initialiseTesteur() {
		return null;
	}

}
