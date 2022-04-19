package com.example.pzespolowe.Repositories;

import com.example.pzespolowe.Models.Klient;
import com.example.pzespolowe.Models.Newsletter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsletterRepository extends JpaRepository<Newsletter, Klient> {
}