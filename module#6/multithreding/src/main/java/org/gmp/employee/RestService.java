package org.gmp.employee;

import java.util.HashMap;
import java.util.Map;

public class RestService {

    private Map<String, String> employees;
    private Map<String, String> salaries;


    public RestService(){
        this.employees = Map.of(
                "user1", "not-defined",
                "user2", "not-defined",
                "user4", "not-defined",
                "user5", "not-defined",
                "user6", "not-defined",
                "user7", "not-defined",
                "user8", "not-defined",
                "user9", "not-defined",
                "user10", "not-defined");

        this.salaries = Map.of(
                "user1", "1000$",
                "user2", "1200$",
                "user4", "1300$",
                "user5", "1400$",
                "user6", "1500$",
                "user7", "1600$",
                "user8", "1700$",
                "user9", "1800$",
                "user10", "1900$");
    }


    public Map<String, String> hiredEmployees(){
        return employees;
    }


    public String getSalary(String userId){
        return salaries.get(userId);
    }

}
