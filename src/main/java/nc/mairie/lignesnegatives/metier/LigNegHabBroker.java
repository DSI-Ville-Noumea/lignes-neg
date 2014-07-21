package nc.mairie.lignesnegatives.metier;

import nc.mairie.technique.BasicRecord;
/**
 * Broker de l'Objet métier LigNegHab
 */
public class LigNegHabBroker extends nc.mairie.technique.BasicBroker {
/**
 * Constructeur LigNegHabBroker.
 * @param aMetier aMetier 
 */
public LigNegHabBroker(nc.mairie.technique.BasicMetier aMetier) {
	super(aMetier);
}
/**
 * @return JavaSource/nc.mairie.lignesnegatives.metier.LigNegHabMetier
 */
protected nc.mairie.technique.BasicMetier definirMyMetier() {
	return new LigNegHab() ;
}
/**
 * @return JavaSource/nc.mairie.lignesnegatives.metier.LigNegHabMetier
 */
protected LigNegHab getMyLigNegHab() {
	return (LigNegHab)getMyBasicMetier();
}
/**
 * Retourne le nom de la table.
 */
protected java.lang.String definirNomTable() {
	return "MAIRIE.LIGNEGHAB";
}
/**
 * Retourne le mappage de chaque colonne de la table.
 */
protected java.util.Hashtable<String, BasicRecord> definirMappageTable() throws NoSuchFieldException {
	java.util.Hashtable<String, BasicRecord> mappage = new java.util.Hashtable<String, BasicRecord>();
	mappage.put("USERNAME", new BasicRecord("USERNAME", "VARCHAR", getMyLigNegHab().getClass().getField("username"), "STRING"));
	return mappage;
}
/**
 * Retourne un ArrayList d'objet métier : LigNegHab.
 * @param aTransaction aTransaction
 * @return java.util.ArrayList
 * @throws Exception Exception
 */
public java.util.ArrayList<LigNegHab> listerLigNegHab(nc.mairie.technique.Transaction aTransaction) throws Exception {
	return executeSelectListe(aTransaction,"select * from "+getTable()+"");
}
}
