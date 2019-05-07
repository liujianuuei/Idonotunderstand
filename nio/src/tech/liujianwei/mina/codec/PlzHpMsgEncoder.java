package tech.liujianwei.mina.codec;

import org.apache.mina.common.ByteBuffer;
import org.apache.mina.common.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.MessageEncoder;
import tech.liujianwei.mina.model.AbstractPlzMsg;
import tech.liujianwei.mina.model.PlzHpMsg;

import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class PlzHpMsgEncoder implements MessageEncoder {

    private static final Set<Class> TYPES;

    static {
        Set<Class> types = new HashSet();
        types.add(PlzHpMsg.class);
        TYPES = Collections.unmodifiableSet(types);
    }

    @Override
    public Set<Class> getMessageTypes() {
        return TYPES;
    }

    @Override
    public void encode(IoSession session, Object message, ProtocolEncoderOutput output) throws Exception {
        if (message != null) {
            AbstractPlzMsg msg = (AbstractPlzMsg) message;
            ByteBuffer buf = ByteBuffer.allocate(msg.getLength());
            buf.putString(msg.getData(), Charset.forName("UTF-8").newEncoder());
            buf.flip();
            output.write(buf);
        }
    }
}
