package it.idcert.wallet.dto;

import lombok.AllArgsConstructor;

import java.time.LocalDate;


public class CertificationResponse {
    private String name;
    private String description;
    private LocalDate releaseDate;

    public CertificationResponse(String name, String description, LocalDate releaseDate) {
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
    }
}
