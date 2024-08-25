package project.mass.project.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.mass.project.entity.Case;

import java.util.List;

public interface CaseDAO {

    void saveCase(Case c);
    Case findCaseById(int id);
    Page<Case> findCasesByCustomerSupportId(int id, Pageable pageable);
    List<Case> findAllCaseItems();
    void updateCase(Case caseItem);
    void deleteCase(int id);

}
