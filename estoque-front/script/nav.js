document.getElementById('open_btn').addEventListener('click', function () {
    document.getElementById('sidebar').classList.toggle('open-sidebar');
});


document.getElementById("logout_btn").addEventListener("click", function () {
    // Redireciona para a página de login
    window.location.href = "login.html";
});