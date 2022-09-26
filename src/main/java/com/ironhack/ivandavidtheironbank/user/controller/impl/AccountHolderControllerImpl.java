package com.ironhack.ivandavidtheironbank.user.controller.impl;

import com.ironhack.ivandavidtheironbank.user.config.KeycloakProvider;
import com.ironhack.ivandavidtheironbank.user.controller.AccountHolderController;
import com.ironhack.ivandavidtheironbank.user.dto.AccountHolderDTO;
import com.ironhack.ivandavidtheironbank.user.dto.LoginRequest;
import com.ironhack.ivandavidtheironbank.user.service.AccountHolderService;
import com.ironhack.ivandavidtheironbank.user.service.impl.KeycloakAdminClientService;
import lombok.RequiredArgsConstructor;
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

@RestController
@RequestMapping("/api/v1/accountholders")
@RequiredArgsConstructor
public class AccountHolderControllerImpl implements AccountHolderController {

    //todo: @Valid permite hacer las validaciones en los atributos. P.ej. la anotación @NotNull. Estas se ponen en los DTO

    private final AccountHolderService accountHolderService;

    private final KeycloakAdminClientService kcAdminClient;

    private final KeycloakProvider kcProvider;

    @Override
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountHolderDTO create(@RequestBody @Valid AccountHolderDTO accountHolderDTO) throws InterruptedException {
        AccountHolderDTO userDTO = kcAdminClient.createKeycloakUserAccountHolder(accountHolderDTO);
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
    @ResponseStatus(HttpStatus.OK)
    public List<AccountHolderDTO> findAll() {
        return accountHolderService.findAll();
    }

    @Override
    @GetMapping("/check-balance/{accountId}")
    public ResponseEntity<BigDecimal> checkAccountBalance(@PathVariable Long accountId, Principal principal) {
        System.out.println("Este es el id del usuario en keycloak: " + principal.getName());
        var balance = accountHolderService.checkAccountBalance(accountId, principal.getName());
        if (balance == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(balance);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(balance);
        }
    }

}
