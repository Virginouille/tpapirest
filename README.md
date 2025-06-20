# README - API REST Java avec Jetty embarqué

## Présentation

Ce projet est une API REST Java minimaliste pour la gestion de tâches (*Task Management API*), développée avec des **Servlets** et un serveur **Jetty embarqué**.

L’API propose les fonctionnalités principales de création et lecture des tâches (`POST` et `GET`). Les méthodes **`PUT`** (mise à jour) et **`DELETE`** (suppression) ne sont **pas encore implémentées** dans cette version.

---

## Fonctionnalités actuelles

- **GET /tasks** : liste toutes les tâches.
- **GET /tasks/{id}** : récupère une tâche par son identifiant.
- **POST /tasks** : crée une nouvelle tâche.

---

## Fonctionnalités prévues mais non implémentées

- **PUT /tasks/{id}** : mise à jour d’une tâche existante.
- **DELETE /tasks/{id}** : suppression d’une tâche.

---

## Installation & exécution

1. Cloner le projet.
2. Compiler avec Maven :
   ```bash
   mvn clean package

