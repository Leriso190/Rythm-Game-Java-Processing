#Hit the Ball (PIS SoSe 20211)

Autor: Nikolai Reimelt, Matrikelnummer: 5027318

##Kurzbeschreibung

In diesem Spiel geht es darum, die herunterschnellenden Bälle in dem Moment per Tastendruck zu treffen, indem sie über den "festsitzenden" (mit Buchstaben gekennzeichneten) Bällen  sind. Die zu drückende Taste wird durch den Buchstaben auf dem "festsitzenden" Ball impliziert.

Ziel des Spiels ist es, möglichst viele Punkte zu sammeln, bevor der Lebenspunktebalken komplett rot ist. Im Spiel werden besonders akkurate Treffer mit mehr Punkten belohnt als weniger akkurate. Drückt man eine Taste, ohne einen Treffer zu erzielen oder kommt der Ball zum unteren Spielfeldende, ohne getroffen worden zu sein, werden Lebenspunkte abgezogen. Wenn man viele Bälle hintereinander ohne Fehler trifft, wird dies mit dem Verdoppeln der Punkte beim Treffen belohnt. Beim Erreichen bestimmter Punkteschwellen wird die Geschwindigkeit und zum Teil auch die Anzahl der herunterschnellenden Bälle nach und nach erhöht, was den Schwierigkeitsgrad erhöht. Als Ausgleich werden aber auch Lebenspunkte wieder hergestellt. (142 Wörter)

##Screenshot
![Screenshot](app\src\main\resources\ScreenshotAnwendung.png)

##Bedienungshinweise

### Hinweise zum Starten des Programms und Bedienen des Startmenüs
Das Programm lässt sich mittels gradle run zum Laufen bringen und mittels gradle test testen. Zum Starten des Spiels bitte den Anweisungen des Startmenüs folgen.

###Hinweise zur Spielbedienung
Die Ausgangsposition der Finger beim Spiel ist der linke Zeigefinger auf der "F"-Taste und der rechte Zeigefinger auf der "J"-Taste. Wenn ein herunterschnellender Ball über dem jeweiligen, festsitzenden "Buchstaben-Ball" ist, muss der auf dem "Buchstaben-Ball" ausgewiesene Buchstabe auf der Tastatur gedrückt werden.

*Beispiel:* ein gelber Ball befindet sich über dem mit "F" gekennzeichnetem Ball. In diesem Fall muss die Taste "F" im richtigen Moment gedrückt werden.

Es kann durchaus sein, dass mehrere Bälle gleichzeitig über den "Buchstaben-Bällen" liegen. In diesem Fall müssen die entsprechenden Tasten gleichzeitig gedrückt werden.

###Bedienung des Game-Over-Menüs
Hier muss man lediglich den Anweisungen des Game-Over-Menüs folgen

## Dateiübersicht und Lines of Code
 ###Dateiübersicht
\gradlew
\gradlew.bat
\README.md
\settings.gradle
\app\build.gradle
\app\core.jar
\app\src\main\java\HelperClasses\Ball.java
\app\src\main\java\MainClasses\GameEngine.java
\app\src\main\java\MainClasses\GUI.java
\app\src\main\java\MainClasses\Main.java
\app\src\main\java\MainClasses\Playable.java
\app\src\main\resources\ScreenshotAnwendung.png
\app\src\test\java\MainClasses\GameEngineTest.java
\gradle\wrapper\gradle-wrapper.jar
\gradle\wrapper\gradle-wrapper.properties

###Lines of Code
In dieser Tabelle werden alle Lines Of Code des Projektes (inklusive Tests) angezeigt:

| Language     | files | blank | comment | code |
|--------------|-------|-------|---------|------|
| Java         | 6     | 85    | 6       | 684  |
| Bourne Shell | 1     | 23    | 36      | 126  |
| DOS Batch    | 1     | 21    | 2       | 66   |
| Groovy       | 2     | 10    | 21      | 24   |
| SUM:         | 10    | 139   | 65      | 900  |

In folgender Tabelle werden die Lines of Code für das Java Programm ohne Testfälle angezeigt:

| Language | files | blank | comment | code |
|----------|-------|-------|---------|------|
| Java     | 5     | 70    | 2       | 566  |
| SUM:     | 5     | 70    | 2       | 566  |

## Verwendete Quellen

* Frey, B., Reas, C. (o.J.):Processing Website,abgerufen am 22.06.2021,https://processing.org/reference/
* Username: aioobe (2012): Get random boolean in Java ,abgerufen am 24.05.2021 ,https://stackoverflow.com/questions/11468221/get-random-boolean-in-java