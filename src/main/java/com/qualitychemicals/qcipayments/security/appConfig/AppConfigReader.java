package com.qualitychemicals.qcipayments.security.appConfig;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class AppConfigReader {
    @Value("${api.userName}")
    private String userName;
    @Value("${api.password}")
    private String password;

}
