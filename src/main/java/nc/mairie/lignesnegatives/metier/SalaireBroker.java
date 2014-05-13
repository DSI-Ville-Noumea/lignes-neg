package nc.mairie.lignesnegatives.metier;

import nc.mairie.technique.BasicRecord;
/**
 * Broker de l'Objet métier Salaire
 */
public class SalaireBroker extends nc.mairie.technique.BasicBroker {
/**
 * Constructeur SalaireBroker.
 */
public SalaireBroker(nc.mairie.technique.BasicMetier aMetier) {
	super(aMetier);
}
/**
 * @return JavaSource/nc.mairie.lignesnegatives.metier.SalaireMetier
 */
protected nc.mairie.technique.BasicMetier definirMyMetier() {
	return new Salaire() ;
}
/**
 * @return JavaSource/nc.mairie.lignesnegatives.metier.SalaireMetier
 */
protected Salaire getMySalaire() {
	return (Salaire)getMyBasicMetier();
}

/**
 * Retourne le nom de la table.
 */
protected java.lang.String definirNomTable() {
	return "SPMSM14N";
}
/**
 * Retourne le mappage de chaque colonne de la table.
 */
protected java.util.Hashtable<String, BasicRecord> definirMappageTable() throws NoSuchFieldException {
	java.util.Hashtable<String, BasicRecord> mappage = new java.util.Hashtable<String, BasicRecord>();
	mappage.put("NOMATR", new BasicRecord("NOMATR", "NUMERIC", getMySalaire().getClass().getField("nomatr"), "STRING"));
	mappage.put("NUMCPTE", new BasicRecord("NUMCPTE", "CHAR", getMySalaire().getClass().getField("numcpte"), "STRING"));
	mappage.put("CODFON", new BasicRecord("CODFON", "CHAR", getMySalaire().getClass().getField("codfon"), "STRING"));
	mappage.put("NOACTI", new BasicRecord("NOACTI", "CHAR", getMySalaire().getClass().getField("noacti"), "STRING"));
	mappage.put("MONTNT", new BasicRecord("MONTNT", "DECIMAL", getMySalaire().getClass().getField("montnt"), "STRING"));
	mappage.put("IDETBS", new BasicRecord("IDETBS", "NUMERIC", getMySalaire().getClass().getField("idetbs"), "STRING"));
	mappage.put("REFEMP", new BasicRecord("REFEMP", "CHAR", getMySalaire().getClass().getField("refemp"), "STRING"));

	mappage.put("SUMMONTANT", new BasicRecord("SUMMONTANT", "CHAR", getMySalaire().getClass().getField("summontant"), "STRING"));
	mappage.put("ENSCOM", new BasicRecord("ENSCOM", "CHAR", getMySalaire().getClass().getField("enscom"), "STRING"));
	mappage.put("CDCHAI", new BasicRecord("CDCHAI", "CHAR", getMySalaire().getClass().getField("cdchai"), "STRING"));
	return mappage;
}
/**
 * Methode supprimerObjetMetierBroker qui retourne
 * true ou false
 */
public boolean modifierSalaire(nc.mairie.technique.Transaction aTransaction, String nomatr, String numcpte, String idetbs, String noacti, String codfon, String refemp, String montnt, String newNoacti, String newCodeFon, String newRefemp, String bib) throws java.lang.Exception {
	String clauseWhere = " where nomatr = "+nomatr+" and "+
		" numcpte = '"+numcpte+ "' and "+
		" idetbs = "+idetbs+" and "+
		" noacti = '"+noacti+"' and "+
		" codfon = '"+codfon+"' and "+
		" refemp = '"+refemp+"' and "+
		" montnt = "+montnt;
	
	if (executeCompter(aTransaction, "select count(*) from "+bib+"."+getTable() + clauseWhere) > 1) {
		aTransaction.declarerErreur("Impossible de modifier plus d'une ligne");
		return false;
	}
	return executeUpdate(aTransaction," update "+bib+"."+getTable()+ 
			" set noacti = '"+newNoacti+"' ," +
			" codfon = '"+newCodeFon+"' ," +
			" refemp = '"+newRefemp+"' " +
			clauseWhere	);
}
/**
 * Methode supprimerObjetMetierBroker qui retourne
 * true ou false
 */
public boolean supprimerSalaire(nc.mairie.technique.Transaction aTransaction, String nomatr, String numcpte, String idetbs, String noacti, String codfon, String refemp, String montnt, String bib) throws java.lang.Exception {
	String clauseWhere = " where nomatr = "+nomatr+" and "+
		" numcpte = '"+numcpte+ "' and "+
		" idetbs = "+idetbs+" and "+
		" noacti = '"+noacti+"' and "+
		" codfon = '"+codfon+"' and "+
		" refemp = '"+refemp+"' and "+
		" montnt = "+montnt;
	
	if (executeCompter(aTransaction, "select count(*) from "+bib+"."+getTable() + clauseWhere) > 1) {
		aTransaction.declarerErreur("Impossible de supprimer plus d'une ligne");
		return false;
	}
	return executeUpdate(aTransaction," delete from "+bib+"."+getTable()+ clauseWhere			);
}


public java.util.ArrayList<Salaire> listerSalaireNegatif(nc.mairie.technique.Transaction aTransaction, String bib) throws Exception {
	
	return executeSelectListe(aTransaction,
			
			" select NUMCPTE, s.idetbs, noacti, codfon, refemp, sum(montnt) as summontant, e.enscom, cdchai" +
			" from "+bib+"."+getTable()+" s" +
			" inner join mairie.gftiers e on e.idetbs = s.idetbs" +
			" group by numcpte, s.idetbs, noacti, codfon, refemp, e.enscom, cdchai" +
			" having sum(montnt) < 0");
			

}
/**
 * Retourne un ArrayList d'objet métier : Salaire.
 * @return java.util.ArrayList
 */
public java.util.ArrayList<Salaire> listerSalaireNegatifFromSalaireNegatif(nc.mairie.technique.Transaction aTransaction, String numcpte, String idetbs, String noacti, String codfon, String refemp, String bib) throws Exception {
	return executeSelectListe(aTransaction,
			
			" select nomatr, NUMCPTE, s.idetbs, noacti, codfon, refemp, montnt, e.enscom, cdchai" +
			" from "+bib+"."+getTable()+" s" +
			" inner join mairie.gftiers e on e.idetbs = s.idetbs" +
			" where" + 
			" numcpte = '"+numcpte+ "' and "+
			" s.idetbs = "+idetbs+ " and "+
			" noacti = '"+noacti+"' and "+
			" codfon = '"+codfon+"' and "+
			" refemp = '"+refemp+"' and "+
			" montnt < 0"
			);
			

}
/**
 * Retourne un ArrayList d'objet métier : Salaire.
 * @return java.util.ArrayList
 */
public java.util.ArrayList<Salaire> listerSalairePossiblesFromSalaireMatricule(nc.mairie.technique.Transaction aTransaction, String nomatr, String numcpte, String idetbs, String montnt, String bib) throws Exception {
	return executeSelectListe(aTransaction,
			
			" select NUMCPTE, s.idetbs, noacti, codfon, refemp, sum(montnt) as summontant, e.enscom, cdchai" +
			" from "+bib+"."+getTable()+" s" +
			" inner join mairie.gftiers e on e.idetbs = s.idetbs" +
			" where numcpte = '"+numcpte+"' and "+
			" s.idetbs = "+idetbs+
			" group by numcpte, s.idetbs, noacti, codfon, refemp, e.enscom, cdchai" +
			" having sum(montnt) >= abs("+montnt+") ");			

}


/**
 * Retourne un Salaire.
 * @return Salaire
 */
public Salaire chercherSalaire(nc.mairie.technique.Transaction aTransaction, String cle) throws Exception {
	return (Salaire)executeSelect(aTransaction,"select * from "+getTable()+" where CODE = "+cle+"");
}
}
