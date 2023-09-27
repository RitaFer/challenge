package com.athornatus.controllers;

import com.athornatus.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AddressRestController {
    private final AddressService addressService;

}
