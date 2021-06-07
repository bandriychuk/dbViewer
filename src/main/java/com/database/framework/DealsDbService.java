package com.database.framework;

import com.database.DbViewer;
import com.database.framework.repository.DealsRepo;

public class DealsDbService {

    private final DealsRepo dealsRepo;

    public DealsDbService(DbViewer viewer) {
        dealsRepo = viewer.create(DealsRepo.class);
    }

    public Deal findById(int id){
        return dealsRepo.findOne(id);
    }
}
