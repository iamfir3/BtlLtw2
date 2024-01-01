const sideBarL = document.getElementById('sidebar');
window.addEventListener('resize', function () {
  const horizontalSize = window.innerWidth;
  if (horizontalSize <= 960) {
    sideBarL.classList.add('hide');
  } else {
    const sidebarClass = sideBarL.classList.value;
    if (sidebarClass.includes('hide')) {
      sideBarL.classList.remove('hide');
    }
  }
});
