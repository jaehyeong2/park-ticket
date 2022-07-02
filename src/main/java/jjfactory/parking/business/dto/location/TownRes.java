package jjfactory.parking.business.dto.location;

import jjfactory.parking.business.domain.location.Region;
import jjfactory.parking.business.domain.location.Town;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TownRes {
    private String name;
    private Region region;

    public TownRes(Town town) {
        this.name = town.getName();
        this.region = town.getRegion();
    }
}
