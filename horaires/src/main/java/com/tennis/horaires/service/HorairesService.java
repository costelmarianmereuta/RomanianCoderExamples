package com.tennis.horaires.service;

import com.tennis.horaires.assembler.HoraireAssembler;
import com.tennis.horaires.entities.HoraireEntity;
import com.tennis.horaires.repository.HoraireRepository;
import com.tennis.horaires.utils.Constants;
import lombok.RequiredArgsConstructor;
import model.generated.CreateHoraireRequestBody;
import model.generated.Horaire;
import model.generated.Horaires;
import model.generated.UpdateHoraireRequestBody;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.tennis.horaires.utils.Constants.*;

@Service
@RequiredArgsConstructor
public class HorairesService {

    private final HoraireRepository horaireRepository;
    private final HoraireAssembler horaireAssembler;

    public Horaires getHoraires() {
        List<HoraireEntity> horaireEntities = horaireRepository.findAll();
        CollectionModel<Horaire> horaires = horaireAssembler.toCollectionModel(horaireEntities);
        return Horaires.builder()
                .horaireList(new ArrayList<>(horaires.getContent()))
                .build();
    }

    public Horaire getHoraire(Long id) {
        Optional<HoraireEntity> horaireEntity = horaireRepository.findById(id);
        if (horaireEntity.isPresent()) {
            return horaireAssembler.toModel(horaireEntity.get());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, HORAIRE_NOT_FOUND);
    }

    public String createHoraire(CreateHoraireRequestBody createHoraireRequestBody) {
        checkStartAndEndTime(createHoraireRequestBody.getStartTime(), createHoraireRequestBody.getEndTime());
        HoraireEntity horaireEntity = HoraireEntity.builder()
                .name(createHoraireRequestBody.getName())
                .startTime(LocalTime.parse(createHoraireRequestBody.getStartTime()))
                .endTime(LocalTime.parse(createHoraireRequestBody.getEndTime()))
                .horaireSpecial(createHoraireRequestBody.getHoraireSpecialHoraire())
                .interval(createHoraireRequestBody.getInterval().getValue())
                .build();
        horaireRepository.save(horaireEntity);
        return Constants.HORAIRE_CREATED;
    }

    private void checkStartAndEndTime(String startTime, String endTime) {
        if (LocalTime.parse(startTime).isAfter(LocalTime.parse(endTime))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, INVALID_TIME);
        }
    }

    public String deleteHoraire(Long id) {
        horaireRepository.deleteById(id);
        return HORAIRE_DELETED;
    }

    public Horaire updateHoraire(UpdateHoraireRequestBody updateHoraireRequestBody) {
        Optional<HoraireEntity> horaire = horaireRepository.findById(updateHoraireRequestBody.getId());
        if (horaire.isPresent()) {
            HoraireEntity horaireEntity = horaire.get();
            horaireEntity.setName(updateHoraireRequestBody.getName());
            horaireEntity.setStartTime(LocalTime.parse(updateHoraireRequestBody.getStartTime()));
            horaireEntity.setEndTime(LocalTime.parse(updateHoraireRequestBody.getEndTime()));
            horaireEntity.setHoraireSpecial(updateHoraireRequestBody.getHoraireSpecialHoraire());
            horaireEntity.setInterval(updateHoraireRequestBody.getInterval().getValue());
            horaireRepository.save(horaireEntity);
            return horaireAssembler.toModel(horaireEntity);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, HORAIRE_NOT_FOUND);
    }
}
