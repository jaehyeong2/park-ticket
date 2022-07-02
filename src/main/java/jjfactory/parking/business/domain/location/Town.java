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

    private Boolean isView;

    @Builder
    public Town(String name, Region region,Boolean isView) {
        this.name = name;
        this.region = region;
        this.isView = isView;
    }

    public void delete() {
        isView = false;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public static Town create(TownDto dto){
        return builder()
                .name(dto.getName())
                .region(dto.getRegion())
                .isView(true)
                .build();
    }

}
