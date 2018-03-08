# Projet_tut_S2

>Si vous voulez des infos pour l'edition de ce fichier (README.md), [cliquez ici](https://help.github.com/articles/basic-writing-and-formatting-syntax/)

>J'ai trouvé 2 bons tutos pour git/github avant qu'on commence vraiment le projet ce serait bien qu'on les fasses tous.<br />- [Tuto 1 : interactif dans un navigateur, environ 15min](https://try.github.io/levels/1/challenges/1) <br />- [Tuto 2 : Vidéos rapides et quelques cours un peu plus long mais peut-être mieux expliqué](https://eu.udacity.com/course/how-to-use-git-and-github--ud775)<br />
*C'est surtout la partie sur les branch et la partie collaboration pour le 2eme lien*


## Petites infos concernant les configs du projet: :+1:
- Ajout du .gitignore permettant d'ignorer les fichiers .idea et .iml
- Dans le menu 
	```
	Run/Edit Configuration (sur Intellij IDEA)
	```
	Ajouter, pour le main que vous lancer (choisir dans le menu à gauche), dans l'option VM
	```
	"-Djava.library.path=/home/aurel/Projet_tut_S2/Bataille_Navale/lib/natives"
	```
	(Remplacer mon path par le votre !)
- Manip si Intellij Idea ne reconnait pas la structure du projet : 
	- fermer Idea 
	-supprimer dossier .idea et le fichier .iml du projet
	-Réouvrir Idea
	-Nouveau projet/selectionner le dossier du projet/donner le meme nom
-ajouter tous les jars dans Project Structure/dependancies/ (Selectionner tous les jars du dossier libs)


## Au fur et à mesure, on completera ce document avec une todo list et en y mettant des infos