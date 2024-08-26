let storedCustomerSupportId = document.getElementById('customerSupportId').value;
const employeeId = document.querySelector('.employeeId').value;

function highlightAndSubmit(row) {
    const rows = document.querySelectorAll('tbody tr');
    rows.forEach(r => r.classList.remove('table-active'));

    row.classList.add('table-active');
    const caseTaskId = row.querySelector('.caseTaskId').value;

    const submitLink = document.getElementById('submitLink');

    if (caseTaskId) {
        submitLink.href = '/customerSupportEmployees/showTask?caseTaskId=' + encodeURIComponent(caseTaskId);
    } else {
        console.error('No caseTaskId found.');
    }
}

function exitToHomePage() {
    if (storedCustomerSupportId) {
        window.location.href = '/customerSupportEmployees/home_page?employeeId=' + encodeURIComponent(storedCustomerSupportId);
    } else {
        console.error('No customerSupportId available.');
    }
}

function navigateBack() {
    if (employeeId) {
        window.location.href = '/customerSupportEmployees/caseItems?employeeId=' + encodeURIComponent(employeeId);
    } else {
        console.error('No caseItemId found.');
    }
}