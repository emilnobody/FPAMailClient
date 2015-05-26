/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package message;

import java.io.File;
import java.util.Calendar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author emil
 */
public class Costumer {

    private static ObservableList<Message> data;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        try {
            
            data = FXCollections.observableArrayList();
            File file = new File("src/xmlmessages");
            JAXBContext jaxbContext = JAXBContext.newInstance(Message.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Message nachrichten;
            
            for(File each: file.listFiles()){
            nachrichten = (Message) jaxbUnmarshaller.unmarshal(each);
            showMessage(nachrichten);
            System.out.println("---------------------------------");
            }


        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

    private static void showMessage(Message message) {
        System.out.println("ID: " + message.getId());
        System.out.println("Read Status: " + message.getReadStatus());
        System.out.println("Received at: " + message.getReceivedAt());
        for (MessageStakeholder person : message.getRecipients()) {
            System.out.println("Person:\n  Mail: " + person.getMailAddress()
                    + "\n  Name: " + person.getName());
        }
        System.out.println("Sender:\n  Mail: " + message.getSender().getMailAddress()
                + "\n  Name: " + message.getSender().getName());
        System.out.println("Subject: " + message.getSubject());
        System.out.println("Text: " + message.getText());
        System.out.println("---------------------------------");
        
    }

}
