var employees = "";

// Function to render employees in the table
function renderEmployeeList() {
    const tableBody = document.getElementById('employees');
    tableBody.innerHTML = '';

    employees.forEach((employee, index) => {
        const row = document.createElement('tr');
        
        row.innerHTML = `
            <td>${employee.firstName}</td>
            <td>${employee.lastName}</td>
            <td>${employee.email}</td>
            <td>
                <button onclick="viewEmployee(${index})">View</button>
                <button onclick="editEmployeeForm(${index})">Edit</button>
                <button onclick="deleteEmployee(${index})">Delete</button>
            </td>
        `;
        tableBody.appendChild(row);
    });
}

// Function to display employee details
function viewEmployee(index) {
    const employee = employees[index];
    alert(`First Name: ${employee.firstName}\nLast Name: ${employee.lastName}\nEmail: ${employee.email}`);
}

// Function to populate edit employee form
function editEmployeeForm(index) {
    const employee = employees[index];
    document.getElementById('firstName').value = employee.firstName;
    document.getElementById('lastName').value = employee.lastName;
    document.getElementById('email').value = employee.email;

    const submitButton = document.querySelector('#employeeForm button[type="submit"]');
    submitButton.textContent = 'Update Employee';
    submitButton.onclick = function(event) {
        event.preventDefault();
        const firstName = document.getElementById('firstName').value;
        const lastName = document.getElementById('lastName').value;
        const email = document.getElementById('email').value;
        editEmployee(index, firstName, lastName, email);
        submitButton.textContent = 'Add Employee';
        submitButton.onclick = function(event) {
            event.preventDefault();
            const firstName = document.getElementById('firstName').value;
            const lastName = document.getElementById('lastName').value;
            const email = document.getElementById('email').value;
            addEmployee(firstName, lastName, email);
        };
    };
}

// Function to fetch all employees from the server
async function fetchEmployees() {
    try {
        const username = 'user';
        const password = 'password';
        const token = btoa(`${username}:${password}`);

        const response = await axios.get('http://localhost:8080/employees', {
            headers: {
                'Authorization': `Basic ${token}`
            }
        });

        employees = response.data;
        renderEmployeeList();
    } catch (error) {
        console.error('Error fetching employees:', error);
    }
}
document.getElementById('employeeForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent the default form submission behavior

    // Retrieve input values
    const firstName = document.getElementById('firstName').value;
    const lastName = document.getElementById('lastName').value;
    const email = document.getElementById('email').value;

    // Call the addEmployee function
    addEmployee(firstName, lastName, email);
});

// Function to add employee
async function addEmployee(firstName, lastName, email) {
    try {
        const username = 'user';
        const password = 'password';
        const token = btoa(`${username}:${password}`);

        const response = await axios.post('http://localhost:8080/save', {
            firstName,
            lastName,
            email
        }, {
            headers: {
                'Authorization': `Basic ${token}`
            }
        });

        employees.push(response.data);
        renderEmployeeList();
    } catch (error) {
        console.error('Error adding employee:', error);
    }
}

// Function to edit employee
async function editEmployee(index, firstName, lastName, email) {
    try {
        const employee = employees[index];
        const empId = employee.empId; 
        const username = 'user';
        const password = 'password';
        const token = btoa(`${username}:${password}`);

        await axios.put('http://localhost:8080/update-employee', {
            empId,
            firstName,
            lastName,
            email
        }, {
            headers: {
                'Authorization': `Basic ${token}`
            }
        });

        // Update the employee object in the employees array
        employees[index].firstName = firstName;
        employees[index].lastName = lastName;
        employees[index].email = email;

        // Render the updated employee list
        renderEmployeeList();
    } catch (error) {
        console.error('Error editing employee:', error);
    }
}


// Function to delete employee
async function deleteEmployee(index) {
    const emp = employees[index];
    const id = emp.empId; 
    try {
        const username = 'user';
        const password = 'password';
        const token = btoa(`${username}:${password}`);

        const response = await axios.delete(`http://localhost:8080/delete-employee/${id}`, {
            headers: {
                'Authorization': `Basic ${token}`
            }
        });

        // Assuming the server responds with a success message or status
        // Update the UI only if the deletion was successful
        if (response.status === 200) {
            employees.splice(index, 1);
            renderEmployeeList();
        } else {
            console.error('Error deleting employee:', response.statusText);
        }
    } catch (error) {
        console.error('Error deleting employee:', error);
    }
}


// Fetch employees when the page loads
fetchEmployees();
