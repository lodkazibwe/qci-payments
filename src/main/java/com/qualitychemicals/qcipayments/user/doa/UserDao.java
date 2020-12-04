package com.qualitychemicals.qcipayments.user.doa;

import com.qualitychemicals.qcipayments.user.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<UserInfo, Integer> {
    UserInfo findByUserName(String userName);

}
