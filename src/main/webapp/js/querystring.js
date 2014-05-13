// querystring.js

// **********************************
// * Nom du script : querystring_fonc v1.0
// * Auteur: FrÃ©dÃ©ric REMISE (Derf)
// * Date de crÃ©ation: 23/01/2003
// * Email : 
// **********************************

// RÃ©cupÃ©ration de la requÃªte contenue dans l'URL (sans le ?)
var req = window.location.search.substr(1,window.location.search.length);

// RÃ©cupÃ©ration des paires paramÃ¨tre=valeur
var dbl = req.split('&');

var aPrm = new Array();    // Pour stock. le nom des paramÃ¨tres
var aVal = new Array();    // Pour stock. la valeur des paramÃ¨tres
var objQS = new Object();  // Objet pour stock. le nom des paramÃ¨tres

for (var i=0;i < dbl.length;i++) {
  // Recup. le nom des parametres (Attention : elem.1 du tab. = param.1)
  aPrm[i+1] = dbl[i].substring(0,dbl[i].indexOf('='));
  
  // Recup. la valeur des parametres (Attention : elem.1 du tab. = val.param.1)
  aVal[i+1] = unescape(dbl[i].substring(dbl[i].indexOf('=')+1,dbl[i].length));
  
  // Stock la valeur des paramÃ¨tres sous forme de propriÃ©tÃ©
  objQS[aPrm[i+1]] = aVal[i+1];
}

// Renvoie la valeur d'un parametre par son nom
function getQueryStringByName(pname) 
{
  return eval("objQS." + pname);
}

// Renvoie la valeur d'un paramÃ¨tre par sa position dans la requete
// Attention : le premier paramÃ¨tre de la requete est le 1 etc..
function getQueryStringByPos(ppos)
{
  return aVal[ppos];
}

// Renvoie le nom d'un paramÃ¨tre par sa position dans la requete
// Attention : le premier paramÃ¨tre de la requete est le 1 etc..
function getParamNameByPos(ppos)
{
  return aPrm[ppos];
}

// Renvoie la valeur d'un paramÃ¨tre par sa position dans la requete
// Attention : le premier paramÃ¨tre de la requete est le 1 etc..
function getAllQueryString()
{
  return req;
}
