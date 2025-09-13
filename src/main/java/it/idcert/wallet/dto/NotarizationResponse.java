package it.idcert.wallet.dto;

public class NotarizationResponse {
    private String certificateHash;
    private boolean notarized;
    private String blockchainProof;

    public String getCertificateHash() {
        return certificateHash;
    }

    public void setCertificateHash(String certificateHash) {
        this.certificateHash = certificateHash;
    }

    public boolean isNotarized() {
        return notarized;
    }

    public void setNotarized(boolean notarized) {
        this.notarized = notarized;
    }

    public String getBlockchainProof() {
        return blockchainProof;
    }

    public void setBlockchainProof(String blockchainProof) {
        this.blockchainProof = blockchainProof;
    }

    public NotarizationResponse(String certificateHash, boolean notarized, String blockchainProof) {
        this.certificateHash = certificateHash;
        this.notarized = notarized;
        this.blockchainProof = blockchainProof;
    }
}
