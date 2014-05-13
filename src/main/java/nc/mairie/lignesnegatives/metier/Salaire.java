package nc.mairie.lignesnegatives.metier;

/**
 * Objet métier Salaire
 */
public class Salaire extends nc.mairie.technique.BasicMetier {
	public String nomatr;
	public String numcpte;
	public String codfon;
	public String noacti;
	public String montnt;
	public String idetbs;
	public String refemp;
	
	public String summontant;
	public String enscom;
	public String cdchai;
	
/**
 * Constructeur Salaire.
 */
public Salaire() {
	super();
}
/**
 * Getter de l'attribut nomatr.
 */
public String getNomatr() {
	return nomatr;
}
/**
 * Setter de l'attribut nomatr.
 */
public void setNomatr(String newNomatr) { 
	nomatr = newNomatr;
}
/**
 * Getter de l'attribut numcpte.
 */
public String getNumcpte() {
	return numcpte;
}
/**
 * Setter de l'attribut numcpte.
 */
public void setNumcpte(String newNumcpte) { 
	numcpte = newNumcpte;
}
/**
 * Getter de l'attribut codfon.
 */
public String getCodfon() {
	return codfon;
}
/**
 * Setter de l'attribut codfon.
 */
public void setCodfon(String newCodfon) { 
	codfon = newCodfon;
}
/**
 * Getter de l'attribut noacti.
 */
public String getNoacti() {
	return noacti;
}
/**
 * Setter de l'attribut noacti.
 */
public void setNoacti(String newNoacti) { 
	noacti = newNoacti;
}
/**
 * Getter de l'attribut montnt.
 */
public String getMontnt() {
	return montnt;
}
/**
 * Setter de l'attribut montnt.
 */
public void setMontnt(String newMontnt) { 
	montnt = newMontnt;
}
/**
 * Getter de l'attribut idetbs.
 */
public String getIdetbs() {
	return idetbs;
}
/**
 * Setter de l'attribut idetbs.
 */
public void setIdetbs(String newIdetbs) { 
	idetbs = newIdetbs;
}
	public String getRefemp() {
		return refemp;
	}
	public void setRefemp(String refemp) {
		this.refemp = refemp;
	}
	public String getEnscom() {
		return enscom;
	}
	public void setEnscom(String enscom) {
		this.enscom = enscom;
	}
	public String getSummontant() {
		return summontant;
	}
	public void setSummontant(String summontant) {
		this.summontant = summontant;
	}
	public String getCdchai() {
		return cdchai;
	}
	public void setCdchai(String cdchai) {
		this.cdchai = cdchai;
	}
/**
 Methode à définir dans chaque objet Métier pour instancier un Broker 
*/
protected nc.mairie.technique.BasicBroker definirMyBroker() { 
	return new SalaireBroker(this); 
}
/**
 Methode à définir dans chaque objet Métier pour instancier un Broker 
*/
protected SalaireBroker getMySalaireBroker() {
	return (SalaireBroker)getMyBasicBroker();
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
 * Retourne un ArrayList d'objet métier : Salaire.
 * @return java.util.ArrayList
 */
public static java.util.ArrayList<Salaire> listerSalaireNegatif(nc.mairie.technique.Transaction aTransaction, String bib) throws Exception{
	Salaire unSalaire = new Salaire();
	return unSalaire.getMySalaireBroker().listerSalaireNegatif(aTransaction, bib);
}
/**
 * Retourne un ArrayList d'objet métier : Salaire.
 * @return java.util.ArrayList
 */
public static java.util.ArrayList<Salaire> listerSalaireNegatifFromSalaireNegatif(nc.mairie.technique.Transaction aTransaction, Salaire aSalaire, String bib) throws Exception{
	Salaire unSalaire = new Salaire();
	return unSalaire.getMySalaireBroker().listerSalaireNegatifFromSalaireNegatif(aTransaction, aSalaire.getNumcpte(), aSalaire.getIdetbs(), aSalaire.getNoacti(), aSalaire.getCodfon(), aSalaire.getRefemp(), bib);
}
/**
 * Retourne un ArrayList d'objet métier : Salaire.
 * @return java.util.ArrayList
 */
public static java.util.ArrayList<Salaire> listerSalairePossiblesFromSalaireMatricule(nc.mairie.technique.Transaction aTransaction, Salaire aSalaire, String bib) throws Exception{
	Salaire unSalaire = new Salaire();
	return unSalaire.getMySalaireBroker().listerSalairePossiblesFromSalaireMatricule(aTransaction, aSalaire.getNomatr(), aSalaire.getNumcpte(), aSalaire.getIdetbs(), aSalaire.getMontnt(), bib);
}
/**
 * Retourne un Salaire.
 * @return Salaire
 */
public static Salaire chercherSalaire(nc.mairie.technique.Transaction aTransaction, String code) throws Exception{
	Salaire unSalaire = new Salaire();
	return unSalaire.getMySalaireBroker().chercherSalaire(aTransaction, code);
}
/**
 * Methode modifierObjetMetier qui retourne
 * true ou false
 */
public boolean modifierSalaire(nc.mairie.technique.Transaction aTransaction, Salaire aSalairePossible, String bib) throws Exception {
	//Modification du Salaire
	return getMySalaireBroker().modifierSalaire(aTransaction,getNomatr(),getNumcpte(),getIdetbs(), getNoacti(),getCodfon(),getRefemp(),getMontnt(), aSalairePossible.getNoacti(),aSalairePossible.getCodfon(),aSalairePossible.getRefemp(), bib);
}
/**
 * Methode supprimerObjetMetier qui retourne
 * true ou false
 */
public boolean supprimerSalaire(nc.mairie.technique.Transaction aTransaction, String bib) throws Exception{
	//Suppression de l'Salaire
	return getMySalaireBroker().supprimerSalaire(aTransaction,getNomatr(),getNumcpte(),getIdetbs(), getNoacti(),getCodfon(),getRefemp(),getMontnt(), bib);
}
}
