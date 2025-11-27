***System Analizy Grafów – Java + Spring Boot + C + Prolog (JPL)***

Projekt umożliwia analizę grafów z wykorzystaniem algorytmów napisanych w C oraz Prologu.

**URUCHOMIENIE PROJEKTU (DOCKER)**

W katalogu projektu uruchom:
docker compose up --build

docker:
- zbuduje projekt Mavenem
- skompiluje biblioteki C
- zainstaluje SWI-Prolog wraz z JPL
- uruchomi aplikację pod adresem: http://localhost:8080

wiec nic nie trzeba instalowac

**PRZYKŁADOWE CURLE**

nie ma teraz żadnej autoryazaji



1. Sprawdzenie ciągu stopni (PROLOG):
curl -X POST http://localhost:8080/api/degree/check -H "Content-Type: application/json" -d "{"degrees":[5,3,2,2,2]}"

2. Algorytm dokładny (C):
curl -X POST http://localhost:8080/api/exact -H "Content-Type: application/json" -d "{"degrees":[5,3,2,2,2]}"

3. Algorytm zachłanny (C):
curl -X POST http://localhost:8080/api/greedy -H "Content-Type: application/json" -d "{"degrees":[5,3,2,2,2]}"









