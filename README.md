# Receptes

## Universitātes kursa "Objektorientēta programmēšana" (OOP) grupu darbs "Recepšu apkopošanas sistēma"


# DOKUMENTĀCIJA PIEEJAMA DOCS MAPĒ
## Vai arī šeit ir saites:
 - prezentācija: 
 ```
 	https://docs.google.com/presentation/d/1IPymoSThkCkRDukXCCktNai88dX2INPzsZrj-IiHCus/edit?usp=sharing
 ```

 - dokumentācija: 
 ```
 	https://docs.google.com/document/d/1AIOqsqhoI9dFk2CxTe6cIl95icFrWl40DLTihd_6iLE/edit?usp=sharing
 ```

# Priekš programmētājiem jeb grupas dalībniekiem:
## GIT

### Uzstadi Git
- Iespejams, ka stumjot (push) uz GitHub, prasis username un paroli. Lai katru reizi tas nebutu javada, var uzstadit ssh: https://www.youtube.com/watch?v=EoLrCX1VVog un (vai) https://www.youtube.com/watch?v=bc3_FL9zWWs&t=496s
- Zaru iespējams uztaisīt pa taisno no uzdevuma, izmantojot "Create branch".

### Galvenais zars ir MAIN
- Ieteiktu taisit izstrades zarus (feature) un tikai tad taisit Pull Request uz MAIN, lai samazinatu iespeju, ka kads koda fragments neveiksmigi pazud.

## Citas svarigas lietas
- Sis projekts izmanto java versiju 11 - ta ir ta pati, ko izmanto pasniedzejs. Personigi taisisu uz tas pasas virtualas masinas, kas prieks laboriem;
- Projekts izmanto spring boot versiju 2.7.x - ta ir pedeja versija, kas atbalsta java 11. Spring Boot 3.x atbalsta tikai java 17+;
- Pasniedzejs mineja, ka sagaida, ka darba apjoms ir CILVEKU_SKAITS * 40h. Tas nozime, ka velamais darba apjoms uz mums 4iem ir 160 person-stundas.
- Kursa prasibas - [OOP_prasibas](https://pad.riseup.net/p/kxIMtcn-TmUrEISZZbC_-keep)

## TODO
- Skatīt zem GitHub repository sadaļas -> Projects. Tur varēs redzēt sarakstu ar uzdevumiem;
- Ja šobrīd taisāt kādu no uzdevumiem, tad paņemiet to uz sevi (Assignees) un Status "In Progress".

## Datubāze
- Visi izmantojam vienu datubāzi, kad atrodas AWS (mākonī). Tā varēsim darboties visi ar tiem pašiem datiem un nevajadzēs uztraukties par datubāzes uzstādījumiem.
- db backup var taisit ar: mysqldump -h receptes1.ch62g4ug00sh.eu-north-1.rds.amazonaws.com -u admin -p ReceptesDB > backup_file.sql

## Frontend
- Piedāvāju lietot Thymeleaf. Tam pamatā parasti ir HTML, CSS, JS un fīčas no Java. Krietni ietaupīsim laiku uzstādot React, kuru tāpat nevērtēs, jo nav OOP.
