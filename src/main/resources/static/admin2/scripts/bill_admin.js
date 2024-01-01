const filterDateForm = document.querySelector(
  '.table-data .order form.date-filter-form'
);
const submitFilterDateFormBtn = filterDateForm.querySelector(
  '.form-group button.btn.submit-btn'
);
const endDateInput = filterDateForm.querySelector(
  'form.date-filter-form input#end-date'
);

const today = new Date();
const todayISO = today.toISOString().split('T')[0];
endDateInput.value = todayISO;
