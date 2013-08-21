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
	class="nc.mairie.lignesnegatives.process.GestionLogLignesNegatives" scope="session"></jsp:useBean>
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

<TABLE border="0" class="sigp2" width="100%">
<tr>
						<TD align="right" width="50%">Chaîne/Percou :</TD>
<TD align="left" width="50%">

			<SELECT size="1" name="<%=process.getNOM_LB_CHAINE_PERCOU()%>"
							onchange="executeBouton('<%=process.getNOM_PB_CHAINE_PERCOU() %>')"
							class="sigp2-liste">
<%=process.forComboHTML(process.getVAL_LB_CHAINE_PERCOU(), process.getVAL_LB_CHAINE_PERCOU_SELECT()) %>
						</SELECT>
<INPUT type="submit" name="<%=process.getNOM_PB_CHAINE_PERCOU() %>"
						value="Chercher" class="sigp2-Bouton-100" style="display: none"></TD>
</TR>
</table>

	<FIELDSET class="sigp2Fieldset" style="width: 600" id="popol"><LEGEND align="left">Lignes négatives supprimées</LEGEND>



			<TABLE width="100%" border="1" cellpadding="0" cellspacing="0" class = "sigp2">
				<THEAD>
<TR>
						<TH>Nomatr</TH>
						<TH>Compte</TH>
						<TH>OPI</TH>
						<TH>NFA</TH>
						<TH>Employeur</TH>
						<TH>Montant</TH>
						<TH>Code</TH>
						<TH>Organisme</TH>
<TR>
				</THEAD>
					<%=process.getLignesNegativesSupprimees() %>
			</TABLE>
			<BR>
			</FIELDSET>



			<INPUT name="JSP" type="hidden" value="<%= process.getJSP() %>"><BR></FORM>
			</TD>
		</TR>
	</TBODY>
</TABLE>
</BODY>
</HTML>