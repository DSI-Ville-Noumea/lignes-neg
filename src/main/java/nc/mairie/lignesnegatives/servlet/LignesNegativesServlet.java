/*
 * Created on 11 mai 2006
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
package nc.mairie.lignesnegatives.servlet;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nc.mairie.lignesnegatives.metier.LigNegHab;
import nc.mairie.lignesnegatives.robot.LignesNegativesRobot;
import nc.mairie.robot.Robot;
import nc.mairie.servlets.Frontale;
import nc.mairie.technique.Transaction;
import nc.mairie.technique.UserAppli;
import nc.mairie.technique.VariableGlobale;

/**
 * @author boulu72
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LignesNegativesServlet extends Frontale {

	/**
	 * 
	 */
	private static final long serialVersionUID = 890074412743062933L;

	/* (non-Javadoc)
	 * @see nc.mairie.servlets.Frontale#getServletRobot()
	 */
	protected Robot getServletRobot() {
		return new LignesNegativesRobot();
	}
	/**
	 * Insérez la description de la méthode à cet endroit.
	 *  Date de création : (22/02/2002 10:51:46)
	 * @return fr.averse.servlets.Contexte
	 */
	protected void initialiseAutresParametres() {
		super.initialiseAutresParametres();
		setVeutGererActivitite(true);
	}
	
	/**
	 * Controle l'habilitation
	 *
	 * @author Luc Bourdil
	 * @param request Object that encapsulates the request to the servlet 
	 */
	protected boolean performControleHabilitation(javax.servlet.http.HttpServletRequest request) throws Exception {
		//Habilitation du super
		if (!super.performControleHabilitation(request)) return false;
		
		UserAppli user =getUserAppli(request);
		
		//On récupère la liste des users
		Transaction aTransaction = new Transaction(user);
		ArrayList<LigNegHab> a = LigNegHab.listerLigNegHab(aTransaction);
		aTransaction.rollbackTransaction();
		aTransaction.fermerConnexion();
		
		
		//on cherche si le user est habilité
		for (int i = 0; i < a.size(); i++) {
			if (user.getUserName().equals(((LigNegHab)a.get(i)).getUsername()))
				return true;
		}
		
		//KO alors on vide le user appli
		VariableGlobale.enlever(request,VariableGlobale.GLOBAL_USER_APPLI);
		return false;
	}
	
	@Override
	public void performTask(HttpServletRequest request, HttpServletResponse response) {
		//Si bib dans le param, on le stocke dans la session
		String bib = request.getParameter("BIB");
		if (bib != null) {
			VariableGlobale.ajouter(request, "BIB", bib);
		}
		
		super.performTask(request, response);
	}
	
}
