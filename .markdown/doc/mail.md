# Gruppenarbeit 1 für Mails/Spam

## Attribute für die '.arff'-File

Siehe in Mail:

Location: src > main > java > configurator > Mail

        spam (Vorgabe)
    
        blackWordInSub
        blackWordInText
        whiteWordInSub
        whiteWordInText
    
        averageSentenceLength
        maximumSentenceLength
    
        biggerThanAverageSubjectLength
        biggerThanAverageTextLength

## Funktionen des 'ArffFileGenerator'

Location: src > main > java > configurator

__Preprocessing__

* Alle Daten werden aus der 'enron.csv' über den 'DataProvider' eingelesen und verarbeitet.
* Hier werden auch schon erste Durchschnittswerte berechnet mit denen später verglichen wird.

__Ausführung der Analyse__

* Auf Grundlage des 'DataProvider's werden viele Analyseschritte in 'Mail' aufgerufen.
* Die Ergebnisse der Analyseschritte werden in 'Mail' zwischengespeichert.
* Der 'ArffFileGenerator' nimmt am Ende die Daten aus Mail und packt sie in ein von Hand erstelltes '.arff'-File-Format.

__Anmerkungen__

* Da wird die Analyse der Black-/Whitelist sehr genau gestaltet haben, nimmt die Ausführung sehr viel Zeit in Anspruch.
* Die Auswertung wurde Multithreaded erstellt und nutzt 12 Kerne.

## Aufschlüsselung der Ressourcen

Location: src > main > resources

__analytic_enron.arff__

* Ergebnis Datei nach der Analyse von enron.csv .

__blacklist.csv__

* Datei aus Worten, die durch Zeilenumbrüche getrennt sind.
* Inhalt sind 'negative' Worte für die Einsortierung in die Spam-Kategorie.
* Falls eines dieser Worte in einer Mail vorkommt, wird dies vermerkt.

__enron.csv__

* Quelle für die Mails aus der Vorlesung.

__whitelist.csv__

* Datei aus Worten, die durch Zeilenumbrüche getrennt sind.
* Inhalt sind 'positive' Worte für die Einsortierung in die Ham-Kategorie.
* Falls eines dieser Worte in einer Mail vorkommt, wird dies vermerkt.
