public class Attachment {
    //set up my variables

    private String name;

    private byte[] contents;

    //set up my getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getContents() {
        return contents;
    }
    //set up my java bean contents

    public void setContents(byte[] contents) {
        this.contents = contents;
    }
}
