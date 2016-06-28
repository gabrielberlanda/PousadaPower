
SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 8 (class 2615 OID 144187)
-- Name: auditing; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA IF NOT EXISTS auditing;


ALTER SCHEMA auditing OWNER TO postgres;

--
-- TOC entry 9 (class 2615 OID 144188)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA IF NOT EXISTS public;


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 2 (class 3079 OID 11855)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2132 (class 0 OID 0)
-- Dependencies: 2
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- TOC entry 1 (class 3079 OID 19086)
-- Name: unaccent; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS unaccent WITH SCHEMA pg_catalog;


--
-- TOC entry 2133 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION unaccent; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION unaccent IS 'text search dictionary that removes accents';


SET search_path = auditing, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 177 (class 1259 OID 144203)
-- Name: revision; Type: TABLE; Schema: auditing; Owner: pousadapower; Tablespace: 
--

CREATE TABLE revision (
    id bigint NOT NULL,
    "timestamp" bigint NOT NULL,
    user_id bigint
);


ALTER TABLE revision OWNER TO pousadapower;

--
-- TOC entry 176 (class 1259 OID 144201)
-- Name: revision_id_seq; Type: SEQUENCE; Schema: auditing; Owner: pousadapower
--

CREATE SEQUENCE revision_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE revision_id_seq OWNER TO pousadapower;

--
-- TOC entry 2134 (class 0 OID 0)
-- Dependencies: 176
-- Name: revision_id_seq; Type: SEQUENCE OWNED BY; Schema: auditing; Owner: pousadapower
--

ALTER SEQUENCE revision_id_seq OWNED BY revision.id;


--
-- TOC entry 178 (class 1259 OID 144209)
-- Name: user_audited; Type: TABLE; Schema: auditing; Owner: pousadapower; Tablespace: 
--

CREATE TABLE user_audited (
    id bigint NOT NULL,
    revision bigint NOT NULL,
    revision_type smallint,
    email character varying(144),
    enabled boolean,
    last_login timestamp without time zone,
    name character varying(50),
    password character varying(100),
    role integer
);


ALTER TABLE user_audited OWNER TO pousadapower;

--
-- TOC entry 181 (class 1259 OID 144229)
-- Name: usuario_audited; Type: TABLE; Schema: auditing; Owner: pousadapower; Tablespace: 
--

CREATE TABLE usuario_audited (
    id bigint NOT NULL,
    revision bigint NOT NULL,
    revision_type smallint,
    email character varying(144),
    enabled boolean,
    last_login timestamp without time zone,
    name character varying(50),
    password character varying(100),
    role integer
);


ALTER TABLE usuario_audited OWNER TO pousadapower;

SET search_path = public, pg_catalog;

--
-- TOC entry 183 (class 1259 OID 144236)
-- Name: cidade; Type: TABLE; Schema: public; Owner: pousadapower; Tablespace: 
--

CREATE TABLE cidade (
    id bigint NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone,
    nome character varying(50) NOT NULL,
    estado_id bigint NOT NULL
);


ALTER TABLE cidade OWNER TO pousadapower;

--
-- TOC entry 182 (class 1259 OID 144234)
-- Name: cidade_id_seq; Type: SEQUENCE; Schema: public; Owner: pousadapower
--

CREATE SEQUENCE cidade_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cidade_id_seq OWNER TO pousadapower;

--
-- TOC entry 2135 (class 0 OID 0)
-- Dependencies: 182
-- Name: cidade_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pousadapower
--

ALTER SEQUENCE cidade_id_seq OWNED BY cidade.id;


--
-- TOC entry 185 (class 1259 OID 144244)
-- Name: estado; Type: TABLE; Schema: public; Owner: pousadapower; Tablespace: 
--

CREATE TABLE estado (
    id bigint NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone,
    nome character varying(50) NOT NULL,
    pais_id bigint NOT NULL
);


ALTER TABLE estado OWNER TO pousadapower;

