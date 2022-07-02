package jjfactory.parking.business.repository.town;

import jjfactory.parking.business.domain.location.Region;
import jjfactory.parking.business.domain.location.Town;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TownRepository extends JpaRepository<Town,Long> {
    Town findByName(String name);
//    Page<Town> findByRegion(Region region);
}
