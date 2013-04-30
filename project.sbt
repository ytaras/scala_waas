scalaVersion := "2.10.1"

resolvers ++= Seq("snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
                  "releases"  at "http://oss.sonatype.org/content/repositories/releases")

libraryDependencies ++= Seq(
    "org.scalacheck" %% "scalacheck" % "1.10.1" % "test",
    "org.specs2" %% "specs2" % "1.14" % "test"
)
