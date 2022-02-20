package com.tennis.horaires.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "horaires", uniqueConstraints = {
        @UniqueConstraint(columnNames = "horaire_name", name = "horaire_name")})
public class HoraireEntity {
    @Id
    @GeneratedValue
    @Column(name = "id_horaire")
    private Long idHoraire;
    @Column(name = "horaire_name")
    private String name;
    @Column(name = "start_time")
    @NotNull
    private LocalTime startTime;
    @NotNull
    @Column(name = "end_time")
    private LocalTime endTime;
    @Column(name = "horaire_special")
    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate horaireSpecial;
    @Column(name = "interval_mesure")
    @NotNull
    private Integer interval;

}
