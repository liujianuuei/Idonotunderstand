package tech.liujianwei.xinhua08.client.processors;

import tech.liujianwei.xinhua08.model.AbstractPlzMsg;

public class PlzMsgHpProcessor extends PlzMsgProcessor {
    @Override
    public void process(AbstractPlzMsg msg) {
        System.out.println("message " + msg.getData() + " processed");
    }
}
