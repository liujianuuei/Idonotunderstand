package tech.liujianwei.nio.mina.codec;

import org.apache.mina.common.ByteBuffer;
import org.apache.mina.common.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoder;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;
import tech.liujianwei.nio.mina.model.PlzHpMsg;
import tech.liujianwei.nio.mina.utils.PlzUtils;

public class PlzHpMsgDecoder implements MessageDecoder {

    @Override
    public MessageDecoderResult decodable(IoSession session, ByteBuffer buf) {
        System.out.println("buf position is "+buf.position());
        System.out.println("buf remaining is "+buf.remaining());

        if (buf.remaining() < 16) {
            return MessageDecoderResult.NEED_DATA;
        }
        buf.get();
        String type = PlzUtils.getStringFromByteBuffer(buf, 2);
        if("HP".equals(type)) {
            return MessageDecoderResult.OK;
        }
        return MessageDecoderResult.NOT_OK;
    }

    @Override
    public MessageDecoderResult decode(IoSession session, ByteBuffer buf, ProtocolDecoderOutput output) throws Exception {
        String s = PlzUtils.getStringFromByteBuffer(buf, 22);
        PlzHpMsg msg = new PlzHpMsg();
        msg.setData(s);
        output.write(msg);
        return MessageDecoderResult.OK;
    }

    @Override
    public void finishDecode(IoSession ioSession, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {

    }
}
