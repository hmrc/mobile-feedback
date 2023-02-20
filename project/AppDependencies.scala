import play.core.PlayVersion
import play.sbt.PlayImport._
import sbt.Keys.libraryDependencies
import sbt._

object AppDependencies {

  private val bootstrapVersion = "7.13.0"
  

  val compile: Seq[ModuleID] = Seq(
    "uk.gov.hmrc"             %% "bootstrap-backend-play-28"  % bootstrapVersion,
    "eu.timepit"              %% "refined"                     % "0.10.1"
  )

  val test: Seq[ModuleID] = Seq(
    "uk.gov.hmrc"             %% "bootstrap-test-play-28"     % bootstrapVersion            % "test,it",
    "org.scalamock"           %% "scalamock"                  % "5.2.0"                     % "test,it",
    "org.scalatestplus"       %% "scalatestplus-mockito"      % "1.0.0-M2"                  % "test,it",
    "org.scalatestplus"       %% "scalatestplus-scalacheck"   % "3.1.0.0-RC2"               % "test,it",
    "com.github.tomakehurst"  %  "wiremock-jre8"              % "2.35.0"                    % "test,it"
  )

  val jacksonVersion = "2.13.2"
  val jacksonDatabindVersion = "2.13.2.2"

  val jacksonOverrides: Seq[ModuleID] = Seq(
    "com.fasterxml.jackson.core" % "jackson-core",
    "com.fasterxml.jackson.core" % "jackson-annotations",
    "com.fasterxml.jackson.datatype" % "jackson-datatype-jdk8",
    "com.fasterxml.jackson.datatype" % "jackson-datatype-jsr310"
  ).map(_ % jacksonVersion)

  val jacksonDatabindOverrides: Seq[ModuleID] = Seq(
    "com.fasterxml.jackson.core" % "jackson-databind" % jacksonDatabindVersion
  )

  val akkaSerializationJacksonOverrides: Seq[ModuleID] = Seq(
    "com.fasterxml.jackson.dataformat" % "jackson-dataformat-cbor",
    "com.fasterxml.jackson.module" % "jackson-module-parameter-names",
    "com.fasterxml.jackson.module" %% "jackson-module-scala",
  ).map(_ % jacksonVersion)


}
