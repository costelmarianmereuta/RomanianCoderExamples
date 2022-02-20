package com.tennis.horaires.controller;

import com.tennis.horaires.service.HorairesService;
import lombok.RequiredArgsConstructor;
import model.generated.CreateHoraireRequestBody;
import model.generated.Horaire;
import model.generated.Horaires;
import model.generated.UpdateHoraireRequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/horaires")
@RequiredArgsConstructor
public class HoraireController {

    private final HorairesService horairesService;

    @GetMapping
    public ResponseEntity<Horaires> getHoraires() {
        Horaires horaires = horairesService.getHoraires();
        return ResponseEntity.ok(horaires);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Horaire> getHoraire(@PathVariable Long id) {
        Horaire horaire = horairesService.getHoraire(id);
        return ResponseEntity.ok(horaire);
    }

    @PostMapping
    public ResponseEntity<String> createHoraire(@RequestBody CreateHoraireRequestBody createHoraireRequestBody) {
        return ResponseEntity.ok().body(horairesService.createHoraire(createHoraireRequestBody));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHoraire(@PathVariable Long id) {
        return ResponseEntity.ok().body(horairesService.deleteHoraire(id));
    }

    @PutMapping
    public ResponseEntity<Horaire> updateHoraire(@RequestBody UpdateHoraireRequestBody updateHoraireRequestBody) {
        Horaire horaire = horairesService.updateHoraire(updateHoraireRequestBody);
        return ResponseEntity.ok().body(horaire);
    }


}
