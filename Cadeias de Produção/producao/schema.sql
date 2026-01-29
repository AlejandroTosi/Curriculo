-- Table: recurso
-- -----------------------------
CREATE TABLE recurso (
    idrecurso INTEGER NOT NULL,
    nome TEXT NOT NULL,
    PRIMARY KEY (idrecurso)
);

-- Table: receita
-- -----------------------------
CREATE TABLE receita (
    idreceita INTEGER NOT NULL,
    saida INTEGER NOT NULL,
    tempo REAL NOT NULL,
    PRIMARY KEY (idreceita),
    FOREIGN KEY (saida)
        REFERENCES recurso (idrecurso)
);


-- Table: maquinas
-- -----------------------------
CREATE TABLE maquinas (
    idmaquinas INTEGER NOT NULL,
    nome TEXT NOT NULL,
    energia REAL NOT NULL,
    modificador_tempo REAL,
    PRIMARY KEY (idmaquinas)
);


-- Table: maquinario
-- -----------------------------
CREATE TABLE maquinario (
    idmaquinario INTEGER NOT NULL,
    maquinario_tipo INTEGER NOT NULL,
    maquinario_receita INTEGER NOT NULL,
    maquinario_quantidade INTEGER NOT NULL,
    PRIMARY KEY (idmaquinario),
    FOREIGN KEY (maquinario_tipo)
        REFERENCES maquinas (idmaquinas),
    FOREIGN KEY (maquinario_receita)
        REFERENCES receita (idreceita)
);


-- Table: saida_desejada
-- -----------------------------
CREATE TABLE saida_desejada (
    idsaida_desejada INTEGER NOT NULL,
    saida_desejada INTEGER NOT NULL,
    quantidade_desejada REAL NOT NULL,
    PRIMARY KEY (idsaida_desejada),
    FOREIGN KEY (saida_desejada)
        REFERENCES receita (idreceita)
);


-- Table: custos
-- -----------------------------
CREATE TABLE custos (
    idreceita INTEGER NOT NULL,
    idrecurso INTEGER NOT NULL,
    quantidade REAL NOT NULL,
    PRIMARY KEY (idreceita, idrecurso),
    FOREIGN KEY (idreceita)
        REFERENCES receita (idreceita),
    FOREIGN KEY (idrecurso)
        REFERENCES recurso (idrecurso)
);



-- -----------------------------
-- View: custo manufaturado
-- -----------------------------
CREATE VIEW custo_receita_faturada AS
SELECT
    ra.idreceita,
    ra.saida        AS recurso_produzido,
    cs.idrecurso    AS recurso_consumido,
    ro.nome         AS nome_recurso,
    cs.quantidade
FROM receita ra
JOIN custos cs  ON cs.idreceita = ra.idreceita
JOIN recurso ro ON ro.idrecurso = cs.idrecurso;


-- -----------------------------
-- View: receitas que usam X recurso
-- -----------------------------
CREATE VIEW receitas_que_usam_recurso AS
SELECT
    ra.idreceita,
    ro_saida.nome  AS produto,
    ro_custo.nome  AS insumo,
    cs.quantidade,
    ra.tempo
FROM receita ra
JOIN custos cs       ON cs.idreceita = ra.idreceita
JOIN recurso ro_custo ON ro_custo.idrecurso = cs.idrecurso
JOIN recurso ro_saida ON ro_saida.idrecurso = ra.saida;

-- -----------------------------
-- View: saida atual
-- -----------------------------
CREATE VIEW saida_atual AS
SELECT
    r.idreceita,
    r_saida.nome AS produto,
    SUM(
        (m.maquinario_quantidade)
        / (r.tempo * COALESCE(ma.modificador_tempo, 1))
    ) AS producao_por_tempo -- tempo = ingame
FROM maquinario m
JOIN receita r ON r.idreceita = m.maquinario_receita
JOIN maquinas ma ON ma.idmaquinas = m.maquinario_tipo
JOIN recurso r_saida ON r_saida.idrecurso = r.saida
GROUP BY r.idreceita, r_saida.nome;

-- -----------------------------
-- View: saida atual vs saida desejada
-- -----------------------------
CREATE VIEW saida_atual_vs_saida_desejada AS
SELECT
    r_saida.nome AS produto,
    sd.quantidade_desejada,
    sa.producao_por_tempo AS producao_atual,
    ROUND(
        (sa.producao_por_tempo / sd.quantidade_desejada) * 100,
        2
    ) AS percentual
