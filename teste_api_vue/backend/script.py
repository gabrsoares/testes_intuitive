from flask import Flask, request
from flask_cors import CORS
import pandas as pd

app = Flask(__name__)
CORS(app) # é necessário habilitar o CORS pois a API está em outro endereço


df = pd.read_csv('backend/files/Relatorio_cadop.csv', sep=';')

@app.route('/search', methods=['GET'])
def search():
    query = request.args.get('query', '')

    operadoras = df[df['Nome_Fantasia'].str.contains(query, case=False, na=False)].head(15) # filtra através do nome fantasia e retorna os 10 primeiros

    return operadoras.to_json(orient="records")

if __name__ == '__main__':
    app.run(debug=True)