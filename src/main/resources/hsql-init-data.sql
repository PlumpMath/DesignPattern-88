insert into users (name, firstName, email, password) values ('Dupont', 'Jérôme', 'jerome@dupont.com', 'jerome');
insert into users (name, firstName, email, password) values ('Legrand', 'Didier', 'didier@legrand.com', 'didier');
insert into users (name, firstName, email, password) values ('Grangé', 'Bertrand', 'bertrand@grange.com', 'bertrand');
insert into users (name, firstName, email, password) values ('Hautbois', 'Sylvie', 'sylvie@hautbois.com', 'sylvie');
insert into users (name, firstName, email, password) values ('toto', 'toto', 'toto', 'toto');


insert into topics (title, publicTopic, creatorId) values ('Voyage de fin d''année',true, 1);

insert into messages (text, dateAndTime, ownerId, topicId) values ('Où est-ce que vous voulez partir pour le voyage de la boite cette année ?', NOW(), 1, 1);
insert into messages (text, dateAndTime, ownerId, topicId) values ('Moi ça m''est égal, tant qu''il y fait beau et chaud !', NOW(), 2, 1);
insert into messages (text, dateAndTime, ownerId, topicId) values ('J''ai toujours rêvé de visiter Madère...', NOW(), 3, 1);
insert into messages (text, dateAndTime, ownerId, topicId) values ('Madère ? C''est où ça ? Moi j''aimerais mieux aller en Norvège ou en Suède.', NOW(), 4, 1);
insert into messages (text, dateAndTime, ownerId, topicId) values ('Bon, je vois avec l''agence de voyage ce qui est possible dans notre budget, et je reviens vers vous.', NOW(), 1, 1);


insert into topics (title, publicTopic, creatorId) values ('Anniversaire Jéjé',false, 3);

insert into shares (topicId, readerId) values (2, 2);
insert into shares (topicId, readerId) values (2, 3);
insert into shares (topicId, readerId) values (2, 4);
insert into shares (topicId, readerId) values (2, 5);

insert into messages (text, dateAndTime, ownerId, topicId) values ('C''est bientôt l''anniv de Jéjé. Qui pour un cadeau ?', NOW(), 3, 2);
insert into messages (text, dateAndTime, ownerId, topicId) values ('Son GPS est mort depuis la semaine dernière. Ca pourrait le faire, non ?', NOW(), 4, 2);
insert into messages (text, dateAndTime, ownerId, topicId) values ('C''est un peu cher je trouve...', NOW(), 2, 2);
insert into messages (text, dateAndTime, ownerId, topicId) values ('T''es pas un peu radin Didier ? On va bien être une dizaine sur le cadeau !', NOW(), 3, 2);
