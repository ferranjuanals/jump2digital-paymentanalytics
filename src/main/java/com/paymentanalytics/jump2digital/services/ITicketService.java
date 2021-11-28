package com.paymentanalytics.jump2digital.services;

import com.paymentanalytics.jump2digital.dtos.TicketDto;

import java.util.Map;
import java.util.UUID;

public interface ITicketService {

    TicketDto createTicket(TicketDto ticketDto);

    TicketDto getTicket(UUID id);

    void deleteTicket(UUID id);

    Map<String, Object> getSummary();
}
