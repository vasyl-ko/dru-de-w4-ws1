lazy val model =
  Project(id = "model", base = file("model"))
    .settings(
      version := "0.1",
      scalaVersion := "2.12.6"
    )

lazy val dataTables =
  Project(id = "datatables", base = file("datatables"))
    .dependsOn(model)
    .settings(
      version := "0.1",
      scalaVersion := "2.12.6",
      libraryDependencies ++= Seq(
        "com.typesafe.slick" %% "slick" % "3.2.3",
        "org.slf4j" % "slf4j-nop" % "1.6.4",
        "com.typesafe.slick" %% "slick-hikaricp" % "3.2.3"
      )
    )

lazy val application =
  Project(id = "application", base = file("application"))
    .dependsOn(dataTables)
    .settings(
      version := "0.1",
      scalaVersion := "2.12.6"
    )

lazy val root =
  Project("workshop", file("."))
    .aggregate(application)
