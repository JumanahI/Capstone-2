package com.example.capstone_2.Repository;

import com.example.capstone_2.Model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface RequestRepository extends JpaRepository<Request,Integer> {
    Request findRequestById(Integer id);

    List<Request> findRequestByProjectId(Integer projectId);

    @Query("select r from Request r where r.investorId=?1")
    List<Request> getRequestsByInvestorId(Integer investorId);

}
