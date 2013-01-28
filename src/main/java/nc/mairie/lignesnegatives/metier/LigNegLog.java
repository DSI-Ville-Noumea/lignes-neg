package nc.mairie.lignesnegatives.metier;

/**
 * Objet métier LigNegLog
 */
public class LigNegLog extends nc.mairie.technique.BasicMetier {
	public String user;
	public String bib;
	public String chaine;
	public String action;
	public String dateaction;
	public String libelleaction;
/**
 * Constructeur LigNegLog.
 */
public LigNegLog() {
	super();
}
/**
 * Getter de l'attribut user.
 */
public String getUser() {
	return user;
}
/**
 * Setter de l'attribut user.
 */
public void setUser(String newUser) { 
	user = newUser;
}
/**
 * Getter de l'attribut bib.
 */
public String getBib() {
	return bib;
}
/**
 * Setter de l'attribut bib.
 */
public void setBib(String newBib) { 
	bib = newBib;
}
/**
 * Getter de l'attribut chaine.
 */
public String getChaine() {
	return chaine;
}
/**
 * Setter de l'attribut chaine.
 */
public void setChaine(String newChaine) { 
	chaine = newChaine;
}
/**
 * Getter de l'attribut action.
 */
public String getAction() {
	return action;
}
/**
 * Setter de l'attribut action.
 */
public void setAction(String newAction) { 
	action = newAction;
}
/**
 * Getter de l'attribut dateaction.
 */
public String getDateaction() {
	return dateaction;
}
/**
 * Setter de l'attribut dateaction.
 */
public void setDateaction(String newDateaction) { 
	dateaction = newDateaction;
}
/**
 * Getter de l'attribut libelleaction.
 */
public String getLibelleaction() {
	return libelleaction;
}
/**
 * Setter de l'attribut libelleaction.
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
 * @return java.util.ArrayList
 */
public static java.util.ArrayList listerLigNegLogPourTitreRecette(nc.mairie.technique.Transaction aTransaction, String bib) throws Exception{
	LigNegLog unLigNegLog = new LigNegLog();
	return unLigNegLog.getMyLigNegLogBroker().listerLigNegLogPourTitreRecette(aTransaction, bib);
}
/**
 * Retourne un ArrayList d'objet métier : LigNegLog.
 * @return java.util.ArrayList
 */
public static java.util.ArrayList listerLigNegLogChainePercou(nc.mairie.technique.Transaction aTransaction, String bib, String chaine, String percou) throws Exception{
	LigNegLog unLigNegLog = new LigNegLog();
	return unLigNegLog.getMyLigNegLogBroker().listerLigNegLogChainePercou(aTransaction, bib, chaine, percou);
}
/**
 * Methode creerObjetMetier qui retourne
 * true ou false
 */
public boolean creerLigNegLog(nc.mairie.technique.Transaction aTransaction )  throws Exception {
	//Creation du LigNegLog
	return getMyLigNegLogBroker().creerLigNegLog(aTransaction);
}
}
