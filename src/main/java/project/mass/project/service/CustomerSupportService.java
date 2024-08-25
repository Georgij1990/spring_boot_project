package project.mass.project.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.mass.project.entity.Case;
import project.mass.project.entity.CaseTask;
import project.mass.project.entity.CustomerSupport;

import java.util.List;

public interface CustomerSupportService {

    void createCustomerSupportEmployees();
    List<CustomerSupport> findAllCustomerSupportEmployees();
    CustomerSupport findCustomerSupportEmployeeById(int id);
    void createCaseItems();
    Page<Case> findAllCasesByCustomerSupportId(int customerSupportId, Pageable pageable);
    List<Case> findAllCaseItems();
    Case findCaseItemByID(int caseItemId);
    void createCaseTaskItems();
    void saveCaseTask(CaseTask caseTask);
    void updateCaseTask(CaseTask caseTask, int id);
    CaseTask findCaseTaskById(int caseTaskId);
    Page<CaseTask> findAllCaseTasksByCustomerSupportId(int customerSupportId, Pageable pageable);
}
