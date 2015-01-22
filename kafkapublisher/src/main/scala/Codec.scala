/**
 * Created by Swetha on 11/9/14.
 * Modified by Bilna on 12/9/14
 */

import kafka.serializer.{Decoder, Encoder}
import kafka.utils.VerifiableProperties
import org.jnetpcap.packet.PcapPacket


class PcapEncoder(props: VerifiableProperties) extends Encoder[PcapPacket] {
   def toBytes(packet:PcapPacket):Array[Byte] = {
     // Added by Bilna
     val toByte = new Array[Byte](packet.getTotalSize());
     packet.transferStateAndDataTo(toByte)
     toByte
   }
}

class PcapDecoder(props: VerifiableProperties) extends Decoder[PcapPacket] {
    def fromBytes(bytes:Array[Byte]) : PcapPacket = {
      var packet: PcapPacket = null
      try {
        packet = new PcapPacket(bytes)
      } catch {
        case e: Exception => e.getMessage
      }
      packet
    }
}


