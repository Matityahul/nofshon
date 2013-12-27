
insert into users values (1, 'Lior1989', '123456', CURRENT_DATE(), 'Lior', 'Yaffe', 'Bialik 96 Ramat-Gan', 'lieo@walla.co.il', '0501234567');
insert into users values (2, 'LiorMa', 'Aa123456', CURRENT_DATE(), 'Lior', 'Matityahu', 'Petach-Tikva', 'lala@gmail.com', '05098721234');


insert into payment_methods value (1, 'Credit card');
insert into payment_methods value (2, 'Cash');
insert into payment_methods value (3, 'Paypal');


insert into countries values(1,'USA');
insert into countries values(2,'Israel');
insert into countries values(3,'Italy');
insert into countries values(4,'Germany');
insert into countries values(5,'Turkey');
insert into countries values(6,'Greece');
insert into countries values(7,'France');
insert into countries values(8,'Spain');
insert into countries values(9,'Mexico');
insert into countries values(10,'Japan');


insert into cities values(1,1,'New-York');
insert into cities values(2,1,'Boston');
insert into cities values(3,2,'Tel-Aviv');
insert into cities values(4,3,'Rome');
insert into cities values(5,4,'Munich');
insert into cities values(6,4,'Berlin');
insert into cities values(7,5,'Istanbul');
insert into cities values(8,6,'Athens');
insert into cities values(9,7,'Paris');
insert into cities values(10,8,'Madrid');
insert into cities values(11,8,'Barcelona');
insert into cities values(12,9,'Mexico City');
insert into cities values(13,10,'Tokyo');

insert into hotels values (1,'Holiday Inn', 3, 'Hayarkon 92', '03-5812342',300);
insert into hotels values (2,'Sheraton', 3, 'Tel-Aviv', '03-23024245', 370);
insert into hotels values (3, 'Crowne Plaza Berlin',6,'Berlin', '329342842', 450);
insert into hotels values (4, 'New York Skyline',1,'725 10th Avenue', '2125863400', 700);

insert into airports values(1,3,'Ben-Gurion airport');
insert into airports values(2,1,'JFK airport');
insert into airports values(3,6,'Schonefeld Airport');

insert into airlines values (1,'El-AL');
insert into airlines values (2,'Delta');
insert into airlines values (3,'Alitalia');
insert into airlines values (4,'Air France');

