var onglet = new Array();
var lien = new Array();

var nbOnglet = 0;
var defaultWidth = 80;

function ajoute(unOnglet, unLien) {
	onglet[nbOnglet] = unOnglet;
	lien[nbOnglet] = unLien;
	nbOnglet++;
}

function cacherMenu() {
	contenu.style.display="none";
	MenuClosed.style.width=defaultWidth;
}

function montrerMenu() {
	contenu.style.display="";
	MenuClosed.style.width=150;
}

function insererMenu(unTitre) {

document.writeln('<DIV class="sigp2Fieldset" style="width : defaultWidth;height : 10px;top : 2px;left : 2px;');
document.writeln('  position : absolute;');
document.writeln('  z-index : 1;');
document.writeln('  visibility : visible;');
document.writeln('  text-align : center;');
document.writeln('background-color : #309098;color : white;font-family : Arial;font-size : 16px;cursor : hand;" id="MenuClosed" onmouseover="montrerMenu()" onmouseout="cacherMenu()">');

document.writeln(unTitre+'<nobr>');

document.writeln('<TABLE class="sigp2" border="1" width="100%" id="contenu" style="display : none;color : white;text-align : center;">');
document.writeln('<TBODY>');

	for (i=0;i<nbOnglet;i++) {
		document.writeln('<TR>');
//		document.writeln('<TD>');
//		document.writeln('<A href="' + lien[i] + '">'+onglet[i]+'</A>');
//		document.writeln('</TD>');

		document.writeln('<A href="' + lien[i] + '">');
		document.writeln('<TD>');
		document.writeln(onglet[i]);
		document.writeln('</TD>');
		document.writeln('</A>');


		document.writeln('</TR>');
	}

document.write('<tr><TD><IMG src="images/print.gif" width="28" height="30" border="0" onclick="cacherMenu();document.body.focus();window.print()" style="cursor : pointer;" title="Cliquer pour imprimer ;-)"></TD></tr>');


document.writeln('</TBODY>');
document.writeln('</TABLE>');

document.writeln('</DIV>');

cacherMenu();
}
