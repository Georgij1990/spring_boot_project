package project.mass.project.dao;

import project.mass.project.entity.Division;

import java.util.List;

public interface DivisionDAO {
    void saveDivision(Division division);
    void updateDivision(Division division);
    void deleteDivision(int divisionId);
    Division getDivision(int id);
    List<Division> getDivisions();
}
