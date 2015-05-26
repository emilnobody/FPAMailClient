package message;

import java.io.File;
import javafx.beans.property.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Benjamin Haupt on 26.03.15.
 */
@XmlRootElement(name = "message")
public class Message {

    private String id;
    private ObjectProperty<MessageImportance> importanceOfMessage;
    private ObjectProperty<LocalDateTime> receivedAt;
    private BooleanProperty readStatus;
    private ObjectProperty<MessageStakeholder> sender;
    private StringProperty subject;
    private StringProperty text;
    private List<MessageStakeholder> recipients;

    
   
    private List<Message> messages = null;

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    
    

    public Message() {
        this.importanceOfMessage = new SimpleObjectProperty<>();
        this.subject = new SimpleStringProperty();
        this.readStatus = new SimpleBooleanProperty();
        this.receivedAt = new SimpleObjectProperty<>();
        this.sender = new SimpleObjectProperty<>();
        this.text = new SimpleStringProperty();
        this.recipients = new ArrayList<>();
    }

    public Message(MessageImportance importanceOfMessage, LocalDateTime receivedAt, Boolean readStatus, MessageStakeholder sender, String subject) {

        this();
        this.importanceOfMessage.set(importanceOfMessage);
        this.subject.set(subject);
        this.readStatus.set(readStatus);
        this.sender.set(sender);
        this.receivedAt.set(receivedAt);

    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public ObjectProperty<MessageImportance> importanceOfMessageProperty() {
        return importanceOfMessage;
    }

    
    public void setImportanceOfMessage(MessageImportance importanceOfMessage) {
        this.importanceOfMessage.set(importanceOfMessage);
    }

    public MessageImportance getImportanceOfMessage() {
        return this.importanceOfMessage.get();
    }

   
  public ObjectProperty<LocalDateTime> receivedAtProperty() {
        return receivedAt;
    } 

    
    public void setReceivedAt(LocalDateTime receivedAt) {
        this.receivedAt.set(receivedAt);
    }

    public LocalDateTime getReceivedAt() {
        return this.receivedAt.get();
    }

    public BooleanProperty readStatusProperty() {
        return readStatus;
    }

    
    public void setReadStatus(boolean readStatus) {
        this.readStatus.set(readStatus);
    }

    public boolean getReadStatus() {
        return this.readStatus.get();
    }

    public ObjectProperty<MessageStakeholder> senderProperty() {
        return sender;
    }

    
    public void setSender(MessageStakeholder messageStakeholder) {
        sender.set(messageStakeholder);
    }

    public MessageStakeholder getSender() {
        return sender.get();
    }

    public StringProperty subjectProperty() {
        return subject;
    }

    
    public void setSubject(String subject) {
        this.subject.set(subject);
    }

    public String getSubject() {
        return this.subject.get();
    }

    
    public StringProperty textProperty() {
        return text;
    }

    public void setText(String text) {
        this.text.set(text);
    }

    public String getText() {
        return this.text.get();
    }
@XmlElementWrapper( name="recipients")
@XmlElement( name="recipient")
    public List<MessageStakeholder> getRecipients() {
        return recipients;
    }

    
    public void setRecipients(List<MessageStakeholder> recipients) {
        this.recipients = recipients;
    }
}
