import java.time.{Instant, LocalDate, LocalTime}

import slick.jdbc.JdbcType
import slick.jdbc.PostgresProfile.api._

package object datatables {

  implicit val localDateToDateMapper: JdbcType[LocalDate] =
    MappedColumnType.base[LocalDate, java.sql.Date](java.sql.Date.valueOf, _.toLocalDate)

  implicit val localTimeToDateMapper: JdbcType[LocalTime] =
    MappedColumnType.base[LocalTime, java.sql.Time](java.sql.Time.valueOf, _.toLocalTime)
}
