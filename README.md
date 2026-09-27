## Kanvertar dla pravapisaŭ biełaruskaj movy

#### ŁT - Łacinka Tradycyjnaja

#### LA - Łacinka Aficyjnaja

#### KK - Kiryličny Klasyčny (pravapis)

#### KA - Kiryličny Aficyjny (pravapis)

Dadzieny prajekt moža vykarystoŭvacca abo jak zaležnaść u inšych prajektach, abo jak samastojny jar-fajł.

Patrebnyja Java 21 (JDK) i Maven. Kab atrymać jar-fajł, dastatkova vykanać kamandu Maven:

```
mvn clean install
```

Jar-fajł źjavicca ŭ `target/orthography-converter.jar`.

Prykład kamandy dla lakalnaha zapusku atrymanaha jar-fajła:

```
java -jar target/orthography-converter.jar KA /home/heorhi/Dakumenty/test.txt ŁT /home/heorhi/Dakumenty/test2.txt
```

Arhumentaŭ pavinna być 4:

- pravapis u fajle, jaki budzie kanvertavacca (ŁT, LA, KK, KA)
- šlach da fajła, jaki budzie kanvertavacca
- pravapis novaha fajła (ŁT, LA, KK, KA)
- šlach da novaha fajła
