package jjfactory.parking.business.controller.history;

import jjfactory.parking.business.service.hisotry.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BuyHistoryApi {
    private final HistoryService historyService;
}
