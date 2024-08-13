package project.mass.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.mass.project.dao.CaseDAO;
import project.mass.project.dao.CaseTaskDAO;
import project.mass.project.dao.PersonDAO;
import project.mass.project.entity.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class CustomerSupportServiceImpl implements CustomerSupportService {

    private PersonDAO personDAO;
    private CaseDAO caseDAO;
    private CaseTaskDAO caseTaskDAO;

    @Autowired
    public CustomerSupportServiceImpl(PersonDAO personDAO, CaseDAO caseDAO, CaseTaskDAO caseTaskDAO) {
        this.personDAO = personDAO;
        this.caseDAO = caseDAO;
        this.caseTaskDAO = caseTaskDAO;
    }

    @Override
    public void createCustomerSupportEmployees() {
        for (int i = 1; i <= 20; i++) {
            Person person = new Person(
                    LocalDate.of(1980 + i, i % 12 + 1, i % 28 + 1),
                    "person" + i + "@example.com",
                    "FirstName" + i,
                    "LastName" + i,
                    "123-456-789" + i
            );

            CustomerSupport customerSupport = new CustomerSupport(
                    LocalDate.now().minusYears(5 + i % 10),
                    "ContractType" + i,
                    30000.0 + i * 1000,
                    person,
                    generateTrainingRecords(i)
            );

            person.setCustomerSupport(customerSupport);

            this.personDAO.savePerson(person);
        }
    }

    @Override
    public List<CustomerSupport> findAllCustomerSupportEmployees() {
        return this.personDAO.findAllCustomerSupports();
    }

    @Override
    public CustomerSupport findCustomerSupportEmployeeById(int id) {
        return this.personDAO.findCustomerSupportById(id);
    }

    @Override
    public void createCaseItems() {
        List<CustomerSupport> customerSupports = this.findAllCustomerSupportEmployees();
        for (CustomerSupport customerSupport : customerSupports) {
            for (int i = 0; i < 10; i++) {
                Case caseItem = new Case(
                        "Case Subject " + (i + 1),
                        CaseStatus.NEW,
                        LocalDate.now(),
                        null,
                        new ArrayList<>(),
                        customerSupport,
                        new ArrayList<>()
                );
                this.caseDAO.saveCase(caseItem);
            }
        }
    }

    @Override
    public List<Case> findAllCasesByCustomerSupportId(int customerSupportId) {
        return this.caseDAO.findCasesByCustomerSupportId(customerSupportId);
    }

    @Override
    public List<Case> findAllCaseItems() {
        return this.caseDAO.findAllCaseItems();
    }

    @Override
    public void createCaseTaskItems() {
        List<Case> caseItemList = this.findAllCaseItems();
        for (Case caseItem : caseItemList) {
            for (int i = 1; i <= 10; i++) {
                CaseTask caseTask = new CaseTask(
                        "Task " + i,
                        Status.NOT_STARTED,
                        Priority.NORMAL,
                        "",
                        caseItem
                );
                this.caseTaskDAO.saveCaseTask(caseTask);
            }
        }
    }

    @Override
    public void saveCaseTask(CaseTask caseTask) {
        this.caseTaskDAO.saveCaseTask(caseTask);
    }

    @Override
    public CaseTask findCaseTaskById(int caseTaskId) {
        return this.caseTaskDAO.findCaseTaskById(caseTaskId);
    }

    @Override
    public List<CaseTask> findAllCaseTasksByCustomerSupportId(int customerSupportId) {
        return this.caseTaskDAO.findCaseTasksByCaseId(customerSupportId);
    }

    private static List<String> generateTrainingRecords(int i) {
        return IntStream.range(0, i % 5 + 1)
                .mapToObj(j -> "TrainingRecord" + j)
                .collect(Collectors.toList());
    }
}
