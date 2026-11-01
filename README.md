# Application de Location de Véhicules 🚗🏍️

> **Projet Java** — Programmation Orientée Objet  
> Faculté des Sciences de Sfax — Année Universitaire 2025/2026

## 👤 Réalisé par

| Nom                  | Groupe       |
| -------------------- | ------------ |
| **Yassine Lamouchi** | **LSI2 2.2** |

---

## 📋 Description du projet

Cette application console permet à une agence de location de véhicules de gérer informatiquement son parc automobile. Elle offre les fonctionnalités suivantes :

- **Gestion du parc automobile** : ajout, suppression, modification et consultation des véhicules (Voitures et Motos)
- **Gestion des clients** : ajout et recherche de clients
- **Gestion des locations** : location de véhicules, restitution, suivi des locations actives
- **Persistance des données** : sauvegarde et chargement automatique des données au format CSV
- **Authentification administrateur** : accès sécurisé à l'espace d'administration

---

## 📁 Structure du projet

```
ApplicationLocationVehicules/
├── src/
│   ├── model/                          # Package modèle (classes métier)
│   │   ├── Vehicule.java               # Classe abstraite - véhicule générique
│   │   ├── Voiture.java                # Classe Voiture (hérite de Vehicule)
│   │   ├── Moto.java                   # Classe Moto (hérite de Vehicule)
│   │   ├── Client.java                 # Classe Client
│   │   ├── Administrateur.java         # Classe Administrateur
│   │   ├── Location.java               # Classe Location (transaction)
│   │   └── LocationException.java      # Exception personnalisée
│   │
│   ├── service/                        # Package service (logique métier)
│   │   └── AgenceLocation.java         # Gestion du parc, clients et locations
│   │
│   ├── util/                           # Package utilitaire
│   │   └── FichierUtil.java            # Sauvegarde/chargement CSV
│   │
│   └── main/                           # Package principal (interface console)
│       ├── AppLocation.java            # Point d'entrée de l'application
│       ├── MenuAdmin.java              # Menu administrateur
│       └── MenuClient.java             # Menu client
│
├── out/                                # Fichiers compilés (.class)
│   ├── model/
│   ├── service/
│   ├── util/
│   └── main/
│
└── README.md                           # Ce fichier
```

---

## 🏗️ Architecture et Packages

### 📦 `model` — Classes métier

| Classe              | Description                                                                                                                                        |
| ------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------- |
| `Vehicule`          | Classe **abstraite** définissant les attributs communs (référence, marque, modèle, prix, disponibilité) et la méthode abstraite `afficheDetails()` |
| `Voiture`           | Hérite de `Vehicule`, ajoute l'attribut `nbPortes`. Référence auto-générée au format **V**xxxx (ex: V1000)                                         |
| `Moto`              | Hérite de `Vehicule`, ajoute l'attribut `cylindre`. Référence auto-générée au format **M**xxxx (ex: M2000)                                         |
| `Client`            | Modélise un client avec un identifiant unique auto-généré au format **C**xxxx                                                                      |
| `Administrateur`    | Gère l'authentification par login/mot de passe                                                                                                     |
| `Location`          | Représente une transaction de location (client, véhicule, dates, montant)                                                                          |
| `LocationException` | Exception personnalisée pour les erreurs de gestion                                                                                                |

### 📦 `service` — Logique métier

| Classe           | Description                                                                                                                                                                                         |
| ---------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `AgenceLocation` | Classe centrale gérant le parc (`ArrayList<Vehicule>`), les clients (`ArrayList<Client>`) et les locations actives (`ArrayList<Location>`). Fournit toutes les opérations CRUD et transactionnelles |

### 📦 `util` — Utilitaires

| Classe        | Description                                                                                                                  |
| ------------- | ---------------------------------------------------------------------------------------------------------------------------- |
| `FichierUtil` | Classe utilitaire pour la sauvegarde et le chargement des données au format CSV (`parc.csv`, `clients.csv`, `locations.csv`) |

### 📦 `main` — Interface console

| Classe        | Description                                                                                     |
| ------------- | ----------------------------------------------------------------------------------------------- |
| `AppLocation` | Point d'entrée (`main`). Affiche le menu principal et redirige vers les espaces Admin/Client    |
| `MenuAdmin`   | Menu administrateur (accessible après authentification) avec 9 options de gestion               |
| `MenuClient`  | Menu client (accessible après identification par ID) avec consultation, location et restitution |

---

## ⚙️ Prérequis

- **Java JDK 17** ou supérieur (le projet utilise le pattern matching `instanceof`, nécessitant Java 16+)

Vérifier l'installation :

```bash
java -version
javac -version
```

---

## 🚀 Compilation et Exécution

### 1. Compilation

Depuis la racine du projet `ApplicationLocationVehicules/` :

