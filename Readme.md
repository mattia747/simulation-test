# PROVA DI LABORATORIO — SIMULAZIONE

Dato il progetto Maven, si chiede di:

- Scaricare il codice sorgente nel proprio computer usando Git;
- La consegna deve avvenire caricando il codice sorgente modificato nello **stesso repository GitHub**.  
  È importante ignorare file e cartelle relative alla configurazione dell’IDE e di Maven, caricando solo i file necessari al progetto.

---

## ⚙️ Configurazione Maven

Il progetto Maven deve essere impostato con le seguenti coordinate:

- **Versione:** 1.0
- **Artefatto:** sim-esame-ingsw
- **Gruppo:** package principale del progetto

Modificare inoltre le informazioni generali del progetto come segue:

- **Nome del progetto:** “Simulazione Esame Ingegneria 2024”
- **URL del progetto:** https://demacs.unical.it/
- **Descrizione del progetto:** inserire una descrizione adeguata e significativa del progetto
- **Anno di inizio del progetto:** 2024

---

## 🌿 Gestione dei Branch

Creare un nuovo branch con il nome:
- git branch nomeBranch
- git switch nomeBranch

Questo branch sarà utilizzato per implementare i test JUnit.

---

## 🧪 Test JUnit

Testare la classe **OrderManager.java** utilizzando **JUnit 5**.  
Si consiglia l'uso di:

- `@BeforeEach`
- `@BeforeAll`
- `@AfterEach`
- `@AfterAll`

I metodi da testare sono i seguenti:
- addOrder(String name, int quantity)
- removeOrder(String name)
- getOrders()
- totalQuantity()
- clear()

---

## 🔀 Merge finale

Effettuare il merge del branch **“tests”** con il branch **“main”** una volta che i test siano stati completati.

⚠️ **Non utilizzare Pull Requests su GitHub per il merge.**  
Il merge deve essere fatto **in locale**, poi inviato al repository remoto.

---

## 📌 Note Finali

**PS:** Nel repository remoto devono essere presenti sia il branch **main** che il branch **dev**.

**PS.2:** Si consiglia di leggere i commenti **Javadoc** dei metodi, per comprendere la logica e le aspettative dei test.

**PS.3:** Si consiglia di fare **almeno un commit per ogni test case**.

---
