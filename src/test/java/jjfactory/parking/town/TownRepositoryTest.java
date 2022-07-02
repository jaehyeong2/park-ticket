package jjfactory.parking.town;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jjfactory.parking.business.domain.location.Region;
import jjfactory.parking.business.domain.location.Town;
import jjfactory.parking.business.repository.location.TownRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;

import static jjfactory.parking.business.domain.location.QTown.*;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
//@ExtendWith(SpringExtension.class)
public class TownRepositoryTest {

    @Autowired
    TownRepository townRepository;

    @Autowired
    EntityManager em;
    JPAQueryFactory queryFactory;

    @BeforeEach
    void init(){
        queryFactory = new JPAQueryFactory(em);
    }

    @Test
    @DisplayName("세이브 테스트")
    void saveAll(){
        Town town1 = Town.builder().region(Region.전라남도).name("무슨무슨대로 무슨길").build();
        Town town2 = Town.builder().region(Region.전라남도).name("무슨대로 2번길").build();
        Town town3 = Town.builder().region(Region.전라남도).name("무슨대로 3번길").build();
        Town town4 = Town.builder().region(Region.경상북도).name("무슨무슨대로 경상길").build();

        List<Town> towns = Arrays.asList(town1, town2, town3, town4);
        townRepository.saveAll(towns);

        List<Town> all = townRepository.findAll();
        assertThat(all.size()).isEqualTo(4);
    }

    @Test
    @DisplayName("쿼리dsl region으로 찾기 테스트")
    void selectByRegion(){
        Town town1 = Town.builder().region(Region.전라남도).name("무슨무슨대로 무슨길").build();
        Town town2 = Town.builder().region(Region.전라남도).name("무슨대로 2번길").build();
        Town town3 = Town.builder().region(Region.전라남도).name("무슨대로 3번길").build();
        Town town4 = Town.builder().region(Region.경상북도).name("무슨무슨대로 경상길").build();

        List<Town> towns = Arrays.asList(town1, town2, town3, town4);
        townRepository.saveAll(towns);

        Town findTown = queryFactory.selectFrom(town).where(town.region.eq(Region.경상북도)).fetchOne();
        List<Town> result = queryFactory.selectFrom(town).where(town.region.eq(Region.전라남도)).fetch();

        assertThat(findTown).isEqualTo(town4);
        assertThat(result.size()).isEqualTo(3);
    }
}
