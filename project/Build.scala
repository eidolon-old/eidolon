/**
 * This file is part of the "eidolon" project.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the LICENSE is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

import sbt.Keys._
import sbt.{Build => BaseBuild, _}
import spray.revolver.RevolverPlugin._

/**
 * Main build file
 *
 * @author Elliot Wright <elliot@elliotwright.co>
 */
object Build extends BaseBuild {

    import Dependencies._

    lazy val commonSettings = Seq(
        organization := "io.symcore",
        version := "0.1.0-SNAPSHOT",
        scalaVersion := "2.11.6",
        resolvers ++= Dependencies.repositories
    )

    lazy val root = (project in file("."))
        .settings(commonSettings: _*)
        .settings(name := "eidolon")
        .settings(Revolver.settings)
        .dependsOn(container)

    lazy val container = (project in file("components/eidolon-container"))
        .settings(commonSettings: _*)
        .settings(name := "component.container")
        .settings(libraryDependencies ++=
            test(scalaTest)
        )

    lazy val router = (project in file("components/eidolon-router"))
        .settings(commonSettings: _*)
        .settings(name := "component.router")
        .settings(libraryDependencies ++=
            test(scalaTest)
        )
}
