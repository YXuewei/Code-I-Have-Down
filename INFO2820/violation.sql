-- constraint tests
--

-- expected error, must be 10 digits long
INSERT INTO Member(
    VALUES('1000000','Mr','Su','Corey','AU',NULL,'Athlete')
);
-- expected error, must be numeric
INSERT INTO Member(
    VALUES('100000000A','Mr','Su','Corey','AU',NULL,'Athlete')
);
-- expected error, must be 8 length
INSERT INTO Vehicle(
    VALUES('AAAA',1)
);

-- expected error, must be alphanumeric
INSERT INTO Vehicle(
    VALUES('AAA----A',1)
);

-- expected error because members must be inserted through the member table
INSERT INTO Athlete(
    VALUES('1000000000');
);

INSERT INTO Staff(
    VALUES('1000000000');
);

INSERT INTO Official(
    VALUES('1000000000');
);

---
-- expected error because places must be inserted through the place table
INSERT INTO Venue(
    VALUES('SIT')
);

INSERT INTO Accommodation(
    VALUES('SIT')
);

---
-- expected error because events must be inserted through the event table
INSERT INTO Individual_Event(
    VALUES('100 Meters Sprint for Computer Scientiest')
);

INSERT INTO Team_Event(
    VALUES('100 Meters Sprint for Computer Scientiest')
);

---

INSERT INTO Member
    VALUES(1000000000,'Mr','Su','Corey','AU',NULL,'Athlete')
);

INSERT INTO Member(
    VALUES(1000000001,'Mr','Zhang','Xiangxi','AU',NULL,'Official')
);

INSERT INTO Member(
    VALUES(1000000002,'Mr','Yang','Xuewei','CN',NULL,'Athlete')
);

INSERT INTO Member(
    VALUES(1000000003,'Miss','Underwood','Clare','US',NULL,'Staff')
);

-- expected: member has 3 rows, athlete has 2, official has 1, staff has 1
SELECT * FROM Member;
SElECT * FROM Athlete;
SELECT * FROM Official;
SELECT * FROM Staff;
