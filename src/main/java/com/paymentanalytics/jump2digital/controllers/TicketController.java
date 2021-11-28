package com.paymentanalytics.jump2digital.controllers;

import com.paymentanalytics.jump2digital.dtos.TicketDto;
import com.paymentanalytics.jump2digital.exceptions.ArgumentNotValidException;
import com.paymentanalytics.jump2digital.exceptions.InvalidTypeException;
import com.paymentanalytics.jump2digital.services.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.security.InvalidAlgorithmParameterException;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    ITicketService ticketService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TicketDto createTicket(@RequestBody TicketDto ticketDto) {
        try {
            return ticketService.createTicket(ticketDto);
        } catch (ArgumentNotValidException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The amount is not correct.");
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The product does not exist");
        } catch (InvalidTypeException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The payment type is not correct.");
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public TicketDto getTicket(@RequestBody String id) {
        try {
            return ticketService.getTicket(id);
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket with ID " + id + " does not exist.");
        }
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteTicket(@RequestBody String id) {
        try {
            ticketService.deleteTicket(id);
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket with ID " + id + " does not exist.");
        }
    }
}
