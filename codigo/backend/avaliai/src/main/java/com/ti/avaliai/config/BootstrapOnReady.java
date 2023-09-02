package com.ti.avaliai.config;

import com.google.gson.reflect.TypeToken;
import com.ti.avaliai.databaseloader.DatabaseLoaderService;
import com.ti.avaliai.university.University;
import com.ti.avaliai.university.UniversityService;
import com.ti.avaliai.utils.JsonUtil;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.util.ArrayList;
import java.util.List;

@Log
@Configuration
public class BootstrapOnReady {

    private static final String PATH_JSON_UNIVERSITY = "/databaseloader/university.json";

    @Autowired
    private DatabaseLoaderService databaseLoaderService;

    @Autowired
    private UniversityService universityService;


    @EventListener(ApplicationReadyEvent.class)
    public void bootstraper()  {
        List<University> universities = JsonUtil.objectListFromJson("university", PATH_JSON_UNIVERSITY, new TypeToken<ArrayList<University>>() {
        }.getType());


        if ((databaseLoaderService.loadEntity(University.class.getSimpleName()))) {
            universities.forEach(this::createUniversity);
            databaseLoaderService.save(University.class.getSimpleName());
        }
    }

        private void createUniversity(University university) {
        try {
            universityService.save(university);
        } catch (Exception exception) {
            log.severe("A universidade " + university.getName() + " não pode ser cadastrada. Erro: " + exception.getMessage());
        }
    }
}