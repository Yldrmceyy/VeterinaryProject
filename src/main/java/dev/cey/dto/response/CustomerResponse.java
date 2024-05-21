package dev.cey.dto.response;

import lombok.Data;

@Data
public class CustomerResponse {
    private Long id;
    private String name;
    private String phone;
    private String mail;
    private String address;
    private String city;
}
