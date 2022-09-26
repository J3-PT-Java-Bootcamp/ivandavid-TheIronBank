package com.ironhack.ivandavidtheironbank.user.model;

import com.ironhack.ivandavidtheironbank.account.model.*;
import com.ironhack.ivandavidtheironbank.user.dto.AdminDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "admin")
public class Admin extends User {

    @Column(name = "keycloak_id", nullable = false)
    private String keyckloakId;

    public Admin(String name, String keyckloakId) {
        super(name);
        this.keyckloakId = keyckloakId;
    }

    public static Admin fromDTO(AdminDTO adminDTO) {
        var admin = new Admin();
        admin.setName(adminDTO.getName());
        admin.setKeyckloakId(adminDTO.getKeyckloakId());

        return admin;
    }
}
