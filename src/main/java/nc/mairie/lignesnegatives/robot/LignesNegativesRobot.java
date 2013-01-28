/*
 * Created on 11 mai 2006
 *
 * TODO To change the template for this generated file go to
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
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LignesNegativesRobot extends Robot {

	/* (non-Javadoc)
	 * @see nc.mairie.robot.Robot#getDefaultProcess()
	 */
	public BasicProcess getDefaultProcess() throws Exception {
		// TODO Auto-generated method stub
		return new GestionLignesNegatives();
	}

	/* (non-Javadoc)
	 * @see nc.mairie.robot.Robot#getFirstProcess(java.lang.String)
	 */
	public BasicProcess getFirstProcess(String activite) throws Exception {
		// TODO Auto-generated method stub
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
	protected Hashtable initialiseNavigation() {
		// TODO Auto-generated method stub
		java.util.Hashtable navigation = new java.util.Hashtable();
		
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
		// TODO Auto-generated method stub
		return null;
	}

}
