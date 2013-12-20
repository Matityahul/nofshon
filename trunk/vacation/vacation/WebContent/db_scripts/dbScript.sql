#Users
#======
insert into users values (1, 'Lior1989', '123456', CURRENT_DATE(), 'Lior', 'Yaffe', 'Bialik 96 Ramat-Gan', 'lieo@walla.co.il', '0501234567');
insert into users values (2, 'LiorMa', 'Aa123456', CURRENT_DATE(), 'Lior', 'Matityahu', 'Petach-Tikva', 'lala@gmail.com', '05098721234');

#Payment_Methods
#============
insert into payment_methods value (1, 'Credit card');
insert into payment_methods value (2, 'Cash');
insert into payment_methods value (3, 'Paypal');

#Countries
#============
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

#Cities
#===========
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

#Hotels
#============
insert into hotels values (1,'Holiday Inn', 3, 'Hayarkon 92', '03-5812342',300);
insert into hotels values (2,'Sheraton', 3, 'Tel-Aviv', '03-23024245', 370);
insert into hotels values (3, 'Crowne Plaza Berlin',6,'Berlin', '329342842', 450);


#Airports
#===========
insert into airports values(1,3,'Ben-Gurion airport');
insert into airports values(2,1,'JFK airport');

#Airlines
#============
insert into airlines values (1,'El-AL');
insert into airlines values (2,'Delta');
insert into airlines values (3,'Alitalia');
insert into airlines values (4,'Air France');

#Flights
#============
insert into flights values(1,STR_TO_DATE('2-12-2013', '%d-%m-%Y'), STR_TO_DATE('3-12-2013', '%d-%m-%Y'),1,2,1,950);
insert into flights values(2,STR_TO_DATE('9-12-2013', '%d-%m-%Y'), STR_TO_DATE('10-12-2013', '%d-%m-%Y'),2,1,1,950);
insert into flights values(3,STR_TO_DATE('4-12-2013', '%d-%m-%Y'), STR_TO_DATE('4-12-2013', '%d-%m-%Y'),1,2,2,800);
insert into flights values(4,STR_TO_DATE('10-12-2013', '%d-%m-%Y'), STR_TO_DATE('11-12-2013', '%d-%m-%Y'),2,1,2,800);

#Orders
#=============
insert into orders values(1,1,3,STR_TO_DATE('30-11-2013', '%d-%m-%Y'));
insert into orders values(2,2,1,STR_TO_DATE('3-12-2013', '%d-%m-%Y'));

#Bookings
#=======
insert into bookings values(1,1,1,2,1,7,'Lior Yaffe',123456789);
insert into bookings values(2,1,1,2,1,7,'Ploni Almoni',3493432492);





