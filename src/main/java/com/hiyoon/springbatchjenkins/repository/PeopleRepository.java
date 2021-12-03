//package com.hiyoon.springbatchjenkins.repository;
//
//import com.hiyoon.springbatchjenkins.entity.People;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import java.util.List;
//
//public interface PeopleRepository extends JpaRepository<People, Long> {
//
//    @Query(value = "select p.personId, count(p) from People p group by p.personId")
//    List<People> findAllPeople();
//}
