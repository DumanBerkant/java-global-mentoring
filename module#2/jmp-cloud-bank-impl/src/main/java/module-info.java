module jmp.cloud.bank.impl {
    requires transitive jmp.bank.api;
    requires jmp.dto;
    exports org.jmp.cloudbank;
    provides org.jmp.bank.Bank with org.jmp.cloudbank.CloudBankImpl;
}