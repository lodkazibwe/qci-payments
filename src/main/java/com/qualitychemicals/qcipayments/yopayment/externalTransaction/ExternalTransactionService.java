package com.qualitychemicals.qcipayments.yopayment.externalTransaction;

import com.qualitychemicals.qcipayments.yopayment.externalTransaction.dao.ExternalTransactionDao;
import com.qualitychemicals.qcipayments.yopayment.externalTransaction.model.ExternalTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExternalTransactionService {
    @Autowired
    ExternalTransactionDao externalTransactionDao;

    public ExternalTransaction save(ExternalTransaction externalTransaction){
        return externalTransactionDao.save(externalTransaction);
    }

    public List<ExternalTransaction> getByWalletAndStatus(String wallet, String status){
        return externalTransactionDao.findByWalletAndStatus(wallet, status);
    }

}
