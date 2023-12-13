drop database calculadora;
CREATE DATABASE IF NOT EXISTS calculadora;
USE calculadora;
create table IF NOT EXISTS  calculadora(
idcalculadora int not null auto_increment,
numero1 DOUBLE NOT NULL,
numero2 DOUBLE NOT NULL,
operacao VARCHAR(20) NOT NULL,
resultado DOUBLE NOT NULL,
primary key (idcalculadora)
);

