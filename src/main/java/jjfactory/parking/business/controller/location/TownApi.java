package jjfactory.parking.business.controller.location;

import jjfactory.parking.business.service.location.TownService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TownApi {
    private final TownService townService;
}
