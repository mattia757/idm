# idm

Monorepo applicativa locale per il progetto IDM.

## Struttura

```text
apps/
  idmccnobackend/
```

`apps/idmccnobackend` contiene la copia applicativa preparata a partire da
`C:\Work\Project\CCNO on Prem\idmccnobackend`.

Sono esclusi dalla copia:

- `.git`
- `target`
- log e artefatti generati
- `DB/local`, perche' contiene dump e dati locali
- realm JSON con password/client secret in chiaro

## Build container locale

Il progetto contiene un `Containerfile` multi-stage che usa Java 25 e Maven nel container.
Non e' necessario installare Java o Maven globalmente per la build immagine.

```powershell
cd C:\Work\Project\Locale\idm\apps\idmccnobackend
docker build -f Containerfile -t idmccnobackend:local .
docker images idmccnobackend
```

L'immagine `idmccnobackend:local` e' pensata per Rancher Desktop con Moby e viene usata
dall'overlay Kustomize locale con `imagePullPolicy: Never`.