--
-- TOC entry 184 (class 1259 OID 144242)
-- Name: estado_id_seq; Type: SEQUENCE; Schema: public; Owner: pousadapower
--

CREATE SEQUENCE estado_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE estado_id_seq OWNER TO pousadapower;

--
-- TOC entry 2136 (class 0 OID 0)
-- Dependencies: 184
-- Name: estado_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pousadapower
--

ALTER SEQUENCE estado_id_seq OWNED BY estado.id;


--
-- TOC entry 187 (class 1259 OID 144252)
-- Name: fornecedor; Type: TABLE; Schema: public; Owner: pousadapower; Tablespace: 
--

CREATE TABLE fornecedor (
    id bigint NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone,
    bairro character varying(255),
    cnpj character varying(255),
    email character varying(255),
    endereco character varying(255),
    inscricao_estadual character varying(255),
    nome_fantasia character varying(50) NOT NULL,
    numero integer,
    observacao character varying(255),
    razao_social character varying(255),
    telefone character varying(255),
    cidade_id bigint
);


ALTER TABLE fornecedor OWNER TO pousadapower;

--
-- TOC entry 186 (class 1259 OID 144250)
-- Name: fornecedor_id_seq; Type: SEQUENCE; Schema: public; Owner: pousadapower
--

CREATE SEQUENCE fornecedor_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE fornecedor_id_seq OWNER TO pousadapower;

--
-- TOC entry 2137 (class 0 OID 0)
-- Dependencies: 186
-- Name: fornecedor_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pousadapower
--

ALTER SEQUENCE fornecedor_id_seq OWNED BY fornecedor.id;


--
-- TOC entry 189 (class 1259 OID 144263)
-- Name: pais; Type: TABLE; Schema: public; Owner: pousadapower; Tablespace: 
--

CREATE TABLE pais (
    id bigint NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone,
    cpf_requerido boolean,
    nome character varying(50) NOT NULL
);


ALTER TABLE pais OWNER TO pousadapower;

--
-- TOC entry 188 (class 1259 OID 144261)
-- Name: pais_id_seq; Type: SEQUENCE; Schema: public; Owner: pousadapower
--

CREATE SEQUENCE pais_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE pais_id_seq OWNER TO pousadapower;

--
-- TOC entry 2138 (class 0 OID 0)
-- Dependencies: 188
-- Name: pais_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pousadapower
--

ALTER SEQUENCE pais_id_seq OWNED BY pais.id;


--
-- TOC entry 191 (class 1259 OID 144271)
-- Name: pessoa; Type: TABLE; Schema: public; Owner: pousadapower; Tablespace: 
--

CREATE TABLE pessoa (
    tipo character varying(31) NOT NULL,
    id bigint NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone,
    bairro character varying(50),
    celular character varying(25),
    cep character varying(50),
    complemento character varying(100),
    cpf character varying(11),
    data_nascimento timestamp without time zone,
    email character varying(100) NOT NULL,
    nome character varying(50) NOT NULL,
    numero integer,
    passaporte character varying(20),
    rg character varying(9),
    sexo integer,
    telefone character varying(25) NOT NULL,
    modelo_veiculo character varying(255),
    observacao character varying(500),
    placa character varying(255),
    cidade_id bigint
);


ALTER TABLE pessoa OWNER TO pousadapower;

--
-- TOC entry 190 (class 1259 OID 144269)
-- Name: pessoa_id_seq; Type: SEQUENCE; Schema: public; Owner: pousadapower
--

CREATE SEQUENCE pessoa_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE pessoa_id_seq OWNER TO pousadapower;

--
-- TOC entry 2139 (class 0 OID 0)
-- Dependencies: 190
-- Name: pessoa_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pousadapower
--

ALTER SEQUENCE pessoa_id_seq OWNED BY pessoa.id;


