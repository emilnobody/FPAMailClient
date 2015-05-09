package control;

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
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

public class FpaMailClientViewControler implements Initializable {

    private ObservableList<Message> m;

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
    private Label message;

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

    @Override
    public void initialize(URL location, ResourceBundle resource) {
        // TODO Auto-generated method stub

        m = FXCollections.observableArrayList();
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
                    DateTimeFormatter f1 = DateTimeFormatter.ofPattern("dd MM yyyy HH:mm");
                    setText(f1.format(receivedDate));

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

        Message m1 = new Message(MessageImportance.LOW, LocalDateTime.now(), true, new MessageStakeholder("Karl", "Karl@gmail.de"), "Karlowiski");
        m.add(m1);
        
        Message m2 = new Message(MessageImportance.HIGH, LocalDateTime.now(), Boolean.FALSE, new MessageStakeholder("Pati", "pat@gmail.de"), "Pati");
        m.add(m2);
        
        Message m3 = new Message(MessageImportance.NORMAL, LocalDateTime.now(), true, new MessageStakeholder("Sara", "hexe@gmail.de"), "Sara");
        m.add(m3);
        
       tableview.setItems(m);
    }

    public LocalDateTime inLocalTime(LocalDate localDate) throws IllegalArgumentException {

        DateTimeFormatter germanFormatter = DateTimeFormatter.ofLocalizedDate(
                FormatStyle.MEDIUM).withLocale(Locale.GERMAN);

        localDate = LocalDate.parse(LocalDateTime.now().toString(), germanFormatter);
        LocalDateTime datetime = LocalDateTime.of(localDate, LocalTime.MIN);

        return datetime;

    }

   

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
        m.add(m1);
        tableview.setItems(m);

        Message m2 = new Message(MessageImportance.LOW, LocalDateTime.now(), true, new MessageStakeholder(), "ko");
        m.add(m1);
        tableview.setItems(m);

    }

}
