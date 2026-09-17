--TEST
INSERT IGNORE INTO IDMCCNO.CCNO_BE_PARAMETER (NOME, VALORE, DESCRIZIONE)
VALUES
    ('COUPON_IMAGE_APIM_AUTH_KEY', '57444648f0a045bc8323998158fa7c3f', 'Chiave authentication-key APIM per il servizio saveImage'),
    ('COUPON_IMAGE_GCP_BASE_URL', 'https://storage.googleapis.com/stg-coop-italia-application', 'Base URL pubblico GCP per le immagini coupon');
--STAGING
INSERT IGNORE INTO IDMCCNO.CCNO_BE_PARAMETER (NOME, VALORE, DESCRIZIONE)
VALUES
    ('COUPON_IMAGE_APIM_AUTH_KEY', '57444648f0a045bc8323998158fa7c3f', 'Chiave authentication-key APIM per il servizio saveImage'),
    ('COUPON_IMAGE_GCP_BASE_URL', 'https://storage.googleapis.com/stg-coop-italia-application', 'Base URL pubblico GCP per le immagini coupon');

