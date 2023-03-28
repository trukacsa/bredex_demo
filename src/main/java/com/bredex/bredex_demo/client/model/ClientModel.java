package com.bredex.bredex_demo.client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientModel {
    private String name;
    private String email;
    private UUID apiKey;
}
