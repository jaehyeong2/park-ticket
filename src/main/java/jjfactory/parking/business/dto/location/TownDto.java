package jjfactory.parking.business.dto.location;

import jjfactory.parking.business.domain.location.Region;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TownDto {
    private String name;
    private Region region;
}
