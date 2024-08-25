package project.mass.project.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.mass.project.entity.Case;
import project.mass.project.entity.CaseTask;
import project.mass.project.entity.CustomerSupport;
import project.mass.project.service.CustomerSupportService;

import java.util.List;

@Controller
@RequestMapping("/customerSupportEmployees")
public class CustomerSupportController {

    private static final int PAGE_SIZE = 10;

    private CustomerSupportService customerSupportService;

    @Autowired
    public CustomerSupportController(CustomerSupportService customerSupportService) {
        this.customerSupportService = customerSupportService;
    }

    @GetMapping("/home_page")
    public String showHomePage(@RequestParam("employeeId") int theId, Model model) {
        CustomerSupport customerSupportEmployee = this.customerSupportService.findCustomerSupportEmployeeById(theId);
        model.addAttribute("customerSupportEmployee", customerSupportEmployee);
        return "customer-support-employees/home-page";
    }

    @GetMapping("/list")
    public String listCustomerSupportEmployees(Model model) {
        List<CustomerSupport> customerSupportList = customerSupportService.findAllCustomerSupportEmployees();
        model.addAttribute("customerSupportList", customerSupportList);
        return "customer-support-employees/list-employees";
    }

    @GetMapping("/caseItems")
    public String listCaseItems(@RequestParam("employeeId") int theId,
                                @RequestParam(value = "page", defaultValue = "0") int page,
                                Model model) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        Page<Case> casePage = customerSupportService.findAllCasesByCustomerSupportId(theId, pageable);
        setModelAttributesForPagination(page, casePage, model);
        model.addAttribute("employeeId", theId);
        model.addAttribute("caseItemList", casePage.getContent());
        return "customer-support-employees/list-case-items";
    }

    @GetMapping("/caseTasks")
    public String listCaseTasks(@RequestParam("caseItemId") int theId,
                                @RequestParam(value = "page", defaultValue = "0") int page,
                                Model model) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        Case caseItem = this.customerSupportService.findCaseItemByID(theId);
        Page<CaseTask> caseTaskList = this.customerSupportService.findAllCaseTasksByCustomerSupportId(theId, pageable);
        setModelAttributesForPagination(page, caseTaskList, model);
        model.addAttribute("caseTaskList", caseTaskList.getContent());
        model.addAttribute("caseItem", caseItem);
        return "customer-support-employees/list-task-items";
    }

    @GetMapping("/showTask")
    public String showCaseTask(@RequestParam("caseTaskId") int theId,  Model model) {
        CaseTask caseTask = this.customerSupportService.findCaseTaskById(theId);
        model.addAttribute("caseTask", caseTask);
        return "customer-support-employees/task-item";
    }

    @PostMapping("/caseTask/save")
    public String updateTask(
            @Valid @ModelAttribute("caseTask") CaseTask caseTaskItem,
            BindingResult bindingResult,
            @RequestParam("caseItemId") int id,
            @RequestParam("caseTaskId") int caseTaskId,
            Model model
            ) {
        Case caseItem2 = this.customerSupportService.findCaseItemByID(id);
        CaseTask caseTask = this.customerSupportService.findCaseTaskById(caseTaskId);
        caseTask.setCaseItem(caseItem2);
        caseTask.setName(caseTaskItem.getName());
        caseTask.setStatus(caseTaskItem.getStatus());
        caseTask.setPriority(caseTaskItem.getPriority());
        caseTask.setEditedStatusReason(caseTaskItem.getEditedStatusReason());
        this.customerSupportService.updateCaseTask(caseTaskItem, id);
        model.addAttribute("caseTask", caseTask);
        return "customer-support-employees/next-case-task";

    }

    public <T> void setModelAttributesForPagination(int page, Page<T> pageList, Model model) {
        int totalPages = pageList.getTotalPages();
        int currentPage = pageList.getNumber();
        int maxPagesToShow = 5;
        int startPage = Math.max(0, currentPage - (maxPagesToShow / 2));
        int endPage = Math.min(totalPages - 1, startPage + maxPagesToShow - 1);

        if (endPage - startPage < maxPagesToShow - 1) {
            startPage = Math.max(0, endPage - maxPagesToShow + 1);
        }

        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageList.getTotalPages());
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
    }
}
