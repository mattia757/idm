package com.nttdata.idmccnobe.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseStoreLocatorDTO {

    private Header header;
    private Payload payload;

    public Header getHeader() { return header; }
    public void setHeader(Header header) { this.header = header; }
    public Payload getPayload() { return payload; }
    public void setPayload(Payload payload) { this.payload = payload; }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Header {
        private String result;
        private String errorCode;
        private String errorMessage;

        public String getResult() { return result; }
        public void setResult(String result) { this.result = result; }
        public String getErrorCode() { return errorCode; }
        public void setErrorCode(String errorCode) { this.errorCode = errorCode; }
        public String getErrorMessage() { return errorMessage; }
        public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Payload {
        private List<Pdv> pdv;

        public List<Pdv> getPdv() { return pdv; }
        public void setPdv(List<Pdv> pdv) { this.pdv = pdv; }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Pdv {
        private String codicePdvCoop;
        private String name;
        private String catalogType;
        private String idStoreLocator;
        private String coopId;

        public String getCodicePdvCoop() { return codicePdvCoop; }
        public void setCodicePdvCoop(String codicePdvCoop) { this.codicePdvCoop = codicePdvCoop; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getCatalogType() { return catalogType; }
        public void setCatalogType(String catalogType) { this.catalogType = catalogType; }
        public String getIdStoreLocator() { return idStoreLocator; }
        public void setIdStoreLocator(String idStoreLocator) { this.idStoreLocator = idStoreLocator; }
        public String getCoopId() { return coopId; }
        public void setCoopId(String coopId) { this.coopId = coopId; }
    }
}
