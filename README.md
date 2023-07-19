# ProgettoIS
Progetto per Esame di Ingegneria del Software

### SOMMARIO 

MyOrg è un software per la gestione di un organigramma aziendale. 
Consente all'utente di creare un'azienda da zero e di definire un organigramma aziendale, il quale è composto da aree organizzative disposte secondo una struttura gerarchica.
L'applicazione permette di aggiornare dinamicamente la struttura di una determinata azienda. 
Ogni area organizzativa è connessa ad altre aree secondo legami padre-figlio. Ogni area presenta al più un'area di riferimento (area padre).
Ogni singola area è composta da un insieme di ruoli, i quali possono essere associati ai dipendenti dell'azienda.
I dipendenti possono presentare più ruoli. Un ruolo specifico può essere assegnato a più dipendenti.
I ruoli introdotti hanno senso in alcune aree, mentre in altre no.

L'azienda comprende quindi:
* Informazioni generali in merito all'azienda
* Insieme di dipendenti
* Insieme di ruoli
* Organigramma aziendale

## FUNZIONAMENTO

### ESECUZIONE

Per eseguire il file jar, è richiesta una versione della JDK uguale o superiore alla versione 17.0.6 (class file version 61).

In alternativa al jar, è possibile lanciare l'applicazione attraverso un apposito IDE (Es. IntelliJ).
Una volta caricato il progetto, bisogna lanciare il main contenuto all'interno della classe Application.

Per lo sviluppo del software è stato utilizzato l'ambiente di sviluppo IntelliJ lanciato su un LAPTOP Legion 5 Pro avente:
* 11th Gen Intel(R) Core(TM) i7-11800H 2.30GHz 
* Ram: 16.0 GB 
* S.O.: Windows 11

### CREAZIONE AZIENDA

Lanciata l'applicazione, se non è stata ancora creata l'azienda, l'utente viene reindirizzato alla schermata di accesso dalla quale è possibile creare una nuova azienda. 

All'atto di creazione l'utente deve inserire:
* Nome azienda
* Codice ATECO: codice utilizzato per classificare un'azienda in base alle attività economiche
* Sede centrale
* Settore azienda
* Password di accesso al sistema

IMPORTANTE!
Una volta confermata la password di accesso, questa non sarà più modificabile. Attenzione: ricorda la password per accedere in futuro all'applicativo.

Se invece è stata già salvata un'azienda, l'applicazione reindirizza l'utente alla schermata di log-in.

L'utente per accedere all'azienda deve inserire obbligatoriamente:
* Nome azienda
* Password di accesso

In alternativa, dalla schermata di log-in è possibile memorizzarne una nuova azienda, cancellando quella corrente.
P.S. Memorizzata la nuova azienda, non sarà più possibile recuperare quella precedente.

### MEMORIZZAZIONE AZIENDA

L'azienda viene memorizzata all'interno di un file denominato "data.txt". 
Se l'applicazione viene lanciata mediante l'esecuzione del main della classe Application, il file "data.txt" verrà memorizzato nella directory "ProgettoIS".
In alternativa l'applicazione può essere lanciata da prompt dei comandi utilizzando il file jar, mediante il comando "java -jar percorso\file.jar".
Il file "data.txt" viene memorizzato nella directory da cui l'utente lancia il comando.

### SERVIZI OFFERTI

Una volta definita l'azienda, l'applicazione consente all'utente di effettuare diverse operazioni.

In merito alle aree, l'applicazione consente di:
* Inserire una nuova area
* Visionare l'elenco delle aree 
* Ottenere le informazioni di una specifica area
* Modificare un'area (un'area validata non può essere modificata)
* Eliminare una specifica area (un'area può essere rimossa se tale area e tutte le sotto-aree non presentano dipendenti. L'area radice non si può eliminare.)

In merito ai ruoli, l'applicazione consente di:
* Definire un nuovo ruolo
* Visionare l'elenco dei ruoli
* Cercare uno specifico ruolo
* Modificare un ruolo
* Eliminare un ruolo (un ruolo si può rimuovere se non è stato assegnato ad alcun dipendente)

In merito ai dipendenti, l'applicazione consente di:
* Inserire un nuovo dipendente
* Visionare l'elenco dei dipendenti
* Ottenere la scheda di un singolo dipendente
* Modificare le informazioni di un dipendente
* Dismettere un dipendente
* Assegnare un ruolo ad un dipendente
* Dissociare un ruolo da un dipendente (per dissociare un ruolo, il dipendente deve possedere almeno due ruoli. Un dipendente non può rimanere senza ruolo!)

È possibile inoltre accedere alle informazioni generali dell'azienda.

Per accedere alle varie funzionalità l'applicazione dispone di una barra del menu. 

### ESEMPI FORNITI

Nella directory ProgettoIS è presente una sotto-directory denominata "esempi". 
All'interno sono disponibili due esempi pratici di aziende definite e memorizzate con MyOrg. 

Inizialmente, è stato preso in esame l'organigramma dell'agenzia delle entrate.
Per poter accedere a tali dati, se l'applicazione viene lanciata mediante un IDE, è necessario trascinare il file "data.txt" nella directory ProgettoIS,
Se invece si utilizza il file jar, bisogna trascinare il file "data.txt" nella directory nella quale è presente il jar.
Eventuali file denominati allo stesso modo, già presenti nella directory devono essere necessariamente spostati o rimossi (Deve esserci un unico file denominato "data.txt" nella directory).
Per poter accedere al sistema bisogna inserire:
* Nome azienda: Agenzia delle entrate
* Password: 1234

Successivamente, è stato preso in esame l'organigramma dell'Agenzia Spaziale Italiana.
Una volta eseguita la procedura descritta in precedenza, bisogna digitare nella schermata di log in i seguenti dati:
* Nome azienda: Agenzia Spaziale Italiana
* Password: 200769

Progetto realizzato da Luca Antonio Luigi Barbieri matricola: 220359.
