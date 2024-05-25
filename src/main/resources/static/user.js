const url = 'http://localhost:8080/api/user'
;

function getUser() {
    fetch(url)
        .then(res => res.json())
        .then(user => {
            const roles = user.roles.map(role => role.name).join(', ')
            const usersTable= `$(
                    <tr class="table-secondary">
                        <td>${user.id}</td>
                        <td>${user.name}</td>
                        <td>${user.email}</td>
                        <td>${roles}</td>
                    </tr>)`;
            $('#userAuthName').append(`<span>${user.name}</span>`)
            $('#userAuthRole').append(`<span>${roles}</span>`)
            $('#userTable').append(usersTable)
        })
}

getUser()