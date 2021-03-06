package datatables

import java.time.{LocalDate, LocalTime}

import slick.jdbc.PostgresProfile.api._

class PassengerInTripTable(tag: Tag)
    extends Table[model.PassengerInTrip](tag, "Pass_in_trip") {
  val tripId = column[Int]("trip_no")
  val passengerId = column[Int]("ID_psg")
  val date = column[LocalDate]("date")
  val place = column[String]("place")

  val pk = primaryKey("pass_in_trip_pk", (tripId, passengerId, date))

  val tripIdForeignKey = foreignKey(
    "trip_id_fk", tripId, TripTable.table)(
    _.id, ForeignKeyAction.Cascade, ForeignKeyAction.Cascade
  )

  val passengerIdForeignKey = foreignKey(
    "passenger_id_fk", passengerId, PassengerTable.table)(
    _.id, ForeignKeyAction.Cascade, ForeignKeyAction.Cascade
  )

  def * = (tripId, passengerId, date, place).mapTo[model.PassengerInTrip]
}

object PassengerInTripTable{
  val table = TableQuery[PassengerInTripTable]
}
