package by.logonuk.repository.deal;

import by.logonuk.domain.Deal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DealRepositoryTest {

    private DealRepositoryInterface repository;

    @BeforeEach
    public void setRepository(){
        repository = new DealRepository();
    }

    @Test
    void createAndFindById_success() {

        // given
        Deal testDeal = new Deal();

        testDeal.setUserId(1);
        testDeal.setProductId(2);
        testDeal.setQuantity(10);
        testDeal.setPrice(1000.5);

        Deal deal = repository.create(testDeal);

        //when
        assertEquals(testDeal.getPrice(), deal.getPrice());
        assertEquals(Integer.class, deal.getId().getClass());

        repository.delete(deal.getId());
    }

    @Test
    void update_success() {

        //given
        Deal testDeal = new Deal();

        testDeal.setUserId(1);
        testDeal.setProductId(2);
        testDeal.setQuantity(10);
        testDeal.setPrice(1000.5);

        Deal deal = repository.create(testDeal);

        testDeal.setId(deal.getId());
        testDeal.setQuantity(12);
        testDeal.setModificationDate(new Timestamp(new Date().getTime()));

        //when
        assertEquals(testDeal.getPrice(), repository.update(testDeal).getPrice());

        repository.delete(deal.getId());
    }

    @Test
    void delete_success() {
        //given
        Deal testDeal = new Deal();

        testDeal.setUserId(1);
        testDeal.setProductId(2);
        testDeal.setQuantity(10);
        testDeal.setPrice(1000.5);

        Deal deal = repository.create(testDeal);

        //when
        assertEquals(deal.getId(), repository.delete(deal.getId()));
    }
}