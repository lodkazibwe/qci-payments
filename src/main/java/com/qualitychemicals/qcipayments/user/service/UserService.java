package com.qualitychemicals.qcipayments.user.service;

public interface UserService {
    void requestPin(String contact);

    void createPass(String password);
}
