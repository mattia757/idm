package com.nttdata.idmccnobe.service;

import com.nttdata.idmccnobe.dto.CommonDto;
import com.nttdata.idmccnobe.dto.CouponImageRequestDto;
import com.nttdata.idmccnobe.dto.CouponImageResponseDto;
import com.nttdata.idmccnobe.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("couponImageService")
public class CouponImageService extends AbstractService {

    private static final byte[] PNG_SIGNATURE = new byte[]{
        (byte) 0x89, 0x50, 0x4E, 0x47, 0x0D, 0x0A, 0x1A, 0x0A
    };

    @Autowired
    private ParameterBeService parameterBeService;

    @Autowired
    private UtilsService utilsService;

    public String saveVoucherImage(String voucherId, byte[] fileInput) {
        int imageSize = fileInput != null ? fileInput.length : 0;
        logger.info("Avvio salvataggio immagine voucher. voucherId=" + voucherId
                + ", dimensioneBytes=" + imageSize);

        try {
            validatePng(fileInput);
            logger.info("Validazione PNG completata. voucherId=" + voucherId);

            logger.info("Lettura parametri per il servizio saveImage. voucherId=" + voucherId);
            String apimBaseUrl = getRequiredParameter(Constants.APIM_ENDPOINT);
            String authenticationKey = getRequiredParameter(Constants.COUPON_IMAGE_APIM_AUTH_KEY);
            String gcpBaseUrl = getRequiredParameter(Constants.COUPON_IMAGE_GCP_BASE_URL);
            String imageName = voucherId + ".png";
            logger.info("Parametri saveImage caricati. voucherId=" + voucherId
                    + ", imageName=" + imageName
                    + ", folder=" + Constants.COUPON_IMAGE_FOLDER);

            CouponImageRequestDto request = new CouponImageRequestDto();
            request.setFileInput(fileInput);
            request.setImageName(imageName);
            request.setFolder(Constants.COUPON_IMAGE_FOLDER);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("authentication-key", authenticationKey);

            RestTemplate restTemplate = utilsService.proxyRestTemplate();
            String serviceUrl = removeTrailingSlash(apimBaseUrl) + Constants.COUPON_IMAGE_APIM_PATH;
            logger.info("Invio richiesta POST al servizio saveImage. voucherId=" + voucherId
                    + ", serviceUrl=" + serviceUrl);
            ResponseEntity<CouponImageResponseDto> response = restTemplate.exchange(
                    serviceUrl,
                    HttpMethod.POST,
                    new HttpEntity<>(request, headers),
                    CouponImageResponseDto.class
            );

            CouponImageResponseDto responseBody = response.getBody();
            String responseResult = responseBody != null && responseBody.getHeader() != null
                    ? responseBody.getHeader().getResult()
                    : null;
            logger.info("Risposta ricevuta dal servizio saveImage. voucherId=" + voucherId
                    + ", httpStatus=" + response.getStatusCode().value()
                    + ", result=" + responseResult);

            if (!response.getStatusCode().is2xxSuccessful()
                    || responseBody == null
                    || responseBody.getHeader() == null
                    || !CommonDto.ACK_OK.equalsIgnoreCase(responseResult)) {
                String errorMessage = responseBody != null && responseBody.getHeader() != null
                        ? responseBody.getHeader().getErrorMessage()
                        : null;
                throw new IllegalStateException(errorMessage != null && !errorMessage.trim().isEmpty()
                        ? errorMessage
                        : "Il servizio saveImage ha restituito KO");
            }

            String publicImageUrl = removeTrailingSlash(gcpBaseUrl)
                    + Constants.COUPON_IMAGE_PUBLIC_PATH
                    + "/"
                    + imageName;
            logger.info("Salvataggio immagine completato con successo. voucherId=" + voucherId
                    + ", imageUrl=" + publicImageUrl);
            return publicImageUrl;
        } catch (RuntimeException ex) {
            logger.error("Errore durante il salvataggio immagine voucher. voucherId=" + voucherId
                    + ", dimensioneBytes=" + imageSize
                    + ", errore=" + ex.getMessage(), ex);
            throw ex;
        }
    }

    private String getRequiredParameter(String parameterName) {
        String value = parameterBeService.getParameterValueByName(parameterName);
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalStateException("Parametro " + parameterName + " non configurato");
        }
        return value.trim();
    }

    private String removeTrailingSlash(String value) {
        return value.endsWith("/") ? value.substring(0, value.length() - 1) : value;
    }

    private void validatePng(byte[] fileInput) {
        if (fileInput == null || fileInput.length < PNG_SIGNATURE.length) {
            throw new IllegalArgumentException("Immagine PNG non valorizzata o non valida");
        }
        for (int i = 0; i < PNG_SIGNATURE.length; i++) {
            if (fileInput[i] != PNG_SIGNATURE[i]) {
                throw new IllegalArgumentException("Il file selezionato non è un'immagine PNG valida");
            }
        }
    }
}
