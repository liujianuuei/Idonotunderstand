package tech.liujianwei.xinhua08.model;

import java.io.Serializable;

public abstract class AbstractPlzMsg implements Serializable {
    public abstract String getData();
    public abstract int getLength();
    public abstract String getType();

    public abstract void setData(String data);
    public abstract void setLength(int len);
    public abstract void setType(String type);
}