--
-- TOC entry 193 (class 1259 OID 144282)
-- Name: produto; Type: TABLE; Schema: public; Owner: pousadapower; Tablespace: 
--

CREATE TABLE produto (
    id bigint NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone,
    descricao character varying(200),
    nome character varying(50) NOT NULL,
    preco_custo double precision NOT NULL,
    preco_unitario double precision NOT NULL,
    quantidade_estoque integer NOT NULL,
    quantidade_minima integer NOT NULL,
    tipo_produto integer,
    fornecedor_id bigint NOT NULL
);


ALTER TABLE produto OWNER TO pousadapower;

--
-- TOC entry 192 (class 1259 OID 144280)
-- Name: produto_id_seq; Type: SEQUENCE; Schema: public; Owner: pousadapower
--

CREATE SEQUENCE produto_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE produto_id_seq OWNER TO pousadapower;

--
-- TOC entry 2140 (class 0 OID 0)
-- Dependencies: 192
-- Name: produto_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pousadapower
--

ALTER SEQUENCE produto_id_seq OWNED BY produto.id;


--
-- TOC entry 175 (class 1259 OID 144189)
-- Name: schema_version; Type: TABLE; Schema: public; Owner: pousadapower; Tablespace: 
--

CREATE TABLE schema_version (
    version_rank integer NOT NULL,
    installed_rank integer NOT NULL,
    version character varying(50) NOT NULL,
    description character varying(200) NOT NULL,
    type character varying(20) NOT NULL,
    script character varying(1000) NOT NULL,
    checksum integer,
    installed_by character varying(100) NOT NULL,
    installed_on timestamp without time zone DEFAULT now() NOT NULL,
    execution_time integer NOT NULL,
    success boolean NOT NULL
);


ALTER TABLE schema_version OWNER TO pousadapower;

--
-- TOC entry 180 (class 1259 OID 144221)
-- Name: usuario; Type: TABLE; Schema: public; Owner: pousadapower; Tablespace: 
--

CREATE TABLE usuario (
    id bigint NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone,
    email character varying(144) NOT NULL,
    enabled boolean NOT NULL,
    name character varying(50) NOT NULL,
    password character varying(100) NOT NULL,
    role integer NOT NULL,
    last_login timestamp without time zone
);


ALTER TABLE usuario OWNER TO pousadapower;

--
-- TOC entry 179 (class 1259 OID 144219)
-- Name: usuario_id_seq; Type: SEQUENCE; Schema: public; Owner: pousadapower
--

CREATE SEQUENCE usuario_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE usuario_id_seq OWNER TO pousadapower;

--
-- TOC entry 2141 (class 0 OID 0)
-- Dependencies: 179
-- Name: usuario_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pousadapower
--

ALTER SEQUENCE usuario_id_seq OWNED BY usuario.id;


SET search_path = auditing, pg_catalog;

--
-- TOC entry 1943 (class 2604 OID 144206)
-- Name: id; Type: DEFAULT; Schema: auditing; Owner: pousadapower
--

ALTER TABLE ONLY revision ALTER COLUMN id SET DEFAULT nextval('revision_id_seq'::regclass);


SET search_path = public, pg_catalog;

--
-- TOC entry 1945 (class 2604 OID 144239)
-- Name: id; Type: DEFAULT; Schema: public; Owner: pousadapower
--

ALTER TABLE ONLY cidade ALTER COLUMN id SET DEFAULT nextval('cidade_id_seq'::regclass);


--
-- TOC entry 1946 (class 2604 OID 144247)
-- Name: id; Type: DEFAULT; Schema: public; Owner: pousadapower
--

ALTER TABLE ONLY estado ALTER COLUMN id SET DEFAULT nextval('estado_id_seq'::regclass);


--
-- TOC entry 1947 (class 2604 OID 144255)
-- Name: id; Type: DEFAULT; Schema: public; Owner: pousadapower
--

ALTER TABLE ONLY fornecedor ALTER COLUMN id SET DEFAULT nextval('fornecedor_id_seq'::regclass);


