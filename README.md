Questo progetto Spring Boot permette di:

1. Validare certificazioni OpenBadge contenute in immagini PNG.
2. Estrarre il JSON dalla certificazione.
3. Generare un hash della certificazione.
4. Notarizzare il certificato in blockchain (mock per test).

---

## Tecnologie utilizzate

- Java 17+
- Spring Boot 3
- Apache HttpClient 5
- PostgreSQL
- Lombok
- JUnit 5 per test

---

## Architettura

- **CertificateController**  
  Espone gli endpoint REST per:
  - `/validator` → Validazione e inserimento nuova certificazione.
  - `/validator/{id}` → Notarizzazione della certificazione (mock della proof blockchain).
  - `/validator/test` → Endpoint di test rapido.

- **OpenBadgeValidatorService**  
  Gestisce la validazione delle immagini PNG tramite [OpenBadge Validator](https://validator.open-badge.eu/results) e l’estrazione del JSON.

- **CertificateService**  
  Salva le certificazioni nel database PostgreSQL e calcola l’hash (TODO: generazione reale).

- **BlockchainNotarizationService**  
  Mock della notarizzazione in blockchain, restituisce una `NotarizationResponse` simulata.

---

## Database

Tabella `certification` (PostgreSQL):

```sql
CREATE TABLE certification (
   id BIGSERIAL PRIMARY KEY,
   blob_id_pdf VARCHAR(255),
   openbadge_json JSON NOT NULL,
   hash VARCHAR(64) NOT NULL,
   issued_at TIMESTAMP,
   blockchain_tx_id VARCHAR(100) UNIQUE,
   name VARCHAR(255) NOT NULL,
   description VARCHAR(255) NOT NULL
);
