async function getOneUser(id) {
    let response = await fetch("/api/user/" + id);
    return await response.json();
}

async function openAndFillInTheModal(form, modal, id) {
    modal.show();
    let user = await getOneUser(id);
    form.id.value = user.id;
    form.name.value = user.name;
    form.email.value = user.email;
    form.roles.value = user.roles.join(',');
}