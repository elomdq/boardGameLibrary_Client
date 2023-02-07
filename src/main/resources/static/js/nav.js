const button = document.querySelector('#hamburger-menu');
const buttonContainer = document.querySelector('.nav-toogle-container');
const menu = document.querySelector('.ham-menu');
const menuCover = document.querySelector('.menu-cover');
const menuItems = menu.getElementsByTagName('ul')[0];
const span = button.getElementsByTagName('span')[0];

menuCover.style.width = menu.clientWidth + 'px';

window.addEventListener('DOMContentLoaded',()=>{

    button.onclick =  function() {
        span.classList.toggle('hamburger-menu-button-close');
    };
    button.addEventListener('click', toggleOnClass);
    translateMenu()
});

function toggleOnClass() {
    const toggleElementId = button.dataset.toggle;
    const elements = document.querySelectorAll('#'+toggleElementId);

    button.classList.toggle('open');
    elements.forEach(element=>element.classList.toggle('on'));

    translateButton();
    translateMenu();
}

function translateButton(){
    const menuContainer = document.querySelector('#ham-navigation');
    const withValue = menuContainer.clientWidth/2 + button.clientWidth/2;

    if(button.classList.contains('open')){
        button.style.transform = 'translateX('+ withValue + 'px)';
        button.style.transition = 'all 0.5s ease-in-out';

        menuCover.style.width = button.clientWidth+'px';
        menuCover.style.transform = 'translateX('+ withValue + 'px)';
        menuCover.style.transition = 'all 0.5s ease-in-out';

        return;
    }

    button.style.transform = 'none';
    button.style.transition = 'all 0.5s ease-in-out';

    menuCover.style.transform = 'none';
    menuCover.style.width = (menu.clientWidth + 5) + 'px';
    menuCover.style.transition = 'all 0.5s ease-in-out';
}

function translateMenu(){
    const withValue = button.clientWidth/2;

    if(menu.classList.contains('on')){
        menu.style.transform = 'translateX(-'+ withValue + 'px)';
        menuItems.style.transition = 'transform 0.5s ease-in-out';
        menu.style.overflow = 'visible';
        return;
    }
    menu.style.transform = 'none';
    menu.style.overflow = 'hidden';
    menu.style.transition = 'all 0.5s ease-in-out';
}

// close hamburger menu after click a
// $( '.menu li a' ).on("click", function(){
//   $('#hamburger-menu').click();
// });
