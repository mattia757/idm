# Installazione e configurazione Rancher Desktop per ambiente CCNO

Questa guida descrive solo i passaggi necessari per installare e configurare Rancher Desktop come runtime Docker locale per il progetto CCNO.

## 1. Prerequisiti

Verificare di avere:

- Windows con WSL2 abilitato;
- permessi per installare applicazioni;
- accesso al repository del progetto;
- Rancher Desktop `1.24.0`.

## 2. Installazione Rancher Desktop

Installare Rancher Desktop `1.24.0` su Windows.

Percorso usato nell'ambiente locale:

```text
C:\Users\mbonfigl\AppData\Local\Programs\Rancher Desktop
```

Durante la configurazione iniziale scegliere:

```text
Container Engine: Moby / dockerd
```

Non abilitare Kubernetes in questa fase.

Impostazione richiesta:

```text
Kubernetes: Disabled
```

## 3. Configurazione Docker CLI

Rancher Desktop fornisce il Docker CLI in questo percorso:

```text
C:\Program Files\Rancher Desktop\resources\resources\win32\bin\docker.exe
```

I plugin Docker Compose sono in:

```text
C:\Program Files\Rancher Desktop\resources\resources\win32\docker-cli-plugins
```

Nel progetto e' stato aggiunto lo script:

```text
scripts\docker-env.ps1
```

Lo script serve ad aggiungere alla sessione PowerShell i percorsi Docker di Rancher Desktop.

Uso:

```powershell
cd "C:\Work\Project\CCNO on Prem\idmccnobackend"
.\scripts\docker-env.ps1
```

## 4. Verifica installazione

Dopo aver aperto PowerShell ed eseguito `docker-env.ps1`, verificare:

```powershell
docker version
docker compose version
docker ps
```

Il Docker Engine deve risultare quello fornito da Rancher Desktop/Moby.

Nell'ambiente configurato sono stati rilevati:

```text
Docker Client: 29.6.2-rd
Docker Server/Moby: 29.5.3
Docker Compose: v5.3.1
```

## 5. Configurazione progetto

Nel progetto e' stato usato Docker Compose tramite:

```text
compose.yml
```

Sono stati configurati tre servizi:

```text
mysql
keycloak
idmccnobackend
```

Tutti i servizi sono sulla stessa rete Docker Compose:

```text
ccno-onprem-net
```

Tra container si usano i nomi servizio, non `localhost`.

Esempi:

```text
backend -> mysql:3306
backend -> http://keycloak:8080
keycloak -> mysql:3306
```

Da Windows invece si usano le porte pubblicate su `localhost`.

## 6. Porte configurate

Porte esposte verso Windows:

```text
Backend:  http://localhost:8080
Keycloak: http://localhost:8081
MySQL:    127.0.0.1:3307
```

La porta MySQL e' stata esposta per permettere il collegamento da DBeaver:

```yaml
ports:
  - "3307:3306"
```

## 7. Database configurati

Il servizio MySQL usa:

```text
Immagine: mysql:8.4.5
```

Database:

```text
spesaadomicilio
keycloak
```

Credenziali locali:

```text
Username: idmccno
Password: idmccno_local_password
```

I dump locali sono posizionati in:

```text
DB\local\data\01-dump.sql
DB\local\keycloak\01-keycloak.sql
```

La cartella `DB\local\` e' ignorata da Git perche' contiene dati locali.

## 8. Keycloak configurato

Il servizio Keycloak usa:

```text
Immagine: quay.io/keycloak/keycloak:26.6.4
Realm: IdmCCNO
```

URL interno tra container:

```text
http://keycloak:8080
```

URL da Windows:

```text
http://localhost:8081
```

## 9. Backend configurato

Il backend viene buildato localmente con:

```text
Containerfile
```

Immagine locale:

```text
idmccnobackend:local
```

Variabili principali:

```text
DB_HOST=mysql
DB_PORT=3306
DB_NAME=spesaadomicilio
DB_USER=idmccno
DB_PASSWORD=idmccno_local_password
KEYCLOAK_BASE_URL=http://keycloak:8080
KEYCLOAK_REALM=IdmCCNO
```

## 10. Avvio ambiente

Comando standard:

```powershell
cd "C:\Work\Project\CCNO on Prem\idmccnobackend"
.\scripts\docker-env.ps1
docker compose up -d --build
```

Oppure usando lo script:

```powershell
.\scripts\start-docker.ps1
```

Per ricreare tutto da zero, inclusi i volumi:

```powershell
.\scripts\start-docker.ps1 -Reset
```

## 11. Stop ambiente

Per fermare i container mantenendo i dati:

```powershell
docker compose down
```

Oppure:

```powershell
.\scripts\stop-docker.ps1
```

## 12. Verifica rapida

Controllo container:

```powershell
docker compose ps
```

Controllo backend:

```powershell
curl.exe http://127.0.0.1:8080/actuator/health
```

Controllo Keycloak:

```powershell
curl.exe http://127.0.0.1:8081/realms/IdmCCNO/.well-known/openid-configuration
```

Test autenticazione Keycloak:

```powershell
.\scripts\test-keycloak-auth-docker.ps1
```

## 13. Connessione DBeaver

Tipo connessione:

```text
MySQL
```

Parametri:

```text
Host: 127.0.0.1
Port: 3307
Database: spesaadomicilio
Username: idmccno
Password: idmccno_local_password
```

Per Keycloak usare lo stesso host e la stessa porta, cambiando database:

```text
Database: keycloak
```

