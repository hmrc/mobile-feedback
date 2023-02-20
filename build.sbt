import uk.gov.hmrc.DefaultBuildSettings.integrationTestSettings
import uk.gov.hmrc.sbtdistributables.SbtDistributablesPlugin.publishingSettings

val appName = "mobile-feedback"

lazy val microservice = Project(appName, file("."))
  .enablePlugins(play.sbt.PlayScala, SbtDistributablesPlugin, ScoverageSbtPlugin)
  .settings(
    majorVersion        := 0,
    scalaVersion        := "2.13.8",
    libraryDependencies ++= AppDependencies.compile ++ AppDependencies.jacksonDatabindOverrides
      ++ AppDependencies.jacksonOverrides
      ++ AppDependencies.akkaSerializationJacksonOverrides
      ++ AppDependencies.test,
    // https://www.scala-lang.org/2021/01/12/configuring-and-suppressing-warnings.html
    // suppress warnings in generated routes files
    scalacOptions += "-Wconf:src=routes/.*:s",
  )
  .settings(
    routesImport ++= Seq(
      "uk.gov.hmrc.mobilefeedback.types._",
      "uk.gov.hmrc.mobilefeedback.types.ModelTypes._"
    )
  )
  .configs(IntegrationTest)
  .settings(PlayKeys.playDefaultPort := 8263)
  .settings(integrationTestSettings(): _*)
  .settings(resolvers += Resolver.jcenterRepo)
  .settings(CodeCoverageSettings.settings: _*)
  .disablePlugins(JUnitXmlReportPlugin)


