package jjfactory.parking.business.domain.location;

import jjfactory.parking.business.dto.location.TownDto;
import jjfactory.parking.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Town extends BaseTimeEntity {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Region region;

    @Builder
    public Town(String name, Region region) {
        this.name = name;
        this.region = region;
    }

    public static Town create(TownDto dto){
        return builder()
                .name(dto.getName())
                .region(dto.getRegion())
                .build();
    }

}
