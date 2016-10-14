### Aihemäärittely

**Aihe:** Tetris-peli

Toteutetaan peli, jossa pyritään kokoamaan pelialustan levyisiä vaakasuoria rivejä ilman aukkoja ylhäältä putoavista erimuotoisista palikoista. Kun rivi on valmis, se katoaa ja ylempänä olleet rivit putoavat alemmas.
Palikoita eli tetrominoja on seitsemän erilaista ja kaikki koostuvat neljästä neliön muotoisesta palasta. Palikoita pystyy kääntämään 90° sekä liikuttamaan oikealle, vasemmalle ja alas.

**Käyttäjät:** Pelaaja

**Pelaajan toiminnot:**
 * ohjelman käynnistys
 * uuden pelin aloitus
 * pelin keskeytys
 * pelin lopetus
 * ohjelmasta poistuminen
 * tetriminon liikuttaminen

&nbsp;

### Rakennekuvaus

Ohjelma koostuu logiikasta ja käyttöliittymästä, ja niiden yhteysluokkana toimivasta Game-luokasta. 

Logiikkaan kuuluvat luokat Tetrimino, TetriminoType ja Board. TetriminoType on enum-luokka ja määrittelee erilaiset tetriminotyypit ja niiden kaikki mahdolliset rotaatiot. Tetrimino puolestaan kuvaa tietyntyyppistä tetriminoa ja Board pelilautaa.

Käyttöliittymäpakettiin kuuluvat luokat UI, joka toteuttaa rajapinnan Runnable, KeyBoardListener, joka toteuttaa rajapinnan KeyListener, abstrakti luokka AbstractPanel, joka perii luokan JPanel, ja AbstractPanelin perivät luokat GamePanel ja SidePanel. UI-luokka luo yhden kappaleen AbstractPanelin kumpaakin aliluokkaa, sekä KeyBoardListenerin, joille se antaa viitteen Game-luokkaan, jonka se on itse saanut konstruktorin parametrina.

Game koostuu pelilaudasta Board ja kahdesta Tetriminosta, putoavasta ja seuraavana putoavasta. Game toteuttaa rajapinnan ActionListener ja perii luokan Timer, jotka tarjoavat ajastustoiminnallisuuden pelin päivittämiseen. Luokkaan liittyvät GamePanel ja SidePanel piirtävät pelin ja sen tiedot näkyviin, ja KeyBoardListener siirtää näppäimistöltä tulevat käskyt Gamen metodien hoidettaviksi.

&nbsp;

**Luokkakaavio:**
&nbsp;

![luokkakaavio](/dokumentaatio/luokkakaavio.png)

&nbsp;

**Sekvenssikaavioita:**
&nbsp;

![pelin käynnistys](/dokumentaatio/start.png)
(loopista ja sen jälkeen puuttuu getSidePanel ja setSidePanel)
&nbsp;

![pelin keskeytys](/dokumentaatio/pause.png)

&nbsp;

![tetriminon kääntäminen](/dokumentaatio/rotate.png)
