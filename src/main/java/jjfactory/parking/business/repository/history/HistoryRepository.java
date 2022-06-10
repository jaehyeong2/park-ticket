package jjfactory.parking.business.repository.history;

import jjfactory.parking.business.domain.history.BuyHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<BuyHistory,Long> {
}
