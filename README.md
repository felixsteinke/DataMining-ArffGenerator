# Repository zur Implementierung der Projektarbeit 1 für ML-DM

Mitglieder: Steinke Felix,  Grabowski Florian, Fili Leo Marco



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

## System Bedingungen

* Das Java-Projekt ist auf Maven aufgebaut.
    * Java sollte installiert sein. (Vorgesehen ist 11+.)
    * Maven sollte installiert sein.
* Probleme könnte die Java-Version machen. Folgendes muss eventuell in der 'pom.xml' angepasst werden:
    
    
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>11</source> <-- Diese Version muss eventuell angepasst werden.
                    <target>11</target> <-- Diese Version muss eventuell angepasst werden.
                </configuration>
            </plugin>
        </plugins>
    </build>
