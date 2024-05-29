package com.example.koalasystem.controller;

import com.example.koalasystem.dto.ReservationDto;
import com.example.koalasystem.dto.RestResponseDto;
import com.example.koalasystem.entity.Reservation;
import com.example.koalasystem.mapper.ReservationMapper;
import com.example.koalasystem.service.ReservationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
@AllArgsConstructor
@Slf4j
public class ReservationController {
    private final ReservationService reservationService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    RestResponseDto makeReservation(@RequestBody @Valid ReservationDto dto) {
        log.info("Starting making new reservation into koala");
        Reservation res = reservationService.saveReservation(dto, ReservationMapper.INSTANCE::toReservation);
        return RestResponseDto.builder().message("Saved new reservation").data(res).build();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    RestResponseDto getReservation() {
        log.info("Retrieving reservations from koala");
        List<Reservation> res = reservationService.getAll();
        return RestResponseDto.builder().message("Retrieved new reservation").data(res).build();
    }
}
