package com.example.koalasystem.mapper;

import com.example.koalasystem.dto.ReservationDto;
import com.example.koalasystem.entity.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReservationMapper {
    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);
    @Mapping(source = "reservationTime", target = "reservationTime", dateFormat = "yyyy-MM-dd'T'HH:mm")
    Reservation toReservation(ReservationDto dto);
}
