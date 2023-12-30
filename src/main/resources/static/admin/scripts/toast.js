function toast({ title = '', message = '', type = 'info', duration = 3000 }) {
  const toast_main = document.getElementById('toast');
  if (toast_main) {
    const toast = document.createElement('div');
    const delay = (duration / 1000).toFixed(2);
    const icons = {
      info: 'bx bxs-info-circle',
      success: 'bx bxs-check-circle',
      error: 'bx bxs-error',
      warning: 'bx bxs-error',
    };

    const removeToast = setTimeout(() => {
      toast_main.removeChild(toast);
    }, duration + 1000);

    toast.onclick = function (e) {
      if (e.target.closest('.toast__close')) {
        toast_main.removeChild(toast);
        clearTimeout(removeToast);
      }
    };

    toast.classList.add('toast__outline', `toast--${type}`);
    toast.style.animation = `slideToRight ease 0.3s, fadeOutToTop linear 1s ${delay}s forwards`;
    toast.innerHTML = `
            <div class="toast__container">
                <div class="toast__icon">
                    <i class="${icons[type]}"></i>
                </div>
                <div class="toast__body">
                    <h3 class="toast__title">${title}</h3>
                    <p class="toast__content">${message}</p>
                </div>
                <div class="toast__close">
                  <i class='bx bx-x'></i>
                </div>
            </div>
        `;
    toast_main.appendChild(toast);
  }
}

export default toast;
