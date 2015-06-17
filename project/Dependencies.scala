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

import sbt._

/**
 * Main Dependencies File
 *
 * @author Elliot Wright <elliot@elliotwright.co>
 */
object Dependencies {
    val repositories = Seq(
        "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/",
        "spray repo" at "http://repo.spray.io",
        "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
    )

    def compile(deps: ModuleID*): Seq[ModuleID] = deps.map(_ % "compile")
    def container(deps: ModuleID*): Seq[ModuleID] = deps.map(_ % "container")
    def provided(deps: ModuleID*): Seq[ModuleID] = deps.map(_ % "provided")
    def runtime(deps: ModuleID*): Seq[ModuleID] = deps.map(_ % "runtime")
    def test(deps: ModuleID*): Seq[ModuleID] = deps.map(_ % "test")

    val akkaVersion = "2.3.11"
    val sprayVersion = "1.3.3"

    val akkaActor  = "com.typesafe.akka"      %% "akka-actor"  % akkaVersion
    val scalaAsync = "org.scala-lang.modules" %% "scala-async" % "0.9.3"
    val scalaTest  = "org.scalatest"          %% "scalatest"   % "2.2.4"
    val sprayCan   = "io.spray"               %% "spray-can"   % sprayVersion
}
