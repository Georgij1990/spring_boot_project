package project.mass.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.mass.project.entity.Case;
import project.mass.project.entity.CaseTask;
import project.mass.project.entity.CustomerSupport;
import project.mass.project.service.CustomerSupportService;

import java.util.List;

@Controller
@RequestMapping("/customerSupportEmployees")
public class CustomerSupportController {

    private CustomerSupportService customerSupportService;

    @Autowired
    public CustomerSupportController(CustomerSupportService customerSupportService) {
        this.customerSupportService = customerSupportService;
    }

    @GetMapping("/list")
    public String listCustomerSupportEmployees(Model model) {
//        customerSupportService.createCustomerSupportEmployees();
//        this.customerSupportService.createCaseItems();
//        this.customerSupportService.createCaseTaskItems();
        List<CustomerSupport> customerSupportList = customerSupportService.findAllCustomerSupportEmployees();
        model.addAttribute("customerSupportList", customerSupportList);
        return "customer-support-employees/list-employees";
    }

    @GetMapping("/caseItems")
    public String listCaseItems(@RequestParam("employeeId") int theId,  Model model) {
        List<Case> caseItemList = this.customerSupportService.findAllCasesByCustomerSupportId(theId);
        model.addAttribute("caseItemList", caseItemList);
        return "customer-support-employees/list-case-items";
    }

    @GetMapping("/caseTasks")
    public String listCaseTasks(@RequestParam("caseItemId") int theId,  Model model) {
        List<CaseTask> caseTaskList = this.customerSupportService.findAllCaseTasksByCustomerSupportId(theId);
        model.addAttribute("caseTaskList", caseTaskList);
        return "customer-support-employees/list-task-items";
    }

    @GetMapping("/showTask")
    public String showCaseTask(@RequestParam("caseTaskId") int theId,  Model model) {
        CaseTask caseTask = this.customerSupportService.findCaseTaskById(theId);
        model.addAttribute("caseTask", caseTask);
        return "customer-support-employees/task-item";
    }

    @PostMapping("/caseTask/save")
    public String saveCaseTask(@ModelAttribute("caseTaskItem") CaseTask caseTask) {
        this.customerSupportService.saveCaseTask(caseTask);
        return "customer-support-employees/list-task-items";
    }
}
