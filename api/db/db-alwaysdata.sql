USE `fwadevidfinalproject_db`;


-- USER (+ ROLE + RATING)

CREATE TABLE role (
   id bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY   
  ,code varchar(10)
  ,name varchar(50)
);

CREATE TABLE user (
   id bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY   
  ,email varchar(50) NOT NULL
  ,password varchar(255) NOT NULL
  ,first_name varchar(50) NOT NULL
  ,idRole bigint(20) NOT NULL
);

ALTER TABLE role
  ADD CONSTRAINT u_role_code UNIQUE(code)
;

ALTER TABLE user
   ADD CONSTRAINT u_user_email UNIQUE(email)
  ,ADD CONSTRAINT fk_user_role 
  FOREIGN KEY(idRole)
  REFERENCES role(id)  
  ON DELETE CASCADE ON UPDATE RESTRICT
;


CREATE TABLE rating (
    id bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY
    ,rating smallint(1) NOT NULL
    ,idRater bigint(20) NOT NULL 
    ,idRated bigint(20) NOT NULL
);

ALTER TABLE rating
  ADD CONSTRAINT fk_user_rater_rating 
  FOREIGN KEY(idRater)
  REFERENCES user(id)  
  ON DELETE CASCADE ON UPDATE RESTRICT
  ,ADD CONSTRAINT fk_user_rated_rating 
  FOREIGN KEY(idRated)
  REFERENCES user(id)  
  ON DELETE CASCADE ON UPDATE RESTRICT
;



INSERT INTO role(id, code, name) VALUES
     (10, 'ADMIN', 'Administrator')
    ,(20, 'TEACH', 'Teacher')
    ,(30, 'LEARN', 'Learner')
;

INSERT INTO user(id, email, password, first_name, idRole) VALUES
    (1, 'gest@mail.fr', 'mdp', 'gestionnaire', 10)
    ,(2, 'learner@mail.fr', 'mdp','learner', 30)
    ,(3, 'teacher1@mail.fr', 'mdp','teacher1', 20)
    ,(4, 'teacher2@mail.fr', 'mdp','teacher2', 20)
    ,(5, 'teacher3@mail.fr', 'mdp','teacher3', 20)
    ,(6, 'teacher4@mail.fr', 'mdp','teacher4', 20)
    ,(7, 'teacher5@mail.fr', 'mdp','teacher5', 20)
;

INSERT INTO rating(id, rating, idRater, idRated) VALUES 
    (1, 4, 2, 3)
    ,(2, 2, 2, 4)
    ,(3, 5, 2, 5)
    ,(4, 1, 2, 6)
;     


/* MESSAGE */

CREATE TABLE message (
    id bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY
    ,content smallint(1) NOT NULL
    ,created_at datetime NOT NULL 
    ,idSender bigint(20) NOT NULL 
    ,idReceiver bigint(20) NOT NULL
);

ALTER TABLE message
  ADD CONSTRAINT fk_user_sender_message
  FOREIGN KEY(idSender)
  REFERENCES user(id) 
  ON DELETE CASCADE ON UPDATE RESTRICT 
  ,ADD CONSTRAINT fk_user_receiver_message
  FOREIGN KEY(idReceiver)
  REFERENCES user(id)  
  ON DELETE CASCADE ON UPDATE RESTRICT
;


/* AD */ 

CREATE TABLE ad (
    id bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY
    ,ad_reference varchar(36) NOT NULL 
    ,title varchar(80) NOT NULL
    ,photo varchar(255) NOT NULL
    ,description text NOT NULL
    ,place varchar(80) NOT NULL
    ,location varchar(80) NOT NULL
    ,price decimal(10, 2) NOT NULL
    ,created_at datetime NOT NULL
    ,approved boolean NOT NULL
    ,idUser bigint(20) NOT NULL
);


ALTER TABLE ad
  ADD CONSTRAINT fk_ad_user
  FOREIGN KEY(idUser)
  REFERENCES user(id)  
  ON DELETE CASCADE ON UPDATE RESTRICT
