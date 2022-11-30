module jmp.cloud.service.impl {
    requires transitive jmp.service.api;
    requires jmp.dto;
    exports org.jmp.cloudservice;
    provides org.jmp.service.Service with org.jmp.cloudservice.ServiceImpl;
}