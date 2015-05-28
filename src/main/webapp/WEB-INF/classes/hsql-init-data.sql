insert into users (name, firstName, email, password) values ('Dupont', 'Jérôme', 'jerome@dupont.com', 'jerome');
insert into users (name, firstName, email, password) values ('Legrand', 'Didier', 'didier@legrand.com', 'didier');
insert into users (name, firstName, email, password) values ('Grangé', 'Bertrand', 'bertrand@grange.com', 'bertrand');
insert into users (name, firstName, email, password) values ('Hautbois', 'Sylvie', 'sylvie@hautbois.com', 'sylvie');


insert into topics (title, public) values ('Voyage de fin d''année',true);

insert into messages (text, dateAndTime, owner, topic) values ('Où est-ce que vous voulez partir pour le voyage de la boite cette année ?', NOW(), 0, 0);
insert into messages (text, dateAndTime, owner, topic) values ('Moi ça m''est égal, tant qu''il y fait beau et chaud !', NOW(), 1, 0);
insert into messages (text, dateAndTime, owner, topic) values ('J''ai toujours rêvé de visiter Madère...', NOW(), 2, 0);
insert into messages (text, dateAndTime, owner, topic) values ('Madère ? C''est où ça ? Moi j''aimerais mieux aller en Norvège ou en Suède.', NOW(), 3, 0);
insert into messages (text, dateAndTime, owner, topic) values ('Bon, je vois avec l''agence de voyage ce qui est possible dans notre budget, et je reviens vers vous.', NOW(), 0, 0);


insert into topics (title, public) values ('Anniversaire Jéjé',false);

insert into shares (topic, reader) values (1, 1);
insert into shares (topic, reader) values (1, 2);
insert into shares (topic, reader) values (1, 3);

insert into messages (text, dateAndTime, owner, topic) values ('C''est bientôt l''anniv de Jéjé. Qui pour un cadeau ?', NOW(), 2, 1);
insert into messages (text, dateAndTime, owner, topic) values ('Son GPS est mort depuis la semaine dernière. Ca pourrait le faire, non ?', NOW(), 3, 1);
insert into messages (text, dateAndTime, owner, topic) values ('C''est un peu cher je trouve...', NOW(), 1, 1);
insert into messages (text, dateAndTime, owner, topic) values ('T''es pas un peu radin Didier ? On va bien être une dizaine sur le cadeau !', NOW(), 2, 1);
