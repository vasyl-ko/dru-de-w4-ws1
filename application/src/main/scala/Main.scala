import java.time.LocalTime
import java.time.format.DateTimeFormatter

import datatables.{CompanyTable, PassengerInTripTable, PassengerTable, TripTable}
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

object Main {
  def main(args: Array[String]): Unit = {
    val db = Database.forConfig("postgresql")

    val companies: Seq[model.Company] = Seq(
      (1, "Don_avia"),
      (2, "Aeroflot"),
      (3, "Dale_avia"),
      (4, "air_France"),
      (5, "British_AW")
    ).map(model.Company.apply _ tupled)

    val passangers: Seq[model.Passenger] = Seq(
      (1, "Bruce Willis"),
      (2, "George Clooney"),
      (3, "Kevin Costner"),
      (4, "Donald Sutherland"),
      (5, "Jennifer Lopez"),
      (6, "Ray Liotta"),
      (7, "Samuel L. Jackson"),
      (8, "Nikole Kidman"),
      (9, "Alan Rickman"),
      (10, "Kurt Russell"),
      (11, "Harrison Ford"),
      (12, "Russell Crowe"),
      (13, "Steve Martin"),
      (14, "Michael Caine"),
      (15, "Angelina Jolie"),
      (16, "Mel Gibson"),
      (17, "Michael Douglas"),
      (18, "John Travolta"),
      (19, "Sylvester Stallone"),
      (20, "Tommy Lee Jones"),
      (21, "Catherine Zeta-Jones"),
      (22, "Antonio Banderas"),
      (23, "Kim Basinger"),
      (24, "Sam Neill"),
      (25, "Gary Oldman"),
      (26, "Clint Eastwood"),
      (27, "Brad Pitt"),
      (28, "Johnny Depp"),
      (29, "Pierce Brosnan"),
      (30, "Sean Connery"),
      (31, "Bruce Willis"),
      (37, "Mullah Omar")
    ).map(model.Passenger.apply _ tupled)

    val timeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss.SSS")
    val trips = Seq(
      (1100, 4, "Boeing", "Rostov", "Paris", "19800101 14:30:00.000", "19800101 17:50:00.000"),
      (1101, 4, "Boeing", "Paris", "Rostov", "19800101 08:12:00.000", "19800101 11:45:00.000"),
      (1123, 3, "TU-154", "Rostov", "Vladivostok", "19800101 16:20:00.000", "19800101 03:40:00.000"),
      (1124, 3, "TU-154", "Vladivostok", "Rostov", "19800101 09:00:00.000", "19800101 19:50:00.000"),
      (1145, 2, "IL-86", "Moscow", "Rostov", "19800101 09:35:00.000", "19800101 11:23:00.000"),
      (1146, 2, "IL-86", "Rostov", "Moscow", "19800101 17:55:00.000", "19800101 20:01:00.000"),
      (1181, 1, "TU-134", "Rostov", "Moscow", "19800101 06:12:00.000", "19800101 08:01:00.000"),
      (1182, 1, "TU-134", "Moscow", "Rostov", "19800101 12:35:00.000", "19800101 14:30:00.000"),
      (1187, 1, "TU-134", "Rostov", "Moscow", "19800101 15:42:00.000", "19800101 17:39:00.000"),
      (1188, 1, "TU-134", "Moscow", "Rostov", "19800101 22:50:00.000", "19800101 00:48:00.000"),
      (1195, 1, "TU-154", "Rostov", "Moscow", "19800101 23:30:00.000", "19800101 01:11:00.000"),
      (1196, 1, "TU-154", "Moscow", "Rostov", "19800101 04:00:00.000", "19800101 05:45:00.000"),
      (7771, 5, "Boeing", "London", "Singapore", "19800101 01:00:00.000", "19800101 11:00:00.000"),
      (7772, 5, "Boeing", "Singapore", "London", "19800101 12:00:00.000", "19800101 02:00:00.000"),
      (7773, 5, "Boeing", "London", "Singapore", "19800101 03:00:00.000", "19800101 13:00:00.000"),
      (7774, 5, "Boeing", "Singapore", "London", "19800101 14:00:00.000", "19800101 06:00:00.000"),
      (7775, 5, "Boeing", "London", "Singapore", "19800101 09:00:00.000", "19800101 20:00:00.000"),
      (7776, 5, "Boeing", "Singapore", "London", "19800101 18:00:00.000", "19800101 08:00:00.000"),
      (7777, 5, "Boeing", "London", "Singapore", "19800101 18:00:00.000", "19800101 06:00:00.000"),
      (7778, 5, "Boeing", "Singapore", "London", "19800101 22:00:00.000", "19800101 12:00:00.000"),
      (8881, 5, "Boeing", "London", "Paris", "19800101 03:00:00.000", "19800101 04:00:00.000"),
      (8882, 5, "Boeing", "Paris", "London", "19800101 22:00:00.000", "19800101 23:00:00.000")

    ).map {
      case (id, companyId, plane, townFrom, townTo, startTime, endTime) =>
        model.Trip(id, companyId, plane, townFrom, townTo,
          LocalTime.parse(startTime, timeFormatter),
          LocalTime.parse(endTime, timeFormatter))
    }

    val future = for {
      //add companies
      //_ <- db.run(CompanyTable.table ++= companies)

      //remove company
      //_ <- db.run(CompanyTable.table/*.filter(_.id === 2)*/.delete)

      //update company
      //_ <- db.run(CompanyTable.table.filter(_.id === 2).map(_.name).update("new name"))

      //show all companies
      _ <- db.run(CompanyTable.table.result)
      result <- db.run(sql"""select "ID_comp", "name" from "Company"""".as[(Int, String)])

      //add passengers
      //_ <- db.run(PassengerTable.table ++= passangers)

      //add trips
      //result <- db.run(TripTable.table ++= trips)

      //make join
      //result <- db.run(TripTable.table.join(CompanyTable.table).on(_.companyId === _.id).result)

      //allTrips <- db.run(TripTable.table.result)
      //allCompanies <- db.run(CompanyTable.table.result)
    } yield {
      println(CompanyTable.table.result.statements.toList)
      println(result.map(model.Company.apply _ tupled) /*.map(t => t._2.name -> t._1).mkString("\n")*/)
      //println(allTrips.map{trip => allCompanies.find(_.id == trip.companyId) -> trip})
    }

    Thread.sleep(10000)
    future.onComplete(println)
    db.close()
  }
}
