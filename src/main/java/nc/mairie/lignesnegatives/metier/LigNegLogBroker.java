package nc.mairie.lignesnegatives.metier;

import nc.mairie.technique.BasicRecord;
/**
 * Broker de l'Objet métier LigNegLog
 */
public class LigNegLogBroker extends nc.mairie.technique.BasicBroker {
/**
 * Constructeur LigNegLogBroker.
 * @param aMetier aMetier
 */
public LigNegLogBroker(nc.mairie.technique.BasicMetier aMetier) {
	super(aMetier);
}
/**
 * @return JavaSource/nc.mairie.lignesnegatives.metier.LigNegLogMetier
 */
protected nc.mairie.technique.BasicMetier definirMyMetier() {
	return new LigNegLog() ;
}
/**
 * @return JavaSource/nc.mairie.lignesnegatives.metier.LigNegLogMetier
 */
protected LigNegLog getMyLigNegLog() {
	return (LigNegLog)getMyBasicMetier();
}
/**
 * Retourne le nom de la table.
 */
protected java.lang.String definirNomTable() {
	return "MAIRIE.LIGNEGLOG";
}
/**
 * Retourne le mappage de chaque colonne de la table.
 */
protected java.util.Hashtable<String, BasicRecord> definirMappageTable() throws NoSuchFieldException {
	java.util.Hashtable<String, BasicRecord> mappage = new java.util.Hashtable<String, BasicRecord>();
	mappage.put("USER", new BasicRecord("USER", "VARCHAR", getMyLigNegLog().getClass().getField("user"), "STRING"));
	mappage.put("BIB", new BasicRecord("BIB", "VARCHAR", getMyLigNegLog().getClass().getField("bib"), "STRING"));
	mappage.put("CHAINE", new BasicRecord("CHAINE", "VARCHAR", getMyLigNegLog().getClass().getField("chaine"), "STRING"));
	mappage.put("ACTION", new BasicRecord("ACTION", "VARCHAR", getMyLigNegLog().getClass().getField("action"), "STRING"));
	mappage.put("DATEACTION", new BasicRecord("DATEACTION", "VARCHAR", getMyLigNegLog().getClass().getField("dateaction"), "STRING"));
	mappage.put("LIBELLEACTION", new BasicRecord("LIBELLEACTION", "VARCHAR", getMyLigNegLog().getClass().getField("libelleaction"), "STRING"));
	return mappage;
}
/**
 * Methode creerObjetMetierBroker qui retourne
 * true ou false
 * @param aTransaction aTransaction
 * @return boolean
 * @throws Exception Exception
 */
public boolean creerLigNegLog(nc.mairie.technique.Transaction aTransaction)  throws Exception{
	return creer(aTransaction);
}
/**
 * Retourne un ArrayList d'objet métier : LigNegLog.
 * @param aTransaction aTransaction
 * @param bib bib
 * @return java.util.ArrayList
 * @throws Exception Exception
 */
public java.util.ArrayList<LigNegLog> listerLigNegLogPourTitreRecette(nc.mairie.technique.Transaction aTransaction, String bib) throws Exception {
	return executeSelectListe(aTransaction,
			"select distinct chaine, substr(dateaction,1,7) as dateaction"+
			" from mairie.ligneglog"+
			" where action = 'S'"+
			" AND bib = '"+bib+"'"+
			" order by substr(dateaction,1,7), chaine");
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
public java.util.ArrayList<LigNegLog> listerLigNegLogChainePercou(nc.mairie.technique.Transaction aTransaction, String bib, String chaine, String percou) throws Exception {
	return executeSelectListe(aTransaction,
			"select *"+
			" from mairie.ligneglog"+
			" where action = 'S'"+
			" AND bib = '"+bib+"'"+
			" AND chaine ='"+chaine+"'"+
			" AND dateaction like '"+percou+"%'");
}
}
