--
-- PostgreSQL database dump
--

-- Dumped from database version 10.10 (Ubuntu 10.10-0ubuntu0.18.04.1)
-- Dumped by pg_dump version 10.10 (Ubuntu 10.10-0ubuntu0.18.04.1)

-- Started on 2019-11-24 18:14:18 -03

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 13043)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner:
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2936 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner:
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 197 (class 1259 OID 29445)
-- Name: emp_employee; Type: TABLE; Schema: public; Owner: hugoiguana
--

CREATE TABLE public.emp_employee (
    emp_id bigint NOT NULL,
    emp_name character varying(255) NOT NULL,
    emp_password character varying(255) NOT NULL,
    emp_pis character varying(255) NOT NULL,
    emp_profile integer
);


ALTER TABLE public.emp_employee OWNER TO hugoiguana;

--
-- TOC entry 196 (class 1259 OID 29443)
-- Name: emp_employee_emp_id_seq; Type: SEQUENCE; Schema: public; Owner: hugoiguana
--

CREATE SEQUENCE public.emp_employee_emp_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.emp_employee_emp_id_seq OWNER TO hugoiguana;

--
-- TOC entry 2937 (class 0 OID 0)
-- Dependencies: 196
-- Name: emp_employee_emp_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: hugoiguana
--

ALTER SEQUENCE public.emp_employee_emp_id_seq OWNED BY public.emp_employee.emp_id;


--
-- TOC entry 199 (class 1259 OID 29456)
-- Name: tim_timer_register; Type: TABLE; Schema: public; Owner: hugoiguana
--

CREATE TABLE public.tim_timer_register (
    tim_id bigint NOT NULL,
    date_time_point timestamp without time zone NOT NULL,
    emp_id bigint NOT NULL
);


ALTER TABLE public.tim_timer_register OWNER TO hugoiguana;

--
-- TOC entry 198 (class 1259 OID 29454)
-- Name: tim_timer_register_tim_id_seq; Type: SEQUENCE; Schema: public; Owner: hugoiguana
--

CREATE SEQUENCE public.tim_timer_register_tim_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tim_timer_register_tim_id_seq OWNER TO hugoiguana;

--
-- TOC entry 2938 (class 0 OID 0)
-- Dependencies: 198
-- Name: tim_timer_register_tim_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: hugoiguana
--

ALTER SEQUENCE public.tim_timer_register_tim_id_seq OWNED BY public.tim_timer_register.tim_id;


--
-- TOC entry 2795 (class 2604 OID 29448)
-- Name: emp_employee emp_id; Type: DEFAULT; Schema: public; Owner: hugoiguana
--

ALTER TABLE ONLY public.emp_employee ALTER COLUMN emp_id SET DEFAULT nextval('public.emp_employee_emp_id_seq'::regclass);


--
-- TOC entry 2796 (class 2604 OID 29459)
-- Name: tim_timer_register tim_id; Type: DEFAULT; Schema: public; Owner: hugoiguana
--

ALTER TABLE ONLY public.tim_timer_register ALTER COLUMN tim_id SET DEFAULT nextval('public.tim_timer_register_tim_id_seq'::regclass);


--
-- TOC entry 2926 (class 0 OID 29445)
-- Dependencies: 197
-- Data for Name: emp_employee; Type: TABLE DATA; Schema: public; Owner: hugoiguana
--

COPY public.emp_employee (emp_id, emp_name, emp_password, emp_pis, emp_profile) FROM stdin;
1	Hugo	$2a$10$aoxCaOfuZtFaX0aIK/GFd.i5EXx9HsLiex5YeOxvepuYBHRPA/yGa	123456789	1
\.


--
-- TOC entry 2928 (class 0 OID 29456)
-- Dependencies: 199
-- Data for Name: tim_timer_register; Type: TABLE DATA; Schema: public; Owner: hugoiguana
--

COPY public.tim_timer_register (tim_id, date_time_point, emp_id) FROM stdin;
1	2019-11-01 07:00:00	1
2	2019-11-01 11:00:00	1
3	2019-11-01 12:00:00	1
4	2019-11-01 16:00:00	1
5	2019-11-02 07:00:00	1
6	2019-11-03 07:00:00	1
7	2019-11-03 10:00:00	1
8	2019-11-04 07:00:00	1
9	2019-11-04 11:00:00	1
10	2019-11-04 12:00:00	1
11	2019-11-04 16:00:00	1
12	2019-11-04 17:00:00	1
13	2019-11-04 20:00:00	1
14	2019-11-05 07:00:00	1
15	2019-11-05 11:00:00	1
16	2019-11-05 12:00:00	1
17	2019-11-06 07:35:00	1
18	2019-11-06 11:20:00	1
19	2019-11-06 12:40:00	1
20	2019-11-06 16:45:00	1
21	2019-11-07 07:35:00	1
22	2019-11-07 14:20:00	1
23	2019-11-08 07:35:00	1
24	2019-11-08 09:20:00	1
\.


--
-- TOC entry 2939 (class 0 OID 0)
-- Dependencies: 196
-- Name: emp_employee_emp_id_seq; Type: SEQUENCE SET; Schema: public; Owner: hugoiguana
--

SELECT pg_catalog.setval('public.emp_employee_emp_id_seq', 1, true);


--
-- TOC entry 2940 (class 0 OID 0)
-- Dependencies: 198
-- Name: tim_timer_register_tim_id_seq; Type: SEQUENCE SET; Schema: public; Owner: hugoiguana
--

SELECT pg_catalog.setval('public.tim_timer_register_tim_id_seq', 24, true);


--
-- TOC entry 2798 (class 2606 OID 29453)
-- Name: emp_employee emp_employee_pkey; Type: CONSTRAINT; Schema: public; Owner: hugoiguana
--

ALTER TABLE ONLY public.emp_employee
    ADD CONSTRAINT emp_employee_pkey PRIMARY KEY (emp_id);


--
-- TOC entry 2802 (class 2606 OID 29461)
-- Name: tim_timer_register tim_timer_register_pkey; Type: CONSTRAINT; Schema: public; Owner: hugoiguana
--

ALTER TABLE ONLY public.tim_timer_register
    ADD CONSTRAINT tim_timer_register_pkey PRIMARY KEY (tim_id);


--
-- TOC entry 2800 (class 2606 OID 29463)
-- Name: emp_employee uk_mlyl2vbxpopk5ub0f9x8a5u56; Type: CONSTRAINT; Schema: public; Owner: hugoiguana
--

ALTER TABLE ONLY public.emp_employee
    ADD CONSTRAINT uk_mlyl2vbxpopk5ub0f9x8a5u56 UNIQUE (emp_pis);


--
-- TOC entry 2803 (class 2606 OID 29464)
-- Name: tim_timer_register fkqk8regw928ykmd1dxd250odfp; Type: FK CONSTRAINT; Schema: public; Owner: hugoiguana
--

ALTER TABLE ONLY public.tim_timer_register
    ADD CONSTRAINT fkqk8regw928ykmd1dxd250odfp FOREIGN KEY (emp_id) REFERENCES public.emp_employee(emp_id);


-- Completed on 2019-11-24 18:14:18 -03

--
-- PostgreSQL database dump complete
--
