const customerSupportEmployeeId = document.querySelector('.employeeId').value;

function highlightAndSubmit(row) {
    const rows = document.querySelectorAll('tbody tr');
    rows.forEach(r => r.classList.remove('table-active'));

    row.classList.add('table-active');

    const caseItemId = row.querySelector('.caseItemId').value;

    const submitLink = document.getElementById('submitLink');
    if (caseItemId) {
        submitLink.href = '/customerSupportEmployees/caseTasks?caseItemId=' + encodeURIComponent(caseItemId);
    } else {
        console.error('No caseItemId found.');
    }
}

function navigateBack() {
    if (customerSupportEmployeeId) {
        window.location.href = '/customerSupportEmployees/home_page?employeeId=' + encodeURIComponent(customerSupportEmployeeId);
    } else {
        console.error('No caseItemId found.');
    }
}