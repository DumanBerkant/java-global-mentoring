import org.jmp.bank.Bank;
import org.jmp.service.Service;

module applicaton {
    uses Service;
    uses Bank;
    requires jmp.cloud.service.impl;
    requires jmp.cloud.bank.impl;
    requires jmp.dto;
}