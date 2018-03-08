# Projet_tut_S2

>Si vous voulez des infos pour l'edition de ce fichier (README.md), [cliquez ici](https://help.github.com/articles/basic-writing-and-formatting-syntax/#paragraphs-and-line-breaks)
##Petites infos concernant les configs du projet: :+1:
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


##Au fur et à mesure, on completera ce document avec une todo list et en y mettant des infos