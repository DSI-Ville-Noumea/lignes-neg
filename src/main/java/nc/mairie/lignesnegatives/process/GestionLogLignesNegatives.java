package nc.mairie.lignesnegatives.process;

import java.util.ArrayList;

import nc.mairie.lignesnegatives.metier.LigNegLog;
import nc.mairie.technique.*;
/**
 * Process GestionLogLignesNegatives
 * Date de création : (19/06/06 15:40:21)
 * @author : Générateur de process
*/
public class GestionLogLignesNegatives extends nc.mairie.technique.BasicProcess {
	public static final int STATUT_LIGNES_NEGATIVES = 1;
	private java.lang.String[] LB_CHAINE_PERCOU;
	private String bibCourant;
	private ArrayList listeChainePercou = null;
	private LigNegLog ligNegLogCourant = null;
	private String lignesNegativesSupprimees = null;

	private ArrayList getListeChainePercou() {
		if (listeChainePercou == null) {
			listeChainePercou = new ArrayList();
		}
		return listeChainePercou;
	}
	private void setListeChainePercou(ArrayList listeChainePercou) {
		this.listeChainePercou = listeChainePercou;
	}
	/**
	 * Initialisation de la liste des Chaines/Percou
	 * @author : Luc Bourdil
	 */
	private void initialiseListeChainePercou(javax.servlet.http.HttpServletRequest request) throws Exception{

		java.util.ArrayList a = new ArrayList();
		
		a=LigNegLog.listerLigNegLogPourTitreRecette(getTransaction(), getBibCourant());
		a.add(0,new LigNegLog());
		setListeChainePercou(a);
		
		//S'il y a des lignes
		if(a.size() != 0){
			int tailles [] = {3,7};
			String attr [] = {"chaine","dateaction"};
			String pad [] = {"G","G"};
			FormateListe aFormat = new FormateListe(tailles,a,attr,pad,false);
			setLB_CHAINE_PERCOU(aFormat.getListeFormatee());
		} else {
			//getTransaction().declarerErreur("INFO : Pas de lignes négatives pour cette bibliothèque");
			setLB_CHAINE_PERCOU(null);
		}
		
	}

	
/**
 * Initialisation des zones à afficher dans la JSP
 * Alimentation des listes, s'il y en a, avec setListeLB_XXX()
 * ATTENTION : Les Objets dans la liste doivent avoir les Fields PUBLIC
 * Utilisation de la méthode addZone(getNOMxxx, String);
 * Date de création : (19/06/06 15:40:21)
 * @author : Générateur de process
 */
public void initialiseZones(javax.servlet.http.HttpServletRequest request) throws Exception{

	//Si la lib en param
	if (request.getParameter("BIB")!=null) {
		setBibCourant(request.getParameter("BIB"));
		initialiseListeChainePercou(request);
		setLigNegLogCourant(null);
	}
	
	//Si la BIB est nulle alors erreur
	if (getBibCourant() == null || "null".equals(getBibCourant())) {
		setStatut(STATUT_MESSAGE_INFO,false, "Erreur: Impossible de trouver la bibliothèque. Relancer l'application.");
	}
	
}
/**
 * Méthode appelée par la servlet qui aiguille le traitement : 
 * en fonction du bouton de la JSP 
 * Date de création : (19/06/06 15:40:21)
 * @author : Générateur de process
 */
public boolean recupererStatut(javax.servlet.http.HttpServletRequest request) throws Exception{

	//Si on arrive de la JSP alors on traite le get
	if (request.getParameter("JSP")!=null && request.getParameter("JSP").equals(getJSP())) {

		//Si clic sur le bouton PB_CHAINE_PERCOU
		if (testerParametre(request, getNOM_PB_CHAINE_PERCOU())) {
			return performPB_CHAINE_PERCOU(request);
		}

	}
	//Si TAG INPUT non géré par le process
	setStatut(STATUT_MEME_PROCESS);
	return true;
}
/**
 * Constructeur du process GestionLogLignesNegatives.
 * Date de création : (19/06/06 15:40:21)
 * @author : Générateur de process
 */
public GestionLogLignesNegatives() {
	super();
}
/**
 * Retourne le nom de la JSP du process
 * Zone à utiliser dans un champ caché dans chaque formulaire de la JSP.
 * Date de création : (19/06/06 15:40:21)
 * @author : Générateur de process
 */
public String getJSP() {
	return "GestionLogLignesNegatives.jsp";
}
/**
 * Retourne le nom d'un bouton pour la JSP :
 * PB_CHAINE_PERCOU
 * Date de création : (19/06/06 15:40:21)
 * @author : Générateur de process
 */
public java.lang.String getNOM_PB_CHAINE_PERCOU() {
	return "NOM_PB_CHAINE_PERCOU";
}
/**
 * - Traite et affecte les zones saisies dans la JSP.
 * - Implémente les règles de gestion du process
 * - Positionne un statut en fonction de ces règles :
 *   setStatut(STATUT, boolean veutRetour) ou setStatut(STATUT,Message d'erreur)
 * Date de création : (19/06/06 15:40:21)
 * @author : Générateur de process
 */
public boolean performPB_CHAINE_PERCOU(javax.servlet.http.HttpServletRequest request) throws Exception {
	//On récupère la ligne
	int numligne = (Services.estNumerique(getZone(getNOM_LB_CHAINE_PERCOU_SELECT())) ? Integer.parseInt(getZone(getNOM_LB_CHAINE_PERCOU_SELECT())) : -1);
	setLigNegLogCourant(numligne < 1 || getListeChainePercou().size() == 0 ?
			null : (LigNegLog)getListeChainePercou().get(numligne));
	
	formateLignesNegativesSupprimees();
	
	return true;
}
/**
 * Getter de la liste avec un lazy initialize :
 * LB_CHAINE_PERCOU
 * Date de création : (19/06/06 15:40:21)
 * @author : Générateur de process
 */
private String [] getLB_CHAINE_PERCOU() {
	if (LB_CHAINE_PERCOU == null)
		LB_CHAINE_PERCOU = initialiseLazyLB();
	return LB_CHAINE_PERCOU;
}
/**
 * Setter de la liste:
 * LB_CHAINE_PERCOU
 * Date de création : (19/06/06 15:40:21)
 * @author : Générateur de process
 */
private void setLB_CHAINE_PERCOU(java.lang.String[] newLB_CHAINE_PERCOU) {
	LB_CHAINE_PERCOU = newLB_CHAINE_PERCOU;
}
/**
 * Retourne le nom de la zone pour la JSP :
 * NOM_LB_CHAINE_PERCOU
 * Date de création : (19/06/06 15:40:21)
 * @author : Générateur de process
 */
public java.lang.String getNOM_LB_CHAINE_PERCOU() {
	return "NOM_LB_CHAINE_PERCOU";
}
/**
 * Retourne le nom de la zone de la ligne sélectionnée pour la JSP :
 * NOM_LB_CHAINE_PERCOU_SELECT
 * Date de création : (19/06/06 15:40:21)
 * @author : Générateur de process
 */
public java.lang.String getNOM_LB_CHAINE_PERCOU_SELECT() {
	return "NOM_LB_CHAINE_PERCOU_SELECT";
}
/**
 * Méthode à personnaliser
 * Retourne la valeur à afficher pour la zone de la JSP :
 * LB_CHAINE_PERCOU
 * Date de création : (19/06/06 15:40:21)
 * @author : Générateur de process
 */
public java.lang.String [] getVAL_LB_CHAINE_PERCOU() {
	return getLB_CHAINE_PERCOU();
}
/**
 * Méthode à personnaliser
 * Retourne l'indice à sélectionner pour la zone de la JSP :
 * LB_CHAINE_PERCOU
 * Date de création : (19/06/06 15:40:21)
 * @author : Générateur de process
 */
public java.lang.String getVAL_LB_CHAINE_PERCOU_SELECT() {
	return getZone(getNOM_LB_CHAINE_PERCOU_SELECT());
}
/**
 * Retourne le nom de la zone pour la JSP :
 * NOM_LB_PERCOU
 * Date de création : (19/06/06 15:40:21)
 * @author : Générateur de process
 */
public java.lang.String getNOM_LB_PERCOU() {
	return "NOM_LB_PERCOU";
}
/**
 * Retourne le nom de la zone de la ligne sélectionnée pour la JSP :
 * NOM_LB_PERCOU_SELECT
 * Date de création : (19/06/06 15:40:21)
 * @author : Générateur de process
 */
public java.lang.String getNOM_LB_PERCOU_SELECT() {
	return "NOM_LB_PERCOU_SELECT";
}
/**
 * Méthode à personnaliser
 * Retourne l'indice à sélectionner pour la zone de la JSP :
 * LB_PERCOU
 * Date de création : (19/06/06 15:40:21)
 * @author : Générateur de process
 */
public java.lang.String getVAL_LB_PERCOU_SELECT() {
	return getZone(getNOM_LB_PERCOU_SELECT());
}
public String getBibCourant() {
	return bibCourant;
}
private void setBibCourant(String bibCourant) {
	this.bibCourant = bibCourant;
	//setSalaireNegatifCourant(null);
	//addZone(getNOM_ST_ACTION(),null);
}
private void setLignesNegativesSupprimees(String lignesNegativesSupprimees) {
	this.lignesNegativesSupprimees = lignesNegativesSupprimees;
}

private void formateLignesNegativesSupprimees() throws Exception {

	String res="";
	
	LigNegLog aLog = getLigNegLogCourant();
	
	//si le logligneg courant est null, on ne met rien dans le tableau
	if (aLog == null || aLog.getChaine() == null) {
		setLignesNegativesSupprimees("");
		return; 
	}
	
	ArrayList a = LigNegLog.listerLigNegLogChainePercou(getTransaction(),getBibCourant(), aLog.getChaine(), aLog.getDateaction());
	
	for (int i = 0; i < a.size(); i++) {
		String temp = ((LigNegLog)a.get(i)).getLibelleaction();
		res+="<TBODY><TR><TD>";
		
		for (int j = 0; j < temp.length(); j++) {
			char c = temp.charAt(j);
			if (c == '|') {
				res+="</TD><TD>";
				if (j< temp.length() -1 && temp.charAt(j+1) == '|' ) {
					//ALT 255
					res +=" "; 
				}
			} else res+=String.valueOf(temp.charAt(j)) ;
		}
		res += "</TD></TR></TBODY>";
	}	
	
	setLignesNegativesSupprimees(res);

}

	public String getLignesNegativesSupprimees() {
		if (lignesNegativesSupprimees == null) {
			lignesNegativesSupprimees = "";
		}
		return lignesNegativesSupprimees;
	}	
	
	private LigNegLog getLigNegLogCourant() {
		return ligNegLogCourant;
	}
	private void setLigNegLogCourant(LigNegLog ligNegLogCourant) {
		this.ligNegLogCourant = ligNegLogCourant;
	}
}

