# ProgettoIS
Progetto per Esame di Ingegneria del Software

MyOrg è un software per la gestione di un organigramma aziendale. 
Consente all'utente di creare un'azienda da zero e di definire un organigramma aziendale, il quale è composto da aree organizzative disposte secondo una struttura gerarchica.
L'applicazione permette di aggiornare dinamicamente la struttura di una determinata azienda. 
Ogni unità organizzativa è connessa ad altre unità secondo legami padre-figlio. Ogni unità può presentare al più un'unica area di riferimento (area padre).
Ogni singola unità è composta da un insieme di dipendenti ai quali viene assegnato uno specifico ruolo.
I dipendenti possono presentare più ruoli. Un ruolo specifico può essere assegnato a più dipendenti.
I ruoli introdotti hanno senso in alcune aree, mentre in altre no. Ogni area presenta infatti un insieme di ruoli: alcuni risultano già assegnati a dipendenti, altri ancora no.

L'azienda comprende quindi:
-informazioni generali in merito all'azienda
-insieme di dipendenti
-insieme di ruoli
-organigramma aziendale.

L'azienda viene memorizzata in memoria secondaria, salvando i dati secondo il formato XML.

//FUNZIONAMENTO

1) ESECUZIONE

L'applicazione è stata compilata con la versione più recente della Java RunTime (class file version 64.0). Per eseguire il file jar si richiede la versione 20.0.1 della JDK.

In alternativa al jar, è possibile lanciare l'applicazione attraverso un apposito IDE (Es. IntelliJ).
Una volta caricato il progetto, bisogna lanciare il main contenuto all'interno della classe Application.

Per lo sviluppo del software è stato utilizzato l'ambiente di sviluppo IntelliJ lanciato su un LAPTOP Legion 5 Pro avente:
-11th Gen Intel(R) Core(TM) i7-11800H 2.30GHz 
-Ram: 16.0 GB 
-S.O.: Windows 11.

2) CREAZIONE AZIENDA

Lanciata l'applicazione, se non è stata ancora creata l'azienda, l'utente viene rimandato all'area di accesso dalla quale è possibile creare una nuova azienda. 

All'atto di creazione l'utente deve inserire:
-Nome azienda
-Codice ATECO: codice utilizzato per classificare un'azienda in base alle attività economiche
-Sede centrale
-Settore azienda
-Password di accesso al sistema.

IMPORTANTE!
Una volta confermata la password di accesso, quetsa non sarà più modificabile. Tenere quindi a mente la password così da accedere ai dati aziendali.

Se invece è già stata salvata un'azienda, l'applicazione rimanda l'utente all'area di log-in.

L'utente per accedere all'azienda deve inserire obbligatoriamente:
-Nome azienda
-Password di accesso.

In alternativa, dall'area di log-in è possibile cancellare l'azienda presente nel sistema per memorizzarne una nuova.
P.S. Memorizzata la nuova azienda, non sarà più possibile recuperare quella precedente.

3) MEMORIZZAZIONE AZIENDA

L'azienda viene memorizzata all'interno di un file denominato "data.txt". 
Se l'applicazione viene lanciata mediate l'esecuzione del main della classe Application, il file "data.txt" viene memorizzato nella directory "ProgettoIS", facente parte del progetto. 
In alternativa l'applicazione può lanciata da prompt dei comandi utilizzando il file jar, mediante il comando "java -jar percorso\file.jar".
Il file "data.txt" viene memorizzato nella directory da cui l'utente lancia il comando.

4) FUNZIONALITÀ

Una volta definita l'azienda, l'applicazione consente all'utente di effettuare diverse operazioni.

In merito alle aree, l'applicazione consente di:
-Inserire una nuova area
-Visionare l'elenco delle aree (per ogni area viene specificato il nome dell'area di riferimento. L'area radice, ovvero l'area in cima all'organigramma, non presenta alcuna area di riferimento.)
-Ottenere le informazioni di una specifica area
-Modificare un'area (un'area validata non può essere modificata)
-Eliminare una specifica area (un'area può essere rimossa se tale area e tutte le sotto-aree non presentano dipendenti. L'area radice non si può eliminare.)

In merito ai ruoli, l'applicazione consente di:
-Definire un nuovo ruolo
-Visionare l'elenco dei ruoli (per ogni ruolo viene indicato, in aggiunta, il numero di dipendenti che presenta tale ruolo)
-Cercare uno specifico ruolo
-Modificare un ruolo
-Eliminare un ruolo (un ruolo si può rimuovere se non è stato assegnato a nessun dipendente)

In merito ai dipendenti, l'applicazione consente di:
-Inserire un nuovo dipendente
-Visionare l'elenco dei dipendenti
-Ottenere la scheda di un singolo dipendente
-Licenziare un dipendente
-Assegnare un nuovo ruolo ad un dipendente
-Levare un ruolo da un dipendente (per rimuovere un ruolo, il dipendente deve possedere almeno due ruoli. Un dipendente non può rimanere senza ruolo!)

È possibile inoltre accedere alle informazioni generali dell'azienda.

Per accedere alle varie funzionalità l'applicazione dispone di una barra del menu. 


Progetto realizzato da Luca Antonio Luigi Barbieri matricola: 220359.
