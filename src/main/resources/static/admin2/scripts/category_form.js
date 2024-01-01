import Toast from './toast.js';

const url = new URL(window.location.href);
const queryParams = new URLSearchParams(url.search);
const actionParam = queryParams.get('action');
console.log(actionParam);

const formManage = document.getElementById('form-manage');
const categoryFormTitle = formManage.querySelector('#category-form .head h3');
const productTableTitle = formManage.querySelector(
  '.table-data .table-product .head h3'
);
const categoryFormSubmitBtns = formManage.querySelectorAll(
  '#category-form .btn.submit-btn'
);

// const formBackBtns = document.querySelectorAll('#form-manage form .back-btn');
// formBackBtns.forEach((backBtn) => {
//   backBtn.onclick = (e) => {
//     e.preventDefault();
//     if (e.target.closest('.btn-label')) {
//       const url = e.target.getAttribute('href');
//       console.log(url);
//       window.location.pathname = url;
//     }
//   };
// });

switch (actionParam) {
  case 'add-category':
    handleAddAction();
    break;
  case 'edit-category':
    handleEditAction();
    break;
  case 'view-category':
    handleViewAction();
  default:
    break;
}

// Action handler
function handleAddAction() {
  formManage.classList.add('add-category');
  categoryFormSubmitBtns.forEach((submitBtn) => {
    submitBtn.addEventListener('click', handleSubmitCategoryForm);
  });
}

function handleEditAction() {
  formManage.classList.add('edit-category');
  categoryFormTitle.innerText = 'Edit Category';
  categoryFormSubmitBtns.forEach((submitBtn) => {
    submitBtn.addEventListener('click', handleSubmitCategoryForm);
  });
}

function handleViewAction() {
  formManage.classList.add('view-category');
  categoryFormSubmitBtns.forEach((submitBtn) => {
    submitBtn.addEventListener('click', handleSubmitCategoryForm);
  });

  categoryFormTitle.innerText = 'View Category';
  productTableTitle.innerText = 'View Product';

  const inputs = document.querySelectorAll(
    '#form-manage input, #form-manage textarea,  #form-manage select'
  );
  const selects = document.querySelectorAll('#form-manage select');
  inputs.forEach((input) => {
    input.onchange = null;
    input.onclick = null;
    input.setAttribute('readonly', true);
    input.style.backgroundColor = 'var(--grey)';
  });

  selects.forEach((select) => {
    Array.from(select.children).forEach((selectChildren) => {
      selectChildren.setAttribute('disabled', true);
    });
  });
}

// Submit Form
function handleSubmitCategoryForm(e) {
  e.preventDefault();
  Toast({
    title: 'Thành công',
    message: 'Đúng rồi. "Đầu tiên" là thứ quan trọng!',
    type: 'success',
    duration: 3000,
  });
  Toast({
    title: 'Cảnh báo',
    message: 'Đây là những thông tin tới bạn.',
    type: 'warning',
    duration: 3000,
  });
}
