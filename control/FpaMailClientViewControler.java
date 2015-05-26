package control;

import java.io.*;
import java.net.URL;
import java.util.*;

import javax.xml.stream.*;
import javax.xml.stream.events.XMLEvent;

import java.io.File;
import javax.xml.bind.*;
import message.Message;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import message.MessageImportance;
import message.MessageStakeholder;

/*
* Eine Klasse die einen MaiClient initializiert 
* Autor: Emil Steinkopf
*/
public class FpaMailClientViewControler implements Initializable {

    @FXML
    private Label date;

    @FXML
    private TableColumn<Message, Boolean> read;

    @FXML
    private TableColumn<Message, String> subject;

    @FXML
    private Button forward;

    @FXML
    private TextArea textarea;

    @FXML
    private Button replay;

    @FXML
    private TableColumn<Message, MessageImportance> priority;

    @FXML
    private Label messageContent;

    @FXML
    private TableColumn<Message, String> sender;

    @FXML
    private TableColumn<Message, LocalDateTime> recived;

    @FXML
    private Label from;

    @FXML
    private TableView<Message> tableview;

    @FXML
    private Label to;

    @FXML
    private Button replayall;

    private DateTimeFormatter grermanDateTime;
    private ObservableList<Message> messageList;

    @Override
    public void initialize(URL location, ResourceBundle resource) {
        // TODO Auto-generated method stub
        File file = new File("src/xmlmessages");
        grermanDateTime = DateTimeFormatter.ofPattern("dd MM yyyy HH:mm");
        messageList = FXCollections.observableArrayList();
        fillTableWithMessages( file);
        tableview.getSelectionModel().selectedItemProperty().addListener((ObservableValue,oldValue,newValue)-> load(newValue));
        setContextMenu();
        configureTable();

    }

    private void configureTable() {

        textarea.setEditable(false);

        recived.setCellValueFactory(cellData -> cellData.getValue().receivedAtProperty());
        recived.setCellFactory(cellData -> new TableCell<Message, LocalDateTime>() {
            @Override
            protected void updateItem(LocalDateTime receivedDate, boolean empty) {
                super.updateItem(receivedDate, empty);

                if (receivedDate == null || empty) {
                    setText(null);
                    setStyle("");

                } else {
                    
                    setText(grermanDateTime.format(receivedDate));

                }

            }
        }
        );

        read.setCellValueFactory(cellData -> cellData.getValue().readStatusProperty());
        read.setCellFactory((TableColumn<Message, Boolean> column) -> {
            return new TableCell<Message, Boolean>() {
                @Override
                protected void updateItem(Boolean readStatus, boolean empty) {
                    super.updateItem(readStatus, empty);

                    if (readStatus == null || empty) {
                        setText(null);
                        setStyle("");
                    } else {
                        ImageView imageView = new ImageView();
                        Image priorityImage;
                        imageView.setFitWidth(15.0);
                        imageView.setFitHeight(15.0);
                        if (readStatus) {
                            priorityImage = new Image("images/erfolgreich_icon.jpg.png");
                        } else {
                            priorityImage = new Image("images/x_button.jpg.png");
                        }
                        imageView.setImage(priorityImage);

                        setGraphic(imageView);
                    }
                }
            };
        });
        priority.setCellValueFactory(column -> column.getValue().importanceOfMessageProperty());
        priority.setCellFactory(column -> new TableCell<Message, MessageImportance>() {
            @Override
            protected void updateItem(MessageImportance importanceOfMessage, boolean empty) {
                super.updateItem(importanceOfMessage, empty);

                if (importanceOfMessage == null || empty) {
                    setText(null);
                    setText("");
                } else {
                    ImageView imageView = new ImageView();
                    Image priorityImage = null;
                    imageView.setFitWidth(15.0);
                    imageView.setFitHeight(15.0);

                    if (MessageImportance.LOW.equals(importanceOfMessage)) {
                        priorityImage = new Image("images/pfeil_gelb.jpg");
                    }

                    if (MessageImportance.NORMAL.equals(importanceOfMessage)) {
                        priorityImage = new Image("images/pfeil_gruen_rechts.jpg");
                    }

                    if (MessageImportance.HIGH.equals(importanceOfMessage)) {
                        priorityImage = new Image("images/pfeilrot.jpg.png");
                    }
                    imageView.setImage(priorityImage);
                    setGraphic(imageView);
                }

            }
        });

        sender.setCellValueFactory(cellData -> cellData.getValue().senderProperty().get().mailAddressProperty());

        subject.setCellValueFactory(cellData -> cellData.getValue().subjectProperty());

        /* Message m1 = new Message(MessageImportance.LOW, LocalDateTime.now(), true, new MessageStakeholder("Karl", "Karl@gmail.de"), "Karlowiski");
         m.add(m1);

         Message m2 = new Message(MessageImportance.HIGH, LocalDateTime.now(), Boolean.FALSE, new MessageStakeholder("Pati", "pat@gmail.de"), "Pati");
         m.add(m2);

         Message m3 = new Message(MessageImportance.NORMAL, LocalDateTime.now(), true, new MessageStakeholder("Sara", "hexe@gmail.de"), "Sara");
         m.add(m3);

         //tableview.setItems(m); *
        

         /*data = FXCollections.observableArrayList();
         File file = new File("src/xmlmessages");
         JAXBContext jaxbContext = JAXBContext.newInstance(Message.class);
         Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

         // for (File each : file.listFiles()) {
         //   nachrichten = (Message) jaxbUnmarshaller.unmarshal(each);
         ////} */
    }

