package org.example.netty.upload;


public class FileDto {


    private String fileName;

    // 1 request create file update
    // 2 upload
    // file
    private Integer commend;

    private byte[] bytes;


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getCommend() {
        return commend;
    }

    public void setCommend(Integer commend) {
        this.commend = commend;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
