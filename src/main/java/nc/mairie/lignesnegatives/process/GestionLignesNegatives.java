package nc.mairie.lignesnegatives.process;

import java.sql.Timestamp;
import java.util.ArrayList;

import nc.mairie.lignesnegatives.metier.LigNegLog;
import nc.mairie.lignesnegatives.metier.Salaire;
import nc.mairie.technique.*;
/**
 * Process GestionLignesNegatives
 * Date de création : (11/05/06 11:23:48)
 * @author : Générateur de process
*/
public class GestionLignesNegatives extends nc.mairie.technique.BasicProcess {
	public static final int STATUT_RECETTE = 1;
	private java.lang.String[] LB_BIBLIOTHEQUE;
	private java.lang.String[] LB_LIGNES_NEG;
	private java.lang.String[] LB_LIGNES_NOMATR;
	private java.lang.String[] LB_LIGNES_POSSIBLES;
	private String ACTION_SUPPRESSION = "Suppression d'une ligne negative.<FONT color='red'> Veuillez valider votre choix.</FONT>";
	public String ACTION_MODIFICATION = "Modification d'une ligne negative.";
	private java.util.ArrayList listeBibliotheques;
	private java.util.ArrayList listeLignesNegatives;
	private java.util.ArrayList listeLignesNomatr;
	private java.util.ArrayList listeLignesPossibles;
	private String bibCourant;
	private Salaire salaireNegatifCourant;
	private Salaire salaireNomatrCourant;
	private Salaire salairePossibleCourant;
	private String chaineCourant;
	
/**
 * Initialisation des zones à afficher dans la JSP
 * Alimentation des listes, s'il y en a, avec setListeLB_XXX()
 * ATTENTION : Les Objets dans la liste doivent avoir les Fields PUBLIC
 * Utilisation de la méthode addZone(getNOMxxx, String);
 * Date de création : (11/05/06 11:23:48)
 * @author : Générateur de process
 */
public void initialiseZones(javax.servlet.http.HttpServletRequest request) throws Exception{

	//Si BIBcou rant null, on le récupère de la session
	if (getBibCourant() == null) {
		setBibCourant((String)VariableGlobale.recuperer(request, "BIB"));
	}
	
	//Si la lib en param
	if (request.getParameter("BIB")!=null) {
		
		String old = getBibCourant();
		
		setBibCourant(request.getParameter("BIB"));
		
		if (! getBibCourant().equals(old)){
			setLB_BIBLIOTHEQUE(null);
		}
		 
	}


	//	Si la BIB est nulle alors erreur
	if (getBibCourant() == null || "null".equals(getBibCourant())) {
		setStatut(STATUT_MESSAGE_INFO,true, "Erreur: Impossible de trouver la bibliothèque. Relancer l'application.");
	}
	
	//Si la liste est vide
	if (getLB_BIBLIOTHEQUE() == LBVide) {
		initialiseListeBibliotheque(request,true);
	}
	
}
/**
 * Retourne la valeur à afficher par la JSP  pour la zone :
 * ST_ACTION
 * Date de création : (28/04/03 11:26:32)
 * @author : Générateur de process
 */
public java.lang.String getVAL_ST_ACTION() {
	return getZone(getNOM_ST_ACTION());
}
/**
 * Retourne pour la JSP le nom de la zone statique :
 * ST_ACTION
 * Date de création : (28/04/03 11:26:32)
 * @author : Générateur de process
 */
public java.lang.String getNOM_ST_ACTION() {
	return "NOM_ST_ACTION";
}
/**
 * Constructeur du process GestionLignesNegatives.
 * Date de création : (11/05/06 11:23:48)
 * @author : Générateur de process
 */
public GestionLignesNegatives() {
	super();
}
/**
 * Retourne le nom d'un bouton pour la JSP :
 * PB_ANNULER
 * Date de création : (11/05/06 11:23:48)
 * @author : Générateur de process
 */
public java.lang.String getNOM_PB_ANNULER() {
	return "NOM_PB_ANNULER";
}
/**
 * - Traite et affecte les zones saisies dans la JSP.
 * - Implémente les règles de gestion du process
 * - Positionne un statut en fonction de ces règles :
 *   setStatut(STATUT, boolean veutRetour) ou setStatut(STATUT,Message d'erreur)
 * Date de création : (11/05/06 11:23:48)
 * @author : Générateur de process
 */
public boolean performPB_ANNULER(javax.servlet.http.HttpServletRequest request) throws Exception {
	
	addZone(getNOM_ST_ACTION(),null);
	
	return true;
}
/**
 * Retourne le nom d'un bouton pour la JSP :
 * PB_BIBLIOTHEQUE
 * Date de création : (11/05/06 11:23:48)
 * @author : Générateur de process
 */
public java.lang.String getNOM_PB_BIBLIOTHEQUE() {
	return "NOM_PB_BIBLIOTHEQUE";
}
/**
 * - Traite et affecte les zones saisies dans la JSP.
 * - Implémente les règles de gestion du process
 * - Positionne un statut en fonction de ces règles :
 *   setStatut(STATUT, boolean veutRetour) ou setStatut(STATUT,Message d'erreur)
 * Date de création : (11/05/06 11:23:48)
 * @author : Générateur de process
 */
public boolean performPB_BIBLIOTHEQUE(javax.servlet.http.HttpServletRequest request) throws Exception {

	//	On récupère la ligne
	int numligne = (Services.estNumerique(getZone(getNOM_LB_BIBLIOTHEQUE_SELECT())) ? Integer.parseInt(getZone(getNOM_LB_BIBLIOTHEQUE_SELECT())) : -1);
	setBibCourant(numligne <= 0 || getLB_BIBLIOTHEQUE().length == 0? null : getLB_BIBLIOTHEQUE()[numligne]);
		
	initialiseListeLignesNegatives(request,true);
	
	setStatut(STATUT_MEME_PROCESS);
	
	return true;
}
/**
 * Retourne le nom d'un bouton pour la JSP :
 * PB_LIGNES_NEG
 * Date de création : (11/05/06 11:23:48)
 * @author : Générateur de process
 */
public java.lang.String getNOM_PB_LIGNES_NEG() {
	return "NOM_PB_LIGNES_NEG";
}
/**
 * - Traite et affecte les zones saisies dans la JSP.
 * - Implémente les règles de gestion du process
 * - Positionne un statut en fonction de ces règles :
 *   setStatut(STATUT, boolean veutRetour) ou setStatut(STATUT,Message d'erreur)
 * Date de création : (11/05/06 11:23:48)
 * @author : Générateur de process
 */
public boolean performPB_LIGNES_NEG(javax.servlet.http.HttpServletRequest request) throws Exception {
	//	On récupère la ligne
	int numligne = (Services.estNumerique(getZone(getNOM_LB_LIGNES_NEG_SELECT())) ? Integer.parseInt(getZone(getNOM_LB_LIGNES_NEG_SELECT())) : -1);
	setSalaireNegatifCourant(numligne < 0 || getListeLignesNegatives().size() == 0 ?
			null : (Salaire)getListeLignesNegatives().get(numligne));
	
	initialiseListeLignesNomatr(request,true);
	
	addZone(getNOM_ST_ACTION(),"");
	setStatut(STATUT_MEME_PROCESS);
	
	return true;
}
/**
 * Retourne le nom d'un bouton pour la JSP :
 * PB_LIGNES_NOMATR
 * Date de création : (11/05/06 11:23:48)
 * @author : Générateur de process
 */
public java.lang.String getNOM_PB_LIGNES_NOMATR() {
	return "NOM_PB_LIGNES_NOMATR";
}
/**
 * Retourne le nom d'un bouton pour la JSP :
 * PB_MODIFIER
 * Date de création : (11/05/06 11:23:48)
 * @author : Générateur de process
 */
public java.lang.String getNOM_PB_MODIFIER() {
	return "NOM_PB_MODIFIER";
}
/**
 * - Traite et affecte les zones saisies dans la JSP.
 * - Implémente les règles de gestion du process
 * - Positionne un statut en fonction de ces règles :
 *   setStatut(STATUT, boolean veutRetour) ou setStatut(STATUT,Message d'erreur)
 * Date de création : (11/05/06 11:23:48)
 * @author : Générateur de process
 */
public boolean performPB_MODIFIER(javax.servlet.http.HttpServletRequest request) throws Exception {
	
	initialiseListeLignesPossibles(request, true);
	addZone(getNOM_LB_LIGNES_POSSIBLES_SELECT(),"-1");
	
	//action
	addZone(getNOM_ST_ACTION(), ACTION_MODIFICATION);
	return true;
}
/**
 * - Traite et affecte les zones saisies dans la JSP.
 * - Implémente les règles de gestion du process
 * - Positionne un statut en fonction de ces règles :
 *   setStatut(STATUT, boolean veutRetour) ou setStatut(STATUT,Message d'erreur)
 * Date de création : (11/05/06 11:23:48)
 * @author : Générateur de process
 */
public boolean performPB_LIGNES_NOMATR(javax.servlet.http.HttpServletRequest request) throws Exception {
	//	On récupère la ligne
	int numligne = (Services.estNumerique(getZone(getNOM_LB_LIGNES_NOMATR_SELECT())) ? Integer.parseInt(getZone(getNOM_LB_LIGNES_NOMATR_SELECT())) : -1);
	setSalaireNomatrCourant(numligne < 0 || getListeLignesNomatr().size() == 0 ?
			null : (Salaire)getListeLignesNomatr().get(numligne));
	
	addZone(getNOM_ST_ACTION(),"");

	return true;
}
/**
 * Retourne le nom d'un bouton pour la JSP :
 * PB_SUPPRIMER
 * Date de création : (11/05/06 11:23:48)
 * @author : Générateur de process
 */
public java.lang.String getNOM_PB_SUPPRIMER() {
	return "NOM_PB_SUPPRIMER";
}
/**
 * - Traite et affecte les zones saisies dans la JSP.
 * - Implémente les règles de gestion du process
 * - Positionne un statut en fonction de ces règles :
 *   setStatut(STATUT, boolean veutRetour) ou setStatut(STATUT,Message d'erreur)
 * Date de création : (11/05/06 11:23:48)
 * @author : Générateur de process
 */
public boolean performPB_SUPPRIMER(javax.servlet.http.HttpServletRequest request) throws Exception {
	//	action
	addZone(getNOM_ST_ACTION(), ACTION_SUPPRESSION);
	return true;
}
/**
 * Retourne le nom d'un bouton pour la JSP :
 * PB_VALIDER
 * Date de création : (11/05/06 11:23:48)
 * @author : Générateur de process
 */
public java.lang.String getNOM_PB_VALIDER() {
	return "NOM_PB_VALIDER";
}
/**
 * - Traite et affecte les zones saisies dans la JSP.
 * - Implémente les règles de gestion du process
 * - Positionne un statut en fonction de ces règles :
 *   setStatut(STATUT, boolean veutRetour) ou setStatut(STATUT,Message d'erreur)
 * Date de création : (11/05/06 11:23:48)
 * @author : Générateur de process
 */
public boolean performPB_VALIDER(javax.servlet.http.HttpServletRequest request) throws Exception {
	
	LigNegLog aLigNegLog = new LigNegLog();
	aLigNegLog.setUser(getTransaction().getConnection().getMetaData().getUserName());
	aLigNegLog.setBib(getBibCourant());
	aLigNegLog.setDateaction(new Timestamp(System.currentTimeMillis()).toString());
	aLigNegLog.setChaine(getChaineCourant());
		
	if (ACTION_MODIFICATION.equals(getVAL_ST_ACTION())) {
		//	On récupère la ligne
		int numligne = (Services.estNumerique(getZone(getNOM_LB_LIGNES_POSSIBLES_SELECT())) ? Integer.parseInt(getZone(getNOM_LB_LIGNES_POSSIBLES_SELECT())) : -1);
		setSalairePossibleCourant(numligne < 0 || getListeLignesPossibles().size() == 0 ?
				null : (Salaire)getListeLignesPossibles().get(numligne));
		if (getSalairePossibleCourant() == null) {
			getTransaction().declarerErreur("Vous devez sélectionner un élément de la liste");
			return false;
		}
		
		//La log
		aLigNegLog.setAction("M");
		aLigNegLog.setLibelleaction(
				getSalaireNomatrCourant().getNomatr().trim()+"|"+
				getSalaireNomatrCourant().getNumcpte().trim()+"|"+
				getSalaireNomatrCourant().getNoacti().trim()+"|"+
				getSalaireNomatrCourant().getCodfon().trim()+"|"+
				getSalaireNomatrCourant().getRefemp().trim()+"|"+
				getSalaireNomatrCourant().getMontnt().trim()+"|"+
				getSalaireNomatrCourant().getIdetbs().trim()+"|"+
				getSalaireNomatrCourant().getEnscom().trim()+"|"+
				" EN "+
				getSalairePossibleCourant().getNumcpte().trim()+"|"+
				getSalairePossibleCourant().getNoacti().trim()+"|"+
				getSalairePossibleCourant().getCodfon().trim()+"|"+
				getSalaireNomatrCourant().getRefemp().trim()
				
				);
				
		getSalaireNomatrCourant().modifierSalaire(getTransaction(), getSalairePossibleCourant(), getBibCourant());
		if (getTransaction().isErreur()){
			return false;
		}

	} else if (ACTION_SUPPRESSION.equals(getVAL_ST_ACTION())) {
		//La log
		aLigNegLog.setAction("S");
		aLigNegLog.setLibelleaction(
				getSalaireNomatrCourant().getNomatr().trim()+"|"+
				getSalaireNomatrCourant().getNumcpte().trim()+"|"+
				getSalaireNomatrCourant().getNoacti().trim()+"|"+
				getSalaireNomatrCourant().getCodfon().trim()+"|"+
				getSalaireNomatrCourant().getRefemp().trim()+"|"+
				getSalaireNomatrCourant().getMontnt().trim()+"|"+
				getSalaireNomatrCourant().getIdetbs().trim()+"|"+
				getSalaireNomatrCourant().getEnscom().trim()
				);
		//on supprime
		getSalaireNomatrCourant().supprimerSalaire(getTransaction(), getBibCourant());
		if (getTransaction().isErreur()) {
			return false;
		}
		getTransaction().declarerErreur("INFO : ATTENTION de ne pas oublier de faire le titre de recette pour le matricule "+getSalaireNomatrCourant().getNomatr());
		
	} else {
		getTransaction().declarerErreur("ACTION en cours non supportée");
		return false;
	}
	
	//tout s'est bien passé
	//on loggue
	aLigNegLog.creerLigNegLog(getTransaction());
	
	commitTransaction();	
	addZone(getNOM_ST_ACTION(),null);
	initialiseListeLignesNegatives(request,false);

	return true;
}
/**
 * Getter de la liste avec un lazy initialize :
 * LB_BIBLIOTHEQUE
 * Date de création : (11/05/06 11:23:48)
 * @author : Générateur de process
 */
private String [] getLB_BIBLIOTHEQUE() {
	if (LB_BIBLIOTHEQUE == null)
		LB_BIBLIOTHEQUE = initialiseLazyLB();
	return LB_BIBLIOTHEQUE;
}
/**
 * Setter de la liste:
 * LB_BIBLIOTHEQUE
 * Date de création : (11/05/06 11:23:48)
 * @author : Générateur de process
 */
private void setLB_BIBLIOTHEQUE(java.lang.String[] newLB_BIBLIOTHEQUE) {
	LB_BIBLIOTHEQUE = newLB_BIBLIOTHEQUE;
}
/**
 * Retourne le nom de la zone pour la JSP :
 * NOM_LB_BIBLIOTHEQUE
 * Date de création : (11/05/06 11:23:48)
 * @author : Générateur de process
 */
public java.lang.String getNOM_LB_BIBLIOTHEQUE() {
	return "NOM_LB_BIBLIOTHEQUE";
}
/**
 * Retourne le nom de la zone de la ligne sélectionnée pour la JSP :
 * NOM_LB_BIBLIOTHEQUE_SELECT
 * Date de création : (11/05/06 11:23:48)
 * @author : Générateur de process
 */
public java.lang.String getNOM_LB_BIBLIOTHEQUE_SELECT() {
	return "NOM_LB_BIBLIOTHEQUE_SELECT";
}
/**
 * Méthode à personnaliser
 * Retourne la valeur à afficher pour la zone de la JSP :
 * LB_BIBLIOTHEQUE
 * Date de création : (11/05/06 11:23:48)
 * @author : Générateur de process
 */
public java.lang.String [] getVAL_LB_BIBLIOTHEQUE() {
	return getLB_BIBLIOTHEQUE();
}
/**
 * Méthode à personnaliser
 * Retourne l'indice à sélectionner pour la zone de la JSP :
 * LB_BIBLIOTHEQUE
 * Date de création : (11/05/06 11:23:48)
 * @author : Générateur de process
 */
public java.lang.String getVAL_LB_BIBLIOTHEQUE_SELECT() {
	return getZone(getNOM_LB_BIBLIOTHEQUE_SELECT());
}
/**
 * Getter de la liste avec un lazy initialize :
 * LB_LIGNES_NEG
 * Date de création : (11/05/06 11:23:48)
 * @author : Générateur de process
 */
private String [] getLB_LIGNES_NEG() {
	if (LB_LIGNES_NEG == null)
		LB_LIGNES_NEG = initialiseLazyLB();
	return LB_LIGNES_NEG;
}
/**
 * Setter de la liste:
 * LB_LIGNES_NEG
 * Date de création : (11/05/06 11:23:48)
 * @author : Générateur de process
 */
private void setLB_LIGNES_NEG(java.lang.String[] newLB_LIGNES_NEG) {
	LB_LIGNES_NEG = newLB_LIGNES_NEG;
}
/**
 * Retourne le nom de la zone pour la JSP :
 * NOM_LB_LIGNES_NEG
 * Date de création : (11/05/06 11:23:48)
 * @author : Générateur de process
 */
public java.lang.String getNOM_LB_LIGNES_NEG() {
	return "NOM_LB_LIGNES_NEG";
}
/**
 * Retourne le nom de la zone de la ligne sélectionnée pour la JSP :
 * NOM_LB_LIGNES_NEG_SELECT
 * Date de création : (11/05/06 11:23:48)
 * @author : Générateur de process
 */
public java.lang.String getNOM_LB_LIGNES_NEG_SELECT() {
	return "NOM_LB_LIGNES_NEG_SELECT";
}
/**
 * Méthode à personnaliser
 * Retourne la valeur à afficher pour la zone de la JSP :
 * LB_LIGNES_NEG
 * Date de création : (11/05/06 11:23:48)
 * @author : Générateur de process
 */
public java.lang.String [] getVAL_LB_LIGNES_NEG() {
	return getLB_LIGNES_NEG();
}
/**
 * Méthode à personnaliser
 * Retourne l'indice à sélectionner pour la zone de la JSP :
 * LB_LIGNES_NEG
 * Date de création : (11/05/06 11:23:48)
 * @author : Générateur de process
 */
public java.lang.String getVAL_LB_LIGNES_NEG_SELECT() {
	return getZone(getNOM_LB_LIGNES_NEG_SELECT());
}
/**
 * Getter de la liste avec un lazy initialize :
 * LB_LIGNES_NOMATR
 * Date de création : (11/05/06 11:23:48)
 * @author : Générateur de process
 */
private String [] getLB_LIGNES_NOMATR() {
	if (LB_LIGNES_NOMATR == null)
		LB_LIGNES_NOMATR = initialiseLazyLB();
	return LB_LIGNES_NOMATR;
}
/**
 * Setter de la liste:
 * LB_LIGNES_NOMATR
 * Date de création : (11/05/06 11:23:48)
 * @author : Générateur de process
 */
private void setLB_LIGNES_NOMATR(java.lang.String[] newLB_LIGNES_NOMATR) {
	LB_LIGNES_NOMATR = newLB_LIGNES_NOMATR;
}
/**
 * Retourne le nom de la zone pour la JSP :
 * NOM_LB_LIGNES_NOMATR
 * Date de création : (11/05/06 11:23:48)
 * @author : Générateur de process
 */
public java.lang.String getNOM_LB_LIGNES_NOMATR() {
	return "NOM_LB_LIGNES_NOMATR";
}
/**
 * Retourne le nom de la zone de la ligne sélectionnée pour la JSP :
 * NOM_LB_LIGNES_NOMATR_SELECT
 * Date de création : (11/05/06 11:23:48)
 * @author : Générateur de process
 */
public java.lang.String getNOM_LB_LIGNES_NOMATR_SELECT() {
	return "NOM_LB_LIGNES_NOMATR_SELECT";
}
/**
 * Méthode à personnaliser
 * Retourne la valeur à afficher pour la zone de la JSP :
 * LB_LIGNES_NOMATR
 * Date de création : (11/05/06 11:23:48)
 * @author : Générateur de process
 */
public java.lang.String [] getVAL_LB_LIGNES_NOMATR() {
	return getLB_LIGNES_NOMATR();
}
/**
 * Méthode à personnaliser
 * Retourne l'indice à sélectionner pour la zone de la JSP :
 * LB_LIGNES_NOMATR
 * Date de création : (11/05/06 11:23:48)
 * @author : Générateur de process
 */
public java.lang.String getVAL_LB_LIGNES_NOMATR_SELECT() {
	return getZone(getNOM_LB_LIGNES_NOMATR_SELECT());
}
/**
 * Getter de la liste avec un lazy initialize :
 * LB_LIGNES_POSSIBLES
 * Date de création : (11/05/06 11:23:48)
 * @author : Générateur de process
 */
private String [] getLB_LIGNES_POSSIBLES() {
	if (LB_LIGNES_POSSIBLES == null)
		LB_LIGNES_POSSIBLES = initialiseLazyLB();
	return LB_LIGNES_POSSIBLES;
}
/**
 * Setter de la liste:
 * LB_LIGNES_POSSIBLES
 * Date de création : (11/05/06 11:23:48)
 * @author : Générateur de process
 */
private void setLB_LIGNES_POSSIBLES(java.lang.String[] newLB_LIGNES_POSSIBLES) {
	LB_LIGNES_POSSIBLES = newLB_LIGNES_POSSIBLES;
}
/**
 * Retourne le nom de la zone pour la JSP :
 * NOM_LB_LIGNES_POSSIBLES
 * Date de création : (11/05/06 11:23:48)
 * @author : Générateur de process
 */
public java.lang.String getNOM_LB_LIGNES_POSSIBLES() {
	return "NOM_LB_LIGNES_POSSIBLES";
}
/**
 * Retourne le nom de la zone de la ligne sélectionnée pour la JSP :
 * NOM_LB_LIGNES_POSSIBLES_SELECT
 * Date de création : (11/05/06 11:23:48)
 * @author : Générateur de process
 */
public java.lang.String getNOM_LB_LIGNES_POSSIBLES_SELECT() {
	return "NOM_LB_LIGNES_POSSIBLES_SELECT";
}
/**
 * Méthode à personnaliser
 * Retourne la valeur à afficher pour la zone de la JSP :
 * LB_LIGNES_POSSIBLES
 * Date de création : (11/05/06 11:23:48)
 * @author : Générateur de process
 */
public java.lang.String [] getVAL_LB_LIGNES_POSSIBLES() {
	return getLB_LIGNES_POSSIBLES();
}
/**
 * Méthode à personnaliser
 * Retourne l'indice à sélectionner pour la zone de la JSP :
 * LB_LIGNES_POSSIBLES
 * Date de création : (11/05/06 11:23:48)
 * @author : Générateur de process
 */
public java.lang.String getVAL_LB_LIGNES_POSSIBLES_SELECT() {
	return getZone(getNOM_LB_LIGNES_POSSIBLES_SELECT());
}


