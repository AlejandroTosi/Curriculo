CREATE TABLE recurso (
    id INTEGER PRIMARY KEY,
    nome TEXT NOT NULL,
    tipo TEXT NOT NULL
);


CREATE TABLE receita (
    id INTEGER PRIMARY KEY,
    saida INTEGER NOT NULL,
    produto1_id INTEGER,
    quantidade1 REAL,
    produto2_id INTEGER,
    quantidade2 REAL,
    produto3_id INTEGER,
    quantidade3 REAL,
    produto4_id INTEGER,
    quantidade4 REAL,
    tempo REAL NOT NULL,
    
    FOREIGN KEY (saida) REFERENCES recurso(id),
    FOREIGN KEY (produto1_id) REFERENCES recurso(id),
    FOREIGN KEY (produto2_id) REFERENCES recurso(id),
    FOREIGN KEY (produto3_id) REFERENCES recurso(id),
    FOREIGN KEY (produto4_id) REFERENCES recurso(id)
);

-- Tabela de construções
CREATE TABLE construcao (
    id INTEGER PRIMARY KEY,
    nome TEXT NOT NULL,
    ativas INTEGER,
    receita_id INTEGER NOT NULL,
    FOREIGN KEY (receita_id) REFERENCES receita(id)
    
);


CREATE TABLE construtora (
    id INTEGER PRIMARY KEY,
    quantidade INTEGER NOT NULL,
    producao_receita_id INTEGER,
    FOREIGN KEY(producao_receita_id) REFERENCES receita(id)
    
);


CREATE TABLE geradores (
    id INTEGER PRIMARY KEY,
    nome TEXT NOT NULL,
    combustivel TEXT NOT NULL,
    consumo REAL NOT NULL
);


CREATE TABLE pesquisas (
    id INTEGER PRIMARY KEY,
    nome TEXT NOT NULL,
    desbloqueia TEXT,
    pesquisa_vermelha INTEGER,
    pesquisa_verde INTEGER,
    pesquisa_azul INTEGER,
    pesquisa_amarela INTEGER,
    pesquisa_roxa INTEGER,
    pesquisa_cinza INTEGER
);


CREATE VIEW view_receitas AS
SELECT
    rec.id,
    r_saida.nome AS receita,
    r1.nome AS ingrediente1,
    rec.quantidade1,
    r2.nome AS ingrediente2,
    rec.quantidade2,
    r3.nome AS ingrediente3,
    rec.quantidade3,
    r4.nome AS ingrediente4,
    rec.quantidade4,
    rec.tempo
FROM receita AS rec
JOIN recurso AS r_saida ON rec.saida = r_saida.id
LEFT JOIN recurso AS r1 ON rec.produto1_id = r1.id
LEFT JOIN recurso AS r2 ON rec.produto2_id = r2.id
LEFT JOIN recurso AS r3 ON rec.produto3_id = r3.id
LEFT JOIN recurso AS r4 ON rec.produto4_id = r4.id;




CREATE VIEW view_pesquisas AS
SELECT
    id,
    nome AS pesquisa,
    desbloqueia,
    pesquisa_vermelha,
    pesquisa_verde,
    pesquisa_azul,
    pesquisa_amarela,
    pesquisa_roxa,
    pesquisa_cinza
FROM pesquisas;


CREATE VIEW view_construtoras AS
SELECT
cons.id,
cons.quantidade,
cons.producao_receita_id
FROM construtora AS cons
JOIN receita ON cons.producao_receita_id = receita.id;



