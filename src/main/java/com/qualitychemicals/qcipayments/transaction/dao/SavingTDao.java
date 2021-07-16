package com.qualitychemicals.qcipayments.transaction.dao;

import com.qualitychemicals.qcipayments.transaction.model.SavingT;
import com.qualitychemicals.qcipayments.transaction.model.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SavingTDao extends JpaRepository<SavingT, Integer> {
    List<SavingT> findByUserNameOrderByDateDesc(String userName);

    List<SavingT> findByWalletOrderByCreationDateTimeDesc(String wallet);

    List<SavingT> findFirst5ByWalletOrderByCreationDateTimeDesc(String wallet);

    //List<RequiredAccountEntity> findByDebitAccNoEqualsAndDateLessThanEqualAndGreaterThanEqualAndTnxAmtEquals
    // (String debitAccNo, Date fromDate, Date toDate, String tnxAmtFlag);
    List<SavingT> findByStatusAndAmountLessThan(TransactionStatus status, Double amount);

    List<SavingT> findByStatusAndAmountGreaterThanAndDateLessThanEqualAndDateGreaterThanEqual(
            TransactionStatus status, Double amount, Date date1, Date date2);

    List<SavingT> findByStatusAndAmountGreaterThanAndDate
            (TransactionStatus success, double v, Date date);

}
