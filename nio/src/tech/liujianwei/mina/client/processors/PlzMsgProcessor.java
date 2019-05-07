package tech.liujianwei.mina.client.processors;

import tech.liujianwei.mina.model.AbstractPlzMsg;

public abstract class PlzMsgProcessor {

    public abstract void process(AbstractPlzMsg msg);
}
