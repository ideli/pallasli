import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class MessageTextDecoder implements Decoder.Text<Message> {
	@Override
	public void init(EndpointConfig ec) {
	}

	@Override
	public void destroy() {
	}

	@Override
	public Message decode(String string) throws DecodeException {
		// // Read message...
		// if ( /* message is an A message */ )
		// return new MessageA( );
		// else if ( /* message is a B message */ )
		// return new MessageB( );
		return null;
	}

	@Override
	public boolean willDecode(String string) {
		// Determine if the message can be converted into either a
		// MessageA object or a MessageB object...
		// return canDecode;
		return false;
	}
}
