package project.mass.project.dao;

import project.mass.project.entity.Case;

import java.util.List;

public interface CaseDAO {

    void saveCase(Case c);
    Case findCaseById(int id);
    List<Case> findCasesByCustomerSupportId(int id);
    List<Case> findAllCaseItems();
    void updateCase(Case caseItem);
    void deleteCase(int id);

}