--
-- TOC entry 1948 (class 2604 OID 144266)
-- Name: id; Type: DEFAULT; Schema: public; Owner: pousadapower
--

ALTER TABLE ONLY pais ALTER COLUMN id SET DEFAULT nextval('pais_id_seq'::regclass);


--
-- TOC entry 1949 (class 2604 OID 144274)
-- Name: id; Type: DEFAULT; Schema: public; Owner: pousadapower
--

ALTER TABLE ONLY pessoa ALTER COLUMN id SET DEFAULT nextval('pessoa_id_seq'::regclass);


--
-- TOC entry 1950 (class 2604 OID 144285)
-- Name: id; Type: DEFAULT; Schema: public; Owner: pousadapower
--

ALTER TABLE ONLY produto ALTER COLUMN id SET DEFAULT nextval('produto_id_seq'::regclass);


--
-- TOC entry 1944 (class 2604 OID 144224)
-- Name: id; Type: DEFAULT; Schema: public; Owner: pousadapower
--

ALTER TABLE ONLY usuario ALTER COLUMN id SET DEFAULT nextval('usuario_id_seq'::regclass);


SET search_path = auditing, pg_catalog;

--
-- TOC entry 2110 (class 0 OID 144203)
-- Dependencies: 177
-- Data for Name: revision; Type: TABLE DATA; Schema: auditing; Owner: pousadapower
--



--
-- TOC entry 2142 (class 0 OID 0)
-- Dependencies: 176
-- Name: revision_id_seq; Type: SEQUENCE SET; Schema: auditing; Owner: pousadapower
--

SELECT pg_catalog.setval('revision_id_seq', 1, false);


--
-- TOC entry 2111 (class 0 OID 144209)
-- Dependencies: 178
-- Data for Name: user_audited; Type: TABLE DATA; Schema: auditing; Owner: pousadapower
--



--
-- TOC entry 2114 (class 0 OID 144229)
-- Dependencies: 181
-- Data for Name: usuario_audited; Type: TABLE DATA; Schema: auditing; Owner: pousadapower
--



SET search_path = public, pg_catalog;

--
-- TOC entry 2116 (class 0 OID 144236)
-- Dependencies: 183
-- Data for Name: cidade; Type: TABLE DATA; Schema: public; Owner: pousadapower
--



--
-- TOC entry 2143 (class 0 OID 0)
-- Dependencies: 182
-- Name: cidade_id_seq; Type: SEQUENCE SET; Schema: public; Owner: pousadapower
--

SELECT pg_catalog.setval('cidade_id_seq', 1, false);


--
-- TOC entry 2118 (class 0 OID 144244)
-- Dependencies: 185
-- Data for Name: estado; Type: TABLE DATA; Schema: public; Owner: pousadapower
--



--
-- TOC entry 2144 (class 0 OID 0)
-- Dependencies: 184
-- Name: estado_id_seq; Type: SEQUENCE SET; Schema: public; Owner: pousadapower
--

SELECT pg_catalog.setval('estado_id_seq', 1, false);


--
-- TOC entry 2120 (class 0 OID 144252)
-- Dependencies: 187
-- Data for Name: fornecedor; Type: TABLE DATA; Schema: public; Owner: pousadapower
--



--
-- TOC entry 2145 (class 0 OID 0)
-- Dependencies: 186
-- Name: fornecedor_id_seq; Type: SEQUENCE SET; Schema: public; Owner: pousadapower
--

SELECT pg_catalog.setval('fornecedor_id_seq', 1, false);


--
-- TOC entry 2122 (class 0 OID 144263)
-- Dependencies: 189
-- Data for Name: pais; Type: TABLE DATA; Schema: public; Owner: pousadapower
--



--
-- TOC entry 2146 (class 0 OID 0)
-- Dependencies: 188
-- Name: pais_id_seq; Type: SEQUENCE SET; Schema: public; Owner: pousadapower
--

