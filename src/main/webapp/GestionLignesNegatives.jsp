<!-- Sample JSP file --> <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@page contentType="text/html;charset=UTF-8"%>
<HTML>
<HEAD>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.3 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<TITLE>Gestion des lignes négatives</TITLE>
<SCRIPT language="javascript" src="js/menu.js"></SCRIPT>
<SCRIPT>

 //afin de sélectionner un élément dans une liste
function executeBouton(nom)
{
document.formu.elements[nom].click();
}

// afin de mettre le focus sur une zone précise
function setfocus(nom)
{
document.formu.elements[nom].focus();
}



</SCRIPT>
<LINK href="theme/sigp2.css" rel="stylesheet" type="text/css">

</HEAD>

<BODY bgcolor="#FFFFFF" BGPROPERTIES="FIXED" background="images/fond.jpg" >
<jsp:useBean id="process"
	class="nc.mairie.lignesnegatives.process.GestionLignesNegatives" scope="session"></jsp:useBean>
<script>
	ajoute('Gestion', '<%=request.getContextPath()%>/LignesNegativesServlet?ACTIVITE=GestionLignesNegatives&BIB=<%=process.getBibCourant()%>');
	ajoute('Titres de Recette', '<%=request.getContextPath()%>/LignesNegativesServlet?ACTIVITE=GestionLogLignesNegatives&BIB=<%=process.getBibCourant()%>');
	insererMenu('Menu');
</script>
<TABLE border="0" width="580" style="text-align : center;" align="center">
	<TBODY align="center">
		<%@ include file="BanniereErreur.jsp"%>
		<TR>
			<TD>
			<FORM name="formu" method="POST" action ="LignesNegativesServlet">
			<FONT size="5" color="teal"><I>Gestion des lignes négatives</I></FONT>
			<BR>
			<%//if (process.getBibCourant()==null ) { %>

<!-- Table des lignes négatives -->
<FIELDSET class="sigp2Fieldset" style="width: 600"><LEGEND align="left">Lignes négatives</LEGEND>
<%if (null != process.getBibCourant() && process.initialiseLazyLB().equals(process.getVAL_LB_LIGNES_NEG())) {%>
	<%= "Pas de lignes négatives pour la bibliothèque "+process.getBibCourant()+"." %>
<%} else { %>
			<TABLE border="1" style="width: 100%">
				<TBODY>
					<TR>
						<TD nowrap="nowrap" style="font-family: monospace; font-size: 11px">  Compte   OPI  NFA Employeur  Montant   Code Organisme</TD>
					</TR>
				</TBODY>
			</TABLE><SELECT size="5" onclick="executeBouton('<%=process.getNOM_PB_LIGNES_NEG() %>')" name="<%=process.getNOM_LB_LIGNES_NEG()%>"
				class="sigp2-liste" style="width: 100%">
				<%=process.forComboHTML(process.getVAL_LB_LIGNES_NEG(), process.getVAL_LB_LIGNES_NEG_SELECT()) %>
			</SELECT><INPUT type="submit"
				name="<%=process.getNOM_PB_LIGNES_NEG() %>" value="Chercher"
				class="sigp2-Bouton-100" style="display: none"><BR>
<%} %>
</FIELDSET>

<br>
<!-- Table des nomatr négatives -->
<% if (! process.initialiseLazyLB().equals(process.getVAL_LB_LIGNES_NOMATR()) ) {%>
	<FIELDSET class="sigp2Fieldset" style="width: 600"><LEGEND align="left">Lignes négatives par matricule</LEGEND>

			<TABLE border="1" style="width: 100%">
				<TBODY>
					<TR>
						<TD nowrap="nowrap"
							style="font-family: monospace; font-size: 11px">Nomatr   Compte  OPI  NFA Employeur  Montant   Code Organisme</TD>
					</TR>
				</TBODY>
			</TABLE><SELECT size="9" onclick="executeBouton('<%=process.getNOM_PB_LIGNES_NOMATR() %>')"name="<%=process.getNOM_LB_LIGNES_NOMATR()%>"
				class="sigp2-liste" style="width: 100%">
				<%=process.forComboHTML(process.getVAL_LB_LIGNES_NOMATR(), process.getVAL_LB_LIGNES_NOMATR_SELECT()) %>
			</SELECT><INPUT type="submit"
				name="<%=process.getNOM_PB_LIGNES_NOMATR() %>" value="Chercher"
				class="sigp2-Bouton-100" style="display: none"><BR>

	<%if (process.getSalaireNomatrCourant() != null &&  "".equals(process.getVAL_ST_ACTION()) ) { %>
			<TABLE border="0">
				<TBODY>
					<TR>
						
						<TD><INPUT type="submit"
							name="<%=process.getNOM_PB_MODIFIER()%>" value="Modifier"
							class="sigp2-Bouton-100"></TD>
<TD></TD>
					<TD><INPUT type="submit" name="<%=process.getNOM_PB_SUPPRIMER() %>" value="Supprimer" class="sigp2-Bouton-100"></TD></TR>
				</TBODY>
			</TABLE>
	<%} %>
</FIELDSET>
<%} %>
<BR>
<!-- Actions de validation -->
<%if (!"".equals(process.getVAL_ST_ACTION()) ) {%>
			<FIELDSET class="sigp2Fieldset" style="width: 600"><LEGEND align="left">Validation
			de l'action</LEGEND> <%=process.getVAL_ST_ACTION() %> <%if (process.ACTION_MODIFICATION.equals(process.getVAL_ST_ACTION())) {%>
			<TABLE border="1" style="width: 100%">
				<TBODY>
					<TR>
						<TD nowrap="nowrap"
							style="font-family: monospace; font-size: 11px">  Compte   OPI  NFA Employeur  Montant   Code Organisme</TD>
					</TR>
				</TBODY>
			</TABLE>
			<SELECT size="1" name="<%=process.getNOM_LB_LIGNES_POSSIBLES()%>"
				class="sigp2-liste" style="width: 100%">
				<%=process.forComboHTML(process.getVAL_LB_LIGNES_POSSIBLES(), process.getVAL_LB_LIGNES_POSSIBLES_SELECT()) %>
			</SELECT> <%} %>
			<TABLE border="0">
				<TBODY>
					<TR>

						<TD><INPUT type="submit" name="<%=process.getNOM_PB_VALIDER()%>"
							value="Valider" class="sigp2-Bouton-100"></TD>
						<TD></TD>
						<TD><INPUT type="submit" name="<%=process.getNOM_PB_ANNULER() %>"
							value="Annuler" class="sigp2-Bouton-100"></TD>
					</TR>
				</TBODY>
			</TABLE>

			</FIELDSET>
			<%} %>
<!-- Fin. place au hidden -->
			<INPUT name="JSP" type="hidden" value="<%= process.getJSP() %>"><BR></FORM>
			</TD>
		</TR>
	</TBODY>
</TABLE>
</BODY>
</HTML>