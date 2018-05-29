import datatables.{CompanyTable, PassengerInTripTable, PassengerTable, TripTable}
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

object Main {
  def main(args: Array[String]): Unit = {
    val db = Database.forConfig("postgresql")

    val future = for {
      _ <- db.run(CompanyTable.table.schema.create)
      _ <- db.run(PassengerTable.table.schema.create)
      _ <- db.run(TripTable.table.schema.create)
      _ <- db.run(PassengerInTripTable.table.schema.create)
    } yield {
      println("All tables created")
    }

    Await.result(future, Duration.Inf)

    db.close()

//    CompanyTable.table.schema.create.statements.foreach(println)
//    println()
//    PassengerTable.table.schema.create.statements.foreach(println)
//    println()
//    TripTable.table.schema.create.statements.foreach(println)
//    println()
//    PassengerInTripTable.table.schema.create.statements.foreach(println)

//    CompanyTable.table.filter(_.name === "A").result.statements.foreach(println)
//
//    CompanyTable.table.filter(_.name === "A").filter(_.name =!= "B").result.statements.foreach(println)
//
//    (CompanyTable.table returning CompanyTable.table += model.Company(4, "Company name")).statements.foreach(println)
//
//    (CompanyTable.table returning CompanyTable.table += model.Company(4, "Company name")).statements.foreach(println)
//
//    (CompanyTable.table join TripTable.table on (_.id === _.companyId)).result.statements.foreach(println)
  }
}
