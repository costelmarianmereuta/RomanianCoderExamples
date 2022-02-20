package com.tennis.horaires.repository;

import com.tennis.horaires.entities.HoraireEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoraireRepository extends JpaRepository<HoraireEntity, Long> {
}
