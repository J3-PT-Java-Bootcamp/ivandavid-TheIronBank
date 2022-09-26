package com.ironhack.ivandavidtheironbank.user.dto;

import com.ironhack.ivandavidtheironbank.account.dto.AccountDTO;
import com.ironhack.ivandavidtheironbank.account.model.Account;
import com.ironhack.ivandavidtheironbank.user.model.Admin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminDTO extends UserDTO {

    private String name;
    private String keyckloakId;

    private String username;
    private String password;
    private String email;
    private String firstname;
    private String lastname;

    public static AdminDTO from(Admin admin) {
        var adminDTO = new AdminDTO();
        adminDTO.setName(admin.getName());
        adminDTO.setKeyckloakId(admin.getKeyckloakId());

        return adminDTO;
    }
}