SELECT pg_catalog.setval('pais_id_seq', 1, false);


--
-- TOC entry 2124 (class 0 OID 144271)
-- Dependencies: 191
-- Data for Name: pessoa; Type: TABLE DATA; Schema: public; Owner: pousadapower
--



--
-- TOC entry 2147 (class 0 OID 0)
-- Dependencies: 190
-- Name: pessoa_id_seq; Type: SEQUENCE SET; Schema: public; Owner: pousadapower
--

SELECT pg_catalog.setval('pessoa_id_seq', 1, false);


--
-- TOC entry 2126 (class 0 OID 144282)
-- Dependencies: 193
-- Data for Name: produto; Type: TABLE DATA; Schema: public; Owner: pousadapower
--



--
-- TOC entry 2148 (class 0 OID 0)
-- Dependencies: 192
-- Name: produto_id_seq; Type: SEQUENCE SET; Schema: public; Owner: pousadapower
--

SELECT pg_catalog.setval('produto_id_seq', 1, false);


--
-- TOC entry 2108 (class 0 OID 144189)
-- Dependencies: 175
-- Data for Name: schema_version; Type: TABLE DATA; Schema: public; Owner: pousadapower
--

INSERT INTO schema_version (version_rank, installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) VALUES (1, 1, '0.0.1', 'SNAPSHOT', 'SQL', 'v0.0.1__SNAPSHOT.sql', -573868587, 'pousadapower', '2016-06-28 06:39:32.882', 104, true);


--
-- TOC entry 2113 (class 0 OID 144221)
-- Dependencies: 180
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: pousadapower
--

INSERT INTO usuario (id, created, updated, email, enabled, name, password, role, last_login) VALUES (1, '2016-06-28 06:39:33.084', NULL, 'admin@admin.com', true, 'Administrador de Sistemas', 'd1bd2f08fead38a982aed9d4ca060152400b1b8f', 0, NULL);


--
-- TOC entry 2149 (class 0 OID 0)
-- Dependencies: 179
-- Name: usuario_id_seq; Type: SEQUENCE SET; Schema: public; Owner: pousadapower
--

SELECT pg_catalog.setval('usuario_id_seq', 1, false);


SET search_path = auditing, pg_catalog;

--
-- TOC entry 1957 (class 2606 OID 144208)
-- Name: revision_pkey; Type: CONSTRAINT; Schema: auditing; Owner: pousadapower; Tablespace: 
--

ALTER TABLE ONLY revision
    ADD CONSTRAINT revision_pkey PRIMARY KEY (id);


--
-- TOC entry 1959 (class 2606 OID 144213)
-- Name: user_audited_pkey; Type: CONSTRAINT; Schema: auditing; Owner: pousadapower; Tablespace: 
--

ALTER TABLE ONLY user_audited
    ADD CONSTRAINT user_audited_pkey PRIMARY KEY (id, revision);


--
-- TOC entry 1965 (class 2606 OID 144233)
-- Name: usuario_audited_pkey; Type: CONSTRAINT; Schema: auditing; Owner: pousadapower; Tablespace: 
--

ALTER TABLE ONLY usuario_audited
    ADD CONSTRAINT usuario_audited_pkey PRIMARY KEY (id, revision);


SET search_path = public, pg_catalog;

--
-- TOC entry 1967 (class 2606 OID 144241)
-- Name: cidade_pkey; Type: CONSTRAINT; Schema: public; Owner: pousadapower; Tablespace: 
--

ALTER TABLE ONLY cidade
    ADD CONSTRAINT cidade_pkey PRIMARY KEY (id);


--
-- TOC entry 1971 (class 2606 OID 144249)
-- Name: estado_pkey; Type: CONSTRAINT; Schema: public; Owner: pousadapower; Tablespace: 
--

ALTER TABLE ONLY estado
    ADD CONSTRAINT estado_pkey PRIMARY KEY (id);


