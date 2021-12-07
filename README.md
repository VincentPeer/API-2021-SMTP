
Your report MUST include the following sections:

* **A brief description of your project**: if people exploring GitHub find your repo, without a prior knowledge of the API course, they should be able to understand what your repo is all about and whether they should look at it more closely.

* **Instructions for setting up a mock SMTP server (with Docker - which you will learn all about in the next 2 weeks)**. The user who wants to experiment with your tool but does not really want to send pranks immediately should be able to use a mock SMTP server. For people who are not familiar with this concept, explain it to them in simple terms. Explain which mock server you have used and how you have set it up.

* **Clear and simple instructions for configuring your tool and running a prank campaign**. If you do a good job, an external user should be able to clone your repo, edit a couple of files and send a batch of e-mails in less than 10 minutes.

* **A description of your implementation**: document the key aspects of your code. It is probably a good idea to start with a class diagram. Decide which classes you want to show (focus on the important ones) and describe their responsibilities in text. It is also certainly a good idea to include examples of dialogues between your client and an SMTP server (maybe you also want to include some screenshots here).

### Introduction

Ce projet a pour but de nous familiariser avec le protocole SMTP en envoyant 
des emails contenant des plaisanteries. 

#### Package model
Ce package contient 2 sous packages qui sont le mail et le prank, leur union permet
de construire un mail complet, contenant une plaisanterie.
##### Package mail
La classe Person représente simplement une personne. 
Pour ce projet, nous utilisons uniquement l'adresse mail comme attribut, en effet dans un mail
les noms et prénoms peuvent ne pas être forcément nécessaire et nous  nous sommes contentés du
contenu principal.

La Classe Group représente une liste de Person, elle est munie de quelques méthodes de base
qui permettent de connaître la taille d'un groupe, d'ajouter ou retirer 
une personne, de vérifier si un groupe est vide ou non, et d'autres encore.
Cette classe sera particulièrement utile pour crée les groupes formant un mail, à 
savoir l'émetteur et les récepteurs.

La classe Mail possède tous les champs nécesssaire pour créer un mail dans sa totalité. 
pour chadun de ces champs, il existe un getteur et un setteur pour pouvoir accéder ou modifier
les information du mail.

#### Package prank
La classe PrankConfig stocke les informations qui permettent la création de mail en spécifiant
les noms de fichier à lire pour les listes d'email et de plaisantiries. Le nombre de groupe
est égalmenet stocké ici. Des setteur/getteur sont disponibles pour configurer ce type 
d'information.

La classe PrankGenerator met en oeuvre tout l'algorithme qui écrit un mail dans sa totalité en 
préparant chaque attribut avec des valeurs qu'il détermine lui-même en lisant
les fichiers fournis. Il crée également les groupes de personnes qui feront parties d'un prank.