```bash
javac -encoding UTF-8 -d out src/model/*.java src/util/*.java src/service/*.java src/main/*.java
```

### 2. Exécution

```bash
java -cp out main.AppLocation
```

### En une seule commande (compiler + exécuter)

```bash
javac -encoding UTF-8 -d out src/model/*.java src/util/*.java src/service/*.java src/main/*.java && java -cp out main.AppLocation
```

---

## 🖥️ Utilisation de l'application

### Menu Principal

```
===== MENU PRINCIPAL =====
1. Espace Administrateur
2. Espace Client
0. Quitter
```

### Espace Administrateur

> **Identifiants de connexion :**
>
> - Login : `admin`
> - Mot de passe : `admin@*1234`

```
===== MENU ADMINISTRATEUR =====
1. Ajouter véhicule
2. Supprimer véhicule
3. Modifier prix véhicule
4. Afficher parc
5. Ajouter client
6. Afficher clients
7. Louer un véhicule
8. Afficher locations actives
9. Restituer un véhicule
0. Retour menu principal
```

### Espace Client

> L'accès nécessite l'identifiant du client (ex: `C1000`)

```
===== MENU CLIENT =====
1. Consulter véhicules disponibles
2. Louer un véhicule
3. Restituer un véhicule
0. Retour menu principal
```

---

## 💾 Fichiers de données (CSV)

Les données sont automatiquement sauvegardées à la fermeture de l'application et rechargées au démarrage.

| Fichier         | Contenu           | Format                                                            |
| --------------- | ----------------- | ----------------------------------------------------------------- |
| `parc.csv`      | Véhicules du parc | `Type,Référence,Marque,Modèle,Prix,Disponible,AttributSpécifique` |
| `clients.csv`   | Liste des clients | `IdClient,Nom,Prénom`                                             |
| `locations.csv` | Locations actives | `IdClient,IdVéhicule,DateDébut,DateFin,MontantTotal`              |

**Exemples :**

```
Voiture,V1000,Toyota,Corolla,100.0,true,4
Moto,M2000,BMW,R1250,40.0,false,1250
```

```
C1000,Triki,Amin
C1001,Ben Ali,Sami
```

```
C1000,V1000,2025-02-12,2025-02-26,1400.0
```

---

## 📌 Diagramme de classes (simplifié)

```
                    ┌──────────────┐
                    │  «abstract»  │
                    │   Vehicule   │
                    ├──────────────┤
                    │ - reference  │
                    │ - marque     │
                    │ - modele     │
                    │ - prixJour   │
                    │ - disponible │
                    └──────┬───────┘
                           │
              ┌────────────┼────────────┐
              │                         │
       ┌──────┴──────┐          ┌───────┴──────┐
       │   Voiture   │          │     Moto     │
       ├─────────────┤          ├──────────────┤
       │ - nbPortes  │          │ - cylindre   │
       └─────────────┘          └──────────────┘

  ┌────────────┐    ┌──────────────┐    ┌────────────────┐
  │   Client   │───▶│   Location   │◀───│   Vehicule     │
  ├────────────┤    ├──────────────┤    └────────────────┘
  │ - idClient │    │ - idClient   │
  │ - nom      │    │ - idVehicule │    ┌────────────────┐
  │ - prenom   │    │ - dateDebut  │    │ Administrateur │
  └────────────┘    │ - dateFin    │    ├────────────────┤
                    │ - montant    │    │ - login        │
                    └──────────────┘    │ - motDePasse   │
                                        └────────────────┘

  ┌─────────────────┐    ┌──────────────┐
  │ AgenceLocation  │    │ FichierUtil  │
  ├─────────────────┤    ├──────────────┤
  │ - parc          │    │ + sauvegarder│
  │ - clients       │    │ + charger    │
  │ - locations     │    └──────────────┘
  └─────────────────┘

  ┌──────────────┐  ┌───────────┐  ┌────────────┐
  │ AppLocation  │  │ MenuAdmin │  │ MenuClient │
  │   (main)     │──│           │──│            │
  └──────────────┘  └───────────┘  └────────────┘
```

---

## 📝 Concepts Java utilisés

- **Héritage** et **classes abstraites** (`Vehicule` → `Voiture`, `Moto`)
- **Polymorphisme** (méthode `afficheDetails()`)
- **Encapsulation** (attributs `private`/`protected`, getters/setters)
- **Exceptions personnalisées** (`LocationException`)
- **Collections** (`ArrayList`, `Iterator`)
- **API `java.time`** (`LocalDate`)
- **Pattern matching** (`instanceof` avec variable binding)
- **Lecture/Écriture de fichiers** (`BufferedReader`, `BufferedWriter`)
- **Format CSV** pour la persistance des données

---

_Projet réalisé dans le cadre du cours de Programmation Java — Faculté des Sciences de Sfax_
