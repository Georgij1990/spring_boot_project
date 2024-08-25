package project.mass.project.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.mass.project.entity.Case;
import project.mass.project.entity.CaseTask;

import java.util.List;

public interface CaseTaskDAO {

    void saveCaseTask(CaseTask caseTask);
    CaseTask findCaseTaskById(int id);
    Page<CaseTask> findCaseTasksByCaseId(int id, Pageable pageable);
    void updateCaseTask(CaseTask caseTask);
    void deleteCaseTask(int id);
}
