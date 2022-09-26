package com.ironhack.ivandavidtheironbank.user.service.impl;


import com.ironhack.ivandavidtheironbank.user.config.KeycloakProvider;
import com.ironhack.ivandavidtheironbank.user.dto.AccountHolderDTO;
import com.ironhack.ivandavidtheironbank.user.dto.AdminDTO;
import com.ironhack.ivandavidtheironbank.user.model.AccountHolder;
import com.ironhack.ivandavidtheironbank.user.model.Admin;
import com.ironhack.ivandavidtheironbank.user.repository.AccountHolderRepository;
import com.ironhack.ivandavidtheironbank.user.repository.AdminRepository;
import lombok.extern.java.Log;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;


@Service
@Log
public class KeycloakAdminClientService {
    private final KeycloakProvider kcProvider;
    private final AccountHolderRepository accountHolderRepository;
    private final AdminRepository adminRepository;

    @Value("${keycloak.realm}")
    public String realm;
    @Value(("${keycloak.resource}"))
    public String clientId;


    public KeycloakAdminClientService(KeycloakProvider kcProvider, AccountHolderRepository accountHolderRepository, AdminRepository adminRepository) {
        this.kcProvider = kcProvider;
        this.accountHolderRepository = accountHolderRepository;
        this.adminRepository = adminRepository;
    }

    private static CredentialRepresentation createPasswordCredentials(String password) {
        CredentialRepresentation passwordCredentials = new CredentialRepresentation();
        passwordCredentials.setTemporary(false);
        passwordCredentials.setType(CredentialRepresentation.PASSWORD);
        passwordCredentials.setValue(password);
        return passwordCredentials;
    }

    public AccountHolderDTO createKeycloakUserAccountHolder(AccountHolderDTO user) {
        var adminKeycloak = kcProvider.getInstance();
        UsersResource usersResource = kcProvider.getInstance().realm(realm).users();
        CredentialRepresentation credentialRepresentation = createPasswordCredentials(user.getPassword());

        UserRepresentation kcUser = new UserRepresentation();
        kcUser.setUsername(user.getEmail());
        kcUser.setCredentials(Collections.singletonList(credentialRepresentation));
        kcUser.setFirstName(user.getFirstname());
        kcUser.setLastName(user.getLastname());
        kcUser.setEmail(user.getEmail());
        kcUser.setEnabled(true);
        kcUser.setEmailVerified(false);

//        Change this to change the group logic
        kcUser.setGroups(List.of("members"));


        Response response = usersResource.create(kcUser);
        //wait(100);

        if (response.getStatus() == 201) {
            List<UserRepresentation> userList = adminKeycloak.realm(realm).users().search(kcUser.getUsername()).stream()
                    //.filter(userRep -> userRep.getUsername().equals(kcUser.getUsername()))
                    .toList();
            var createdUser = userList.get(0);
            log.info("User with id: " + createdUser.getId() + " created");

//            TODO you may add you logic to store and connect the keycloak user to the local user here
            user.setKeyckloakId(createdUser.getId());
            accountHolderRepository.save(AccountHolder.fromDTO(user));
        }
        return user;
    }

    public AdminDTO createKeycloakUserAdmin(AdminDTO user) throws InterruptedException {
        var adminKeycloak = kcProvider.getInstance();
        UsersResource usersResource = kcProvider.getInstance().realm(realm).users();
        CredentialRepresentation credentialRepresentation = createPasswordCredentials(user.getPassword());

        UserRepresentation kcUser = new UserRepresentation();
        kcUser.setUsername(user.getEmail());
        kcUser.setCredentials(Collections.singletonList(credentialRepresentation));
        kcUser.setFirstName(user.getFirstname());
        kcUser.setLastName(user.getLastname());
        kcUser.setEmail(user.getEmail());
        kcUser.setEnabled(true);
        kcUser.setEmailVerified(false);

//        Change this to change the group logic
        kcUser.setGroups(List.of("admin"));


        Response response = usersResource.create(kcUser);
        wait(100);

        if (response.getStatus() == 201) {
            List<UserRepresentation> userList = adminKeycloak.realm(realm).users().search(kcUser.getUsername()).stream()
                    //.filter(userRep -> userRep.getUsername().equals(kcUser.getUsername()))
                    .toList();
            var createdUser = userList.get(0);
            log.info("User with id: " + createdUser.getId() + " created");

//            TODO you may add you logic to store and connect the keycloak user to the local user here
            user.setKeyckloakId(createdUser.getId());
            adminRepository.save(Admin.fromDTO(user));
        }
        return user;
    }
}