package com.qualitychemicals.qcipayments.transaction.dao;

import com.qualitychemicals.qcipayments.transaction.model.ShareT;
import com.qualitychemicals.qcipayments.transaction.model.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ShareTDao extends JpaRepository<ShareT, Integer> {

    List<ShareT> findByUserNameOrderByDateDesc(String userName);

    List<ShareT> findByWalletOrderByCreationDateTimeDesc(String wallet);

    List<ShareT> findFirst5ByWalletOrderByCreationDateTimeDesc(String wallet);



    List<ShareT> findByStatusAndAmountGreaterThanAndDate(TransactionStatus success, double v, Date date);

    List<ShareT> findByStatusAndAmountGreaterThanAndDateLessThanEqualAndDateGreaterThanEqual(TransactionStatus success, double v, Date dateTo, Date dateFrom);
}
