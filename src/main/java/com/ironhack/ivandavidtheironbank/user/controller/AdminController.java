package com.ironhack.ivandavidtheironbank.user.controller;

import com.ironhack.ivandavidtheironbank.user.dto.AccountHolderDTO;
import com.ironhack.ivandavidtheironbank.user.dto.AdminDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

public interface AdminController {

    AdminDTO create(AdminDTO adminDTO) throws InterruptedException;

    AdminDTO create(AdminDTO adminDTO, Principal principal) throws InterruptedException;

    ResponseEntity<BigDecimal> checkAccountBalance(Long accountId, Long adminId, Principal principal);

    List<AdminDTO> findAll();

}
