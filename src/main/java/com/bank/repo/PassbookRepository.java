package com.bank.repo;

import com.bank.binding.Passbook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassbookRepository extends JpaRepository<Passbook, Long> {
    List<Passbook> findByAccountId(Long accountId);
}