--
-- TOC entry 1975 (class 2606 OID 144260)
-- Name: fornecedor_pkey; Type: CONSTRAINT; Schema: public; Owner: pousadapower; Tablespace: 
--

ALTER TABLE ONLY fornecedor
    ADD CONSTRAINT fornecedor_pkey PRIMARY KEY (id);


--
-- TOC entry 1977 (class 2606 OID 144268)
-- Name: pais_pkey; Type: CONSTRAINT; Schema: public; Owner: pousadapower; Tablespace: 
--

ALTER TABLE ONLY pais
    ADD CONSTRAINT pais_pkey PRIMARY KEY (id);


--
-- TOC entry 1981 (class 2606 OID 144279)
-- Name: pessoa_pkey; Type: CONSTRAINT; Schema: public; Owner: pousadapower; Tablespace: 
--

ALTER TABLE ONLY pessoa
    ADD CONSTRAINT pessoa_pkey PRIMARY KEY (id);


--
-- TOC entry 1989 (class 2606 OID 144287)
-- Name: produto_pkey; Type: CONSTRAINT; Schema: public; Owner: pousadapower; Tablespace: 
--

ALTER TABLE ONLY produto
    ADD CONSTRAINT produto_pkey PRIMARY KEY (id);


--
-- TOC entry 1953 (class 2606 OID 144197)
-- Name: schema_version_pk; Type: CONSTRAINT; Schema: public; Owner: pousadapower; Tablespace: 
--

ALTER TABLE ONLY schema_version
    ADD CONSTRAINT schema_version_pk PRIMARY KEY (version);


--
-- TOC entry 1969 (class 2606 OID 144289)
-- Name: uk_cidade_nome; Type: CONSTRAINT; Schema: public; Owner: pousadapower; Tablespace: 
--

ALTER TABLE ONLY cidade
    ADD CONSTRAINT uk_cidade_nome UNIQUE (nome);


--
-- TOC entry 1973 (class 2606 OID 144291)
-- Name: uk_estado_nome; Type: CONSTRAINT; Schema: public; Owner: pousadapower; Tablespace: 
--

ALTER TABLE ONLY estado
    ADD CONSTRAINT uk_estado_nome UNIQUE (nome);


--
-- TOC entry 1979 (class 2606 OID 144293)
-- Name: uk_pais_nome; Type: CONSTRAINT; Schema: public; Owner: pousadapower; Tablespace: 
--

ALTER TABLE ONLY pais
    ADD CONSTRAINT uk_pais_nome UNIQUE (nome);


--
-- TOC entry 1983 (class 2606 OID 144295)
-- Name: uk_pessoa_cpf; Type: CONSTRAINT; Schema: public; Owner: pousadapower; Tablespace: 
--

ALTER TABLE ONLY pessoa
    ADD CONSTRAINT uk_pessoa_cpf UNIQUE (cpf);


--
-- TOC entry 1985 (class 2606 OID 144297)
-- Name: uk_pessoa_passaporte; Type: CONSTRAINT; Schema: public; Owner: pousadapower; Tablespace: 
--

ALTER TABLE ONLY pessoa
    ADD CONSTRAINT uk_pessoa_passaporte UNIQUE (passaporte);


--
-- TOC entry 1987 (class 2606 OID 144299)
-- Name: uk_pessoa_rg; Type: CONSTRAINT; Schema: public; Owner: pousadapower; Tablespace: 
--

ALTER TABLE ONLY pessoa
    ADD CONSTRAINT uk_pessoa_rg UNIQUE (rg);


--
-- TOC entry 1991 (class 2606 OID 144301)
-- Name: uk_produto_nome; Type: CONSTRAINT; Schema: public; Owner: pousadapower; Tablespace: 
--

ALTER TABLE ONLY produto
    ADD CONSTRAINT uk_produto_nome UNIQUE (nome);


