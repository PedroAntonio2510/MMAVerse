package br.com.mmaverse.mapper;

import br.com.mmaverse.dto.OrganizationDTO;
import br.com.mmaverse.entity.Organization;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OrganizationMapper {

    public static Organization toOrganization(OrganizationDTO request) {
        return Organization.builder()
                .name(request.name())
                .cnpj(request.cnpj())
                .build();
    }
}