FROM saida_desejada sd
JOIN receita r ON r.idreceita = sd.saida_desejada
JOIN recurso r_saida ON r_saida.idrecurso = r.saida
JOIN saida_atual sa ON sa.idreceita = r.idreceita;



-- -----------------------------
-- Inserts manuais
-- -----------------------------
INSERT INTO recurso (idrecurso, nome) VALUES
(1,  'bau de madeira'),
(2,  'bau de ferro'),
(3,  'bau de aço'),
(4,  'reservatório'),
(5,  'esteira 1'),
(6,  'esteira 2'),
(7,  'esteira 3'),
(8,  'esteira 4'),
(9,  'sub esteira 1'),
(10, 'sub esteira 2'),
(11, 'sub esteira 3'),
(12, 'sub esteira 4'),
(13, 'splitter 1'),
(14, 'splitter 2'),
(15, 'splitter 3'),
(16, 'splitter 4'),
(17, 'inserter carvão preto'),
(18, 'inserter amarelo'),
(19, 'inserter vermelho'),
(20, 'inserter azul'),
(21, 'inserter verde'),
(22, 'poste de madeira'),
(23, 'poste de metal'),
(24, 'grande poste'),
(25, 'sub estação'),
(26, 'cano'),
(27, 'sub cano'),
(28, 'bomba de pressão'),
(29, 'trilho'),
(30, 'drone logístico'),
(31, 'drone de construção'),
(32, 'caldeira'),
(33, 'motor a vapor'),
(34, 'painel solar'),
(35, 'bateria grande'),
(36, 'broca a combustível'),
(37, 'broca elétrica'),
(38, 'bomba de água'),
(39, 'fornalha de pedra'),
(40, 'fornalha de aço'),
(41, 'fornalha elétrica'),
(42, 'montadora 1'),
(43, 'montadora 2'),
(44, 'montadora 3'),
(45, 'refinaria'),
(46, 'planta química'),
(47, 'laboratório'),
(48, 'módulo de velocidade 1'),
(49, 'módulo de eficiência 1'),
(50, 'módulo de produtividade 1'),
(51, 'água'),
(52, 'vapor'),
(53, 'petróleo cru'),
(54, 'petróleo pesado'),
(55, 'petróleo leve'),
(56, 'lubrificante'),
(57, 'petróleo gasoso'),
(58, 'ácido sulfúrico'),
(59, 'madeira'),
(60, 'carvão'),
(61, 'pedra bruta'),
(62, 'ferro bruto'),
(63, 'cobre bruto'),
(64, 'placa de ferro'),
(65, 'placa de cobre'),
(66, 'placa de aço'),
(67, 'combustível sólido'),
(68, 'plástico'),
(69, 'enxofre'),
(70, 'bateria pequena'),
(71, 'explosivo'),
(72, 'engrenagem'),
(73, 'barra de ferro'),
(74, 'fio de cobre'),
(75, 'circuito verde'),
(76, 'circuito vermelho'),
(77, 'circuito azul'),
(78, 'motor'),
(79, 'motor elétrico'),
(80, 'chassi de drone'),
(81, 'estrutura de baixa densidade'),
(82, 'ciência vermelha'),
(83, 'ciência verde'),
(84, 'ciência preta'),
(85, 'ciência azul'),
(86, 'ciência roxa'),
(87, 'ciência amarela'),


