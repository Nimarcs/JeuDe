# JeuDe

## Auteur

Alexis Lopes Vaz
Marcus Richier

## Description

Ce projet Java est une application de jeu de dé utilisant JavaFX pour l'interface graphique.
Ce projet est l'un des projets créé dans le cadre du cours "Analyse, conception et mise en œuvre de SI à base de patrons".
Il intègre un système de fichier JSON, ou de MongoDB pour la persistance des données.

## Comment installer

### Prérequis

- **Java 21** : Assurez-vous que Java 21 est installé sur votre système.
- **Maven** : Installez Apache Maven pour gérer les dépendances et la compilation.
- **MongoDB** (version dégradée disponible sans) : https://www.mongodb.com

### Étapes d'installation

1. Clonez ce dépôt :
   ```bash
   git clone <url-du-dépôt>
   cd jeuDe
   ```
2. Compilez et installez les dépendances :
   ```bash
   mvn clean install
   ```

3. Exécutez le projet :
   ```bash
   mvn javafx:run
   ```

## Versions des dépendances testées

- org.json.json : 20240303
- MongoDB Driver Sync : 5.2.0
- Logback Classic : 1.4.12
- JavaFX Controls et FXML : 23.0.1