import axios from 'axios';

const apiUrl = "https://iatend-estoque-api-back.j7h6yg.easypanel.host/estoque";

// Definimos uma Classe para servir de modelo (padrão)
class Produto {
    constructor(data = {}) {
        this.id = data.id || 0;
        this.name = data.name || "";
        this.brand = data.brand || "";
        this.category = data.category || "";
        this.subcategory = data.subcategory || "";
        this.colorTone = data.colorTone || "";
        this.amount = data.amount || 0;
        this.price = data.price || 0;
        this.utility = data.utility || "";
        this.skinType = data.skinType || "";
        this.finish = data.finish || "";
        this.relatedProducts = data.relatedProducts || "";
        this.imageUrl = data.imageUrl || "";
        this.barCode = data.barCode || "";
    }
}

async function buscarEstoque() {
    try {
        const response = await axios.get(apiUrl);
        const listaProdutos = response.data.map(item => new Produto(item));
        
        console.log("Produtos recebidos com sucesso.");
        return listaProdutos;
    } catch (error) {
        console.error("Erro ao buscar produtos:", error);
    }
}

async function apagarProduto(id){
    try {
        const response = await axios.delete(apiUrl + `/produtos/${id}`);
        console.log("Produto apagado com sucesso: " + id);
        return response.data;
    } catch (error) {
        console.log("Erro ao apagar o produto: " + id, error);
    }
}

async function cadastrarProduto(produto){
    try {
        const {id, ...dadosProduto} = produto;
        const response = await axios.post(apiUrl + '/produtos', dadosProduto);
        console.log("Produto cadastrado com sucesso: " + produto.name);
        return new Produto(response.data);
    } catch (error) {
        console.log("Erro ao cadastrar o produto.", error);
    }
}

async function atualizarProduto(id,produto) {
    try {
        const response = await axios.put(apiUrl + `/produtos/${id}`, produto);
        console.log("Produto atualizado com sucesso: " + produto.name);
    } catch (error){
        console.log("Erro ao atualizar o produto: " + produto.name, error);
    }
}

async function cadastrarProdutosCsv(file) {
    try {
        const formData = new FormData();
        formData.append("file", file);

        const response = await axios.post(apiUrl + "/produtos/csv", formData);
        console.log("Produtos cadastrados com sucesso.");
        return response.data;
    } catch (error) {
        console.log("Erro ao cadastrar os produtos via CSV.", error);
    }
}

// --- Lógica de Interface (DOM) ---

const tabelaBody = document.querySelector('#tabela-produtos tbody');
const btnNovoProduto = document.getElementById('btn-novo-produto');
const btnUploadCsv = document.getElementById('btn-upload-csv');
const inputCsv = document.getElementById('input-csv');

// Função para renderizar a tabela no HTML
function renderizarTabela(listaProdutos) {
    tabelaBody.innerHTML = ''; // Limpa a tabela atual

    listaProdutos.forEach(produto => {
        const tr = document.createElement('tr');
        
        tr.innerHTML = `
            <td>${produto.id}</td>
            <td>${produto.name}</td>
            <td>${produto.brand}</td>
            <td>${produto.category}</td>
            <td>${produto.amount}</td>
            <td>R$ ${produto.price}</td>
        `;
        
        tabelaBody.appendChild(tr);
    });
}

// Inicialização: Busca dados e renderiza
async function init() {
    const produtos = await buscarEstoque();
    if (produtos) {
        renderizarTabela(produtos);
    }
}

// Evento: Botão Importar CSV (Clica no input hidden)
btnUploadCsv.addEventListener('click', () => {
    inputCsv.click();
});

// Evento: Quando um arquivo é selecionado
inputCsv.addEventListener('change', async (event) => {
    const file = event.target.files[0];
    if (file) {
        await cadastrarProdutosCsv(file);
        init(); // Recarrega a tabela após o upload
    }
});

// Inicia a aplicação
init();