package project.mass.project.dao;

import project.mass.project.entity.Case;

import java.util.List;

public interface CaseDAO {

    void createCase(Case c);
    Case findCaseById(int id);
    List<Case> findCasesByCustomerSupportId(int id);
    void updateCase(Case caseItem);
    void deleteCase(int id);

}
