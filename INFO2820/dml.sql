INSERT INTO Country(
    VALUES('AU','Australia')
);

INSERT INTO Country(
    VALUES('CN','China')
);

INSERT INTO Country(
    VALUES('US','United States')
);

INSERT INTO Location(
    VALUES('Fisher','Area','Fisher')
);

INSERT INTO Location(
    VALUES('SIT','Area','SIT')
);

INSERT INTO Place(
    VALUES('SIT','SIT',0,0,' University of Sydney, J12/1 Cleveland St, Darlington NSW 2008','Venue')
);

INSERT INTO Place(
    VALUES ('Fisher','Fisher',1.1,1.1,'F03, University of Sydney, Eastern Ave, Camperdown NSW 2006','Accommodation')
);

--INSERT INTO Accommodation(
--    VALUES('Fisher')
--);

INSERT INTO Member(
    VALUES('1000000000','Mr','Su','Corey','AU',NULL,'Athlete')
);

INSERT INTO Member(
    VALUES('1000000001','Mr','Zhang','Xiangxi','AU',NULL,'Official')
);

INSERT INTO Member(
    VALUES('1000000002','Mr','Yang','Xuewei','CN',NULL,'Athlete')
);

INSERT INTO Member(
    VALUES('1000000003','Miss','Underwood','Clare','US',NULL,'Staff')
);

--INSERT INTO Athlete(
--    VALUES(1000000000)
--);

--INSERT INTO Athlete(
--    VALUES(100000002)
--);

INSERT INTO Sport(
    VALUES('100 Meters Sprint')
);

INSERT INTO Sport(
    VALUES('Basketball')
);


--INSERT INTO Venue(
--    VALUES('SIT')
--);

INSERT INTO Event(
    VALUES('100 Meters Sprint for Computer Scientiest','8:00:00','2017-05-01','Seconds','100 Meters Sprint','SIT','individual')
);

INSERT INTO Event(
    VALUES('Man Basketball for Electrical and Computer Engineering','8:00:00','2017-05-02','Score','Basketball','SIT','team')
);

INSERT INTO Team(
    VALUES('Gardians of Galaxy','Man Basketball for Electrical and Computer Engineering','AU')
);

INSERT INTO Team_Member(
    VALUES('1000000000','Gardians of Galaxy','Man Basketball for Electrical and Computer Engineering')
);

INSERT INTO Team_Member(
    VALUES('1000000002','Gardians of Galaxy','Man Basketball for Electrical and Computer Engineering')
);

--INSERT INTO Staff(
--    VALUES(1000000003)
--);

INSERT INTO Run(
    VALUES('1000000001','100 Meters Sprint for Computer Scientiest','Judge')
);

INSERT INTO Vehicle(
    VALUES('AU000001','20')
);
INSERT INTO Vehicle(
    VALUES('AU000002','20')
);

INSERT INTO Journey(
    VALUES('8:00:00','2017-05-11','AU000001', 0,'SIT','SIT')
);
INSERT INTO Journey(
    VALUES('8:00:00','2017-05-11','AU000002', 0,'SIT','SIT')
);

INSERT INTO Participates(
    VALUES(1,'100 Meters Sprint for Computer Scientiest',1000000000,NULL,'9.78','gold','individual')
);

INSERT INTO Participates(
    VALUES(2,'Man Basketball for Electrical and Computer Engineering',NULL,'Gardians of Galaxy','97','gold','team')
);

INSERT INTO Booking(
    VALUES('1000000001','1000000003','8:00:00','2017-05-11','AU000001',CURRENT_TIMESTAMP)
);
INSERT INTO Booking(
    VALUES('1000000002','1000000003','8:00:00','2017-05-11','AU000001',CURRENT_TIMESTAMP)
);
INSERT INTO Booking(
    VALUES('1000000003','1000000003','8:00:00','2017-05-11','AU000001',CURRENT_TIMESTAMP)
);
--view for athlete results
 SELECT * FROM Athlete_Result;
