document.addEventListener('DOMContentLoaded', function() {
  // Update the active link in the sidebar
  function updateActiveLink() {
    const currentPage = window.location.pathname.split('/').pop();
    
    // Remove active class from all links
    document.querySelectorAll('.sidebar ul li a').forEach(link => {
      link.classList.remove('active');
    });
    
    // Add active class to current page link
    document.querySelectorAll('.sidebar ul li a').forEach(link => {
      if (link.getAttribute('href') === currentPage) {
        link.classList.add('active');
      }
    });
  }
  
  // Initialize
  updateActiveLink();
  
  // Enable code syntax highlighting if Prism.js is included
  if (typeof Prism !== 'undefined') {
    Prism.highlightAll();
  }
  
  // Handle keyboard navigation
  document.addEventListener('keydown', function(event) {
    // Get the next and previous links from the navigation buttons
    const prevLink = document.querySelector('.nav-button.prev');
    const nextLink = document.querySelector('.nav-button.next');
    
    // Left arrow key
    if (event.key === 'ArrowLeft' && prevLink) {
      window.location.href = prevLink.getAttribute('href');
    }
    
    // Right arrow key
    if (event.key === 'ArrowRight' && nextLink) {
      window.location.href = nextLink.getAttribute('href');
    }
  });
});