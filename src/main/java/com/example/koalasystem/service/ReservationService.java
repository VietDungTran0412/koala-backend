package com.example.koalasystem.service;

import com.example.koalasystem.dto.ReservationDto;
import com.example.koalasystem.entity.Reservation;
import com.example.koalasystem.enums.ReservationStatus;
import com.example.koalasystem.repository.ReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;

@Service
@Slf4j
public class ReservationService {
    private final ReservationRepository repository;

    public ReservationService(ReservationRepository repository) {
        this.repository = repository;
    }

    @Transactional(rollbackFor = Exception.class)
    public Reservation saveReservation(ReservationDto dto, Function<ReservationDto, Reservation> mapper) {
        log.info("Make reservation into Koala");
        Reservation reservation = mapper.apply(dto);
        reservation.setStatus(ReservationStatus.PLACED);
        return repository.save(reservation);
    }

    public List<Reservation> getAll() {
        return repository.findAll();
    }
}
