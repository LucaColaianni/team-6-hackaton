package it.idcert.wallet.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class InsertCertificationRequest {
    private String name;
    private String description;
    private LocalDate releaseDate;
}
