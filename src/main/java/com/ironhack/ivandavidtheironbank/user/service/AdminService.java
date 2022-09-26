package com.ironhack.ivandavidtheironbank.user.service;

import java.math.BigDecimal;

public interface AdminService {

    BigDecimal checkAccountBalance(Long accountId, Long adminId, String keycloakName);

}
