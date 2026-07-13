# FranchiseQuest

Videogioco gestionale a turni sviluppato in Java con JavaFX.  
Il giocatore guida una brigata di tre personaggi (Sala, Cucina, Amministrazione) attraverso quattro ere storiche: Antico Egitto, Antica Roma, America anni '80, Cina 2050.
Facendo crescere il proprio franchise superando eventi storici e minigiochi.

## Requisiti

- Java 21
- Gradle (incluso il wrapper `gradlew`)

## Esecuzione

```bash
cd franchisequest
./gradlew run        # Linux / macOS
.\gradlew.bat run    # Windows
```

## Struttura

```
franchisequest/
├── src/main/java/       # Sorgenti (model, controller, view)
├── src/main/resources/  # File FXML
└── build.gradle
```

## Strumenti di AI

Parte del codice di questo progetto è stata realizzata con il supporto di **Claude Code** (Anthropic).  
In particolare, l'AI ha assistito nella generazione e revisione di codice Java/JavaFX relativo al sistema di minigiochi, all'applicazione degli effetti degli eventi e alla struttura MVC generale.
