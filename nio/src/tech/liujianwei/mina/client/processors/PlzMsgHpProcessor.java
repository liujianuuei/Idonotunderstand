package tech.liujianwei.mina.client.processors;

import tech.liujianwei.mina.model.AbstractPlzMsg;

public class PlzMsgHpProcessor extends PlzMsgProcessor {
    @Override
    public void process(AbstractPlzMsg msg) {
        System.out.println("message " + msg.getData() + " processed");
    }
}
