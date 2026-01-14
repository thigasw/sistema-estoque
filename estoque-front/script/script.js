// ===== HERO LOAD ANIMATION =====
window.addEventListener("load", () => {
    const hero = document.querySelector(".hero-content");
    hero.style.opacity = "0";
    hero.style.transform = "translateY(40px)";

    setTimeout(() => {
        hero.style.transition = "all 1s ease";
        hero.style.opacity = "1";
        hero.style.transform = "translateY(0)";
    }, 200);
});

// ===== SCROLL REVEAL =====
function revealOnScroll() {
    const reveals = document.querySelectorAll(".reveal");
    const windowHeight = window.innerHeight;

    reveals.forEach(el => {
        const elementTop = el.getBoundingClientRect().top;
        const visiblePoint = 120;

        if (elementTop < windowHeight - visiblePoint) {
            el.classList.add("active");
        }
    });
}

window.addEventListener("scroll", revealOnScroll);
window.addEventListener("load", revealOnScroll);

// ===== CTA PULSE BUTTON =====
setInterval(() => {
    const btn = document.querySelector(".pulse");
    if (btn) {
        btn.classList.toggle("pulse-active");
    }
}, 2000);

// ===== FORM FEEDBACK =====
const form = document.querySelector("form");

form.addEventListener("submit", function (e) {
    e.preventDefault();

    const button = form.querySelector("button");
    button.innerText = "Enviado ✔";
    button.style.background = "#4caf50";

    setTimeout(() => {
        button.innerText = "Quero conhecer o SmartStock";
        button.style.background = "";
        form.reset();
    }, 3000);
});

