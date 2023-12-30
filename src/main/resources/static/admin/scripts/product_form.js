import Toast from './toast.js';

const url = new URL(window.location.href);
const queryParams = new URLSearchParams(url.search);
const actionParam = queryParams.get('action');
console.log(actionParam);

const formManage = document.getElementById('form-manage');
const categoryFormTitle = formManage.querySelector('#category-form .head h3');
const productFormTitle = formManage.querySelector('#product-form .head h3');

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
  case 'view-product':
    handleViewAction();
    break;
  case 'edit-product':
    handleEditAction();
    handleUploadImage();
  default:
    handleUploadImage();
    break;
}

function handleViewAction() {
  formManage.classList.add('view-product');
  categoryFormTitle.innerText = 'View Category';
  productFormTitle.innerText = 'View Product';

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

  const productImgaeView = document.querySelector(
    '#product-form .productImgaeView'
  );
  productImgaeView.style.borderColor = 'var(--dark)';
  Array.from(productImgaeView.children).forEach((child) => {
    child.style.color = 'var(--grey)';
  });
  const labelsProductImage = document.querySelectorAll(
    '#product-form label[for="productImage"]'
  );
  labelsProductImage.forEach((label) => {
    label.setAttribute('for', '');
  });
}

function handleEditAction() {
  formManage.classList.add('edit-product');
  categoryFormTitle.innerText = 'Edit Category';
  productFormTitle.innerText = 'Edit Product';
}

function handleUploadImage() {
  const productImageView = document.querySelector(
    '#product-form .productImgaeView'
  );
  const productImageInput = document.querySelector('input#productImage');

  productImageInput.addEventListener('change', uploadProductImage);

  function uploadProductImage(e) {
    const imageLink = URL.createObjectURL(productImageInput.files[0]);
    productImageView.style.backgroundImage = `url(${imageLink})`;
    productImageView.textContent = '';
  }

  productImageView.addEventListener('dragOver', (e) => {
    e.preventDefault();
  });

  productImageView.addEventListener('drop', (e) => {
    e.preventDefault();
    productImageInput.files = e.dataTransfer.files;
    uploadProductImage();
  });

  productImageView.addEventListener(
    'dragenter',
    (e) => {
      e.preventDefault();
      e.stopPropagation();
      productImageView.style.backgroundColor = 'rgba(60, 145, 230, 0.1)';
    },
    false
  );
  productImageView.addEventListener(
    'dragleave',
    (e) => {
      e.preventDefault();
      e.stopPropagation();
      productImageView.style.backgroundColor = 'unset';
    },
    false
  );
  productImageView.addEventListener(
    'dragover',
    (e) => {
      e.preventDefault();
      e.stopPropagation();
      productImageView.style.backgroundColor = 'rgba(60, 145, 230, 0.1)';
    },
    false
  );
  productImageView.addEventListener(
    'drop',
    (e) => {
      e.preventDefault();
      e.stopPropagation();
      productImageView.style.backgroundColor = 'unset';
      productImageInput.files = e.dataTransfer.files;
      uploadProductImage();
    },
    false
  );
}

// Submit form
const productFormSubmitBtns = document.querySelectorAll(
  '#product-form button.submit-btn'
);
const categoryFormSubmitBtns = document.querySelectorAll(
  '#category-form button.submit-btn'
);

productFormSubmitBtns.forEach((submitBtn) => {
  submitBtn.addEventListener('click', handleSubmitProductForm);
});

categoryFormSubmitBtns.forEach((submitBtn) => {
  submitBtn.addEventListener('click', handleSubmitCategoryForm);
});

function handleSubmitProductForm(e) {
  e.preventDefault();
  Toast({
    title: 'Thông tin',
    message: 'Đây là những thông tin tới bạn.',
    type: 'info',
    duration: 3000,
  });
  Toast({
    title: 'Lỗi',
    message: 'Nạp lần đầu đi bạn ơi.',
    type: 'error',
    duration: 3000,
  });
}

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