;



CREATE TABLE subject (
    id bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY
    ,code varchar(10)
    ,name varchar(50)
);

ALTER TABLE subject 
  ADD CONSTRAINT u_subject_code UNIQUE(code)
; 

CREATE TABLE ad_subject (
    idAd bigint(20) NOT NULL
    ,idSubject bigint(20) NOT NULL
); 

ALTER TABLE ad_subject
ADD CONSTRAINT fk_ad_subject_ad  
    FOREIGN KEY (`idAd`) 
    REFERENCES `ad` (`id`)
    ON DELETE CASCADE ON UPDATE RESTRICT
,ADD CONSTRAINT fk_ad_subject_subject
    FOREIGN KEY (`idSubject`)
    REFERENCES `subject` (`id`)
    ON DELETE CASCADE ON UPDATE RESTRICT
,ADD CONSTRAINT u_ad_subject 
    UNIQUE (idAd, idSubject)
;



INSERT INTO ad (ad_reference, title, photo, description, place, location, price, created_at, approved, idUser) VALUES 
('ref123', 'Cours de mathématiques', 'https://i0.wp.com/www.cours-ado.com/wp-content/uploads/2018/01/geometry-1044090_1920.jpg?resize=1025%2C675&ssl=1', 'Cours de mathématiques avancées pour tous les niveaux.', 'Chez moi', 'Marseille', 30.00, '2023-12-29 08:00:00', true, 3)
,('ref124', 'Cours de français', 'https://lettres.sorbonne-universite.fr/sites/default/files/media/2023-04/evenement_article_rubrique_site_web.png', 'Cours de français pour débutants et intermédiaires.', 'Chez vous', 'Marseille', 25.00, '2023-12-29 09:30:00', true, 4)
,('ref125', 'Cours de programmation', 'https://www.ionos.fr/digitalguide/fileadmin/DigitalGuide/Teaser/code-t.jpg', 'Cours de programmation en Java et Python.', 'Chez vous', 'Aix', 40.00, '2023-12-29 11:00:00', true, 5)
,('ref126', 'Cours de musique', 'https://www.symphonie41.fr/media/wysiwyg/cours2.jpg', 'Cours de piano et de guitare pour tous les âges.', 'Tiers lieu', 'Gardanne', 35.00, '2023-12-29 14:00:00', false, 6)
,('ref127', 'Cours de langues étrangères', 'https://www.lacouture62.fr/wp-content/uploads/2020/02/cours-langue.jpg', 'Cours anglais, espagnol et allemand.', 'Chez moi', 'Toulon', 30.00, '2023-12-29 16:30:00', false, 7);


INSERT INTO subject (code, name) VALUES 
('MATH', 'Mathématiques')
,('FREN', 'Français')
,('PROG', 'Programmation')
,('MUSI', 'Musique')
,('LANG', 'Langues étrangères')
;

INSERT INTO ad_subject (idAd, idSubject) VALUES 
(1, 1) 
,(2, 2) 
,(3, 3)
,(4, 4)
,(5, 5)
;


CREATE TABLE favorite (
    idUser bigint(20) NOT NULL
    ,idAd bigint(20) NOT NULL
);

ALTER TABLE favorite
ADD CONSTRAINT fk_favorite_user 
    FOREIGN KEY (`idUser`) 
    REFERENCES `user` (`id`)
    ON DELETE CASCADE ON UPDATE RESTRICT
,ADD CONSTRAINT fk_favorite_ad
    FOREIGN KEY (`idAd`)
    REFERENCES `ad` (`id`)
    ON DELETE CASCADE ON UPDATE RESTRICT
,ADD CONSTRAINT u_favorite_user_ad
    UNIQUE (idUser, idAd)
;


INSERT INTO favorite (idUser, idAd) 
VALUES (1,1),(1,2),(2,3),(2,1),(2,4); 



