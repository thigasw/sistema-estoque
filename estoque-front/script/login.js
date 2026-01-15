document.getElementById("loginForm").addEventListener("submit", function(event) {
    event.preventDefault();

    const user = document.getElementById("user").value;
    const password = document.getElementById("password").value;
    const error = document.getElementById("error");

    // EXEMPLO DE LOGIN FIXO (você pode substituir por validação no backend)
    if (user === "admin" && password === "1234") {
        window.location.href = "estoque.html"; // redireciona para o sistema
    } else {
        error.textContent = "Usuário ou senha incorretos!";
    }
});
