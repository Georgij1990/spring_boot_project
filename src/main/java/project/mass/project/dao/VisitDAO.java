package project.mass.project.dao;

import project.mass.project.entity.Visit;

import java.util.List;

public interface VisitDAO {
    void saveVisit(Visit visit);
    void deleteVisit(int visitId);
    void updateVisit(Visit visit);
    Visit getVisit(int visitId);
    List<Visit> getVisits();
}