insert into flights values(1,STR_TO_DATE('1-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('1-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950);

insert into flights values(2,STR_TO_DATE('1-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('2-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000);

insert into flights values(3,STR_TO_DATE('1-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('2-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800);

insert into flights values(4,STR_TO_DATE('2-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('2-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950);

insert into flights values(5,STR_TO_DATE('2-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('3-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000);

insert into flights values(6,STR_TO_DATE('2-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('3-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800);

insert into flights values(7,STR_TO_DATE('3-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('3-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950);

insert into flights values(8,STR_TO_DATE('3-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('4-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000);

insert into flights values(9,STR_TO_DATE('3-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('4-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800);

insert into flights values(10,STR_TO_DATE('4-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('4-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950);

insert into flights values(11,STR_TO_DATE('4-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('5-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000);

insert into flights values(12,STR_TO_DATE('4-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('5-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800);

insert into flights values(13,STR_TO_DATE('5-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('5-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950);

insert into flights values(14,STR_TO_DATE('5-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('6-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000);

insert into flights values(15,STR_TO_DATE('5-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('6-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800);

insert into flights values(16,STR_TO_DATE('6-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('6-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950);

insert into flights values(17,STR_TO_DATE('6-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('7-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000);

insert into flights values(18,STR_TO_DATE('6-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('7-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800);

insert into flights values(19,STR_TO_DATE('7-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('7-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950);

insert into flights values(20,STR_TO_DATE('7-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('8-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000);

insert into flights values(21,STR_TO_DATE('7-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('8-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800);

insert into flights values(22,STR_TO_DATE('8-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('8-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950);

insert into flights values(23,STR_TO_DATE('8-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('9-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000);

insert into flights values(24,STR_TO_DATE('8-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('9-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800);

insert into flights values(25,STR_TO_DATE('9-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('9-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950);

insert into flights values(26,STR_TO_DATE('9-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('10-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000);

insert into flights values(27,STR_TO_DATE('9-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('10-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800);

insert into flights values(28,STR_TO_DATE('10-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('10-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950);

insert into flights values(29,STR_TO_DATE('10-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('11-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000);

insert into flights values(30,STR_TO_DATE('10-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('11-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800);

insert into flights values(31,STR_TO_DATE('11-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('11-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950);

insert into flights values(32,STR_TO_DATE('11-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('12-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000);

insert into flights values(33,STR_TO_DATE('11-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('12-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800);

insert into flights values(34,STR_TO_DATE('12-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('12-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950);

insert into flights values(35,STR_TO_DATE('12-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('13-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000);

insert into flights values(36,STR_TO_DATE('12-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('13-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800);

insert into flights values(37,STR_TO_DATE('13-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('13-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950);

insert into flights values(38,STR_TO_DATE('13-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('14-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000);

insert into flights values(39,STR_TO_DATE('13-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('14-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800);

insert into flights values(40,STR_TO_DATE('14-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('14-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950);

insert into flights values(41,STR_TO_DATE('14-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('15-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000);

insert into flights values(42,STR_TO_DATE('14-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('15-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800);

insert into flights values(43,STR_TO_DATE('15-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('15-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950);

insert into flights values(44,STR_TO_DATE('15-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('16-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000);

insert into flights values(45,STR_TO_DATE('15-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('16-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800);

insert into flights values(46,STR_TO_DATE('16-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('16-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950);

insert into flights values(47,STR_TO_DATE('16-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('17-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000);

insert into flights values(48,STR_TO_DATE('16-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('17-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800);

insert into flights values(49,STR_TO_DATE('17-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('17-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950);

insert into flights values(50,STR_TO_DATE('17-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('18-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000);

insert into flights values(51,STR_TO_DATE('17-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('18-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800);

insert into flights values(52,STR_TO_DATE('18-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('18-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950);

insert into flights values(53,STR_TO_DATE('18-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('19-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000);

insert into flights values(54,STR_TO_DATE('18-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('19-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800);

insert into flights values(55,STR_TO_DATE('19-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('19-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950);

insert into flights values(56,STR_TO_DATE('19-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('20-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000);

insert into flights values(57,STR_TO_DATE('19-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('20-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800);

insert into flights values(58,STR_TO_DATE('20-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('20-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950);

insert into flights values(59,STR_TO_DATE('20-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('21-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000);

insert into flights values(60,STR_TO_DATE('20-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('21-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800);

insert into flights values(61,STR_TO_DATE('21-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('21-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950);

insert into flights values(62,STR_TO_DATE('21-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('22-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000);

insert into flights values(63,STR_TO_DATE('21-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('22-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800);

insert into flights values(64,STR_TO_DATE('22-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('22-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950);

insert into flights values(65,STR_TO_DATE('22-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('23-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000);

insert into flights values(66,STR_TO_DATE('22-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('23-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800);

insert into flights values(67,STR_TO_DATE('23-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('23-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950);

insert into flights values(68,STR_TO_DATE('23-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('24-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000);

insert into flights values(69,STR_TO_DATE('23-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('24-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800);

insert into flights values(70,STR_TO_DATE('24-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('24-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950);

insert into flights values(71,STR_TO_DATE('24-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('25-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000);

insert into flights values(72,STR_TO_DATE('24-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('25-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800);

insert into flights values(73,STR_TO_DATE('25-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('25-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950);

insert into flights values(74,STR_TO_DATE('25-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('26-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000);

insert into flights values(75,STR_TO_DATE('25-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('26-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800);

insert into flights values(76,STR_TO_DATE('26-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('26-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950);

insert into flights values(77,STR_TO_DATE('26-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('27-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000);

insert into flights values(78,STR_TO_DATE('26-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('27-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800);

insert into flights values(79,STR_TO_DATE('27-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('27-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950);

insert into flights values(80,STR_TO_DATE('27-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('28-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000);

insert into flights values(81,STR_TO_DATE('27-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('28-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800);

insert into flights values(82,STR_TO_DATE('28-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('28-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950);

insert into flights values(83,STR_TO_DATE('28-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('29-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000);

insert into flights values(84,STR_TO_DATE('28-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('29-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800);

insert into flights values(85,STR_TO_DATE('29-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('29-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,950);

insert into flights values(86,STR_TO_DATE('29-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('30-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),1,2,1,1000);

insert into flights values(87,STR_TO_DATE('29-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('30-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),1,2,2,800);


insert into flights values(88,STR_TO_DATE('1-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('1-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950);

insert into flights values(89,STR_TO_DATE('1-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('2-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000);

insert into flights values(90,STR_TO_DATE('1-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('2-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800);

insert into flights values(91,STR_TO_DATE('2-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('2-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950);

insert into flights values(92,STR_TO_DATE('2-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('3-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000);

insert into flights values(93,STR_TO_DATE('2-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('3-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800);

insert into flights values(94,STR_TO_DATE('3-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('3-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950);

insert into flights values(95,STR_TO_DATE('3-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('4-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000);

insert into flights values(96,STR_TO_DATE('3-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('4-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800);

insert into flights values(97,STR_TO_DATE('4-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('4-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950);

insert into flights values(98,STR_TO_DATE('4-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('5-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000);

insert into flights values(99,STR_TO_DATE('4-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('5-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800);

insert into flights values(100,STR_TO_DATE('5-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('5-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950);

insert into flights values(101,STR_TO_DATE('5-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('6-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000);

insert into flights values(102,STR_TO_DATE('5-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('6-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800);

insert into flights values(103,STR_TO_DATE('6-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('6-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950);

insert into flights values(104,STR_TO_DATE('6-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('7-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000);

insert into flights values(105,STR_TO_DATE('6-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('7-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800);

insert into flights values(106,STR_TO_DATE('7-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('7-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950);

insert into flights values(107,STR_TO_DATE('7-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('8-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000);

insert into flights values(108,STR_TO_DATE('7-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('8-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800);

insert into flights values(109,STR_TO_DATE('8-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('8-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950);

insert into flights values(110,STR_TO_DATE('8-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('9-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000);

insert into flights values(111,STR_TO_DATE('8-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('9-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800);

insert into flights values(112,STR_TO_DATE('9-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('9-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950);

insert into flights values(113,STR_TO_DATE('9-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('10-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000);

insert into flights values(114,STR_TO_DATE('9-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('10-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800);

insert into flights values(115,STR_TO_DATE('10-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('10-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950);

insert into flights values(116,STR_TO_DATE('10-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('11-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000);

insert into flights values(117,STR_TO_DATE('10-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('11-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800);

insert into flights values(118,STR_TO_DATE('11-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('11-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950);

insert into flights values(119,STR_TO_DATE('11-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('12-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000);

insert into flights values(120,STR_TO_DATE('11-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('12-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800);

insert into flights values(121,STR_TO_DATE('12-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('12-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950);

insert into flights values(122,STR_TO_DATE('12-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('13-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000);

insert into flights values(123,STR_TO_DATE('12-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('13-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800);

insert into flights values(124,STR_TO_DATE('13-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('13-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950);

insert into flights values(125,STR_TO_DATE('13-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('14-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000);

insert into flights values(126,STR_TO_DATE('13-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('14-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800);

insert into flights values(127,STR_TO_DATE('14-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('14-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950);

insert into flights values(128,STR_TO_DATE('14-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('15-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000);

insert into flights values(129,STR_TO_DATE('14-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('15-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800);

insert into flights values(130,STR_TO_DATE('15-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('15-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950);

insert into flights values(131,STR_TO_DATE('15-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('16-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000);

insert into flights values(132,STR_TO_DATE('15-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('16-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800);

insert into flights values(133,STR_TO_DATE('16-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('16-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950);

insert into flights values(134,STR_TO_DATE('16-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('17-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000);

insert into flights values(135,STR_TO_DATE('16-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('17-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800);

insert into flights values(136,STR_TO_DATE('17-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('17-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950);

insert into flights values(137,STR_TO_DATE('17-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('18-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000);

insert into flights values(138,STR_TO_DATE('17-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('18-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800);

insert into flights values(139,STR_TO_DATE('18-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('18-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950);

insert into flights values(140,STR_TO_DATE('18-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('19-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000);

insert into flights values(141,STR_TO_DATE('18-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('19-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800);

insert into flights values(142,STR_TO_DATE('19-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('19-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950);

insert into flights values(143,STR_TO_DATE('19-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('20-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000);

insert into flights values(144,STR_TO_DATE('19-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('20-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800);

insert into flights values(145,STR_TO_DATE('20-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('20-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950);

insert into flights values(146,STR_TO_DATE('20-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('21-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000);

insert into flights values(147,STR_TO_DATE('20-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('21-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800);

insert into flights values(148,STR_TO_DATE('21-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('21-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950);

insert into flights values(149,STR_TO_DATE('21-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('22-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000);

insert into flights values(150,STR_TO_DATE('21-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('22-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800);

insert into flights values(151,STR_TO_DATE('22-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('22-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950);

insert into flights values(152,STR_TO_DATE('22-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('23-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000);

insert into flights values(153,STR_TO_DATE('22-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('23-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800);

insert into flights values(154,STR_TO_DATE('23-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('23-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950);

insert into flights values(155,STR_TO_DATE('23-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('24-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000);

insert into flights values(156,STR_TO_DATE('23-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('24-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800);

insert into flights values(157,STR_TO_DATE('24-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('24-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950);

insert into flights values(158,STR_TO_DATE('24-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('25-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000);

insert into flights values(159,STR_TO_DATE('24-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('25-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800);

insert into flights values(160,STR_TO_DATE('25-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('25-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950);

insert into flights values(161,STR_TO_DATE('25-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('26-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000);

insert into flights values(162,STR_TO_DATE('25-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('26-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800);

insert into flights values(163,STR_TO_DATE('26-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('26-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950);

insert into flights values(164,STR_TO_DATE('26-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('27-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000);

insert into flights values(165,STR_TO_DATE('26-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('27-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800);

insert into flights values(166,STR_TO_DATE('27-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('27-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950);

insert into flights values(167,STR_TO_DATE('27-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('28-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000);

insert into flights values(168,STR_TO_DATE('27-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('28-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800);

insert into flights values(169,STR_TO_DATE('28-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('28-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950);

insert into flights values(170,STR_TO_DATE('28-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('29-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000);

insert into flights values(171,STR_TO_DATE('28-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('29-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800);

insert into flights values(172,STR_TO_DATE('29-1-2014 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('29-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,950);

insert into flights values(173,STR_TO_DATE('29-1-2014 16:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('30-1-2014 03:00:00', '%d-%m-%Y %H:%i:%s'),2,1,1,1000);

insert into flights values(174,STR_TO_DATE('29-1-2014 21:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('30-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'),2,1,2,800);


insert into flights values(175,STR_TO_DATE('1-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('1-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350);

insert into flights values(176,STR_TO_DATE('1-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('1-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450);

insert into flights values(177,STR_TO_DATE('1-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('1-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300);

insert into flights values(178,STR_TO_DATE('2-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('2-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350);

insert into flights values(179,STR_TO_DATE('2-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('2-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450);

insert into flights values(180,STR_TO_DATE('2-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('2-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300);

insert into flights values(181,STR_TO_DATE('3-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('3-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350);

insert into flights values(182,STR_TO_DATE('3-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('3-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450);

insert into flights values(183,STR_TO_DATE('3-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('3-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300);

insert into flights values(184,STR_TO_DATE('4-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('4-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350);

insert into flights values(185,STR_TO_DATE('4-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('4-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450);

insert into flights values(186,STR_TO_DATE('4-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('4-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300);

insert into flights values(187,STR_TO_DATE('5-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('5-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350);

insert into flights values(188,STR_TO_DATE('5-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('5-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450);

insert into flights values(189,STR_TO_DATE('5-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('5-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300);

insert into flights values(190,STR_TO_DATE('6-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('6-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350);

insert into flights values(191,STR_TO_DATE('6-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('6-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450);

insert into flights values(192,STR_TO_DATE('6-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('6-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300);

insert into flights values(193,STR_TO_DATE('7-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('7-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350);

insert into flights values(194,STR_TO_DATE('7-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('7-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450);

insert into flights values(195,STR_TO_DATE('7-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('7-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300);

insert into flights values(196,STR_TO_DATE('8-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('8-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350);

insert into flights values(197,STR_TO_DATE('8-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('8-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450);

insert into flights values(198,STR_TO_DATE('8-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('8-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300);

insert into flights values(199,STR_TO_DATE('9-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('9-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350);

insert into flights values(200,STR_TO_DATE('9-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('9-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450);

insert into flights values(201,STR_TO_DATE('9-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('9-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300);

insert into flights values(202,STR_TO_DATE('10-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('10-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350);

insert into flights values(203,STR_TO_DATE('10-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('10-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450);

insert into flights values(204,STR_TO_DATE('10-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('10-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300);

insert into flights values(205,STR_TO_DATE('11-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('11-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350);

insert into flights values(206,STR_TO_DATE('11-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('11-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450);

insert into flights values(207,STR_TO_DATE('11-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('11-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300);

insert into flights values(208,STR_TO_DATE('12-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('12-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350);

insert into flights values(209,STR_TO_DATE('12-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('12-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450);

insert into flights values(210,STR_TO_DATE('12-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('12-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300);

insert into flights values(211,STR_TO_DATE('13-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('13-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350);

insert into flights values(212,STR_TO_DATE('13-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('13-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450);

insert into flights values(213,STR_TO_DATE('13-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('13-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300);

insert into flights values(214,STR_TO_DATE('14-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('14-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350);

insert into flights values(215,STR_TO_DATE('14-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('14-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450);

insert into flights values(216,STR_TO_DATE('14-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('14-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300);

insert into flights values(217,STR_TO_DATE('15-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('15-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350);

insert into flights values(218,STR_TO_DATE('15-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('15-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450);

insert into flights values(219,STR_TO_DATE('15-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('15-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300);

insert into flights values(220,STR_TO_DATE('16-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('16-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350);

insert into flights values(221,STR_TO_DATE('16-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('16-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450);

insert into flights values(222,STR_TO_DATE('16-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('16-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300);

insert into flights values(223,STR_TO_DATE('17-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('17-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350);

insert into flights values(224,STR_TO_DATE('17-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('17-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450);

insert into flights values(225,STR_TO_DATE('17-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('17-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300);

insert into flights values(226,STR_TO_DATE('18-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('18-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350);

insert into flights values(227,STR_TO_DATE('18-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('18-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450);

insert into flights values(228,STR_TO_DATE('18-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('18-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300);

insert into flights values(229,STR_TO_DATE('19-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('19-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350);

insert into flights values(230,STR_TO_DATE('19-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('19-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450);

insert into flights values(231,STR_TO_DATE('19-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('19-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300);

insert into flights values(232,STR_TO_DATE('20-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('20-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350);

insert into flights values(233,STR_TO_DATE('20-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('20-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450);

insert into flights values(234,STR_TO_DATE('20-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('20-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300);

insert into flights values(235,STR_TO_DATE('21-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('21-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350);

insert into flights values(236,STR_TO_DATE('21-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('21-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450);

insert into flights values(237,STR_TO_DATE('21-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('21-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300);

insert into flights values(238,STR_TO_DATE('22-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('22-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350);

insert into flights values(239,STR_TO_DATE('22-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('22-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450);

insert into flights values(240,STR_TO_DATE('22-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('22-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300);

insert into flights values(241,STR_TO_DATE('23-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('23-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350);

insert into flights values(242,STR_TO_DATE('23-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('23-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450);

insert into flights values(243,STR_TO_DATE('23-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('23-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300);

insert into flights values(244,STR_TO_DATE('24-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('24-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350);

insert into flights values(245,STR_TO_DATE('24-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('24-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450);

insert into flights values(246,STR_TO_DATE('24-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('24-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300);

insert into flights values(247,STR_TO_DATE('25-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('25-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350);

insert into flights values(248,STR_TO_DATE('25-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('25-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450);

insert into flights values(249,STR_TO_DATE('25-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('25-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300);

insert into flights values(250,STR_TO_DATE('26-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('26-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350);

insert into flights values(251,STR_TO_DATE('26-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('26-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450);

insert into flights values(252,STR_TO_DATE('26-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('26-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300);

insert into flights values(253,STR_TO_DATE('27-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('27-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350);

insert into flights values(254,STR_TO_DATE('27-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('27-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450);

insert into flights values(255,STR_TO_DATE('27-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('27-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300);

insert into flights values(256,STR_TO_DATE('28-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('28-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350);

insert into flights values(257,STR_TO_DATE('28-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('28-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450);

insert into flights values(258,STR_TO_DATE('28-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('28-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300);

insert into flights values(259,STR_TO_DATE('29-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('29-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350);

insert into flights values(260,STR_TO_DATE('29-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('29-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450);

insert into flights values(261,STR_TO_DATE('29-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('29-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300);

insert into flights values(262,STR_TO_DATE('30-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('30-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,350);

insert into flights values(263,STR_TO_DATE('30-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('30-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,450);

insert into flights values(264,STR_TO_DATE('30-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('30-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),1,3,1,300);


insert into flights values(265,STR_TO_DATE('1-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('1-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350);

insert into flights values(266,STR_TO_DATE('1-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('1-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450);

insert into flights values(267,STR_TO_DATE('1-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('1-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300);

insert into flights values(268,STR_TO_DATE('2-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('2-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350);

insert into flights values(269,STR_TO_DATE('2-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('2-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450);

insert into flights values(270,STR_TO_DATE('2-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('2-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300);

insert into flights values(271,STR_TO_DATE('3-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('3-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350);

insert into flights values(272,STR_TO_DATE('3-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('3-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450);

insert into flights values(273,STR_TO_DATE('3-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('3-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300);

insert into flights values(274,STR_TO_DATE('4-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('4-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350);

insert into flights values(275,STR_TO_DATE('4-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('4-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450);

insert into flights values(276,STR_TO_DATE('4-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('4-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300);

insert into flights values(277,STR_TO_DATE('5-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('5-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350);

insert into flights values(278,STR_TO_DATE('5-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('5-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450);

insert into flights values(279,STR_TO_DATE('5-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('5-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300);

insert into flights values(280,STR_TO_DATE('6-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('6-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350);

insert into flights values(281,STR_TO_DATE('6-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('6-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450);

insert into flights values(282,STR_TO_DATE('6-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('6-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300);

insert into flights values(283,STR_TO_DATE('7-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('7-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350);

insert into flights values(284,STR_TO_DATE('7-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('7-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450);

insert into flights values(285,STR_TO_DATE('7-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('7-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300);

insert into flights values(286,STR_TO_DATE('8-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('8-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350);

insert into flights values(287,STR_TO_DATE('8-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('8-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450);

insert into flights values(288,STR_TO_DATE('8-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('8-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300);

insert into flights values(289,STR_TO_DATE('9-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('9-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350);

insert into flights values(290,STR_TO_DATE('9-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('9-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450);

insert into flights values(291,STR_TO_DATE('9-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('9-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300);

insert into flights values(292,STR_TO_DATE('10-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('10-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350);

insert into flights values(293,STR_TO_DATE('10-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('10-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450);

insert into flights values(294,STR_TO_DATE('10-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('10-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300);

insert into flights values(295,STR_TO_DATE('11-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('11-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350);

insert into flights values(296,STR_TO_DATE('11-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('11-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450);

insert into flights values(297,STR_TO_DATE('11-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('11-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300);

insert into flights values(298,STR_TO_DATE('12-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('12-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350);

insert into flights values(299,STR_TO_DATE('12-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('12-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450);

insert into flights values(300,STR_TO_DATE('12-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('12-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300);

insert into flights values(301,STR_TO_DATE('13-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('13-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350);

insert into flights values(302,STR_TO_DATE('13-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('13-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450);

insert into flights values(303,STR_TO_DATE('13-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('13-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300);

insert into flights values(304,STR_TO_DATE('14-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('14-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350);

insert into flights values(305,STR_TO_DATE('14-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('14-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450);

insert into flights values(306,STR_TO_DATE('14-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('14-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300);

insert into flights values(307,STR_TO_DATE('15-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('15-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350);

insert into flights values(308,STR_TO_DATE('15-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('15-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450);

insert into flights values(309,STR_TO_DATE('15-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('15-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300);

insert into flights values(310,STR_TO_DATE('16-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('16-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350);

insert into flights values(311,STR_TO_DATE('16-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('16-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450);

insert into flights values(312,STR_TO_DATE('16-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('16-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300);

insert into flights values(313,STR_TO_DATE('17-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('17-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350);

insert into flights values(314,STR_TO_DATE('17-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('17-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450);

insert into flights values(315,STR_TO_DATE('17-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('17-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300);

insert into flights values(316,STR_TO_DATE('18-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('18-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350);

insert into flights values(317,STR_TO_DATE('18-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('18-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450);

insert into flights values(318,STR_TO_DATE('18-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('18-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300);

insert into flights values(319,STR_TO_DATE('19-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('19-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350);

insert into flights values(320,STR_TO_DATE('19-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('19-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450);

insert into flights values(321,STR_TO_DATE('19-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('19-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300);

insert into flights values(322,STR_TO_DATE('20-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('20-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350);

insert into flights values(323,STR_TO_DATE('20-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('20-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450);

insert into flights values(324,STR_TO_DATE('20-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('20-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300);

insert into flights values(325,STR_TO_DATE('21-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('21-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350);

insert into flights values(326,STR_TO_DATE('21-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('21-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450);

insert into flights values(327,STR_TO_DATE('21-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('21-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300);

insert into flights values(328,STR_TO_DATE('22-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('22-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350);

insert into flights values(329,STR_TO_DATE('22-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('22-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450);

insert into flights values(330,STR_TO_DATE('22-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('22-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300);

insert into flights values(331,STR_TO_DATE('23-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('23-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350);

insert into flights values(332,STR_TO_DATE('23-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('23-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450);

insert into flights values(333,STR_TO_DATE('23-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('23-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300);

insert into flights values(334,STR_TO_DATE('24-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('24-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350);

insert into flights values(335,STR_TO_DATE('24-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('24-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450);

insert into flights values(336,STR_TO_DATE('24-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('24-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300);

insert into flights values(337,STR_TO_DATE('25-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('25-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350);

insert into flights values(338,STR_TO_DATE('25-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('25-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450);

insert into flights values(339,STR_TO_DATE('25-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('25-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300);

insert into flights values(340,STR_TO_DATE('26-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('26-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350);

insert into flights values(341,STR_TO_DATE('26-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('26-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450);

insert into flights values(342,STR_TO_DATE('26-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('26-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300);

insert into flights values(343,STR_TO_DATE('27-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('27-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350);

insert into flights values(344,STR_TO_DATE('27-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('27-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450);

insert into flights values(345,STR_TO_DATE('27-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('27-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300);

insert into flights values(346,STR_TO_DATE('28-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('28-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350);

insert into flights values(347,STR_TO_DATE('28-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('28-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450);

insert into flights values(348,STR_TO_DATE('28-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('28-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300);

insert into flights values(349,STR_TO_DATE('29-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('29-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350);

insert into flights values(350,STR_TO_DATE('29-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('29-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450);

insert into flights values(351,STR_TO_DATE('29-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('29-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300);

insert into flights values(352,STR_TO_DATE('30-1-2014 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('30-1-2014 12:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,350);

insert into flights values(353,STR_TO_DATE('30-1-2014 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('30-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,450);

insert into flights values(354,STR_TO_DATE('30-1-2014 19:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('30-1-2014 23:00:00', '%d-%m-%Y %H:%i:%s'),3,1,1,300);


insert into orders values(1,1,3,STR_TO_DATE('30-11-2013', '%d-%m-%Y'));
insert into orders values(2,2,1,STR_TO_DATE('3-12-2013', '%d-%m-%Y'));


insert into bookings values(1,1,1,2,1,7,'Lior Yaffe',123459);
insert into bookings values(2,1,1,2,1,7,'Ploni Almoni',349492);





