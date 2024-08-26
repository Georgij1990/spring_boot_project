let storedCustomerSupportId = document.getElementById('customerSupportId').value;
let storedCaseItemId = document.getElementById('caseItemId').value;
function exitToHomePage() {
    if (storedCustomerSupportId) {
        window.location.href = '/customerSupportEmployees/home_page?employeeId=' + encodeURIComponent(storedCustomerSupportId);
    } else {
        console.error('No customerSupportId available.');
    }
}
function goToTaskList() {
    if (storedCaseItemId) {
        window.location.href = '/customerSupportEmployees/caseTasks?caseItemId=' + encodeURIComponent(storedCaseItemId);
    } else {
        console.error('No caseItemId available.');
    }
}