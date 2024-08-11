package project.mass.project.service;

import project.mass.project.entity.Case;
import project.mass.project.entity.CustomerSupport;

import java.util.List;

public interface CustomerSupportService {

    void createCustomerSupportEmployees();
    List<CustomerSupport> findAllCustomerSupportEmployees();
    void createCaseItems();
    List<Case> findAllCasesByCustomerSupportId(int customerSupportId);
    void createCaseTaskItems();
}
