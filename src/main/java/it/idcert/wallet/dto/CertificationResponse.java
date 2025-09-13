package it.idcert.wallet.dto;

import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class CertificationResponse {
    private String name;
    private String description;
    private LocalDate releaseDate;
}
