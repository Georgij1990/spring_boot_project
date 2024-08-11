package project.mass.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.mass.project.entity.Case;
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
}
