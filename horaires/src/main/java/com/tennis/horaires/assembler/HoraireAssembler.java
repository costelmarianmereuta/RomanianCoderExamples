package com.tennis.horaires.assembler;

import com.tennis.horaires.entities.HoraireEntity;
import model.generated.Horaire;
import model.generated.Interval;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class HoraireAssembler implements RepresentationModelAssembler<HoraireEntity, Horaire> {


    @Override
    public Horaire toModel(HoraireEntity entity) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        return Horaire.builder()
                .horaireId(entity.getIdHoraire())
                .name(entity.getName())
                .startTime(entity.getStartTime().format(dtf))
                .endTime(entity.getEndTime().format(dtf))
                .interval(Interval.fromValue(entity.getInterval()))
                .horaireSpecialHoraire(entity.getHoraireSpecial())
                .build();
    }
}
