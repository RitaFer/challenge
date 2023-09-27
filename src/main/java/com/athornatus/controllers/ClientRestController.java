package com.athornatus.controllers;

import com.athornatus.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ClientRestController {
    private final ClientService clientService;

}
