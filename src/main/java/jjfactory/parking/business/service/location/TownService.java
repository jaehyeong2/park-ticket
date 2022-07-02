package jjfactory.parking.business.service.location;

import jjfactory.parking.business.domain.location.Town;
import jjfactory.parking.business.dto.location.TownDto;
import jjfactory.parking.business.dto.location.TownNameReq;
import jjfactory.parking.business.dto.location.TownRes;
import jjfactory.parking.business.repository.location.TownQueryRepository;
import jjfactory.parking.business.repository.location.TownRepository;
import jjfactory.parking.global.handler.ex.BusinessException;
import jjfactory.parking.global.handler.ex.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class TownService {
    private final TownRepository townRepository;
    private final TownQueryRepository townQueryRepository;

    public TownRes getTown(String name){
        Town town = townRepository.findByName(name);
        return new TownRes(town);
    }

    public Page<TownRes> getTowns(Pageable pageable){
        return townQueryRepository.findTowns(pageable);
    }

    public String createTown(TownDto dto){
        Town town = Town.create(dto);
        townRepository.save(town);
        return "Y";
    }

    public String deleteTown(Long id){
        Town town = getTown(id);
        town.delete();
        return "Y";
    }

    public String changeTownName(TownNameReq dto){
        Town town = getTown(dto.getId());
        town.changeName(dto.getName());
        return "Y";
    }

    private Town getTown(Long id) {
        Town town = townRepository.findById(id).orElseThrow(() -> {
            throw new BusinessException(ErrorCode.ENTITY_NOT_FOUND);
        });
        return town;
    }
}
