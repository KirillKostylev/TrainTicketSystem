package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.controller;

import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.view.Printer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class PrintTicketWindowController {

    @FXML
    private TextArea ticketInfoTextArea;

    @FXML
    void printTicketAction(ActionEvent event) {
        Printer.printToPrinter(ticketInfoTextArea.getText());
    }

    void init(String ticketInfo) {
        ticketInfoTextArea.setText(ticketInfo);
    }

}
