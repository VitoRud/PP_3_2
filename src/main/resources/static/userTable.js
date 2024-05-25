const alluser = $('#allUsers')
getUsers()
function getUsers() {
    fetch("api/users")
        .then(res => res.json())
        .then(f => { f.forEach(user => {
            const roles = user.roles.map(role => role.name).join(',');
            const users = $(
                `<tr class="table-secondary">
                        <td>${user.id}</td>
                        <td>${user.name}</td>
                        <td>${user.email}</td>
                        <td>${roles}</td>
                        <td>
                            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#editModal" onclick="editModal(${user.id})">
                            Edit
                            </button>
                        </td>
                        <td>
                            <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteModal" onclick="deleteModal(${user.id})">
                                Delete
                            </button>
                        </td>
                    </tr>`
            );
            alluser.append(users);
        });
        })
}

