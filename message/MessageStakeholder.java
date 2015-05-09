package message;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Benjamin Haupt on 03.04.15.
 */
public class MessageStakeholder {

    private StringProperty name;
    private StringProperty mailAddress;

    public MessageStakeholder() {
        this.name = new SimpleStringProperty();
        this.mailAddress = new SimpleStringProperty();
    }
    
    
    public MessageStakeholder( String name, String mailAdresse){
    this();
    this.name.set(name);
    this.mailAddress.set(mailAdresse);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getMailAddress() {
        return mailAddress.get();
    }

    public StringProperty mailAddressProperty() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress.set(mailAddress);
    }
}