	public String getBibCourant() {
		return bibCourant;
	}
	private void setBibCourant(String bibCourant) {
		this.bibCourant = bibCourant;
		//setSalaireNegatifCourant(null);
		//addZone(getNOM_ST_ACTION(),null);
	}
	public Salaire getSalaireNegatifCourant() {
		return salaireNegatifCourant;
	}
	private void setSalaireNegatifCourant(Salaire salaireNegatifCourant) {
		this.salaireNegatifCourant = salaireNegatifCourant;
	}
	public Salaire getSalaireNomatrCourant() {
		return salaireNomatrCourant;
	}
	public void setSalaireNomatrCourant(Salaire salaireNomatrCourant) {
		this.salaireNomatrCourant = salaireNomatrCourant;
	}
	public Salaire getSalairePossibleCourant() {
		return salairePossibleCourant;
	}
	public void setSalairePossibleCourant(Salaire salairePossibleCourant) {
		this.salairePossibleCourant = salairePossibleCourant;
	}
/**
 * Initialisation de la liste des administrations
 * @author : Luc Bourdil
 */
private void initialiseListeBibliotheque(javax.servlet.http.HttpServletRequest request, boolean initSelect) throws Exception{

	//Recherche des Administrations
	if (getLB_BIBLIOTHEQUE().equals(BasicProcess.LBVide)) {
		String bibs [] = {"","LUC","MAIRIE","MAIRCCAS","MAIRCDE"};
		setLB_BIBLIOTHEQUE(bibs);
	}
	//setBibCourant(null);

	if (initSelect) addZone(getNOM_LB_BIBLIOTHEQUE_SELECT(),"-1");
	
	initialiseListeLignesNegatives(request,true);
	
}
/**
 * Initialisation de la liste des administrations
 * @author : Luc Bourdil
 */
private void initialiseListeLignesNegatives(javax.servlet.http.HttpServletRequest request, boolean initSelect) throws Exception{

	java.util.ArrayList a = new ArrayList();
	
	if (getBibCourant() != null) {
		//Recherche des Administrations
		a = Salaire.listerSalaireNegatif(getTransaction(), getBibCourant());
		initSelect = initSelect || a.size()!=getListeLignesNegatives().size();
		setListeLignesNegatives(a);
	}

	setChaineCourant(null);
	
	//S'il y a des lignes négative
	if(a.size() != 0){
		setChaineCourant(((Salaire)a.get(0)).getCdchai());
		int tailles [] = {8,4,5,9,8,6,30};
		String attr [] = {"numcpte","noacti","codfon","refemp","summontant","idetbs","enscom"};
		String pad [] = {"D","D","D","C","D","D","G"};
		FormateListe aFormat = new FormateListe(tailles,a,attr,pad,false);
		setLB_LIGNES_NEG(aFormat.getListeFormatee());
	} else {
		//getTransaction().declarerErreur("INFO : Pas de lignes négatives pour cette bibliothèque");
		setLB_LIGNES_NEG(null);
	}
	
	if (initSelect) {
		addZone(getNOM_LB_LIGNES_NEG_SELECT(),"-1");
		setSalaireNegatifCourant(null);
	}
	
	initialiseListeLignesNomatr(request, true);
}
/**
 * Initialisation de la liste des administrations
 * @author : Luc Bourdil
 */
private void initialiseListeLignesNomatr(javax.servlet.http.HttpServletRequest request, boolean initSelect) throws Exception{

	java.util.ArrayList a = new ArrayList();
	
	if (getSalaireNegatifCourant() != null) {
		//Recherche des Administrations
		a = Salaire.listerSalaireNegatifFromSalaireNegatif(getTransaction(), getSalaireNegatifCourant() , getBibCourant());
		initSelect = initSelect || a.size()!=getListeLignesNomatr().size();
		setListeLignesNomatr(a);
	}

	//S'il y a des lignes négative
	if(a.size() != 0){
		int tailles [] = {5,8,4,5,9,8,6,30};
		String attr [] = {"nomatr","numcpte","noacti","codfon","refemp","montnt","idetbs","enscom"};
		String pad [] = {"D","D","D","D","C","D","D","G"};
		FormateListe aFormat = new FormateListe(tailles,a,attr,pad,false);
		
		setLB_LIGNES_NOMATR(aFormat.getListeFormatee());
	} else {
		setLB_LIGNES_NOMATR(null);
	}
	
	if (initSelect) {
		addZone(getNOM_LB_LIGNES_NOMATR_SELECT(),"-1");
		setSalaireNomatrCourant(null);
	}
}
/**
 * Initialisation de la liste des administrations
 * @author : Luc Bourdil
 */
private void initialiseListeLignesPossibles(javax.servlet.http.HttpServletRequest request, boolean initSelect) throws Exception{

	java.util.ArrayList a = new ArrayList();
	
	if (getSalaireNomatrCourant() != null) {
		//Recherche des Administrations
		a = Salaire.listerSalairePossiblesFromSalaireMatricule(getTransaction(), getSalaireNomatrCourant() , getBibCourant());
		initSelect = initSelect || a.size()!=getListeLignesPossibles().size();
		setListeLignesPossibles(a);
	}

	//S'il y a des lignes négative
	if(a.size() != 0){
		int tailles [] = {8,4,5,9,8,6,30};
		String attr [] = {"numcpte","noacti","codfon","refemp","summontant","idetbs","enscom"};
		String pad [] = {"D","D","D","C","D","D","G"};
		FormateListe aFormat = new FormateListe(tailles,a,attr,pad,false);
		
		setLB_LIGNES_POSSIBLES(aFormat.getListeFormatee());
	} else {
		setLB_LIGNES_POSSIBLES(null);
	}
	
	if (initSelect) {
		addZone(getNOM_LB_LIGNES_POSSIBLES_SELECT(),"-1");
	}
}

private java.util.ArrayList getListeBibliotheques() {
		if (listeBibliotheques == null) {
			listeBibliotheques = new ArrayList();
		}
		return listeBibliotheques;
	}
	private void setListeBibliotheques(java.util.ArrayList listeBibliotheques) {
		this.listeBibliotheques = listeBibliotheques;
	}
	private java.util.ArrayList getListeLignesNegatives() {
		if (listeLignesNegatives == null) {
			listeLignesNegatives = new ArrayList();
		}
		return listeLignesNegatives;
	}
	private void setListeLignesNegatives(
			java.util.ArrayList listeLignesNegatives) {
		this.listeLignesNegatives = listeLignesNegatives;
	}
	private java.util.ArrayList getListeLignesNomatr() {
		if (listeLignesNomatr == null) {
			listeLignesNomatr = new ArrayList();
		}
		return listeLignesNomatr;
	}
	private void setListeLignesNomatr(
			java.util.ArrayList listeLignesNomatr) {
		this.listeLignesNomatr = listeLignesNomatr;
	}
	public java.util.ArrayList getListeLignesPossibles() {
		if (listeLignesPossibles == null) {
			listeLignesPossibles = new ArrayList();
		}
		return listeLignesPossibles;
	}
	public void setListeLignesPossibles(java.util.ArrayList listeLignesPossibles) {
		this.listeLignesPossibles = listeLignesPossibles;
	}
	/**
	 * Process incoming requests for information
	 * 
	 * @param request Object that encapsulates the request to the servlet 
	 */
	public boolean recupererPreControles(javax.servlet.http.HttpServletRequest request) throws Exception {
		//On s'en fou de savoir s'il y a le tag JSP !!!
		return true;
	}

