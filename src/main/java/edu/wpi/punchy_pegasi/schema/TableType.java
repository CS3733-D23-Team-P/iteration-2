package edu.wpi.punchy_pegasi.schema;

import lombok.Getter;
import org.intellij.lang.annotations.Language;

public enum TableType {
    NODES(edu.wpi.punchy_pegasi.schema.Node.class, """
DO $$
BEGIN
  IF to_regclass('teamp.nodes') IS NULL THEN
    CREATE SEQUENCE nodes_id_seq;
    CREATE TABLE teamp.nodes
    (
      nodeID bigint DEFAULT nextval('nodes_id_seq') PRIMARY KEY,
      xcoord int,
      ycoord int,
      floor varchar,
      building varchar
    );
    ALTER SEQUENCE nodes_id_seq OWNED BY teamp.nodes.nodeID;
  END IF;
END $$;
"""),
    EDGES(edu.wpi.punchy_pegasi.schema.Edge.class, """
DO $$
BEGIN
  IF to_regclass('teamp.edges') IS NULL THEN
    CREATE SEQUENCE edges_id_seq;
    CREATE TABLE teamp.edges
    (
      uuid bigint DEFAULT nextval('edges_id_seq') PRIMARY KEY,
      startNode bigint,
      endNode bigint
    );
    ALTER SEQUENCE edges_id_seq OWNED BY teamp.edges.uuid;
  END IF;
END $$;
"""),
    MOVES(edu.wpi.punchy_pegasi.schema.Move.class, """
DO $$
BEGIN
  IF to_regclass('teamp.moves') IS NULL THEN
    CREATE SEQUENCE moves_id_seq;
    CREATE TABLE teamp.moves
    (
      uuid bigint DEFAULT nextval('moves_id_seq') PRIMARY KEY,
      nodeID bigint,
      longName varchar,
      date varchar
    );
    ALTER SEQUENCE moves_id_seq OWNED BY teamp.moves.uuid;
  END IF;
END $$;
"""),
    LOCATIONNAMES(edu.wpi.punchy_pegasi.schema.LocationName.class, """
DO $$
BEGIN
  IF to_regclass('teamp.locationnames') IS NULL THEN
    CREATE SEQUENCE locationnames_id_seq;
    CREATE TABLE teamp.locationnames
    (
      uuid bigint DEFAULT nextval('locationnames_id_seq') PRIMARY KEY,
      longName varchar,
      shortName varchar,
      nodeType varchar NOT NULL
    );
    ALTER SEQUENCE locationnames_id_seq OWNED BY teamp.locationnames.uuid;
  END IF;
END $$;
"""),
    REQUESTS(edu.wpi.punchy_pegasi.schema.RequestEntry.class, """
CREATE TABLE IF NOT EXISTS teamp.requests
(
  serviceID uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
  locationName bigint,
  staffAssignment bigint,
  additionalNotes varchar,
  status varchar NOT NULL
);
""")
,
    GENERIC(edu.wpi.punchy_pegasi.schema.GenericRequestEntry.class, """
CREATE TABLE IF NOT EXISTS teamp.generic
(
  
) INHERITS (teamp.requests);
""")
,
    FOODREQUESTS(edu.wpi.punchy_pegasi.schema.FoodServiceRequestEntry.class, """
CREATE TABLE IF NOT EXISTS teamp.foodrequests
(
  foodSelection varchar,
  tempType varchar,
  additionalItems varchar ARRAY,
  dietaryRestrictions varchar,
  patientName varchar,
  beverage varchar
) INHERITS (teamp.requests);
""")
,
    FLOWERREQUESTS(edu.wpi.punchy_pegasi.schema.FlowerDeliveryRequestEntry.class, """
CREATE TABLE IF NOT EXISTS teamp.flowerrequests
(
  flowerSize varchar,
  flowerType varchar,
  flowerAmount varchar,
  patientName varchar
) INHERITS (teamp.requests);
""")
,
    CONFERENCEREQUESTS(edu.wpi.punchy_pegasi.schema.ConferenceRoomEntry.class, """
CREATE TABLE IF NOT EXISTS teamp.conferencerequests
(
  beginningTime varchar,
  endTime varchar,
  date date NOT NULL
) INHERITS (teamp.requests);
""")
,
    FURNITUREREQUESTS(edu.wpi.punchy_pegasi.schema.FurnitureRequestEntry.class, """
CREATE TABLE IF NOT EXISTS teamp.furniturerequests
(
  selectFurniture varchar ARRAY
) INHERITS (teamp.requests);
""")
,
    OFFICEREQUESTS(edu.wpi.punchy_pegasi.schema.OfficeServiceRequestEntry.class, """
CREATE TABLE IF NOT EXISTS teamp.officerequests
(
  officeRequest varchar,
  employeeName varchar
) INHERITS (teamp.requests);
""")
,
    EMPLOYEES(edu.wpi.punchy_pegasi.schema.Employee.class, """
DO $$
BEGIN
  IF to_regclass('teamp.employees') IS NULL THEN
    CREATE SEQUENCE employees_id_seq;
    CREATE TABLE teamp.employees
    (
      employeeID bigint DEFAULT nextval('employees_id_seq') PRIMARY KEY,
      firstName varchar,
      lastName varchar
    );
    ALTER SEQUENCE employees_id_seq OWNED BY teamp.employees.employeeID;
  END IF;
END $$;
"""),
    ACCOUNTS(edu.wpi.punchy_pegasi.schema.Account.class, """
CREATE TABLE IF NOT EXISTS teamp.accounts
(
  username varchar PRIMARY KEY,
  password varchar,
  employeeID bigint,
  accountType varchar NOT NULL
);
""")
,
    SIGNAGE(edu.wpi.punchy_pegasi.schema.Signage.class, """
CREATE TABLE IF NOT EXISTS teamp.signage
(
  longName varchar PRIMARY KEY,
  directionType varchar NOT NULL
);
""")
;
    @Getter
    private final Class<?> clazz;
    @Getter
    private final String tableSQL;

    TableType(Class<?> clazz, @Language(value = "SQL") String tableSQL) {
        this.clazz = clazz;
        this.tableSQL = tableSQL;
    }
}