INSERT INTO recurso (id, nome, tipo) VALUES
(1, 'minerio de ferro', 'bruto'),
(2, 'minerio de cobre', 'bruto'),
(3, 'pedra', 'bruto'),
(4, 'petroleo', 'bruto'),
(5, 'agua', 'bruto'),
(6, 'uranio', 'bruto'),
(7, 'carvao', 'bruto'),
(8, 'madeira', 'bruto'),
(10, 'chapa de ferro', 'refinado'),
(11, 'chapa de cobre', 'refinado'),
(12, 'barra de aço', 'refinado'),
(13, 'petroleo pesado', 'refinado'),
(14, 'petroleo leve', 'refinado'),
(15, 'gas de petroleo', 'refinado'),
(16, 'combustivel sólido', 'refinado'),
(22, 'lubrificante', 'refinado'),
(17, 'engrenagem', 'basico'),
(18, 'barra de ferro', 'basico'),
(19, 'fio de cobre', 'basico'),
(33, 'plastico', 'avançado'),
(34, 'enxofre', 'avançado'),
(35, 'bateria', 'avançado'),
(36, 'explosivo', 'avançado'),
(37, 'circuito verde', 'avançado'),
(38, 'circuito vermelho', 'avançado'),
(39, 'circuito azul', 'avançado'),
(40, 'motor', 'avançado'),
(41, 'motor elétrico', 'avançado'),
(42, 'chassi de drone', 'avançado'),
(23, 'bau de madeira', 'basico'),
(24, 'bau de ferro', 'basico'),
(25, 'bau de aço', 'basico'),
(26, 'esteira mk1', 'basico'),
(27, 'esteira mk2', 'basico'),
(28, 'esteira sub mk1', 'basico'),
(29, 'esteira sub mk2', 'basico'),
(45, 'esteira mk3', 'avançado'),
(46, 'esteira sub mk3', 'avançado'),
(56, 'spliter mk1', 'avançado'),
(57, 'spliter mk2', 'avançado'),
(58, 'spliter mk3', 'avançado'),
(30, 'inserter mk1', 'basico'),
(31, 'inserter mk2', 'basico'),
(59, 'cano', 'basico'),
(60, 'cano subterraneo', 'basico'),
(32, 'boiler', 'basico'),
(47, 'steam engine', 'avançado'),
(48, 'painel solar', 'avançado'),
(49, 'acumulador', 'avançado'),
(50, 'mineradora', 'avançado'),
(51, 'forja elétrica', 'avançado'),
(20, 'pesquisa vermelha', 'basico'),
(21, 'pesquisa verde', 'basico'),
(43, 'pesquisa azul', 'avançado'),
(44, 'pesquisa roxa', 'avançado'),
(55, 'pesquisa cinza', 'avançado'),
(52, 'construtora mk1', 'avançado'),
(53, 'construtora mk2', 'avançado'),
(54, 'construtora mk3', 'avançado'),
(9, 'sulfur', 'refinado');


INSERT INTO receita (id, saida, produto1_id, quantidade1, produto2_id, quantidade2,
                      produto3_id, quantidade3, produto4_id, quantidade4, tempo) 