	private String getChaineCourant() {
		return chaineCourant;
	}
	private void setChaineCourant(String chaineCourant) {
		this.chaineCourant = chaineCourant;
	}
/**
 * Méthode appelée par la servlet qui aiguille le traitement : 
 * en fonction du bouton de la JSP 
 * Date de création : (11/05/06 11:23:48)
 * @author : Générateur de process
 */
public boolean recupererStatut(javax.servlet.http.HttpServletRequest request) throws Exception{

	//Si on arrive de la JSP alors on traite le get
	if (request.getParameter("JSP")!=null && request.getParameter("JSP").equals(getJSP())) {

		//Si clic sur le bouton PB_RECETTE
		if (testerParametre(request, getNOM_PB_RECETTE())) {
			return performPB_RECETTE(request);
		}

		//Si clic sur le bouton PB_ANNULER
		if (testerParametre(request, getNOM_PB_ANNULER())) {
			return performPB_ANNULER(request);
		}

		//Si clic sur le bouton PB_BIBLIOTHEQUE
		if (testerParametre(request, getNOM_PB_BIBLIOTHEQUE())) {
			return performPB_BIBLIOTHEQUE(request);
		}

		//Si clic sur le bouton PB_LIGNES_NEG
		if (testerParametre(request, getNOM_PB_LIGNES_NEG())) {
			return performPB_LIGNES_NEG(request);
		}

		//Si clic sur le bouton PB_LIGNES_NOMATR
		if (testerParametre(request, getNOM_PB_LIGNES_NOMATR())) {
			return performPB_LIGNES_NOMATR(request);
		}

		//Si clic sur le bouton PB_MODIFIER
		if (testerParametre(request, getNOM_PB_MODIFIER())) {
			return performPB_MODIFIER(request);
		}

		//Si clic sur le bouton PB_SUPPRIMER
		if (testerParametre(request, getNOM_PB_SUPPRIMER())) {
			return performPB_SUPPRIMER(request);
		}

		//Si clic sur le bouton PB_VALIDER
		if (testerParametre(request, getNOM_PB_VALIDER())) {
			return performPB_VALIDER(request);
		}

	}
	//Si TAG INPUT non géré par le process
	setStatut(STATUT_MEME_PROCESS);
	return true;
}
/**
 * Retourne le nom de la JSP du process
 * Zone à utiliser dans un champ caché dans chaque formulaire de la JSP.
 * Date de création : (19/06/06 15:33:22)
 * @author : Générateur de process
 */
public String getJSP() {
	return "GestionLignesNegatives.jsp";
}
/**
 * Retourne le nom d'un bouton pour la JSP :
 * PB_RECETTE
 * Date de création : (19/06/06 15:33:22)
 * @author : Générateur de process
 */
public java.lang.String getNOM_PB_RECETTE() {
	return "NOM_PB_RECETTE";
}
/**
 * - Traite et affecte les zones saisies dans la JSP.
 * - Implémente les règles de gestion du process
 * - Positionne un statut en fonction de ces règles :
 *   setStatut(STATUT, boolean veutRetour) ou setStatut(STATUT,Message d'erreur)
 * Date de création : (19/06/06 15:33:22)
 * @author : Générateur de process
 */
public boolean performPB_RECETTE(javax.servlet.http.HttpServletRequest request) throws Exception {
	setStatut(STATUT_RECETTE,false);
	return true;
}
}
