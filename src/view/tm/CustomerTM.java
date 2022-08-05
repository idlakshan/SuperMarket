package view.tm;

public class CustomerTM {
    private String id;
    private String name;
    private String address;
    private String tp;

    public CustomerTM() {
    }

    public CustomerTM(String id, String name, String address, String tp) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.tp = tp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }

    @Override
    public String toString() {
        return "CustomerTM{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", tp='" + tp + '\'' +
                '}';
    }
}
