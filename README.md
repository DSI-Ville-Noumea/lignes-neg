[![Build Status](https://travis-ci.org/DSI-Ville-Noumea/lignes-neg.svg?branch=master)](https://travis-ci.org/DSI-Ville-Noumea/lignes-neg) [![Dependency Status](https://www.versioneye.com/user/projects/5770a698671894004fedd4f4/badge.svg?style=flat-square)](https://www.versioneye.com/user/projects/5770a698671894004fedd4f4)

# lignes-neg
Repo lignes-neg.

Tous les mois, lorsque les payes sont générées, des montants doivent être injectés dans la comptabilité.

Il arrive que certains montants dépassent le budget disponible et génèrent des lignes négatives.
Le but de cette application est de supprimer ces lignes négatives en permettant 2 actions possibles :

    Soit injecter les montants dans d’autres lignes comptables qui ont le budget nécessaire
    Soit supprimer les montants : la DRH doit alors créer un titre de recette pour chaque ligne négative supprimée
