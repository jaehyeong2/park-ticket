package jjfactory.parking.business.service.hisotry;

import jjfactory.parking.business.dto.history.HistoryDto;
import jjfactory.parking.business.repository.history.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class HistoryService {
    private final HistoryRepository historyRepository;

    public String create(HistoryDto dto){
        return "y";
    }

    public String delete(){
        return "y";
    }

    public String update(){
        return "y";
    }
}
