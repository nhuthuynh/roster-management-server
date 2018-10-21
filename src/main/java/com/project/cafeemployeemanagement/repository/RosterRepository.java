package com.project.cafeemployeemanagement.repository;

import com.project.cafeemployeemanagement.model.Roster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface RosterRepository extends JpaRepository<Roster, Long> {
    //@Query("SELECT NEW com.example.polls.model.ChoiceVoteCount(v.choice.id, count(v.id)) FROM Vote v WHERE v.poll.id in :pollIds GROUP BY v.choice.id")
    //List<ChoiceVoteCount> countByPollIdInGroupByChoiceId(@Param("pollIds") List<Long> pollIds);

    @Query("SELECT r FROM Roster AS r WHERE r.fromDate = :fromDate")
    Roster findByStartDate(@Param("fromDate") Date fromDate);

}
