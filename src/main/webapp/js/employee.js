// Modals voor medewerkers

const addModal = document.querySelector('#addModal');
const editModal = document.querySelector('#editModal');
const removeModal = document.querySelector('#removeModal');

const addModalBtn = document.querySelector('#addModalBtn');
const editModalBtn = document.querySelector('#editModalBtn');
const removeModalBtn = document.querySelector('#removeModalBtn');
const AddcloseBtn = document.querySelector('.closeAdd');
const EditcloseBtn = document.querySelector('.closeEdit');
const RemovecloseBtn = document.querySelector('.closeRemove');

addModalBtn.addEventListener('click', openModalAdd);
editModalBtn.addEventListener('click', openModalEdit);
removeModalBtn.addEventListener('click', openModalRemove);
AddcloseBtn.addEventListener('click', closeAddModal);
EditcloseBtn.addEventListener('click', closeEditModal);
RemovecloseBtn.addEventListener('click', closeRemoveModal);

function openModalAdd() {
  addModal.style.display = 'block';
}

function openModalEdit() {
    editModal.style.display = 'block';
  }

function openModalRemove() {
    removeModal.style.display = 'block';
}

function closeAddModal() {
    addModal.style.display = 'none';
}

function closeEditModal() {
    editModal.style.display = 'none';
}

function closeRemoveModal() {
    removeModal.style.display = 'none';
}