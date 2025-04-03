import pdfplumber
import pandas as pd

with pdfplumber.open("anexo_1.pdf") as pdf:
    table_rows = []
    columns = None

    print("Realizando alterações no arquivo...")

    for page in pdf.pages:
        tables = page.extract_tables() # extrai as tabelas da página (no caso das primeiras páginas, tables == None)
        for table in tables:
            if columns is None: 
                columns = table[0]# define a primeira linha como a coluna da tabela, é executado apenas uma vez quando o programa puxar a primeira tabela
            rows = table[1:]# lê a partir da segunda linha(index) por conta da primeira ser o nome da coluna
            table_rows.extend(rows) # adiciona as linhas na tabela principal

# altera as colunas 'OD' e 'AMB' conforme solicitado.
for index,column in enumerate(columns):
    if column == "OD":
        columns[index] = "Seg. Odontológica"
    if column == "AMB":
        columns[index] = "Seg. Ambulatorial"

df = pd.DataFrame(table_rows,columns=columns) # forma um dataframe com os dados do pdf

# converte para .csv e já compacta o arquivo em um .zip
compression_opts = dict(method='zip',
                        archive_name='anexo_1.csv')
df.to_csv('Teste_Gabriel_Alves_Soares.zip', index=False, compression=compression_opts,encoding='utf-8')

print("Arquivo compactado com sucesso.")