package project.mass.project.service;

import project.mass.project.entity.Case;
import project.mass.project.entity.CaseTask;
import project.mass.project.entity.CustomerSupport;

import java.util.List;

public interface CustomerSupportService {

    void createCustomerSupportEmployees();
    List<CustomerSupport> findAllCustomerSupportEmployees();
    void createCaseItems();
    List<Case> findAllCasesByCustomerSupportId(int customerSupportId);
    List<Case> findAllCaseItems();
    void createCaseTaskItems();
    void saveCaseTask(CaseTask caseTask);
    CaseTask findCaseTaskById(int caseTaskId);
    List<CaseTask> findAllCaseTasksByCustomerSupportId(int customerSupportId);
}
