package project.mass.project.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        Person person1 = new Person(
                LocalDate.of(1980, 3, 17),
                "marcin.nowak@gmail.com",
                "Marcin",
                "Nowak",
                "123-456-789"
        );

        Person person2 = new Person(
                LocalDate.of(1991, 1, 8),
                "a.nickolson@gmail.com",
                "Andrew",
                "Nickolson",
                "123-456-789"
        );

        Person person3 = new Person(
                LocalDate.of(1998, 7, 22),
                "s.smith@gmail.com",
                "Sam",
                "Smith",
                "123-456-789"
        );

        Person person4 = new Person(
                LocalDate.of(2001, 10, 29),
                "s.richardson@gmail.com",
                "Sarah",
                "Richardson",
                "123-456-789"
        );
        CustomerSupport customerSupportEmployee1 = null;
        CustomerSupport customerSupportEmployee2 = null;
        CustomerSupport customerSupportEmployee3 = null;
        CustomerSupport customerSupportEmployee4 = null;

        customerSupportEmployee1 = new CustomerSupport(
                LocalDate.now().minusYears(5),
                ContractType.PERMANENT_CONTRACT,
                30000.0,
                person1,
                generateTrainingRecords(2)
        );

        customerSupportEmployee2 = new CustomerSupport(
                LocalDate.now().minusYears(5),
                ContractType.PERMANENT_CONTRACT,
                30000.0,
                person2,
                generateTrainingRecords(2)
        );
        customerSupportEmployee2.setMentor(customerSupportEmployee1);

        customerSupportEmployee3 = new CustomerSupport(
                LocalDate.now().minusYears(5),
                ContractType.PERMANENT_CONTRACT,
                30000.0,
                person3,
                generateTrainingRecords(2)
        );
        customerSupportEmployee3.setMentor(customerSupportEmployee1);

        customerSupportEmployee4 = new CustomerSupport(
                LocalDate.now().minusYears(5),
                ContractType.PERMANENT_CONTRACT,
                30000.0,
                person4,
                generateTrainingRecords(2)
        );
        customerSupportEmployee4.setMentor(customerSupportEmployee1);

        person1.setCustomerSupport(customerSupportEmployee1);
        this.personDAO.savePerson(person1);
        person2.setCustomerSupport(customerSupportEmployee2);
        this.personDAO.savePerson(person2);
        person3.setCustomerSupport(customerSupportEmployee3);
        this.personDAO.savePerson(person3);
        person4.setCustomerSupport(customerSupportEmployee4);
        this.personDAO.savePerson(person4);
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
    public Page<Case> findAllCasesByCustomerSupportId(int customerSupportId, Pageable pageable) {
        return this.caseDAO.findCasesByCustomerSupportId(customerSupportId, pageable);
    }

    @Override
    public List<Case> findAllCaseItems() {
        return this.caseDAO.findAllCaseItems();
    }

    @Override
    public Case findCaseItemByID(int caseItemId) {
        return this.caseDAO.findCaseById(caseItemId);
    }

    @Override
    public void createCaseTaskItems() {
        List<Case> caseItemList = this.findAllCaseItems();
        for (Case caseItem : caseItemList) {
            for (int i = 1; i <= 20; i++) {
                CaseTask caseTask = new CaseTask(
                        "Task " + i,
                        Status.NOT_STARTED,
                        Priority.NORMAL,
                        null,
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
    public void updateCaseTask(CaseTask caseTask) {
        caseTaskDAO.updateCaseTask(caseTask);
    }

    @Override
    public CaseTask findCaseTaskById(int caseTaskId) {
        return this.caseTaskDAO.findCaseTaskById(caseTaskId);
    }

    @Override
    public Page<CaseTask> findAllCaseTasksByCustomerSupportId(int customerSupportId, Pageable pageable) {
        return this.caseTaskDAO.findCaseTasksByCaseId(customerSupportId, pageable);
    }

    private static List<String> generateTrainingRecords(int i) {
        return IntStream.range(0, i % 5 + 1)
                .mapToObj(j -> "TrainingRecord" + j)
                .collect(Collectors.toList());
    }

    @PostConstruct
    public void init() {
        List<CustomerSupport> customerSupport = this.personDAO.findAllCustomerSupports();
        if (customerSupport.isEmpty()) {
            createCustomerSupportEmployees();
            createCaseItems();
            createCaseTaskItems();
        } else {
            Page<Case> caseList = this.caseDAO.findCasesByCustomerSupportId(customerSupport.get(0).getId(), PageRequest.of(0, 10));
            if (caseList.isEmpty()) {
                createCaseItems();
                createCaseTaskItems();
            }
        }
    }
}
