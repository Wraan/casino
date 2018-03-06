package com.wran.controller;

import com.wran.model.Activity;
import com.wran.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/history")
public class ActivitiesController {

    @Autowired
    ActivityRepository activityRepository;

    @RequestMapping("/{login}")
    public List<Activity> showUserActivities(@PathVariable("login") String login){
        return activityRepository.findActivitiesByLogin(login);
    }

}
