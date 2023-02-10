const form_new = document.getElementById('formForNewUser');


async function newUser() {
    form_new.addEventListener('submit', addNewUser);
    async function addNewUser(event) {
        event.preventDefault();
        const urlNew = 'api/admin/users/newAddUser';

        let checkedRoles = () => {
            let array = []
            let options = document.querySelector('#rolesCreate').options
            for (let i = 0; i < options.length; i++) {
                if (options[i].selected) {
                    array.push(roleList[i])
                }
            }
            return array;
        }
        let method = {
            method: 'POST',
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                name: form_new.name.value,
                lastname: form_new.lastname.value,
                age: form_new.age.value,
                email: form_new.email.value,
                password: form_new.password.value,
                roles: checkedRoles()
            })
        }
        await fetch(urlNew,method).then(() => {
            form_new.reset();
            getAdminPage();
        });
    }
}
