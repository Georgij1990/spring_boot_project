package project.mass.project.dao;

import project.mass.project.entity.Case;
import project.mass.project.entity.CaseTask;

import java.util.List;

public interface CaseTaskDAO {

    void createCaseTask(CaseTask caseTask, Case caseItem);
    CaseTask findCaseTaskById(int id);
    List<CaseTask> findCaseTasksByCaseId(int id);
    void updateCaseTask(CaseTask caseTask);
    void deleteCaseTask(int id);
}
