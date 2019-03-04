import sbtrelease.ReleasePlugin.autoImport.ReleaseTransformations._
val rootProjectName = "sbt-release-example"
lazy val releaseSettings = Seq(
  releaseUseGlobalVersion := false,
  releaseVersionFile := Def.setting {
    if (name.value == rootProjectName ) file("./version.sbt")
    else file(name.value + "/version.sbt")
  }.value,
  releaseTagName := s"${name.value}-v${version.value}" ,
  releaseTagComment := s"Releasing ${name.value}-${version.value}",
  releaseCommitMessage := s"Setting version to ${name.value}-${version.value}",
// releaseCrossBuild := true
  releasePublishArtifactsAction := PgpKeys.publishSigned.value,
  releaseProcess := Seq[ReleaseStep](
    checkSnapshotDependencies,
    inquireVersions,
    runClean,
    setReleaseVersion,
    commitReleaseVersion,
    tagRelease,
    //releaseStepCommandAndRemaining("+publishSigned"),
    setNextVersion,
    commitNextVersion,
    //releaseStepCommand("sonatypeReleaseAll"),
    pushChanges
  )
)
val scalaVersion211 = "2.11.12"
val scalaVersion212 = "2.12.8"
val coreSettings = Seq(
  sonatypeProfileName := "com.github.j5ik2o",
  organization := "com.github.j5ik2o",
  scalaVersion := scalaVersion212,
//  crossScalaVersions ++= Seq(scalaVersion211, scalaVersion212),
  scalacOptions ++= {
    Seq(
      "-feature",
      "-deprecation",
      "-unchecked",
      "-encoding",
      "UTF-8",
      "-language:_",
      "-Ydelambdafy:method",
      "-target:jvm-1.8"
    ) ++ {
      CrossVersion.partialVersion(scalaVersion.value) match {
        case Some((2L, scalaMajor)) if scalaMajor == 12 =>
          Seq.empty
        case Some((2L, scalaMajor)) if scalaMajor <= 11 =>
          Seq("-Yinline-warnings")
      }
    }
  },
  publishMavenStyle := true,
  publishArtifact in Test := false,
  pomIncludeRepository := { _ =>
    false
  },
  pomExtra := {
    <url>https://github.com/j5ik2o/sbt-release-example</url>
      <licenses>
        <license>
          <name>The MIT License</name>
          <url>http://opensource.org/licenses/MIT</url>
        </license>
      </licenses>
      <scm>
        <url>git@github.com:j5ik2o/sbt-release-example.git</url>
        <connection>scm:git:github.com/j5ik2o/sbt-release-example</connection>
        <developerConnection>scm:git:git@github.com:j5ik2o/sbt-release-example.git</developerConnection>
      </scm>
      <developers>
        <developer>
          <id>j5ik2o</id>
          <name>Junichi Kato</name>
        </developer>
      </developers>
  },
  publishTo in ThisBuild := sonatypePublishTo.value,
  credentials := {
    val ivyCredentials = (baseDirectory in LocalRootProject).value / ".credentials"
    Credentials(ivyCredentials) :: Nil
  },
  resolvers ++= Seq(
    Resolver.sonatypeRepo("snapshots"),
    Resolver.sonatypeRepo("releases"),
    "Seasar2 Repository" at "http://maven.seasar.org/maven2",
    Resolver.bintrayRepo("danslapman", "maven"),
    "DynamoDB Local Repository" at "https://s3-us-west-2.amazonaws.com/dynamodb-local/release"
  ),
  libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "3.0.5" % Test,
    "org.scalacheck" %% "scalacheck" % "1.14.0" % Test,
    "ch.qos.logback" % "logback-classic" % "1.2.3" % Test,
    "com.github.j5ik2o" %% "scalatestplus-db" % "1.0.7" % Test
  )
)

val sub1 = (project in file("sub1"))
  .settings(coreSettings)
  .settings(releaseSettings)

val sub2 = (project in file("sub2"))
  .settings(coreSettings)
  .settings(releaseSettings)

val root = (project in file("."))
  .settings(coreSettings)
  .settings(releaseSettings)
  .settings(name := rootProjectName)
  .aggregate(sub1, sub2)