    public void fillTableWithMessages(File file) {
        //File file = new File(pathname);
        for (File each : file.listFiles()) {
            try {
                messageList.add(XMLFileConverter(each));

            } catch (JAXBException ex) {
                Logger.getLogger(FpaMailClientViewControler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        tableview.setItems(messageList);
    }
    
    public void save(Message message ){
        try {
		File file = new File("src/xmlmessages/"+message.getId()+".xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(Message.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
 
		// output pretty printed
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
 
		jaxbMarshaller.marshal(message, file);
		jaxbMarshaller.marshal(message, System.out);
 
	      } catch (JAXBException e) {
	      }
 
	
        
       
    }
    
    private void load(Message message) {
         message.setReadStatus(true);
              textarea.setText(message.getText());
              date.setText(message.getReceivedAt().format(grermanDateTime));
              from.setText(message.getSender().getName()+"("+message.getSender().getMailAddress()+")");
              messageContent.setText(message.getSubject());
              
              StringBuilder recipientsname = new StringBuilder(message.getRecipients().get(0).getName());
              for( int i = 1; i< message.getRecipients().size();++i){
              recipientsname.append(", ");
              recipientsname.append(message.getRecipients().get(i).getName());
              }
              to.setText(recipientsname.toString());

         
         save(message);
     }

    private void setContextMenu() {
         MenuItem item = new MenuItem("mark as unread");
         item.setOnAction(event -> {
          Message selectedMessage = tableview.getSelectionModel().getSelectedItem();
         selectedMessage.setReadStatus(false);
//             save(tableview.getSelectionModel().getSelectedItem())
          });
             
         //Kontekxtmenü mit Funktion
         ContextMenu menu = new ContextMenu(item);
         tableview.setContextMenu(menu);
     }
    
    
    
    public Message XMLFileConverter(final File folder) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Message.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (Message) jaxbUnmarshaller.unmarshal(folder);
    }

    
    /*
    private void initTable() {

        read.setCellValueFactory(new PropertyValueFactory<>("readStatus"));
        sender.setCellValueFactory(new PropertyValueFactory<>("sender"));
        subject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        recived.setCellValueFactory(new PropertyValueFactory<>("receivedAt"));
        priority.setCellValueFactory(new PropertyValueFactory<>("importanceOfMessage"));

        DateTimeFormatter germanFormatter = DateTimeFormatter.ofLocalizedDate(
                FormatStyle.MEDIUM).withLocale(Locale.GERMAN);

        LocalDate xmas = LocalDate.parse("24.12.2014", germanFormatter);
        System.out.println(xmas);

        Message m1 = new Message(MessageImportance.LOW, LocalDateTime.now(), true, new MessageStakeholder(), "ko");
        messageList.add(m1);
        tableview.setItems(messageList);

        Message m2 = new Message(MessageImportance.LOW, LocalDateTime.now(), true, new MessageStakeholder(), "ko");
        messageList.add(m1);
        tableview.setItems(messageList);

    } */

    /*public List<String> readIds(URL url) throws IOException,
     XMLStreamException, FactoryConfigurationError {
        
     List<String> titleList = new LinkedList<String>();
     try (InputStream in = url.openStream()) {
     XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(in);

     // while-Schleife zum Verarbeiten der Events vom reader
     while (reader.hasNext()) {
     int event = reader.next();
     if (event == XMLEvent.START_ELEMENT
     && "entry".equals(reader.getLocalName())) {
     titleList.add(readEntry(reader));
     }
     }
     }

     return titleList;
     }

     private String readEntry(XMLStreamReader reader) throws XMLStreamException {
     String title = "";
     while (reader.hasNext()) {
     int event = reader.next();
     if (XMLEvent.START_ELEMENT == event
     && "title".equals(reader.getLocalName())) {
     title = reader.getElementText();
     break;
     }
     }
     return title;
     }*/

    
    
}
