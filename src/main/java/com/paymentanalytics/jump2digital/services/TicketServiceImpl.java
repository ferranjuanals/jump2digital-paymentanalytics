package com.paymentanalytics.jump2digital.services;

import com.paymentanalytics.jump2digital.dtos.TicketDto;
import com.paymentanalytics.jump2digital.exceptions.ArgumentNotValidException;
import com.paymentanalytics.jump2digital.model.entities.Ticket;
import com.paymentanalytics.jump2digital.model.valueobjects.PaymentType;
import com.paymentanalytics.jump2digital.model.valueobjects.ProductType;
import com.paymentanalytics.jump2digital.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements ITicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    IProductService productService;

    @Override
    public TicketDto createTicket(TicketDto ticketDto) {
        Ticket ticket = mapTicket(ticketDto);
        ticketRepository.save(ticket);
        return mapDto(ticket);
    }

    @Override
    public TicketDto getTicket(UUID id) {
        Ticket ticket = ticketRepository.getById(id);
        return mapDto(ticket);
    }

    @Override
    public void deleteTicket(UUID id) {
        Ticket ticket = ticketRepository.getById(id);
        ticketRepository.delete(ticket);
    }

    @Override
    public Map<String, Object> getSummary() {
        HashMap<String, Object> summary = new HashMap<>();
        Double salesValue = ticketRepository.findAll()
                .stream()
                .mapToDouble(t -> t.getAmount().intValue() * t.getProduct().getPrice().doubleValue())
                .sum();
        Map<ProductType, Long> productSales = ticketRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(t -> t.getProduct().getProductType(), Collectors.counting()));
        Map<PaymentType, Long> paymentTickets = ticketRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(Ticket::getPaymentType, Collectors.counting()));
        summary.put("Total value of sold products", salesValue);
        summary.put("Number of sales by product", productSales);
        summary.put("Number of tickets by payment type", paymentTickets);
        return summary;
    }

    private TicketDto mapDto(Ticket ticket) {
        return TicketDto.builder()
                .id(ticket.getId())
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
