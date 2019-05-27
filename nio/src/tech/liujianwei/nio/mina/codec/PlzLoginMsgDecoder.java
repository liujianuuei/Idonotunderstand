package tech.liujianwei.nio.mina.codec;

import org.apache.mina.common.ByteBuffer;
import org.apache.mina.common.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoder;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;
import tech.liujianwei.nio.mina.model.LoginMsg;
import tech.liujianwei.nio.mina.utils.PlzUtils;

public class PlzLoginMsgDecoder implements MessageDecoder {
    @Override
    public MessageDecoderResult decodable(IoSession session, ByteBuffer buf) {
        if (buf.remaining() < 40) {
            return MessageDecoderResult.NEED_DATA;
        }
        buf.get(); // == '|'
        String type = PlzUtils.getStringFromByteBuffer(buf, 2);
        if("LN".equals(type)) {
            return MessageDecoderResult.OK;
        }
        return MessageDecoderResult.NOT_OK;
    }

    @Override
    public MessageDecoderResult decode(IoSession session, ByteBuffer buf, ProtocolDecoderOutput output) throws Exception {
        PlzUtils.getStringFromByteBuffer(buf, 40);
        LoginMsg msg = new LoginMsg();
        msg.setData("|LN|user:liujianwei,password:123456|END|");
        output.write(msg);
        return MessageDecoderResult.OK;
    }

    @Override
    public void finishDecode(IoSession ioSession, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {

    }
}
