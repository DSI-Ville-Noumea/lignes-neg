package nc.mairie.lignesnegatives.metier;

/**
 * Objet métier LigNegHab
 */
public class LigNegHab extends nc.mairie.technique.BasicMetier {
	private String username;
/**
 * Constructeur LigNegHab.
 */
public LigNegHab() {
	super();
}
/**
 * Getter de l'attribut username.
 * @return String
 */
public String getUsername() {
	return username;
}
/**
 * Setter de l'attribut username.
 * @param newUsername newUsername
 */
public void setUsername(String newUsername) { 
	username = newUsername;
}
/**
 Methode à définir dans chaque objet Métier pour instancier un Broker 
*/
protected nc.mairie.technique.BasicBroker definirMyBroker() { 
	return new LigNegHabBroker(this); 
}
/**
 Methode à définir dans chaque objet Métier pour instancier un Broker 
 * @return LigNegHabBroker
*/
protected LigNegHabBroker getMyLigNegHabBroker() {
	return (LigNegHabBroker)getMyBasicBroker();
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
 * Retourne un ArrayList d'objet métier : LigNegHab.
 * @param aTransaction aTransaction
 * @return java.util.ArrayList
 * @throws Exception Exception
 */
public static java.util.ArrayList<LigNegHab> listerLigNegHab(nc.mairie.technique.Transaction aTransaction) throws Exception{
	LigNegHab unLigNegHab = new LigNegHab();
	return unLigNegHab.getMyLigNegHabBroker().listerLigNegHab(aTransaction);
}
}
