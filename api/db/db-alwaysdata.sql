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
    ,(2, 'eleve1@mail.fr', 'mdp','Nicolas', 30)
    ,(3, 'eleve2@mail.fr', 'mdp','Elimie', 30)
    ,(4, 'prof1@mail.fr', 'mdp','Vincent', 20)
    ,(5, 'prof2@mail.fr', 'mdp','Camille', 20)
    ,(6, 'prof3@mail.fr', 'mdp','Thomas', 20)
    ,(7, 'prof4@mail.fr', 'mdp','Alexandre', 20)
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
('ref123', 'Cours de mathématiques tous niveaux', 'https://i0.wp.com/www.cours-ado.com/wp-content/uploads/2018/01/geometry-1044090_1920.jpg?resize=1025%2C675&ssl=1', "Rencontrez le succès en mathématiques avec des cours particuliers adaptés à votre niveau !\n\n Enseignant expérimenté, je vous propose un soutien personnalisé pour surmonter les difficultés, que vous soyez au collège, au lycée, en préparation aux examens, ou simplement en quête de consolidation de vos compétences.\n\n Les séances se concentrent sur la clarification des concepts clés et la résolution pratique d'exercices. Ma méthode interactive s'ajuste à votre rythme d'apprentissage pour renforcer votre compréhension des mathématiques. Les cours peuvent avoir lieu à votre domicile ou dans un lieu public selon vos préférences. Des tarifs compétitifs, ajustés en fonction du niveau d'études, sont proposés, avec la possibilité de forfaits mensuels.", 'Chez moi', 'Marseille', 30.00, '2023-12-29 08:00:00', true, 4)
,('ref124', 'Cours de français (niveau collège, lycée)', 'https://lettres.sorbonne-universite.fr/sites/default/files/media/2023-04/evenement_article_rubrique_site_web.png', "Optimisez vos compétences en français avec des cours particuliers adaptés à votre niveau !\n\nEn tant qu'enseignant expérimenté, je vous offre un soutien personnalisé, que vous soyez au collège, au lycée, en préparation aux examens, ou désireux de perfectionner votre maîtrise de la langue.\n\nLes séances se concentrent sur la consolidation des bases grammaticales, l'enrichissement du vocabulaire, et l'amélioration des compétences de lecture et d'écriture. Ma méthode interactive s'adapte à votre rythme d'apprentissage pour renforcer votre confiance en français.", 'Chez vous', 'Marseille', 25.00, '2023-12-29 09:30:00', true, 5)
,('ref125', 'Cours de programmation (Python, Java)', 'https://www.ionos.fr/digitalguide/fileadmin/DigitalGuide/Teaser/code-t.jpg', "Développez vos compétences en programmation avec des cours particuliers adaptés à votre niveau ! En tant que professionnel expérimenté, je vous propose un soutien personnalisé, que vous soyez débutant, étudiant en informatique, ou souhaitiez approfondir vos connaissances en programmation.\nLes séances se concentrent sur la compréhension des concepts fondamentaux, la résolution de problèmes algorithmiques et le développement de projets pratiques.\n\nMa méthode interactive s\'adapte à votre rythme d\'apprentissage pour renforcer votre maîtrise des langages de programmation. Les cours peuvent se dérouler en personne ou en ligne, selon vos préférences. Des tarifs compétitifs, adaptés au niveau d\'études et à la complexité des sujets abordés, sont proposés, avec la possibilité de forfaits mensuels.", 'Chez vous', 'Aix', 40.00, '2023-12-29 11:00:00', true, 6)
,('ref126', 'Cours de musique', 'https://www.symphonie41.fr/media/wysiwyg/cours2.jpg',"Explorez les subtilités du solfège avec des cours particuliers adaptés à votre niveau ! En tant que passionné de musique et pédagogue expérimenté, je vous propose un accompagnement personnalisé, que vous soyez débutant, musicien en herbe ou désireux d'approfondir vos connaissances en solfège.\n\nLes séances se concentreront sur la compréhension des notes, des rythmes, des clés musicales et des principes de la théorie musicale. Ma méthode interactive s'ajuste à votre rythme d'apprentissage, mettant l'accent sur des exercices pratiques et des applications concrètes.", 'Tiers lieu', 'Gardanne', 35.00, '2023-12-29 14:00:00', false, 7)
;

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



