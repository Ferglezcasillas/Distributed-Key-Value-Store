package se.kth.id2203.simulation

import java.net.{InetAddress, UnknownHostException}

import org.scalatest._
import se.kth.id2203.ParentComponent
import se.kth.id2203.networking._
import se.sics.kompics.network.Address
import java.net.{InetAddress, UnknownHostException}
import se.sics.kompics.sl._
import se.sics.kompics.sl.simulator._
import se.sics.kompics.simulator.{SimulationScenario => JSimulationScenario}
import se.sics.kompics.simulator.run.LauncherComp
import se.sics.kompics.simulator.result.SimulationResultSingleton

import scala.concurrent.duration._

class SequenceConsensusTest extends FlatSpec with Matchers{

  /**
    * # Sequence Paxos Properties
    *
    * ## Validity
    * Only proposed values may be decided
    *
    * Desc: If process p decides v then v is a sequence of proposed commands (without duplicates)
    * Test: Save all propositions from simulated clients, and ensure that decided sequence only contains propositions without duplicates.
    *       (Just checking that the sequences are correct from proposed values is enough to test the termination, uniformity and integrity.)
    *
    * ## Uniform Agreement
    * No two processes decide different values
    *
    * Desc: If process p decides u and process q decides v then one is a prefix of the other
    * Test: (Same as below)
    *
    * ## Integrity
    * Each process can decide at most one value
    *
    * Desc: If process p decides u and later decides v then u is a strict prefix of v
    * Test: (All nodes gets the same sequence. They are not allowed to retract decided sequences)
    *
    * ## Termination (liveness)
    * Every correct process eventually decides a value
    *
    * Desc: If command C is proposed by a correct process then eventually every correct process decides a sequence containing C
    * Test: (After a fixed interval, the value is actually decided upon. We just check that all proposed values were decided upon
    *        at the end of the test)
    */


  "Decided values" should "be non duplicate values" in {
    val seed = 123l
    JSimulationScenario.setSeed(seed)
  }

  "Decided values" should "only contain proposed values" in {

  }

}

object SimpleConsensusScenario {
  import Distributions._

  // needed for the distributions, but needs to be initialised after setting the seed
  implicit val random = JSimulationScenario.getRandom()

  val numberOfNodes = 20
  val topology: List[NetAddress] = (0 to numberOfNodes).toList.map(intToAddress(_))

  private def intToAddress(i: Int): NetAddress = {
    try {
      NetAddress(InetAddress.getByName("192.193.0." + i), 45678)
    } catch {
      case ex: UnknownHostException => throw new RuntimeException(ex)
    }
  }

//  val startServerOp = Op { (self: Integer) =>
//    val selfAddr = intToAddress(self)
//    val conf = Map(
//      "id2203.project.address" -> selfAddr,
//      "id2203.project.topology" -> topology
//    )
//    StartNode(selfAddr, Init.none[], conf)
//  }

//  def scenario(servers: Int): JSimulationScenario = {
//
////    val startCluster = raise(servers, startServerOp, 1.toN).arrival(constant(1.second))
////    val startClients = raise(1, startClientOp, 1.toN).arrival(constant(1.second))
////
////    startCluster andThen
////      10.seconds afterTermination startClients andThen
////      100.seconds afterTermination Terminate
//  }
}