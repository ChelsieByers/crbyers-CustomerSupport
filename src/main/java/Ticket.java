import java.util.HashMap;
import java.util.Map;

public class Ticket {
    //Setting up my variables
    private String customerName;
    private String subject;
    private String body;
    private Map<Integer, Attachment> attachmentMap = new HashMap<>();

    //constructor
    public Ticket() {
        super();
    }

    //constructor
    public Ticket(String customerName, String subject, String body) {
        this.customerName = customerName;
        this.subject = subject;
        this.body = body;
    }

    //creating my getters and setters
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void addAttachment (int index, Attachment attachment) {
        attachmentMap.put(index, attachment);
    }
    public int getNumberAttachments() {
        return attachmentMap.size();
    }
    public Attachment getAttachment(int index) {
        return attachmentMap.get(index);
    }

    public Map<Integer, Attachment> getAllAttachments() {
        return attachmentMap;
    }

    //write the inputs to the ticket string
    @Override
    public String toString() {
        return "Ticket{" + "customerName='" + customerName + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", attachmentsMap=" + attachmentMap +
                '}';
    }
}
