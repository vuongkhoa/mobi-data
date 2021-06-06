package com.abcbank.thirdpartyservice.repository.mysql;

import com.abcbank.thirdpartyservice.model.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Long> {
}
