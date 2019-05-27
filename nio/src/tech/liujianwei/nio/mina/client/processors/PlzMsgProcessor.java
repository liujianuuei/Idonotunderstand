package tech.liujianwei.nio.mina.client.processors;

import tech.liujianwei.nio.mina.model.AbstractPlzMsg;

public abstract class PlzMsgProcessor {

    public abstract void process(AbstractPlzMsg msg);
}
