package com.database.api;

import com.database.Bind;
import com.database.DbViewer;
import com.database.Repository;
import com.database.framework.DealsDbService;
import com.database.framework.repository.DealsRepo;
import com.database.model.Car;
import com.database.model.Deal;
import com.database.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UserApiTest {

    private static DealsDbService dealsDbService;
    private static DealsRepo dealsRepo;

    @BeforeAll
    static void beforeAll() {
        System.getProperty("BD_URL", "");
//        final Jdbi jdbi = Jdbi.create("");

        DbViewer viewer = new DbViewer();

        dealsDbService = new DealsDbService(viewer);
        dealsRepo = new viewer.create(DealRepo.class);
    }

    @Test
    void testCanGetUserById(){
        Deal deal = dealsDbService.findById();
    }

}

interface DealsInterface extends Repository<Long, Deal> {
    List<Deal> findByAmount(double amount);

}

interface UserRepo extends Repository<Integer, User> {
    List<User> findByName(@Bind("first_name") String name);
}

interface CarsRepo extends Repository<Long, Car> {
}
