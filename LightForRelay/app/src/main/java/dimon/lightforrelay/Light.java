package dimon.lightforrelay;


public class Light {
    private int id;
    private String name;
    private boolean open;
    private String bottom_txt;

    public String getBottom_txt() {
        return bottom_txt;
    }

    public void setBottom_txt(String bottom_txt) {
        this.bottom_txt = bottom_txt;
    }

    public Light(int id, String name, boolean open,String bottom_txt) {
        this.id = id;
        this.name = name;
        this.open = open;
        this.bottom_txt = bottom_txt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
