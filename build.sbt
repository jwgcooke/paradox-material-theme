name := "paradox-material-theme"
organization := "io.github.jonas"
licenses += "MIT" -> url("https://github.com/jonas/paradox-material-theme/blob/master/LICENSE")
homepage := Some(url("https://jonas.github.io/paradox-material-theme"))
description := "Material Design theme for Paradox"

scmInfo := Some(
  ScmInfo(
    url("https://github.com/jonas/paradox-material-theme"),
    "scm:git:git@github.com:jonas/paradox-material-theme.git"
  )
)

developers := List(
  Developer("jonas", "Jonas Fonseca", "jonas.fonseca@gmail.com", url("https://github.com/jonas"))
)

enablePlugins(GitPlugin, GhpagesPlugin)
versionWithGit
git.useGitDescribe := true
git.remoteRepo := "git@github.com:jonas/paradox-material-theme.git"
siteSourceDirectory := (target in (Compile, paradox)).value
makeSite := makeSite.dependsOn(paradox in Compile).value

enablePlugins(ParadoxThemePlugin, ParadoxPlugin)
paradoxTheme := None
sourceDirectories in (Compile, paradoxTheme) += sourceDirectory.value / "main" / "assets"
includeFilter in (Compile, paradoxTheme) := "*.st"

WebKeys.webJars in Assets := {
  val out = (WebKeys.webJars in Assets).value
  (WebKeys.webJarsDirectory in Assets).value.**(
    "*.min.js" | "*.min.css" | "lang-*.js" | "prettify.css" | "prettify.js"
  ).get.filter(_.isFile)
}

libraryDependencies += "org.webjars" % "prettify" % "4-Mar-2013-1" % Provided
libraryDependencies += "org.webjars" % "modernizr" % "2.8.3" % Provided
libraryDependencies += Seq("animation", "base", "ripple", "rtl", "theme", "typography").foldLeft("org.webjars.npm" % "material__tabs" % "0.3.1" % Provided) {
  (lib, dep) => lib.exclude("org.webjars.npm", s"material__$dep")
}

paradoxProperties in Compile ++= Map(
  "project.name" -> "Paradox Material Theme",
  "github.base_url" -> "https://github.com/jonas/paradox-material-theme"
)

//#color
paradoxProperties in Compile ++= Map(
  "material.color.primary" -> "teal",
  "material.color.accent" -> "indigo"
)
//#color

//#repository
paradoxProperties in Compile ++= Map(
  "material.repo" -> "https://github.com/jonas/paradox-material-theme",
  "material.repo.type" -> "github",
  "material.repo.name" -> "jonas/paradox-material-theme"
)
//#repository

//#social
paradoxProperties in Compile ++= Map(
  "material.social.github" -> "https://github.com/jonas",
  "material.social.twitter" -> "https://twitter.com/priorarts"
)
//#social

//#analytics
paradoxProperties in Compile ++= Map(
  "material.google.analytics" -> "UA-107934279-1" // Remember to change this!
)
//#analytics

//#copyright
paradoxProperties in Compile ++= Map(
  "material.copyright" -> """
    Based on <a href="https://github.com/squidfunk/mkdocs-material">MkDocs Material</a>
    by <a href="https://github.com/squidfunk">Martin Donath</a>
  """
)
//#copyright

val optionExamples = Def.setting(
  //#font
  paradoxProperties in Compile ++= Map(
    "material.font.text" -> "Ubuntu",
    "material.font.code" -> "Ubuntu Mono"
  )
  //#font
  ,
  //#font-disable
  paradoxProperties in Compile ++= Map(
    "material.font.disabled" -> "true" // NOTE: Any value will do
  )
  //#font-disable
  ,
  //#favicon
  paradoxProperties in Compile ++= Map(
    "material.favicon" -> "assets/images/favicon.png"
  )
  //#favicon
  ,
  //#logo
  paradoxProperties in Compile ++= Map(
    "material.logo" -> "assets/images/logo.png"
  )
  //#logo
  ,
  //#logo-icon
  paradoxProperties in Compile ++= Map(
    "material.logo.icon" -> "cloud"
  )
  //#logo-icon
  ,
  //#custom-stylesheet
  paradoxProperties in Compile ++= Map(
    "material.custom.stylesheet" -> "custom.css"
  )
  //#custom-stylesheet
  ,
  //#custom-javascript
  paradoxProperties in Compile ++= Map(
    "material.custom.javascript" -> "assets/custom.js"
  )
  //#custom-javascript
)