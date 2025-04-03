-- script necessário para criação do ambiente de banco de dados.

create table operadoras (
	registro_ans VARCHAR(6) PRIMARY KEY,
	cnpj VARCHAR(14),
	razao_social VARCHAR(140),
	nome_fantasia VARCHAR(140),
	modalidade TEXT,
	logradouro VARCHAR(40),
	numero VARCHAR(20),
	complemento VARCHAR(40),
	bairro VARCHAR(30),
	cidade VARCHAR(30),
	uf VARCHAR(2),
	cep VARCHAR(8),
	ddd VARCHAR(4),
	telefone VARCHAR(20),
	fax VARCHAR(20),
	endereco_eletronico VARCHAR(255),
	representante VARCHAR(50),
	cargo_representante VARCHAR(40),
	regiao_de_comercializacao SMALLINT,
	data_registro_ans DATE
);

create table demonstracoes_contabeis (
	data DATE,
	reg_ans varchar(6),
	cd_conta_contabil INT,
	descricao VARCHAR(150),
	vl_saldo_inicial MONEY,
	vl_saldo_final MONEY
);

COPY operadoras
FROM 'C:\arquivos_bd\Relatorio_cadop.csv'
DELIMITER ';'
CSV HEADER
NULL '';

COPY demonstracoes_contabeis
FROM 'C:\arquivos_bd\1T2023.csv'
DELIMITER ';'
CSV HEADER
NULL '';

COPY demonstracoes_contabeis
FROM 'C:\arquivos_bd\2t2023.csv'
DELIMITER ';'
CSV HEADER
NULL '';

COPY demonstracoes_contabeis
FROM 'C:\arquivos_bd\3T2023.csv'
DELIMITER ';'
CSV HEADER
NULL '';

COPY demonstracoes_contabeis
FROM 'C:\arquivos_bd\4T2023.csv'
DELIMITER ';'
CSV HEADER
NULL '';

COPY demonstracoes_contabeis
FROM 'C:\arquivos_bd\1T2024.csv'
DELIMITER ';'
CSV HEADER
NULL '';

COPY demonstracoes_contabeis
FROM 'C:\arquivos_bd\2T2024.csv'
DELIMITER ';'
CSV HEADER
NULL '';

COPY demonstracoes_contabeis
FROM 'C:\arquivos_bd\3T2024.csv'
DELIMITER ';'
CSV HEADER
NULL '';

COPY demonstracoes_contabeis
FROM 'C:\arquivos_bd\4T2024.csv'
DELIMITER ';'
CSV HEADER
NULL '';