--
-- TOC entry 1961 (class 2606 OID 144228)
-- Name: uk_user_email; Type: CONSTRAINT; Schema: public; Owner: pousadapower; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT uk_user_email UNIQUE (email);


--
-- TOC entry 1963 (class 2606 OID 144226)
-- Name: user_pkey; Type: CONSTRAINT; Schema: public; Owner: pousadapower; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- TOC entry 1951 (class 1259 OID 144199)
-- Name: schema_version_ir_idx; Type: INDEX; Schema: public; Owner: pousadapower; Tablespace: 
--

CREATE INDEX schema_version_ir_idx ON schema_version USING btree (installed_rank);


--
-- TOC entry 1954 (class 1259 OID 144200)
-- Name: schema_version_s_idx; Type: INDEX; Schema: public; Owner: pousadapower; Tablespace: 
--

CREATE INDEX schema_version_s_idx ON schema_version USING btree (success);


--
-- TOC entry 1955 (class 1259 OID 144198)
-- Name: schema_version_vr_idx; Type: INDEX; Schema: public; Owner: pousadapower; Tablespace: 
--

CREATE INDEX schema_version_vr_idx ON schema_version USING btree (version_rank);


SET search_path = auditing, pg_catalog;

--
-- TOC entry 1992 (class 2606 OID 144214)
-- Name: fk_user_audited_revision; Type: FK CONSTRAINT; Schema: auditing; Owner: pousadapower
--

ALTER TABLE ONLY user_audited
    ADD CONSTRAINT fk_user_audited_revision FOREIGN KEY (revision) REFERENCES revision(id);


--
-- TOC entry 1993 (class 2606 OID 144302)
-- Name: fk_usuario_audited_revision; Type: FK CONSTRAINT; Schema: auditing; Owner: pousadapower
--

ALTER TABLE ONLY usuario_audited
    ADD CONSTRAINT fk_usuario_audited_revision FOREIGN KEY (revision) REFERENCES revision(id);


SET search_path = public, pg_catalog;

--
-- TOC entry 1994 (class 2606 OID 144307)
-- Name: fk_cidade_estado_id; Type: FK CONSTRAINT; Schema: public; Owner: pousadapower
--

ALTER TABLE ONLY cidade
    ADD CONSTRAINT fk_cidade_estado_id FOREIGN KEY (estado_id) REFERENCES estado(id);


--
-- TOC entry 1995 (class 2606 OID 144312)
-- Name: fk_estado_pais_id; Type: FK CONSTRAINT; Schema: public; Owner: pousadapower
--

ALTER TABLE ONLY estado
    ADD CONSTRAINT fk_estado_pais_id FOREIGN KEY (pais_id) REFERENCES pais(id);


--
-- TOC entry 1996 (class 2606 OID 144317)
-- Name: fk_fornecedor_cidade_id; Type: FK CONSTRAINT; Schema: public; Owner: pousadapower
--

ALTER TABLE ONLY fornecedor
    ADD CONSTRAINT fk_fornecedor_cidade_id FOREIGN KEY (cidade_id) REFERENCES cidade(id);


--
-- TOC entry 1997 (class 2606 OID 144322)
-- Name: fk_pessoa_cidade_id; Type: FK CONSTRAINT; Schema: public; Owner: pousadapower
--

ALTER TABLE ONLY pessoa
    ADD CONSTRAINT fk_pessoa_cidade_id FOREIGN KEY (cidade_id) REFERENCES cidade(id);


--
-- TOC entry 1998 (class 2606 OID 144327)
-- Name: fk_produto_fornecedor_id; Type: FK CONSTRAINT; Schema: public; Owner: pousadapower
--

ALTER TABLE ONLY produto
    ADD CONSTRAINT fk_produto_fornecedor_id FOREIGN KEY (fornecedor_id) REFERENCES fornecedor(id);


-- Completed on 2016-06-28 06:41:29

--
-- PostgreSQL database dump complete
--

