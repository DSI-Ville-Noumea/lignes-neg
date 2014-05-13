var onglet = new Array();
var lien = new Array();
var activite = new Array();

var nbOnglet = 0;

function ajoute(unOnglet, unLien, unActivite) {
	onglet[nbOnglet] = unOnglet;
	lien[nbOnglet] = unLien;
	activite[nbOnglet] = unActivite;
	nbOnglet++;
}

function cacherMenu() {
	contenu.style.display="none";
}

function montrerMenu() {
	contenu.style.display="";
}

function insererMenu(unTitre) {

document.writeln('<DIV style="width : 140px;height : 10px;top : 2px;left : 2px;');
document.writeln('  position : absolute;');
//document.writeln('  position : static;');
document.writeln('  z-index : 1;');
document.writeln('  visibility : visible;');
document.writeln('  text-align : center;');
//document.writeln('background-color : #309098;color : white;font-family : Arial;font-size : 16px;cursor : hand;font-weight : bold;" id="MenuClosed" onmouseover="montrerMenu()" onmouseout="cacherMenu()">');
document.writeln('background-color : #1b99a1;color : #ebebeb;font-family : Arial;font-size : 16px;cursor : pointer;font-weight : bold;" id="MenuClosed" onmouseover="montrerMenu()" onmouseout="cacherMenu()">');

//Le titre
document.writeln('<TABLE border="0" cellspacing="0" cellpadding="0" width="100%" style="color : #ebebeb;text-align : center;background-color : #309098">');
document.writeln('<TBODY>');
		document.writeln('<TR style="border = 0">');
		document.writeln('<TD>');
		document.writeln(unTitre);
		document.writeln('&nbsp;</TD>');
		document.writeln('</TR>');
document.writeln('</TBODY>');
document.writeln('</TABLE>');


//document.writeln(unTitre+'<nobr>');
//document.writeln('<FORM name="lemenu" id="lemenu">');
document.writeln('<TABLE border="1" cellspacing="0" cellpadding="0" width="100%" id="contenu" style="display : none;color : #ebebeb;text-align : center;background-color : #309098">');
document.writeln('<TBODY>');

document.writeln('<INPUT id= "ACTIVIT" name="ACTIVIT" type="hidden" value=""/>');

	for (var i=0;i<nbOnglet;i++) {
		document.writeln('<TR>');
		document.writeln('<TD border=0 align="left" onclick="document.getElementById(\'ACTIVIT\').value = \''+activite[i]+'\'; document.getElementById(\'ACTIVIT\').name=(\'ACTIVITE\');  document.formu.submit();">');
		document.writeln(onglet[i]);
		document.writeln('</TD>');
		

		document.writeln('</TR>');
	}

		document.writeln('<TR>');
		document.writeln('<TD onclick="if (confirm(\'Vous désirez vraiment quitter ?\')) {parent.close();}">');
		document.writeln('Quitter');
		document.writeln('</TD>');
		

		document.writeln('</TR>');
	

document.writeln('</TBODY>');
document.writeln('</TABLE>');
//document.writeln('</FORM>');
document.writeln('</DIV>');

}