INSERT INTO receita (idreceita, saida, tempo) VALUES
(1,  1,  0.5),  -- bau de madeira
(2,  2,  0.5),  -- bau de ferro
(3,  3,  0.5),  -- bau de aço
(4,  4,  3),    -- reservatório
(5,  5,  0.5),  -- esteira 1
(6,  6,  0.5),  -- esteira 2
(7,  7,  0.5),  -- esteira 3
(8,  8,  0.5),  -- esteira 4
(9,  9,  1),    -- sub esteira 1
(10, 10, 2),    -- sub esteira 2
(11, 11, 2),    -- sub esteira 3
(12, 12, 2),    -- sub esteira 4
(13, 13, 1),    -- splitter 1
(14, 14, 2),    -- splitter 2
(15, 15, 2),    -- splitter 3
(16, 16, 2),    -- splitter 4
(17, 17, 0.5),  -- inserter carvão preto
(18, 18, 0.5),  -- inserter amarelo
(19, 19, 0.5),  -- inserter vermelho
(20, 20, 0.5),  -- inserter azul
(21, 21, 0.5),  -- inserter verde
(22, 22, 0.5),  -- poste de madeira
(23, 23, 0.5),  -- poste de metal
(24, 24, 0.5),  -- grande poste
(25, 25, 0.5),  -- sub estação
(26, 26, 0.5),  -- cano
(27, 27, 0.5),  -- sub cano
(28, 28, 2),    -- bomba de pressão
(29, 29, 0.5),  -- trilho
(30, 30, 0.5),  -- drone logístico
(31, 31, 0.5),  -- drone de construção
(32, 32, 0.5),  -- caldeira
(33, 33, 0.5),  -- motor a vapor
(34, 34, 10),   -- painel solar
(35, 35, 10),   -- bateria grande
(36, 36, 2),    -- broca a combustível
(37, 37, 2),    -- broca elétrica
(38, 38, 0.5),  -- bomba de água
(39, 39, 0.5),  -- fornalha de pedra
(40, 40, 3),    -- fornalha de aço
(41, 41, 5),    -- fornalha elétrica
(42, 42, 0.5),  -- montadora 1
(43, 43, 0.5),  -- montadora 2
(44, 44, 0.5),  -- montadora 3
(45, 45, 8),    -- refinaria
(46, 46, 5),    -- planta química
(47, 47, 2),    -- laboratório
(48, 48, 15),   -- módulo de velocidade 1
(49, 49, 15),   -- módulo de eficiência 1
(50, 50, 15),   -- módulo de produtividade 1
(51, 54, 5),    -- petróleo pesado
(52, 55, 5),    -- petróleo leve
(53, 56, 1),    -- lubrificante
(54, 57, 5),    -- petróleo gasoso
(55, 58, 1),    -- ácido sulfúrico
(56, 64, 0.5),  -- placa de ferro
(57, 65, 0.5),  -- placa de cobre
(58, 66, 0.5),  -- placa de aço
(59, 67, 0.5),  -- combustível sólido
(60, 68, 0.5),  -- plástico
(61, 69, 0.5),  -- enxofre
(62, 70, 4),    -- bateria pequena
(63, 71, 4),    -- explosivo
(64, 72, 0.5),  -- engrenagem
(65, 73, 0.5),  -- barra de ferro
(66, 74, 0.5),  -- fio de cobre
(67, 75, 0.5),  -- circuito verde
(68, 76, 6),    -- circuito vermelho
(69, 77, 10),   -- circuito azul
(78, 78, 10),   -- motor
(79, 79, 10),   -- motor elétrico
(80, 80, 20),   -- chassi de drone
(81, 81, 15),   -- estrutura de baixa densidade
(82, 82, 5),    -- ciência vermelha
(83, 83, 6),    -- ciência verde
(84, 84, 10),   -- ciência preta
(85, 85, 24),   -- ciência azul
(86, 86, 21),   -- ciência roxa
(87, 87, 21)    -- ciência amarela


INSERT INTO maquinas(idmaquinas, nome, energia, modificador_tempo) VALUES
(1, 'inserter amarelo', -15.1, 1),
(2, 'inserter vermelho', -21.4 , 1),
(3, 'inserter azul', -59.3, 1),
(4, 'inserter verde', -169, 1),
(5, 'logistic robot', -63.75, 1),
(6, 'construction robot', -75.9, 1),
(7, 'roboport', -2000, 1),
(8, 'steam engine', 900, 1),
(9,'solar panel', 42,1),
(10, 'accumulator', 300, 1),
(11, 'elétric drill', 90, 1),
(12, 'stone furnace', 90, 1),
(13, 'steel frunace', 90, 2),
(14, 'eletric furnace', 180, 2),
(15, 'assembler 1', 75, 0.5),
(16, 'assembler 2', 150, 0.75),
(17, 'assembler 3', 375, 1.25),
(18, 'oil refinary', 420, 1),
(19, 'chemical plant', 210, 1),
(20, 'lab', 60, 1),
(21, 'radar', 300, 1);

