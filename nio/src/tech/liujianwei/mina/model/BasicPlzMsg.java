package tech.liujianwei.mina.model;

public class BasicPlzMsg extends AbstractPlzMsg {

    private String data;
    private int length;
    private String type = "Basic";

    @Override
    public String getData() {
        return this.data;
    }

    @Override
    public int getLength() {
        if(this.length >0 ){
            return this.length;
        }
        try {
            return this.data.getBytes("UTF-8").length;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public void setData(String data) {
        this.data = data;
    }

    @Override
    public void setLength(int len) {
        this.length = len;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }
}
