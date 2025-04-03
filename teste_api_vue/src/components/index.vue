<template>
  <div class="main">
    <section class="pesquisa">
      <h1>Busca de operadoras</h1>
      <div class="container">
        <input v-model="query" type="text" placeholder="Insira o nome da empresa:">
        <button @click="search">Buscar</button>
      </div>
    </section>
    <section class="operadoras">
      <div v-for="(operadora, index) in operadoras" :key="index" class="operadora">
        <p>Nome fantasia: {{ operadora.Nome_Fantasia }}</p>
        <p>CNPJ: {{ operadora.CNPJ }}</p>
        <p>Registro ANS: {{ operadora.Registro_ANS}}</p>
        <button @click="toggleInfo(index)">Info</button>
      </div>
      <p v-if="buscou && operadoras.length === 0">Nenhum resultado encontrado.</p>
    </section>
    <div v-if="!esconderModal" class="overlay">
      <div class="info">
        <p>Razão Social: {{ modalInfo.Razao_Social }}</p>
        <p>Nome Fantasia: {{ modalInfo.Nome_Fantasia }}</p>
        <p>CNPJ: {{ modalInfo.CNPJ }}</p>
        <p>Registro ANS: {{ modalInfo.Registro_ANS }}</p>
        <p>Modalidade: {{ modalInfo.Modalidade }}</p>
        <p>Endereço: {{ modalInfo.Logradouro }}, {{ modalInfo.Numero }} - {{ modalInfo.Bairro }}</p>
        <p>Cidade: {{ modalInfo.Cidade }} - {{ modalInfo.UF }}</p>
        <p>CEP: {{ modalInfo.CEP }}</p>
        <p>Complemento: {{ modalInfo.Complemento }}</p>
        <p>Telefone: ({{ modalInfo.DDD }}) {{ modalInfo.Telefone }}</p>
        <p>Fax: {{ modalInfo.Fax }}</p>
        <p>Email: {{ modalInfo.Endereco_eletronico }}</p>
        <p>Representante: {{ modalInfo.Representante }} ({{ modalInfo.Cargo_Representante }})</p>
        <p>Data de Registro ANS: {{ modalInfo.Data_Registro_ANS }}</p>
        <p>Região de Comercialização: {{ modalInfo.Regiao_de_Comercializacao }}</p>
        <button @click="toggleInfo">Fechar</button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'TesteAPI',
  data(){
    return {
      query: '',
      operadoras: [],
      buscou: false,
      modalInfo:{},
      esconderModal: true
    };
  },
  methods: {
    async search(){
      if(this.query.trim() === ''){ //se o input estiver vazio, a busca será encerrada.
        this.operadoras = [];
        this.buscou = false;
        return
      }

      try{
        const response = await axios.get(`http://127.0.0.1:5000/search?query=${this.query}`, {
          responseType:'json'
        });
        console.log(response.data)
        this.operadoras = response.data
      } catch(error){
        console.error("Erro na busca: ", error);
      }
      this.buscou = true
    },
    toggleInfo(index){
      if(this.esconderModal){
        this.modalInfo = this.operadoras[index];
      } else {
        this.modalInfo = {}
      }
      this.esconderModal = !this.esconderModal
    }
  }
}
</script>

<style scoped>
.main {
  display: flex;
  flex-direction: column;
  gap: 3rem;
  align-items: center;
  justify-content: center;
}
input {
  width: 200px;
  padding: .2rem;
  height: 25px;
  border-radius: 5px;
  border: 1px solid #000;
  margin-right: 10px;
}
button {
  width: 75px;
  height: 30px;
  background-color: #555;
  border: 1px solid #000;
  border-radius: 5px;
  color: #fff;
  cursor: pointer
}

.operadora {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 15px;
}

.overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
}

.info {
  background: white;
  padding: 20px;
  border-radius: 10px;
  width: 600px;
  text-align: center;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
}

.hidden {
  display: none;
}

</style>
