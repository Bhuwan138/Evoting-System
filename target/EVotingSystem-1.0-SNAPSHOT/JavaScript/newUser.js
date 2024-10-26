function validateAndSubmitForm() {
  console.log("From form validation");
  // Get form values
  const fullName = document.getElementById('fullName').value;
  const ctznNo = document.getElementById('ctznno').value;
  const email = document.getElementById('email').value;
  const password = document.getElementById('inputPassword4').value;
  const rePassword = document.getElementById('inputrePassword4').value;
  const address = document.getElementById('inputAddress').value;
  const city = document.getElementById('inputCity').value;
  const phone = document.getElementById('phone').value;

  // Define regular expressions for validation
  const nameRegex = /^[A-Za-z\s]{3,}$/;
//  const ctznNoRegex = /^\d{10}$/;
  const emailRegex = /^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]{2,7}$/;
  const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;

  // Perform validation
  if (!nameRegex.test(fullName)) {
    sweetAlert('Error', 'Full Name must be at least 3 characters long and contain only letters and spaces.', 'error');
    return false;
  }

//  if (!ctznNoRegex.test(ctznNo)) {
//    sweetAlert('Error', 'Citizenship Number must be exactly 10 digits.', 'error');
//    return false;
//  }

  if (!emailRegex.test(email)) {
    sweetAlert('Error', 'Invalid email address.', 'error');
    return false;
  }

  if (!passwordRegex.test(password)) {
    sweetAlert('Error', 'Password must be at least 8 characters long and contain at least one lowercase letter, one uppercase letter, one digit, and one special character.', 'error');
    return false;
  }

  if (password !== rePassword) {
    sweetAlert('Error', 'Passwords do not match.', 'error');
    return false;
  }

  if (phone.length !== 10) {
    sweetAlert('Error', 'Phone number must be exactly 10 digits.', 'error');
    return false;
  }

  // If all validations pass, submit the form
  return true;
}

