import sbtprotobuf.{ProtobufPlugin=>PB}

val commonSettings = PB.protobufSettings ++ Seq(
  scalaVersion := "2.11.10",
  version in PB.protobufConfig := "3.2.0",
  PB.runProtoc in PB.protobufConfig := { args =>
    com.github.os72.protocjar.Protoc.runProtoc("-v320" +: args.toArray)
  }
)

val foo = project.settings(
  commonSettings
)

val bar = project.settings(
  commonSettings,
  PB.includePaths in PB.protobufConfig += (sourceDirectory in PB.protobufConfig in foo).value
).dependsOn(foo)
