package com.database;

import com.database.model.Car;
import com.database.model.Deal;
import com.database.model.User;
import org.assertj.core.api.Assertions;
import org.jdbi.v3.core.Jdbi;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SelectQueryTest {

    private static DbViewer viewer;

    @BeforeAll
    static void beforeAll() {
        final Jdbi jdbi = Jdbi.create("jdbc:postgresql://localhost:5432/deals?user=admin&password=admin");
        QueryRunner queryRunner = new JdbiQueryRunner(jdbi);
        queryRunner.addProcessor(new FindOneByIdProcessor(jdbi));
        queryRunner.addProcessor(new FindAllProcessor(jdbi));
        queryRunner.addProcessor(new FindByFieldProcessor(jdbi));

        viewer = new DbViewer(queryRunner);
    }

    @Test
    void testCanGetDealById() {
        DealsInterface dealsRepo = viewer.create(DealsInterface.class);
        Deal deal = dealsRepo.findOne(1L);
        assertThat(deal).isNotNull();

    }

    @Test
    void testCanGetDealByAmount() {
        DealsInterface dealsRepo = viewer.create(DealsInterface.class);
        List<Deal> deals = dealsRepo.findByAmount(914.58);

        assertThat(deals.size()).isEqualTo(1);

    }

    @Test
    void testCanGetUserById() {
        UserRepo userRepo = viewer.create(UserRepo.class);
        User user = userRepo.findOne(1);
        assertThat(user).isNotNull();

    }

    @Test
    void testCanGetUserByName() {
        UserRepo userRepo = viewer.create(UserRepo.class);

        List<User> user = userRepo.findByName("SomeName");
        Assertions.assertThat(user).isNotNull();

    }

    @Test
    void testCanGetCarById() {
        CarsRepo carsRepo = viewer.create(CarsRepo.class);
        Car car = carsRepo.findOne(1L);
        assertThat(car).isNotNull();

    }

    @Test
    void testCanGetAllDeals() {
        DealsInterface dealsRepo = viewer.create(DealsInterface.class);
        List<Deal> deal = dealsRepo.findAll();
        assertThat(deal.size()).isGreaterThan(0);
    }

    @Test
    void testCanGetAllUser() {
        UserRepo userRepo = viewer.create(UserRepo.class);
        List<User> user = userRepo.findAll();
        assertThat(user.size()).isEqualTo(1);

    }

    @Test
    void testCanGetAllCar() {
        CarsRepo carsRepo = viewer.create(CarsRepo.class);
        List<Car> car = carsRepo.findAll();
        assertThat(car.size()).isGreaterThan(0);

    }

}

interface DealsInterface extends Repository<Long, Deal> {
   List <Deal> findByAmount(double amount);

}

interface UserRepo extends Repository<Integer, User> {
    List<User> findByName(@Bind("first_name") String name);
}

interface CarsRepo extends Repository<Long, Car> {
}