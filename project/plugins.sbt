addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.3.18")
addSbtPlugin("org.wartremover"   % "sbt-wartremover"        % "2.3.7")
addSbtPlugin("org.jmotor.sbt" % "sbt-dependency-updates" % "1.1.13")
addSbtPlugin("com.lucidchart"    % "sbt-scalafmt"           % "1.15")
addSbtPlugin("io.get-coursier"    % "sbt-coursier" % "1.0.3")
addSbtPlugin("org.duhemm" % "sbt-errors-summary" % "0.6.3")

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)
