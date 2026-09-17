# Guida ambiente locale CCNO con Rancher Desktop, Docker e Keycloak

Questa guida descrive la configurazione locale preparata per eseguire il backend CCNO, il database applicativo, il database Keycloak e Keycloak 26 usando Rancher Desktop con motore Moby/dockerd e Docker Compose.

La guida non usa Kubernetes e non richiede Podman.

## Obiettivo dell'ambiente

L'ambiente locale deve avviare questi componenti:

- MySQL con due database:
  - `spesaadomicilio`, usato dal backend applicativo;
  - `keycloak`, usato da Keycloak 26.
- Keycloak 26, collegato al database `keycloak`.
- Backend `idmccnobackend`, collegato al database `spesaadomicilio` e a Keycloak.
- Una rete Docker Compose comune per far comunicare i container usando i nomi dei servizi.

Le comunicazioni tra container non usano `localhost`.

Esempi:

- backend verso MySQL: `mysql:3306`
- backend verso Keycloak: `http://keycloak:8080`
- Keycloak verso MySQL: `mysql:3306`

Da Windows invece si usano le porte pubblicate su `localhost`.

## Software installato/usato

Sono stati usati:

- Rancher Desktop `1.24.0`
- Container Engine: `Moby / dockerd`
- Docker CLI
- Docker Compose
- WSL2
- Kubernetes disabilitato

Il Docker CLI usato da Rancher Desktop si trova in:

```text
C:\Program Files\Rancher Desktop\resources\resources\win32\bin\docker.exe
```

I plugin Docker Compose si trovano in:

```text
C:\Program Files\Rancher Desktop\resources\resources\win32\docker-cli-plugins
```

Nel progetto e' stato aggiunto lo script:

```text
scripts\docker-env.ps1
```

Questo script aggiunge al `PATH` della sessione PowerShell i percorsi Docker di Rancher Desktop.

## Verifica Docker

Dalla cartella del progetto:

```powershell
cd "C:\Work\Project\CCNO on Prem\idmccnobackend"
.\scripts\docker-env.ps1
docker version
docker compose version
docker ps
```

Il Docker Engine deve essere quello di Rancher Desktop/Moby.

## File principali del progetto

I file principali coinvolti sono:

```text
compose.yml
Containerfile
README.md
.gitignore
scripts\docker-env.ps1
scripts\start-docker.ps1
scripts\stop-docker.ps1
scripts\test-keycloak-auth-docker.ps1
DB\local\
```

La cartella `DB\local\` contiene dump e script locali di inizializzazione del database. E' ignorata da Git perche' contiene dati locali.

## Dump database

Sono stati gestiti due dump:

- dump applicativo importato nel database `spesaadomicilio`;
- dump Keycloak importato nel database `keycloak`.

Percorsi attesi:

```text
DB\local\data\01-dump.sql
DB\local\keycloak\01-keycloak.sql
```

Il dump applicativo contiene anche alcune tabelle compatibili con la struttura storica Keycloak, che il backend continua a leggere dal database `spesaadomicilio`, per esempio `USER_ENTITY` e `USER_ATTRIBUTE`.

Il dump Keycloak dedicato viene invece usato dal server Keycloak 26 nel database `keycloak`.

## Container configurati

Il file `compose.yml` definisce tre servizi principali.

### MySQL

Immagine:

```text
mysql:8.4.5
```

Database:

```text
spesaadomicilio
keycloak
```

Credenziali:

```text
user: idmccno
password: idmccno_local_password
```

Porta interna:

```text
3306
```

Per accedere da DBeaver e' stata prevista la pubblicazione della porta su Windows:

```text
localhost:3307 -> mysql:3306
```

### Keycloak

Immagine:

```text
quay.io/keycloak/keycloak:26.6.4
```

Realm:

```text
IdmCCNO
```

URL interno tra container:

```text
http://keycloak:8080
```

URL da Windows:

```text
http://localhost:8081
```

### Backend

Immagine locale:

```text
idmccnobackend:local
```

Build:

```text
Containerfile
```

Porta da Windows:

```text
http://localhost:8080
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

## Avvio ambiente

Per avviare tutto:

```powershell
cd "C:\Work\Project\CCNO on Prem\idmccnobackend"
.\scripts\docker-env.ps1
docker compose up -d --build
```

In alternativa:

```powershell
.\scripts\start-docker.ps1
```

Per ricreare completamente i volumi e reimportare i dump:

```powershell
.\scripts\start-docker.ps1 -Reset
```

Attenzione: con `-Reset` vengono eliminati i dati del volume MySQL locale. Gli utenti creati manualmente dopo l'import dei dump vanno ricreati.

## Controllo stato container

```powershell
docker compose ps
docker ps
```

Lo stato atteso e':

- `mysql`: `Up` e `healthy`;
- `keycloak`: `Up`;
- `idmccnobackend`: `Up`.

Per MySQL, se la porta e' esposta correttamente per DBeaver, in `docker compose ps` deve comparire una riga simile:

```text
0.0.0.0:3307->3306/tcp
```

## Controllo log

```powershell
docker compose logs mysql
docker compose logs keycloak
docker compose logs idmccnobackend
```

