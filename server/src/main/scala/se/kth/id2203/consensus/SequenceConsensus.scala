package se.kth.id2203.consensus

import se.sics.kompics.KompicsEvent
import se.sics.kompics.sl.Port

case class SC_Propose(value: RSM_Command) extends KompicsEvent

case class SC_Decide(value: RSM_Command) extends KompicsEvent

trait RSM_Command

class SequenceConsensus extends Port {
  request[SC_Propose]
  indication[SC_Decide]
}
