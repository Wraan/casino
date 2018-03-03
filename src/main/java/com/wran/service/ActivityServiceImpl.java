package com.wran.service;

import com.wran.model.Activity;
import com.wran.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("activityService")
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    ActivityRepository activityRepository;

    @Override
    public void add(String login, int coins, String game) {
        Activity activity = new Activity(login, coins, game);
        activityRepository.saveAndFlush(activity);

    }
}
