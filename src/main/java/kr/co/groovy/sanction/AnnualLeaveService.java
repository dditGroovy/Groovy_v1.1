package kr.co.groovy.sanction;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AnnualLeaveService {
    public void initiate(Map<String, Object> parameters) {
        String startDate = (String) parameters.get("startDate");
        String endDate = (String) parameters.get("endDate");
    }

    public void approve(Map<String, Object> parameters) {
        String approver = (String) parameters.get("approver");
    }

}
