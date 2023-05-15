let navbar = document.querySelector('.header');

document.querySelector('#nav-close').onclick = () =>{
    navbar.classList.remove('active');
}

window.onscroll=()=>{
    if(window.scrollY>0){
        navbar.classList.add('active');
    }else{
        navbar.classList.remove('active');
    }
};
window.onload=()=>{
    if(window.scrollY>0){
        navbar.classList.add('active');
    }else{
        navbar.classList.remove('active');
    }
};
