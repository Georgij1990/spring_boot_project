let storedCustomerSupportId = document.getElementById('customerSupportId').value;
function exitToHomePage() {
    if (storedCustomerSupportId) {
        window.location.href = '/customerSupportEmployees/home_page?employeeId=' + encodeURIComponent(storedCustomerSupportId);
    } else {
        console.error('No customerSupportId available.');
    }
}

function handleStatusChange() {
    const initialStatus = document.getElementById('initialStatus').value;
    const currentStatus = document.getElementById('status').value;
    const editedStatusReason = document.getElementById('editedStatusReason');

    if (initialStatus !== currentStatus) {
        editedStatusReason.required = true;
    } else {
        editedStatusReason.required = false;
        editedStatusReason.setCustomValidity("");
    }
}

function validateForm() {
    handleStatusChange();
    const editedStatusReason = document.getElementById('editedStatusReason');

    if (editedStatusReason.required && !editedStatusReason.value) {
        editedStatusReason.setCustomValidity("Please provide a reason for the status change.");
        editedStatusReason.classList.add("is-invalid");
        return false;
    } else {
        editedStatusReason.setCustomValidity("");
        editedStatusReason.classList.remove("is-invalid");
        return true;
    }
}