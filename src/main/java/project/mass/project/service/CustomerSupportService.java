package project.mass.project.service;

import project.mass.project.entity.Case;
import project.mass.project.entity.CaseTask;
import project.mass.project.entity.CustomerSupport;

import java.util.List;

public interface CustomerSupportService {

    void createCustomerSupportEmployees();
    List<CustomerSupport> findAllCustomerSupportEmployees();
    CustomerSupport findCustomerSupportEmployeeById(int id);
    void createCaseItems();
    List<Case> findAllCasesByCustomerSupportId(int customerSupportId);
    List<Case> findAllCaseItems();
    Case findCaseItemByID(int caseItemId);
    void createCaseTaskItems();
    void saveCaseTask(CaseTask caseTask);
    void updateCaseTask(CaseTask caseTask, int id);
    CaseTask findCaseTaskById(int caseTaskId);
    List<CaseTask> findAllCaseTasksByCustomerSupportId(int customerSupportId);
}
