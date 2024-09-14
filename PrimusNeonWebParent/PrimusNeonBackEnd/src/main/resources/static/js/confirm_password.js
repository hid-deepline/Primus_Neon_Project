function checkPasswordMatch(input) {
        let password = document.querySelector('input[name="password"]').value;
        let confirmPassword = input.value;

        if (password !== confirmPassword) {
            input.setCustomValidity("Les mots de passe ne correspondent pas.");
        } else {
            input.setCustomValidity("");
        }
    }