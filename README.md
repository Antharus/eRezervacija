# eRezervacija

Diegimo instrukcija (Eclipse):
1. Importuojame projektą. 
Spaudžiame File -> Import -> Existing Maven Projects -> Pasirenkame projekto lokacija -> Finish.

2. Priskiriame Java versiją
Spaudžiame dešinį klavišą ant projekto -> Properties -> Java Build Path -> Libraries -> 2 Kartus paspaudžiame ant System Library -> Installed JREs -> Add -> Pasirenkame Java 8+ JDK lokaciją -> OK -> Finish -> OK

3. Pridedame serverį
3.1 Spaudžiame ant Servers tab'o -> "No Servers are available" -> Pasirenkame Tomcat v9.0 -> Pasirenkame Tomcat direktoriją kompiuteryje
3.2 Spaudžiame dešinį klavišą ant pridėto serverio -> Add or remove... -> spaudižiame 2 kartus ant eRezervacija -> Finish

4.Duomenų bazė

(Postgres)
4.1 Parsisiunčiame Postgres 10.0 serverį ir jį susidiegiame, taip pat pgAdmin 4+
4.2 Atidarome pgAdmin, spaudžiame 2 kartus and PostgresSQL 10 -> dešinį pelės klavišą ant Databases -> Create -> Database -> Pavadiname kuriamą duomenų bazę "eRezervacija" ir spaudžiame Save
4.3 Atidaorme katik sukurtą duomenų bazę, viršuj lango spaudžiame Tools -> Query tool -> į atsidariusį langą įvedame testinius duomenis:

CREATE TABLE public.doctor
(
    id bigint NOT NULL,
    doctoroccupation character varying(255) COLLATE pg_catalog."default",
    doctorworkingstate character varying(255) COLLATE pg_catalog."default",
    name character varying(124) COLLATE pg_catalog."default",
    surname character varying(124) COLLATE pg_catalog."default",
    version integer,
    state character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT doctor_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.doctor
    OWNER to postgres;
    
INSERT INTO public.doctor(
	id, doctoroccupation, doctorworkingstate, name, surname, version, state)
	VALUES ('3','SURGEON','ACTIVE','Marius','Daukšas',0,'ACTIVE'), 
    ('2','CARDIOLOGIST','ACTIVE','Darius','Kazlauskas',0,'ACTIVE'),
    ('1','DERMATOLOGIST','ACTIVE','Vaidas','Morkūnas',0,'ACTIVE');
    
CREATE SEQUENCE public.gen_doctor
    INCREMENT 1
    START 4
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.gen_doctor
    OWNER TO postgres;
    

Visa kita automatiškai susikurs startuojant projektą.
(MySQL)
4.1 Sukuriame schemą rezervacija
4.2 Paleidžiame scriptą:
CREATE TABLE person (
  ID numeric(20,0),
  name varchar(124) NOT NULL,
  surname varchar(124) NOT NULL,
  intValue integer default NULL,
  personcode varchar(11) NOT NULL,
  version integer default NULL,
  PRIMARY KEY (ID)
);
CREATE INDEX IX_person on person(personcode);

CREATE TABLE doctor (
  ID numeric(20,0),
  doctoroccupation varchar(30) default NULL,
  doctorworkingstate varchar(30) default NULL,
  name varchar(124) default NULL,
  surname varchar(124) default NULL,
  version integer default NULL,
  state varchar(255) default NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE meeting (
  ID numeric(20,0),
  meetingDate varchar(23) default '2000-01-01 00:00:00',
  version integer default NULL,
  person numeric(20,0),
  doctor numeric(20,0),
  canceled char(1) default 'f',
  PRIMARY KEY (ID)
);

INSERT INTO `rezervacija`.`doctor`
(`ID`,
`doctoroccupation`,
`doctorworkingstate`,
`name`,
`surname`,
`version`,
`state`)
VALUES
(1,'DERMATOLOGIST','ACTIVE','Vaidas','Morkunas',0,'ACTIVE'),
(2,'CARDIOLOGIST','ACTIVE','Darius','Kazlauskas',0,'ACTIVE'),
(3,'SURGEON','ACTIVE','Marius','Daukšas',0,'ACTIVE');


4.3 Užkomentuoti Person,Meeting,Doctor klasėse ID generavimo sequence sukūrimą:
//	@SequenceGenerator(name = "GEN_PERSON", sequenceName = "GEN_PERSON")
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_PERSON")




4.4 Eclipse atsidarome "Server" folderį -> redaguojame context.xml -> Pridedame tekstą į <Context> tag'ą:
Prieš nukopijuojant tekstą būtina pasikeisti, ar pasitikrinti, parametrus -> password, url (jei pavadinote duomenų bazę kitaip, ar nurodėte kitą port'ą), username
  (Postgres)
	<Resource auth="Container" driverClassName="org.postgresql.Driver"
		logAbandoned="true" maxIdle="30" maxTotal="200" maxWaitMillis="4000"
		name="jdbc/eRezervacija" numTestsPerEvictionRun="10" password="root"
		removeAbandonedOnBorrow="true" removeAbandonedOnMaintenance="true"
		removeAbandonedTimeout="300" testOnBorrow="true" testWhileIdle="true"
		timeBetweenEvictionRunsMillis="30000" type="javax.sql.DataSource"
		url="jdbc:postgresql://localhost:5432/rezervacija" username="postgres"
		validationQuery="SELECT 1" />
    
  (MySQL)
  <Resource auth="Container" driverClassName="com.mysql.jdbc.Driver"
		logAbandoned="true" maxIdle="30" maxTotal="200" maxWaitMillis="4000"
		name="jdbc/eRezervacija" numTestsPerEvictionRun="10" password="root"
		removeAbandonedOnBorrow="true" removeAbandonedOnMaintenance="true"
		removeAbandonedTimeout="300" testOnBorrow="true" testWhileIdle="true"
		timeBetweenEvictionRunsMillis="30000" type="javax.sql.DataSource"
		url="jdbc:mysql://localhost:3306/rezervacija" username="root"
		validationQuery="SELECT 1" />
    
    
5. Maven Update
5.0.1 (tik MySQL) Jei naudojate MySQL pakeiskite maven dependency iš:
		<dependency>
			<groupId>org.postgresql</groupId>
			<artifactId>postgresql</artifactId>
			<version>42.1.4</version>
		</dependency>
    
Į MySQL atitinkantį duomenį bazės versiją.
5.0.2 (tik MySQL) perisistance.xml pakeičiame:
<property name="hibernate.dialect" value="org.hibernate.dialect.PostgreSQL95Dialect" />
Į MySQL atitinkantį duomenį bazės versiją. Dialektus galima rasti čia:
https://javabydeveloper.com/what-is-dialect-in-hibernate-and-list-of-dialects/#4-5-list-of-sql-dialects-in-hibernate-

5.1 Spaudžiame dešinį klaviša ant projekto -> Maven -> Update Project -> Pasirenkame projektą ir spaudžiame OK
5.2 Pasibaigus maven atnaujinimo darbams spaudžiame ant dešinį klavišą ant projekto -> Run as -> Maven build... -> į Goals įvedame "clean install" 
5.3 Kai pamatome "BUILD SUCCESS" spaudžiame dešinį klavišą ant projekto -> Refresh

6. Paleidimas
6.1 Spaudžiame 2 kartus ant serverio -> Timeouts -> start=120, stop=120 -> spaudžiame ctrl + S
6.2 Spaudžiame dešinį pelės klavišą ant serverio -> Start

7. Projektas paleistas, jį galite pasiekti http://localhost:8080/eRezervacija/
    
 
