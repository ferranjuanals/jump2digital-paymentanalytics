package com.paymentanalytics.jump2digital.services;

import com.paymentanalytics.jump2digital.dtos.TicketDto;
import com.paymentanalytics.jump2digital.exceptions.ArgumentNotValidException;
import com.paymentanalytics.jump2digital.model.entities.Ticket;
import com.paymentanalytics.jump2digital.model.valueobjects.PaymentType;
import com.paymentanalytics.jump2digital.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TicketServiceImpl implements ITicketService {

    @Autowired
    TicketRepository ticketRepository;

    IProductService productService;

    @Override
    public TicketDto createTicket(TicketDto ticketDto) {
        Ticket ticket = mapTicket(ticketDto);
        ticketRepository.save(ticket);
        return mapDto(ticket);
    }

    @Override
    public TicketDto getTicket(String id) {
        Ticket ticket = ticketRepository.getById(UUID.fromString(id));
        return mapDto(ticket);
    }

    @Override
    public void deleteTicket(String id) {
        Ticket ticket = ticketRepository.getById(UUID.fromString(id));
        ticketRepository.delete(ticket);
    }

    private TicketDto mapDto(Ticket ticket) {
        return TicketDto.builder()
                .id(ticket.getId().toString())
                .product(productService.mapProductToDto(ticket.getProduct()))
                .amount((Integer) ticket.getAmount())
                .paymentType(ticket.getPaymentType().toString())
                .build();
    }

    private Ticket mapTicket(TicketDto ticketDto) {
        Ticket ticket = Ticket.builder()
                .product(productService.getProductById(ticketDto.getProduct().getId()))
                .amount(ticketDto.getAmount())
                .paymentType(PaymentType.getType(ticketDto.getPaymentType()))
                .build();
        validate(ticket);
        return ticket;
    }

    private void validate(Ticket ticket) {
        if(ticket.getAmount().intValue() < 1) throw new ArgumentNotValidException("The ticket amount must be 1 or more.");
    }

}
