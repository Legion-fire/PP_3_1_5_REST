const form_ed = document.getElementById('formForEditing');
const id_ed = document.getElementById('id_ed');
const name_ed = document.getElementById('name_ed');
const lastname_ed = document.getElementById('lastname_ed');
const age_ed = document.getElementById('age_ed');
const email_ed = document.getElementById('email_ed');
const password_ed = document.getElementById('password_ed');

async function editModalData(id) {
    $('#editModal').modal('show');
    const  urlDataEd = 'api/admin/users/' + id;
    let usersPageEd = await fetch(urlDataEd);
    if (usersPageEd.ok) {
        let userData =
            await usersPageEd.json().then(user => {
                id_ed.value = `${user.id}`;
                name_ed.value = `${user.name}`;
                lastname_ed.value = `${user.lastname}`;
                age_ed.value = `${user.age}`;
                email_ed.value = `${user.email}`;
                password_ed.value = `${user.password}`;
            })
    } else {
        alert(`Error, ${usersPageEd.status}`)
    }
}
async function editUser() {
    let urlEdit = 'api/admin/users/' + id_ed.value;
    let checkedRoles = () => {
        let array = []
        let options = document.querySelector('#rolesEdit').options
        for (let i = 0; i < options.length; i++) {
            if (options[i].selected) {
                array.push(roleList[i])
            }
        }
        return array;
    }
    let method = {
        method: 'PATCH',
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            name: form_ed.name.value,
            lastname: form_ed.lastname.value,
            age: form_ed.age.value,
            email: form_ed.email.value,
            password: form_ed.password.value,
            roles: checkedRoles()
        })
    }
    await fetch(urlEdit,method).then(() => {
        $('#editCloseBtn').click();
        getAdminPage();
    })
}
