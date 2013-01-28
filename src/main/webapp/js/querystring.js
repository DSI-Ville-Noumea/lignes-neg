// querystring.js

// **********************************
// * Nom du script : querystring_fonc v1.0
// * Auteur: Frédéric REMISE (Derf)
// * Date de création: 23/01/2003
// * Email : 
// **********************************

// Récupération de la requête contenue dans l'URL (sans le ?)
var req = window.location.search.substr(1,window.location.search.length);

// Récupération des paires paramètre=valeur
var dbl = req.split('&');

var aPrm = new Array();    // Pour stock. le nom des paramètres
var aVal = new Array();    // Pour stock. la valeur des paramètres
var objQS = new Object();  // Objet pour stock. le nom des paramètres

for (i=0;i < dbl.length;i++) {
  // Recup. le nom des parametres (Attention : elem.1 du tab. = param.1)
  aPrm[i+1] = dbl[i].substring(0,dbl[i].indexOf('='));
  
  // Recup. la valeur des parametres (Attention : elem.1 du tab. = val.param.1)
  aVal[i+1] = unescape(dbl[i].substring(dbl[i].indexOf('=')+1,dbl[i].length));
  
  // Stock la valeur des paramètres sous forme de propriété
  objQS[aPrm[i+1]] = aVal[i+1];
}

// Renvoie la valeur d'un parametre par son nom
function getQueryStringByName(pname) 
{
  return eval("objQS." + pname);
}

// Renvoie la valeur d'un paramètre par sa position dans la requete
// Attention : le premier paramètre de la requete est le 1 etc..
function getQueryStringByPos(ppos)
{
  return aVal[ppos];
}

// Renvoie le nom d'un paramètre par sa position dans la requete
// Attention : le premier paramètre de la requete est le 1 etc..
function getParamNameByPos(ppos)
{
  return aPrm[ppos];
}

// Renvoie la valeur d'un paramètre par sa position dans la requete
// Attention : le premier paramètre de la requete est le 1 etc..
function getAllQueryString()
{
  return req;
}