Cose attese nei log:

- MySQL importa il dump applicativo in `spesaadomicilio`.
- MySQL importa il dump Keycloak in `keycloak`.
- Keycloak completa l'avvio e ascolta su `http://0.0.0.0:8080`.
- Il backend avvia Spring Boot, Tomcat su porta `8080` e Hikari verso MySQL.

## Accesso al database da DBeaver

Creare una nuova connessione MySQL in DBeaver.

Parametri:

```text
Driver: MySQL
Host: 127.0.0.1
Port: 3307
Database: spesaadomicilio
Username: idmccno
Password: idmccno_local_password
```

Per il database Keycloak:

```text
Database: keycloak
```

Se DBeaver restituisce `Communications link failure`, verificare prima che la porta sia raggiungibile:

```powershell
Test-NetConnection 127.0.0.1 -Port 3307
```

Il risultato atteso e':

```text
TcpTestSucceeded : True
```

## Tabelle principali

Nel database Keycloak:

```text
keycloak.USER_ENTITY
keycloak.CREDENTIAL
keycloak.USER_ATTRIBUTE
keycloak.REALM
keycloak.DATABASECHANGELOG
```

Query utile:

```sql
SELECT ID, USERNAME, EMAIL, ENABLED, EMAIL_VERIFIED
FROM keycloak.USER_ENTITY
WHERE REALM_ID = 'IdmCCNO';
```

Per verificare l'utente di test:

```sql
SELECT ID, USERNAME, EMAIL, ENABLED, EMAIL_VERIFIED
FROM keycloak.USER_ENTITY
WHERE USERNAME = 'ccno-test-user@example.local';
```

Nel database applicativo:

```text
spesaadomicilio.CCNO_BE_USER
spesaadomicilio.USER_ENTITY
spesaadomicilio.USER_ATTRIBUTE
```

Nota importante:

- `spesaadomicilio.CCNO_BE_USER` viene usata dalla login backoffice del backend.
- `keycloak.USER_ENTITY` viene usata da Keycloak per gli utenti del realm.
- Il backend contiene anche logiche che leggono tabelle tipo `USER_ENTITY` e `USER_ATTRIBUTE` nel database applicativo.

## Test backend

Test health:

```powershell
curl.exe http://127.0.0.1:8080/actuator/health
```

Risultato atteso:

```json
{
  "status": "UP"
}
```

Nel dettaglio devono risultare `UP` anche:

- database;
- Keycloak.

Questo test conferma che il backend raggiunge il proprio database e Keycloak.

## Test Keycloak

Endpoint discovery:

```powershell
curl.exe http://127.0.0.1:8081/realms/IdmCCNO/.well-known/openid-configuration
```

Risultato atteso: HTTP `200` con JSON di configurazione OIDC.

## Test login/autenticazione Keycloak

Utente di test creato nell'ambiente locale:

```text
username: ccno-test-user@example.local
password: <test-password>
client_id: idmCCNOClient
realm: IdmCCNO
```

Chiamata token:

```powershell
curl.exe -X POST "http://127.0.0.1:8081/realms/IdmCCNO/protocol/openid-connect/token" `
  --data-urlencode "grant_type=password" `
  --data-urlencode "client_id=idmCCNOClient" `
  --data-urlencode "username=ccno-test-user@example.local" `
  --data-urlencode "password=<test-password>"
```

Risultato atteso:

```json
{
  "access_token": "...",
  "expires_in": ...
}
```

In alternativa:

```powershell
.\scripts\test-keycloak-auth-docker.ps1
```

Risultato atteso:

```text
Keycloak token OK
```

## Accesso console Keycloak

URL:

```text
http://localhost:8081
```

Admin temporaneo creato nell'ambiente locale corrente:

```text
username: temp-admin
password: <keycloak-admin-password>
```

Attenzione: se il volume MySQL viene cancellato, questo utente temporaneo va ricreato.

## Stop ambiente

Per fermare i container mantenendo i dati:

```powershell
docker compose down
```

oppure:

```powershell
.\scripts\stop-docker.ps1
```

Per fermare e cancellare anche i volumi:

```powershell
docker compose down -v
```

Usare questa opzione solo quando si vuole reimportare tutto dai dump.

## Sequenza consigliata per test giornaliero

```powershell
cd "C:\Work\Project\CCNO on Prem\idmccnobackend"
.\scripts\docker-env.ps1
docker compose up -d --build
docker compose ps
curl.exe http://127.0.0.1:8080/actuator/health
curl.exe http://127.0.0.1:8081/realms/IdmCCNO/.well-known/openid-configuration
.\scripts\test-keycloak-auth-docker.ps1
```

Se questi passaggi sono verdi, l'ambiente locale e' pronto per i successivi test di integrazione.

## Riepilogo porte Windows

```text
Backend:  http://localhost:8080
Keycloak: http://localhost:8081
MySQL:    127.0.0.1:3307
```

## Riepilogo rete Docker

Tutti i container del progetto sono sulla stessa rete Compose:

```text
ccno-onprem-net
```

I nomi servizio da usare tra container sono:

```text
mysql
keycloak
idmccnobackend
```
