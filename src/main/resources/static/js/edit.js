// script.js

document.getElementById("#editBtn").addEventListener("click", function() {
    // Enable form fields for editing
    document.getElementById("memberName").disabled = false;
    document.getElementById("memberEmail").disabled = false;
    document.getElementById("memberPassword").disabled = false;
    document.getElementById("memberPhone").disabled = false;
    document.getElementById("memberRole").disabled = false;
    document.getElementById("memberSuspended").disabled = false;
    document.getElementById("memberImage").disabled = false;

    // Show save and cancel buttons
    document.getElementById("editBtn").style.display = "none";
    document.getElementById("cancelBtn").style.display = "inline-block";
    document.querySelector("#memberForm button[type='submit']").style.display = "inline-block";
});

document.getElementById("cancelBtn").addEventListener("click", function(e) {
    e.preventDefault();

    // Disable form fields and hide buttons
    document.getElementById("memberForm").reset();
    document.getElementById("memberName").disabled = true;
    document.getElementById("memberEmail").disabled = true;
    document.getElementById("memberPassword").disabled = true;
    document.getElementById("memberPhone").disabled = true;
    document.getElementById("memberRole").disabled = true;
    document.getElementById("memberSuspended").disabled = true;
    document.getElementById("memberImage").disabled = true;

    // Show edit button
    document.getElementById("editBtn").style.display = "inline-block";
    document.getElementById("cancelBtn").style.display = "none";
    document.querySelector("#memberForm button[type='submit']").style.display = "none";
});

document.getElementById("memberForm").addEventListener("submit", function(e) {
    e.preventDefault();

    // Perform form submission or AJAX request to save the modified data
    // You can access the updated values using the form element and its fields
    // For example, you can retrieve the updated name value as follows:
    const memberName = document.getElementById("memberName").value;

    // After saving the data, disable form fields and hide buttons
    document.getElementById("memberName").disabled = true;
    document.getElementById("memberEmail").disabled = true;
    document.getElementById("memberPassword").disabled = true;
    document.getElementById("memberPhone").disabled = true;
    document.getElementById("memberRole").disabled = true;
    document.getElementById("memberSuspended").disabled = true;
    document.getElementById("memberImage").disabled = true;

    // Show edit button
    document.getElementById("editBtn").style.display = "inline-block";
    document.getElementById("cancelBtn").style.display = "none";
    document.querySelector("#memberForm button[type='submit']").style.display = "none";
});