VALUES
(1, 10, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL, 1.0),
(2, 11, 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, 1.0),
(3, 12, 10, 5, NULL, NULL, NULL, NULL, NULL, NULL, 3.5),
(4, 13, 4, 10, NULL, NULL, NULL, NULL, NULL, NULL, 0.5),
(5, 14, 4, 100, NULL, NULL, NULL, NULL, NULL, NULL, 0.5),
(6, 15, 4, 100, NULL, NULL, NULL, NULL, NULL, NULL, 1.0),
(7, 16, 15, 20, NULL, NULL, NULL, NULL, NULL, NULL, 2.0),
(8, 17, 10, 2, NULL, NULL, NULL, NULL, NULL, NULL, 0.5),
(9, 18, 10, 0.5, NULL, NULL, NULL, NULL, NULL, NULL, 3.0),
(10, 19, 11, 1, NULL, NULL, NULL, NULL, NULL, NULL, 3.0),
(11, 20, 11, 1, 17, 2, NULL, NULL, NULL, NULL, 1.0),
(12, 21, 30, 1, 26, 1, NULL, NULL, NULL, NULL, 1.0),
(13, 22, 13, 1, NULL, NULL, NULL, NULL, NULL, NULL, 1.0),
(14, 23, 8, 2, NULL, NULL, NULL, NULL, NULL, NULL, 1.5),
(15, 24, 10, 8, NULL, NULL, NULL, NULL, NULL, NULL, 1.5),
(16, 25, 12, 8, NULL, NULL, NULL, NULL, NULL, NULL, 1.5),
(17, 26, 10, 1, 17, 1, NULL, NULL, NULL, NULL, 2.0),
(18, 27, 26, 1, 17, 5, NULL, NULL, NULL, NULL, 1.0),
(19, 28, 19, 3, 10, 1, NULL, NULL, NULL, NULL, 1.0),
(21, 30, 37, 1, 17, 1, 10, 1, NULL, NULL, 0.5),
(22, 31, 37, 2, 30, 1, 10, 2, NULL, NULL, 0.5),
(23, 32, 59, 4, NULL, NULL, NULL, NULL, NULL, NULL, 0.5),
(24, 33, 7, 1, 15, 20, NULL, NULL, NULL, NULL, 1),
(25, 34, 15, 15, 5, 15, NULL, NULL, NULL, NULL, 0.5),
(26, 35, 11, 1, 10, 1, 9, 20, NULL, NULL, 4),
(27, 36, 7, 0.5, 34, 0.5, 5, 5, NULL, NULL, 2),
(28, 37, 19, 3, 10, 1, NULL, NULL, NULL, NULL, 0.5),
(29, 38, 19, 4, 37, 2, 33, 2, NULL, NULL, 6),
(30, 39, 38, 2, 27, 20, 9, 5, NULL, NULL, 10),
(31, 40, 17, 1, 59, 2, 12, 1, NULL, NULL, 10),
(32, 41, 37, 2, 40, 1, 22, 15, NULL, NULL, 10),
(33, 42, 35, 2, 41, 1, 37, 3, 12, 1, 20),
(34, 43, 38, 3, 40, 2, 34, 1, NULL, NULL, 24),
(35, 44, 51, 1, NULL, NULL, NULL, NULL, NULL, NULL, 1.0),
(36, 45, 27, 1, 17, 10, 22, 20, NULL, NULL, 1.0),
(37, 46, 29, 2, 17, 80, 22, 40, NULL, NULL, 1.0),
(38, 47, 17, 8, 10, 10, 59, 5, NULL, NULL, 0.5),
(39, 48, 11, 5, 37, 15, 12, 5, NULL, NULL, 10),
(40, 49, 35, 5, 10, 2, NULL, NULL, NULL, NULL, 1.0),
(41, 50, 37, 3, 17, 5, 10, 10, NULL, NULL, 1.0),
(42, 51, 38, 5, 12, 10, NULL, NULL, NULL, NULL, 1.0),
(43, 52, 37, 3, 17, 5, 10, 9, NULL, NULL, 1.0),
(44, 53, 52, 1, 37, 3, 17, 5, 12, 2, 1.0),
(45, 54, 53, 2, NULL, NULL, NULL, NULL, NULL, NULL, 1.0);


INSERT INTO pesquisas (
    id, nome, desbloqueia, 
    pesquisa_vermelha, pesquisa_verde, 
    pesquisa_azul, pesquisa_amarela, 
    pesquisa_roxa, pesquisa_cinza
)
VALUES
(1, 'automação', 'construtora mk1', 10, NULL, NULL, NULL, NULL, NULL),
(2, 'automação 2', 'construtora mk2', 40, 40, NULL, NULL, NULL, NULL),
(3, 'velocidade de laboratório 1', 'pesquisa +10%', 100, 100, NULL, NULL, NULL, NULL),
(4, 'pacote científico de produção', 'pesquisa roxa', 100, 100, 100, NULL, NULL, NULL),
(5, 'energia solar', 'painel solar', 250, 250, NULL, NULL, NULL, NULL),
(6, 'número de robôs seguidores', 'máximo de robôs +10', 200, 200, NULL, NULL, NULL, 200),
(7, 'motor elétrico', 'motor elétrico', 50, 50, 50, NULL, NULL, NULL),
(8, 'robótica', 'chassi de drone', 50, 50, 50, NULL, NULL, NULL),
(9, 'logística 3', 'esteira mk3, splitter mk3, esteira sub mk3', 50, 50, 50, NULL, NULL, NULL);
