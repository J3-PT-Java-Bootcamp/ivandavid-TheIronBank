package com.ironhack.ivandavidtheironbank.user.controller.impl;

import com.ironhack.ivandavidtheironbank.user.config.KeycloakProvider;
import com.ironhack.ivandavidtheironbank.user.controller.AdminController;
import com.ironhack.ivandavidtheironbank.user.dto.AccountHolderDTO;
import com.ironhack.ivandavidtheironbank.user.dto.AdminDTO;
import com.ironhack.ivandavidtheironbank.user.dto.LoginRequest;
import com.ironhack.ivandavidtheironbank.user.service.AdminService;
import com.ironhack.ivandavidtheironbank.user.service.impl.KeycloakAdminClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.BadRequestException;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

import static com.sun.activation.registries.LogSupport.log;

@Log
@RestController
@RequestMapping("/api/v1/admins")
@RequiredArgsConstructor
public class AdminControllerImpl implements AdminController {

    private final AdminService adminService;
    private final KeycloakAdminClientService kcAdminClient;
    private final KeycloakProvider kcProvider;

    @Override
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public AdminDTO create(@RequestBody @Valid AdminDTO adminDTO) throws InterruptedException {
        AdminDTO userDTO = kcAdminClient.createKeycloakUserAdmin(adminDTO);
        return userDTO;
    }

    @Override
    @PostMapping("/create/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public AdminDTO create(@RequestBody @Valid AdminDTO adminDTO, Principal principal) throws InterruptedException {
        log("Este es el id del usuario en keycloak: " + principal.getName());
        AdminDTO userDTO = kcAdminClient.createKeycloakUserAdmin(adminDTO);
        return userDTO;
    }


    @PostMapping("/get-token")
    public ResponseEntity<AccessTokenResponse> login(@NotNull @RequestBody LoginRequest loginRequest) {
        Keycloak keycloak = kcProvider.newKeycloakBuilderWithPasswordCredentials(loginRequest.getUsername(), loginRequest.getPassword()).build();

        AccessTokenResponse accessTokenResponse = null;
        try {
            accessTokenResponse = keycloak.tokenManager().getAccessToken();
            return ResponseEntity.status(HttpStatus.OK).body(accessTokenResponse);
        } catch (BadRequestException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(accessTokenResponse);
        }
    }

    @Override
    public List<AdminDTO> findAll() {
        return null;
    }

    @Override
    @GetMapping("/check-balance/{accountId}/{adminId}")
    public ResponseEntity<BigDecimal> checkAccountBalance(@PathVariable Long accountId, @PathVariable Long adminId, Principal principal) {
        System.out.println("Este es el id del usuario en keycloak: " + principal.getName());
        var balance = adminService.checkAccountBalance(accountId, adminId, principal.getName());
        if (balance == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(balance);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(balance);
        }
    }
}
