-- 10 maiores despesas em eventos/sinistros... no ultimo trimestre
SELECT o.registro_ans, o.cnpj, o.razao_social, o.nome_fantasia, demo.descricao, SUM(demo.vl_saldo_final - demo.vl_saldo_inicial) as despesas
FROM operadoras o
INNER JOIN demonstracoes_contabeis demo ON o.registro_ans = demo.reg_ans
WHERE TRIM(demo.descricao) = 'EVENTOS/ SINISTROS CONHECIDOS OU AVISADOS  DE ASSISTÊNCIA A SAÚDE MEDICO HOSPITALAR'
AND demo.data BETWEEN '2024-10-01' AND '2024-12-31'
GROUP BY o.registro_ans, demo.descricao
ORDER BY despesas DESC
LIMIT 10;

-- 10 maiores despesas em eventos/sinistros... no ultimo ano
SELECT o.registro_ans, o.cnpj, o.razao_social, o.nome_fantasia, demo.descricao, SUM(demo.vl_saldo_final - demo.vl_saldo_inicial) as despesas
FROM operadoras o
INNER JOIN demonstracoes_contabeis demo ON o.registro_ans = demo.reg_ans
WHERE TRIM(demo.descricao) = 'EVENTOS/ SINISTROS CONHECIDOS OU AVISADOS  DE ASSISTÊNCIA A SAÚDE MEDICO HOSPITALAR'
AND demo.data BETWEEN '2024-01-01' AND '2024-12-31'
GROUP BY o.registro_ans, demo.descricao
ORDER BY despesas DESC
LIMIT 10;

