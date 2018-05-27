package datatables

import java.time.LocalTime

import slick.jdbc.PostgresProfile.api._

class PassengerTable(tag: Tag)
    extends Table[model.Passenger](tag, "Passenger") {
  val id = column[Int]("ID_psg", O.PrimaryKey)
  val name = column[String]("name")

  def * = (id, name).mapTo[model.Passenger]
}

object PassengerTable{
  val table = TableQuery[PassengerTable]
}
