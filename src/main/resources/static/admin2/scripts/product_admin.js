const actionBtnList = document.querySelectorAll(
  '.table-data table .action .action-icon'
);
const actionLists = document.querySelectorAll(
  '.table-data table .action .action-list'
);

actionBtnList.forEach((actionBtn) => {
  actionBtn.addEventListener('click', (e) => {
    actionLists.forEach((actionList) => {
      actionList.style.display = 'none';
      e.stopPropagation();
    });
    const currentActionList = e.currentTarget.nextElementSibling;
    currentActionList.style.display = 'block';
  });
});

document.addEventListener('click', function (e) {
  actionLists.forEach((actionList) => {
    if (!actionList.contains(e.target)) {
      actionList.style.display = 'none';
    }
  });
});
