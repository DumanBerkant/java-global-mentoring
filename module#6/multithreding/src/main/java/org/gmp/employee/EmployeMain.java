package org.gmp.employee;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class EmployeMain {

    public  static void main(String[] args) throws ExecutionException, InterruptedException {
        //******************************************** EMPLOYEE SALARY ********************************************
        RestService service = new RestService();
        CompletableFuture<Map<String, String>> employeesStage = CompletableFuture.supplyAsync(() -> service.hiredEmployees())
                .thenApply(emps -> {
                    Map<String, String> filledList = new HashMap<>();
                    emps.keySet().forEach(empId -> {
                        try {
                            filledList.put(empId, employeSalary(service, empId));
                        } catch (ExecutionException | InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    return filledList;
                });

        Map<String, String> employees = employeesStage.get();
        employees.keySet().stream().forEach(key -> System.out.format("EmployeId: %s - Salary: %s\n", key, employees.get(key)));
    }

    public static String employeSalary(RestService service, String employeId) throws ExecutionException, InterruptedException {
        return CompletableFuture.supplyAsync(() -> service.getSalary(employeId)).get();
    }
}
