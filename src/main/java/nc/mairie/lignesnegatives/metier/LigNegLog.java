package nc.mairie.lignesnegatives.metier;

/**
 * Objet métier LigNegLog
 */
public class LigNegLog extends nc.mairie.technique.BasicMetier {
	private String user;
	private String bib;
	private String chaine;
	private String action;
	private String dateaction;
	private String libelleaction;
/**
 * Constructeur LigNegLog.
 */
public LigNegLog() {
	super();
}
/**
 * Getter de l'attribut user.
  * @return String
 */
public String getUser() {
	return user;
}
/**
 * Setter de l'attribut user.
  * @param newUser newUser
 */
public void setUser(String newUser) { 
	user = newUser;
}
/**
 * Getter de l'attribut bib.
  * @return String
 */
public String getBib() {
	return bib;
}
/**
 * Setter de l'attribut bib.
  * @param newBib newBib
 */
public void setBib(String newBib) { 
	bib = newBib;
}
/**
 * Getter de l'attribut chaine.
  * @return String
 */
public String getChaine() {
	return chaine;
}
/**
 * Setter de l'attribut chaine.
  * @param newChaine newChaine
 */
public void setChaine(String newChaine) { 
	chaine = newChaine;
}
/**
 * Getter de l'attribut action.
  * @return String
 */
public String getAction() {
	return action;
}
/**
 * Setter de l'attribut action.
  * @param newAction newAction
 */
public void setAction(String newAction) { 
	action = newAction;
}
/**
 * Getter de l'attribut dateaction.
  * @return String
 */
public String getDateaction() {
	return dateaction;
}
/**
 * Setter de l'attribut dateaction.
  * @param newDateaction newDateaction
 */
public void setDateaction(String newDateaction) { 
	dateaction = newDateaction;
}
/**
 * Getter de l'attribut libelleaction.
  * @return String
 */
public String getLibelleaction() {
	return libelleaction;
}
/**
 * Setter de l'attribut libelleaction.
  * @param newLibelleaction newLibelleaction
 */
public void setLibelleaction(String newLibelleaction) { 
	libelleaction = newLibelleaction;
}
/**
 Methode à définir dans chaque objet Métier pour instancier un Broker 
*/
protected nc.mairie.technique.BasicBroker definirMyBroker() { 
	return new LigNegLogBroker(this); 
}
/**
 Methode à définir dans chaque objet Métier pour instancier un Broker 
 * @return LigNegLogBroker
*/
protected LigNegLogBroker getMyLigNegLogBroker() {
	return (LigNegLogBroker)getMyBasicBroker();
}
/**
* Renvoie une chaîne correspondant à la valeur de cet objet.
* @return une représentation sous forme de chaîne du destinataire
*/
public String toString() {
	// Insérez ici le code pour finaliser le destinataire
	// Cette implémentation transmet le message au super. Vous pouvez remplacer ou compléter le message.
	return super.toString();
}
/**
 * Retourne un ArrayList d'objet métier : LigNegLog.
 * @param aTransaction aTransaction
 * @param bib bib
 * @return java.util.ArrayList
 * @throws Exception Exception
 */
public static java.util.ArrayList<LigNegLog> listerLigNegLogPourTitreRecette(nc.mairie.technique.Transaction aTransaction, String bib) throws Exception{
	LigNegLog unLigNegLog = new LigNegLog();
	return unLigNegLog.getMyLigNegLogBroker().listerLigNegLogPourTitreRecette(aTransaction, bib);
}
/**
 * Retourne un ArrayList d'objet métier : LigNegLog.
 * @param aTransaction aTransaction
 * @param bib bib
 * @param chaine chaine
 * @param percou percou
 * @return java.util.ArrayList
 * @throws Exception Exception
 */
public static java.util.ArrayList<LigNegLog> listerLigNegLogChainePercou(nc.mairie.technique.Transaction aTransaction, String bib, String chaine, String percou) throws Exception{
	LigNegLog unLigNegLog = new LigNegLog();
	return unLigNegLog.getMyLigNegLogBroker().listerLigNegLogChainePercou(aTransaction, bib, chaine, percou);
}
/**
 * Methode creerObjetMetier qui retourne
 * true ou false
 * @param aTransaction aTransaction
 * @return boolean
 * @throws Exception Exception
 */
public boolean creerLigNegLog(nc.mairie.technique.Transaction aTransaction )  throws Exception {
	//Creation du LigNegLog
	return getMyLigNegLogBroker().creerLigNegLog(aTransaction);
}
}
