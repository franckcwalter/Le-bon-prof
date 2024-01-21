
# PROJET PERSONNEL

APPLICATION DE MISE EN CONTACT
entre des PROFESSEURS PARTICULIERS et des ÉLÈVES (parents d'élèves)






## TODO :


-> utiliser corresctement les thèmes 


MAIN
=> FILTRES = filtres en fonction des critères
-> filtre par favoris ??

--> problème d'encodage (par ex les '  )
--> field pour le delete 

[DETAIL : 
GESTION DES NOTES (de 1 à 5 ÉTOILES)
]()
_______  

UI : -> XML à faire : page conversation  et liste de conversations 

DESIGN : 
-> design adapté à différentes tailles d'écrans (mode paysage notamment)  


Si j'ai le time : 
-> hachage du mot de passe




## NOM 

MonProf
professeur particulier

LeBonProf 



## USE CASE

LISTE DES USE CASE

côté utilisateur élève :
- parcourir les annonces de professeurs
  possibilité de filtrer les annoces (mat, prix, loc)
- consulter l'annonce d'un professeur
- écrire un message à un professeur
- noter et commenter un professeur
  (agenda : réserver un prof sur un créneau)
  (noter un prof)


côté utilisateur professeur :
- publier une annonce  (photo, matière enseignée, description, chez soi/chez l'élève/distance, loc, prix)
  et modifier l'annonce
- consulter la messagerie
- enovoyer et recevoir des messages
- (voir les créneaux réservés)

côté gestionnaire :
- modération des annonces
- modération des avis

(statistiques)


## BASE DE DONNÉES

### DD


RÔLE
- CODE
- nom du rôle (gestionnaire, user élève, user prof)

USER
- adresse e-mail
- mot de passe
- prénom

  un USER possède 1-1 RÔLE  
  un RÔLE est possédé par plusieurs (0-N) USERs 

AD
- référence de l'annonce (id unique)
- titre de l'annonce (limitée en caractères)
- photo
- description de l'annonce
- lieu du cours : chez soi, chez l'élève, en visio (place)
- localisation : toponyme
- prix
r- validée ou non par le gest

  une AD est postée par 1-1 USER 
  un USER peut poster 0 ou plusieurs 0-N ANNONCES

  un USER learner peut mettre en favoris aucune ou plusieurs AD  
  une AD est mise en favoris par aucun ou plusieurs USER learner 

SUBJECT  
- code (?)
- nom de la matière

  une AD peut proposer plusieurs (1-N) SUBJECTS 
  une SUBJECT peut être proposée par aucune ou plusieurs (0-N) AD


MESSAGE
- id unique 
- énoncé du message
- date et heure (datetime)

  un MESSAGE est envoyé par 1-1 seul USER 
  un MESSAGE est envoyé à 1-1 seul USER 
  un USER envoie aucune ou plusieurs (0-N) messages  


RATIING 
- (id unique ?)
- nombre d'étoiles 


  un USER fait aucun ou plusieurs RATINGS 
  un RATING est fait par 1 USER 

  un USER reçoit aucun ou plusierus RATINGS 
  un RATING est reçu par 1 USER 






### Api en php pour la base de données

créer un API en PHP :
connection à la bdd en localhost
avec PDO : créer des query
et avec créer des JSON
echo des JSON

fichier php se conencter à la BDD
envoi et récupération de données avec $GET et $POST
tester les valeurs

hébergeur gratuit
Hostinger
IONOS by 1&1
ALWAYS DATA

DOC API  
https://www.positronx.io/create-simple-php-crud-rest-api-with-mysql-php-pdo/


https://fwadevidfinalproject.alwaysdata.net/

https://fwadevidfinalproject.alwaysdata.net/api/user/get-all.php
https://fwadevidfinalproject.alwaysdata.net/api/subject/get-all.php
https://fwadevidfinalproject.alwaysdata.net/api/ad/get-all.php


phpmyadmin alwaysdata:  
341761
g*vwrF!A9fqngzR




https://web.postman.co/




AD 
getAll()
ajouter les fav du user 

get()
ajouter si fav du user ou non 


USER 
get()
ajouter les infos d'une annonce : id, titre, desc, prix
JOIN MES ANNONCES FAV
(et JOIN MES CONV)

?? 




## FRAGMENTS

### 1. SPLASH

- logo
- vérification si connecté ou non


### 2. REGISTER

- editText : email
- editText : mdp
- choix : prof ou élève


### 3. LOGIN

- editText : email
- editText : mdp


### 4. MAIN : LISTE DES ANNONCES avec FILTRES

Liste des annonces (rv)

Possibilité de filtrer en fonction :
- la matière (liste déroulante ? spinner ?)
- le prix (slider)
- la localité (editText)

Cardview des annonces :
- photo du prof
- prénom du prof
- titre / début du titre
- prix



### 5. PROFILE TEACHER :  PAGE PROFIL POUR PROF

Compte de + adresse e-mail  
Bouton Se déconnecter
(modifier mon mdp)

Affichage de mon annonce si annonce
BOUTON  accéder à mon annonce / BOUTON créer une annonce

Liste des conversations avec les élèves
-> clic sur un conv = accès aux messages échangés avec un.e élève


### 6. PROFILE LEARNER :  PAGE PROFIL POUR PROF

Mon nom
Modifier mon mdp

Liste de mes annonces favorites

Liste des conversations / des messages reçus

Liste des conversations avec les professeurs
-> clic sur un conv = accès aux messages échangés avec un.e élève


### 7. AD CREATE  :  CRÉER UNE ANNONCE

titre
-> prendre ou choisir une photo
choix de la matière / des matières
prix
localisation : toponyme
lieu du cours : chez soi, chez l'élève, en visio
annonce

### 8. AD UPDATE : MODIFIER UNE ANNONCE

titre
-> prendre ou choisir une photo
choix de la matière / des matières
prix
localisation : toponyme
lieu du cours : chez soi, chez l'élève, en visio
annonce



### 9. AD DETAIL : CONSULTER UNE ANNONCE

titre
(référence de l'annonce (id unique))
validée ou non par le gest -> check

prix

photo
nom du prof,
lieu
note du prof en nombre d'étoiles

matière / matières enseignée.s
lieu du cours : chez soi, chez l'élève, en visio, tiers lieu

description de l'annonce

BOUTON pour écrire un message au prof



### 10.  CONVERSATION (ENTRE UN PROF ET UN ÉLÈVE) 

affiche les messages entre un prof et un élève

POUR L'ÉLÈVE : lien vers l'annonce


### 11. ADMIN : PAGE GESTIONNAIRE

liste des annonces postées -> validation

liste des commentaire postés ->  validation des commentaires 




## Comment_

-> penser aux méthodes
remonter en pensant aux classes qui font sens