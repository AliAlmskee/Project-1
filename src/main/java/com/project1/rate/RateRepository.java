package com.project1.rate;

import com.project1.offer.data.Offer;
import com.project1.offer.data.OfferStatus;
import com.project1.profile.ClientProfile;
import com.project1.profile.WorkerProfile;
import com.project1.project.data.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RateRepository extends JpaRepository<Rate, Long> {

    int countByClient(ClientProfile clientProfile);

    int countByWorker(WorkerProfile workerProfile);
}