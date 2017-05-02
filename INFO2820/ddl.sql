CREATE TABLE Location(
  name VARCHAR PRIMARY KEY,
  type VARCHAR,
  parent_name VARCHAR,
  FOREIGN KEY (parent_name) REFERENCES Location(name)
    ON DELETE CASCADE
  	ON UPDATE CASCADE,
    CHECK(type IN ('Suburb','Area','District'))
);

CREATE TABLE Place(
  name VARCHAR PRIMARY KEY,
  location_name VARCHAR  REFERENCES Location(name)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
  longitude FLOAT8,
  latitude FLOAT8,
  address VARCHAR NOT NULL,
  place_type VARCHAR NOT NULL,
  CHECK (place_type in ('Venue', 'Accommodation'))
);

CREATE TABLE Venue(
  name VARCHAR PRIMARY KEY REFERENCES Place(name)
  	ON DELETE CASCADE
  	ON UPDATE CASCADE
);

CREATE TABLE Accommodation(
  name VARCHAR PRIMARY KEY REFERENCES Place(name)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE Country(
  code CHAR(2) PRIMARY KEY,
  name VARCHAR UNIQUE NOT NULL
);

CREATE TABLE Member(
  member_id CHAR(10) PRIMARY KEY,
  title VARCHAR,
  family_name VARCHAR,
  given_name VARCHAR,
  country CHAR(2) NOT NULL,
  accommodation VARCHAR,
  member_type VARCHAR NOT NULL,
  CHECK (member_type IN ('Athlete', 'Staff', 'Official')),
  CHECK (member_id  SIMILAR TO '[0-9]{10}'),
  FOREIGN KEY (country) REFERENCES Country(code)
    ON DELETE CASCADE
  	ON UPDATE CASCADE,
  FOREIGN KEY (accommodation) REFERENCES Accommodation(name)
    ON DELETE CASCADE
  	ON UPDATE CASCADE
);

CREATE TABLE Athlete(
  athlete_id CHAR(10) PRIMARY KEY REFERENCES Member( member_id )
  	ON DELETE CASCADE
  	ON UPDATE CASCADE
);

CREATE TABLE Official(
  official_id CHAR(10) PRIMARY KEY REFERENCES Member( member_id )
  	ON DELETE CASCADE
  	ON UPDATE CASCADE
);

CREATE TABLE Staff(
  staff_id CHAR(10) PRIMARY KEY REFERENCES Member( member_id )
  	ON DELETE CASCADE
  	ON UPDATE CASCADE
);

CREATE TABLE Team(
  name VARCHAR,
  event_name VARCHAR,
  country_code CHAR(2) NOT NULL,
  PRIMARY KEY(name, event_name),
  FOREIGN KEY (country_code) REFERENCES Country( code )
  	ON DELETE CASCADE
  	ON UPDATE CASCADE
);

CREATE TABLE Vehicle(
  code CHAR(8) PRIMARY KEY,
  capacity INTEGER DEFAULT 1
  CHECK (capacity > 0)
  CHECK (code SIMILAR TO '[0-9a-zA-Z]{8}')
);

CREATE TABLE Journey(
  start_time TIME,
  start_date DATE,
  vehicle_code CHAR(8) REFERENCES Vehicle( code )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  nbooked INTEGER DEFAULT 0,
  from_place VARCHAR REFERENCES Place(name) NOT NULL,
  to_place VARCHAR REFERENCES Place(name) NOT NULL,
  CHECK(nbooked >= 0),
  PRIMARY KEY(start_time, start_date, vehicle_code)

);

CREATE TABLE Booking(
  member_id CHAR(10) REFERENCES Member(member_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  staff_id CHAR(10) REFERENCES Staff(staff_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  start_time TIME,
  start_date DATE,
  vehicle_code CHAR(8),
  when_booked TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  PRIMARY KEY (member_id, staff_id, start_time, start_date, vehicle_code),
  FOREIGN KEY(start_time, start_date, vehicle_code) REFERENCES Journey(start_time, start_date, vehicle_code)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CHECK( when_booked <= ( start_date + start_time ) )
);
CREATE TABLE Sport(
  name VARCHAR PRIMARY KEY

);

CREATE TABLE Event(
  name VARCHAR PRIMARY KEY,
  start_time TIME,
  start_date DATE,
  result_type VARCHAR,
  sport VARCHAR REFERENCES Sport(name),
  venue_name VARCHAR REFERENCES Venue( name )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  event_type VARCHAR NOT NULL,
  CHECK (event_type IN ('team', 'individual'))

);

CREATE TABLE Run(
  official_id CHAR(10) REFERENCES Official(official_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  event_name VARCHAR REFERENCES Event(name)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  role_ VARCHAR,
  PRIMARY KEY (official_id, event_name)
);

CREATE TABLE Participates(
  participation_id SERIAL PRIMARY KEY,
  event_name VARCHAR REFERENCES Event(name),
  athlete_id CHAR(10) REFERENCES Athlete(athlete_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  team_name VARCHAR,
  result FLOAT NOT NULL,
  medal VARCHAR(6) DEFAULT NULL,
  participation_type VARCHAR(10),
  FOREIGN KEY (team_name, event_name) REFERENCES Team(name, event_name)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CHECK (medal in ('gold', 'silver', 'bronze', NULL)),
  CHECK (participation_type in ('team', 'individual'))

);

CREATE TABLE Team_Member(
  athlete_id CHAR(10) REFERENCES Athlete(athlete_id),
  team_name VARCHAR,
  event_name VARCHAR,
  PRIMARY KEY (athlete_id, team_name, event_name),
  FOREIGN KEY (team_name, event_name) REFERENCES Team(name, event_name)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE Individual_Event (
    event_name VARCHAR REFERENCES Event(name)
);
CREATE TABLE Team_Event (
    event_name VARCHAR REFERENCES Event(name)
);

CREATE VIEW Athlete_Result AS  (
  SELECT A.athlete_id, M.title, M.family_name, M.given_name, M.country, M.accommodation,
      (COALESCE((SELECT COUNT(medal)
          FROM Participates
          WHERE Participates.athlete_id = A.athlete_id
          GROUP BY(athlete_id, medal)
          HAVING medal = 'gold'),0) +
        COALESCE((SELECT COUNT(medal)
        FROM Participates JOIN Team_Member USING (team_name, event_name)
        WHERE Team_Member.athlete_id = A.athlete_id
        GROUP BY(Team_Member.athlete_id, medal)
        HAVING medal = 'gold'),0)
      ) as gold,
      (COALESCE((SELECT COUNT(medal)
          FROM Participates
          WHERE Participates.athlete_id = A.athlete_id
          GROUP BY(athlete_id, medal)
          HAVING medal = 'silver'),0) +
        COALESCE((SELECT COUNT(medal)
        FROM Participates JOIN Team_Member USING (team_name, event_name)
        WHERE Team_Member.athlete_id = A.athlete_id
        GROUP BY(Team_Member.athlete_id, medal)
        HAVING medal = 'silver'),0)
      ) as silver,
      (COALESCE((SELECT COUNT(medal)
          FROM Participates
          WHERE Participates.athlete_id = A.athlete_id
          GROUP BY(athlete_id, medal)
          HAVING medal = 'bronze'),0) +
        COALESCE((SELECT COUNT(medal)
        FROM Participates JOIN Team_Member USING (team_name, event_name)
        WHERE Team_Member.athlete_id = A.athlete_id
        GROUP BY(Team_Member.athlete_id, medal)
        HAVING medal = 'bronze'),0)
      ) as bronze
  FROM Member M JOIN Athlete A ON (member_id = athlete_id)
);


-- FUNCTIONS

CREATE FUNCTION UpdatePlaceChild() RETURNS TRIGGER AS $$
BEGIN
  IF(NEW.place_type = 'Venue') THEN
    INSERT INTO Venue SELECT NEW.name;
    RETURN NEW;
  ELSIF(NEW.place_type = 'Accommodation') THEN
    INSERT INTO Accommodation SELECT NEW.name;
    RETURN NEW;
  END IF;
END; $$  LANGUAGE plpgsql;

CREATE FUNCTION StopInsertPlace() RETURNS TRIGGER AS $$
BEGIN
  IF ((SELECT COUNT(*)
      FROM Accommodation JOIN Venue ON (Accommodation.name = Venue.name)) <> 0) THEN
      RAISE EXCEPTION 'CANNOT INSERT';
  END IF;
  RETURN OLD;
END; $$  LANGUAGE plpgsql;

CREATE FUNCTION UpdateMemberChild() RETURNS TRIGGER AS $$
BEGIN
  IF(NEW.member_type = 'Athlete') THEN
    INSERT INTO Athlete SELECT NEW.member_id;
    RETURN NEW;
  ELSIF(NEW.member_type = 'Staff') THEN
    INSERT INTO Staff SELECT NEW.member_id;
    RETURN NEW;
  ELSIF(NEW.member_type = 'Official') THEN
    INSERT INTO Official SELECT NEW.member_id;
    RETURN NEW;
  END IF;
END; $$  LANGUAGE plpgsql;

CREATE FUNCTION StopInsertMember() RETURNS TRIGGER AS $$
BEGIN
  IF ((SELECT COUNT(*)
      FROM Official JOIN Athlete ON (Official.official_id = Athlete.athlete_id)) <> 0) THEN
      RAISE EXCEPTION 'CANNOT INSERT';
  ELSIF ((SELECT COUNT(*)
      FROM Staff JOIN Athlete ON (Staff.staff_id = Athlete.athlete_id)) <> 0) THEN
      RAISE EXCEPTION 'CANNOT INSERT';
  ELSIF ((SELECT COUNT(*)
      FROM Official JOIN Staff ON (Official.official_id = Staff.staff_id)) <> 0) THEN
      RAISE EXCEPTION 'CANNOT INSERT';
  END IF;
  RETURN OLD;
END; $$  LANGUAGE plpgsql;

CREATE FUNCTION UpdateEventChild() RETURNS TRIGGER AS $$
BEGIN
  IF(NEW.event_type = 'individual') THEN
    INSERT INTO Individual_Event SELECT(NEW.name);
    RETURN NEW;
  ELSIF(NEW.event_type = 'team') THEN
    INSERT INTO Team_Event VALUES(NEW.name);
    RETURN NEW;

  END IF;
END; $$  LANGUAGE plpgsql;

CREATE FUNCTION StopInsertEvent() RETURNS TRIGGER AS $$
BEGIN
  IF ((SELECT COUNT(*)
      FROM Individual_Event I JOIN Team_Event T ON (I.event_name = T.event_name)) <> 0) THEN
      RAISE EXCEPTION 'EVENT CANNOT BE BOTH INDIVIDUAL AND TEAM';
END IF;
  RETURN OLD;
END; $$  LANGUAGE plpgsql;

CREATE FUNCTION StopInsertParticipates() RETURNS TRIGGER AS $$
BEGIN
  IF(NEW.participation_type = 'individual' AND NEW.athlete_id IS NULL) THEN
      RAISE EXCEPTION 'tuples with participation_type individual must have a non-null athlete_id';
      RETURN OLD;
  ELSIF(NEW.participation_type = 'team' AND NEW.team_name IS NULL) THEN
      RAISE EXCEPTION 'tuples with participation_type team must have non-null team_name and ';
      RETURN OLD;
  ELSIF(NEW.participation_type = 'individual' AND NEW.team_name IS NOT NULL) THEN
      RAISE EXCEPTION 'tuples with participation_type individual must have null team_name';
      RETURN OLD;
  ELSIF(NEW.participation_type = 'team' AND NEW.athlete_id IS NOT NULL) THEN
      RAISE EXCEPTION 'tuples with participation_type team must have null athlete_id';
      RETURN OLD;
  END IF;
  RETURN OLD;
END; $$  LANGUAGE plpgsql;

CREATE FUNCTION nbooked0 () RETURNS TRIGGER AS $$
BEGIN
  IF(NEW.nbooked != 0) THEN
      RAISE EXCEPTION 'nbooked must be zero when first inserted';
      RETURN OLD;
  END IF;
  RETURN OLD;
END; $$  LANGUAGE plpgsql;

CREATE FUNCTION updateNBooked () RETURNS TRIGGER AS $$
BEGIN
  IF(TG_OP = 'INSERT') THEN
    UPDATE Journey SET nbooked=nbooked + 1 WHERE ((Journey.start_time, Journey.start_date, Journey.vehicle_code)=
                                                  (NEW.start_time, NEW.start_date, NEW.vehicle_code));
  RETURN NEW;
  ELSIF (TG_OP = 'UPDATE') THEN
    UPDATE Journey SET nbooked=nbooked + 1 WHERE ((Journey.start_time, Journey.start_date, Journey.vehicle_code)=
                                                (NEW.start_time, NEW.start_date, NEW.vehicle_code));
    UPDATE Journey SET nbooked=nbooked - 1 WHERE ((Journey.start_time, Journey.start_date, Journey.vehicle_code)=
                                                  (OLD.start_time, OLD.start_date, OLD.vehicle_code));
  RETURN NEW;
  ELSIF (TG_OP = 'DELETE') THEN
    UPDATE Journey SET nbooked=nbooked - 1 WHERE ((Journey.start_time, Journey.start_date, Journey.vehicle_code)=
                                                  (OLD.start_time, OLD.start_date, OLD.vehicle_code));
    RETURN NEW;
  END IF;
  RETURN NULL;
END; $$  LANGUAGE plpgsql;

CREATE FUNCTION StopUpdateNBooked() RETURNS TRIGGER AS $$
BEGIN
  IF ((SELECT COUNT(*)
     FROM Booking WHERE (Booking.start_time, Booking.start_date, Booking.vehicle_code) =
     (NEW.start_time, NEW.start_date, NEW.vehicle_code)) != NEW.nbooked) THEN
      RAISE EXCEPTION 'CANNOT INSERT';
  END IF;
  RETURN OLD;
END; $$  LANGUAGE plpgsql;

-- TRIGGERS

CREATE TRIGGER Stop_Insert_Athlete
AFTER INSERT OR UPDATE ON Athlete
EXECUTE PROCEDURE StopInsertMember();

CREATE TRIGGER Stop_Insert_Staff
AFTER INSERT OR UPDATE ON Staff
EXECUTE PROCEDURE StopInsertMember();

CREATE TRIGGER Stop_Insert_Official
AFTER INSERT OR UPDATE ON Official
EXECUTE PROCEDURE StopInsertMember();

CREATE TRIGGER Member_Insert
AFTER INSERT ON Member
FOR EACH ROW
EXECUTE PROCEDURE UpdateMemberChild();

CREATE TRIGGER Stop_Insert_Venue
AFTER INSERT OR UPDATE ON Venue
EXECUTE PROCEDURE StopInsertPlace();

CREATE TRIGGER Stop_Insert_Accommodation
AFTER INSERT OR UPDATE ON Accommodation
EXECUTE PROCEDURE StopInsertPlace();

CREATE TRIGGER Place_Insert
AFTER INSERT ON Place
FOR EACH ROW
EXECUTE PROCEDURE UpdatePlaceChild();

CREATE TRIGGER Stop_Insert_Participates
AFTER INSERT OR UPDATE ON Participates
FOR EACH ROW
EXECUTE PROCEDURE StopInsertParticipates();

CREATE TRIGGER bookingINSERTUPDATEDELTE
AFTER INSERT OR UPDATE OR DELETE ON Booking
FOR EACH ROW
EXECUTE PROCEDURE updateNBooked ();

CREATE TRIGGER Stop_Insert_Individual_Event
AFTER INSERT OR UPDATE ON Individual_Event
EXECUTE PROCEDURE StopInsertEvent();

CREATE TRIGGER Stop_Insert_Team_Event
AFTER INSERT OR UPDATE ON Team_Event
EXECUTE PROCEDURE StopInsertEvent();

CREATE TRIGGER Event_Insert
AFTER INSERT ON Event
FOR EACH ROW
EXECUTE PROCEDURE UpdateEventChild();

CREATE TRIGGER journey_insert
AFTER INSERT ON Journey
FOR EACH ROW
EXECUTE PROCEDURE nbooked0();

CREATE TRIGGER journey_update
AFTER UPDATE ON Journey
FOR EACH ROW
EXECUTE PROCEDURE stopUpdateNBooked();

-- NBOOKED ASSERTIONS

--CREATE ASSERTION Participates_Constraints
--    CHECK
--  (
--    NOT EXISTS(
--     (SELECT *
--      FROM Participates
--     WHERE participation_type = 'individual' AND athlete_id IS NULL
--     )
--        UNION
--     (
--     SELECT *
--      FROM Participates
--     WHERE participation_type = 'team' AND team_name IS NULL
--     )
--      UNION
--    (
--     SELECT *
--      FROM Participates
--     WHERE participation_type = 'individual' AND team_name IS NOT NULL
--     )
--      UNION
--    (
--     SELECT *
--      FROM Participates
--     WHERE participation_type = 'team' AND athlete_id IS NOT NULL
--     )
--    )

--  )


--CREATE ASSERTION nbookedassertion
--CHECK (
--  EXISTS(
--  SELECT *
--  FROM (SELECT nbooked,COUNT (*)
--        FROM Journey J JOIN Booking B USING(start_time, start_date,vehicle_code)
--         GROUP BY(J.start_time, J.start_date,J.vehicle_code)) AS A
--  WHERE a.nbooked != a.count
--);
--)
