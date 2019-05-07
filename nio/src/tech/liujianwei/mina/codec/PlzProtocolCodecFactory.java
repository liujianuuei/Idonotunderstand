package tech.liujianwei.mina.codec;

import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;

public class PlzProtocolCodecFactory extends DemuxingProtocolCodecFactory {

    public PlzProtocolCodecFactory() {
        super.register(PlzLoginMsgEncoder.class);
        super.register(PlzLoginMsgDecoder.class);
        super.register(PlzHpMsgEncoder.class);
        super.register(PlzHpMsgDecoder.class);
    }
}
