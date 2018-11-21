
    drop table billetera if exists;

    drop table movimiento if exists;

    drop table usuario if exists;

    drop table usuario_billeteras if exists;

    create table billetera (
      id BIGINT       NOT NULL AUTO_INCREMENT,
      version bigint not null,
      date_created timestamp not null,
      last_updated timestamp not null,
      nombre varchar(255) not null,
      saldo double not null,
      primary key (id)
    );

    create table movimiento (
      id BIGINT       NOT NULL AUTO_INCREMENT,
      version bigint not null,
      billetera_id bigint not null,
      cantidad double not null,
      date_created timestamp not null,
      descripcion varchar(255) not null,
      last_updated timestamp not null,
      primary key (id)
    );

    create table usuario (
      id BIGINT       NOT NULL AUTO_INCREMENT,
      version bigint not null,
      date_created timestamp not null,
      email varchar(255) not null,
      google_id varchar(255) not null,
      last_updated timestamp not null,
      url_imagen_perfil varchar(255) not null,
      primary key (id)
    );

    create table usuario_billeteras (
      billetera_id bigint not null,
      usuario_id bigint not null,
      primary key (usuario_id, billetera_id)
    );

    ALTER TABLE movimiento
      ADD CONSTRAINT FK_cbcft1fmlh36rg4pjnrc00jey
    FOREIGN KEY (billetera_id)
    REFERENCES billetera (id);

    alter table usuario_billeteras
      add constraint FK_kknvojvjicwleoep86l2kvu1n
    foreign key (usuario_id)
    references usuario (id);

    alter table usuario_billeteras
      add constraint FK_2qx4ms9qi9n9pdgekob0n1efd
    foreign key (billetera_id)
    references billetera (id);
