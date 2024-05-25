let form = document.forms["create"];
createNewUser()

function createNewUser() {
    form.addEventListener("submit", ev => {
        ev.preventDefault();
        let roles = [];
        for (let i = 0; i < form.roles.options.length; i++) {
            if (form.roles.options[i].selected) roles.push({
                id: form.roles.options[i].value,
                role: "ROLE_" + form.roles.options[i].text
            });
        }
        fetch("api/users", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                name: form.name.value,
                email: form.email.value,
                password: form.password.value,
                roles: roles
            })
        }).then(r => {
            form.reset()
            if(!r.ok) {
                alert("User with this username already exist!!")
            }else {
                $('#home-tab').click()
            }
        });
    });
}