package com.axon.axondemo.commandside.repository;

import com.axon.axondemo.queryside.entity.ComplaintQueryObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintQueryObjectRepository extends JpaRepository<ComplaintQueryObject, String> {
}
