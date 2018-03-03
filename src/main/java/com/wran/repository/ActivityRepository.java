package com.wran.repository;

import com.wran.model.Activity;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Primary
@Repository("activityRepository")
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    @Query("Select ac from Activity ac where ac.login = ?1 order by ac.id desc ")
    List<Activity> findActivitiesByLogin(String login);

}
