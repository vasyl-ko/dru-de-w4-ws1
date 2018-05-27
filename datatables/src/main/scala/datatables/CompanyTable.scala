package datatables

import slick.jdbc.PostgresProfile.api._

class CompanyTable(tag: Tag) extends Table[model.Company](tag, "Company") {
  val id = column[Int]("ID_comp", O.PrimaryKey)
  val name = column[String]("name")

  def * = (id, name).mapTo[model.Company]
}

object CompanyTable{
  val table = TableQuery[CompanyTable]
}