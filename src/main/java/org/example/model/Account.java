package org.example.model;

import lombok.Builder;
import lombok.Data;
import org.example.enums.CreditCards;

@Data
@Builder
public class Account {
    private int id;
    private float amount;
    private CreditCards type;
}
