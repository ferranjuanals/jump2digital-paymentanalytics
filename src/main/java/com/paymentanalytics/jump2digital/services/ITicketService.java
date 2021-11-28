package com.paymentanalytics.jump2digital.services;

import com.paymentanalytics.jump2digital.dtos.TicketDto;

public interface ITicketService {

    TicketDto createTicket(TicketDto ticketDto);

    TicketDto getTicket(String id);

    void deleteTicket(String id);
}
