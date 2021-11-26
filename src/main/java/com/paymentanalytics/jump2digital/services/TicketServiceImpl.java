package com.paymentanalytics.jump2digital.services;

import com.paymentanalytics.jump2digital.dtos.TicketDto;
import com.paymentanalytics.jump2digital.model.entities.Product;
import com.paymentanalytics.jump2digital.model.entities.Ticket;
import com.paymentanalytics.jump2digital.model.valueobjects.PaymentType;
import com.paymentanalytics.jump2digital.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    private TicketDto mapDto(Ticket ticket) {
        return TicketDto.builder()
                .id(ticket.getId().toString())
                .product(productService.mapProductToDto(ticket.getProduct()))
                .amount((Integer) ticket.getAmount())
                .paymentType(ticket.getPaymentType().toString())
                .build();
    }

    private Ticket mapTicket(TicketDto ticketDto) {
        Product product = productService.getProductById(ticketDto.getProduct().getId());
        return Ticket.builder()
                .product(product)
                .amount(ticketDto.getAmount())
                .paymentType(PaymentType.getType(ticketDto.getPaymentType()))
                .build();
    }

}
