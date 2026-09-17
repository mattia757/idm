package com.nttdata.idmccnobe.dto;

import java.io.Serializable;

public class CouponImageRequestDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private byte[] fileInput;
    private String imageName;
    private String folder;

    public byte[] getFileInput() {
        return fileInput;
    }

    public void setFileInput(byte[] fileInput) {
        this.fileInput = fileInput;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }
}
