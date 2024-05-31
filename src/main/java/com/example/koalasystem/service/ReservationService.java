package com.example.koalasystem.service;

import com.example.koalasystem.dto.ReservationDto;
import com.example.koalasystem.entity.Reservation;
import com.example.koalasystem.enums.ReservationStatus;
import com.example.koalasystem.exception.EntityNotFoundException;
import com.example.koalasystem.repository.ReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/* Handle Reservation relevant business logic */
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

    @Transactional(rollbackFor = Exception.class)
    public Reservation deleteById(Long id) {
//        Find the Reservation by id if it is existed
        Optional<Reservation> wrapper = repository.findById(id);
        if(!wrapper.isPresent()) {
            throw new EntityNotFoundException("Reservation id is not found.");
        }
        repository.delete(wrapper.get());
        return wrapper.get();
    }
}
