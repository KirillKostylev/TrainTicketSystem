package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.controller;

import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.view.Printer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import org.apache.log4j.Logger;

public class PrintTicketWindowController {

    private static final Logger LOG = Logger.getLogger(PrintTicketWindowController.class);

    @FXML
    private TextArea ticketInfoTextArea;

    @FXML
    void printTicketAction(ActionEvent event) {
        Printer.printToPrinter(ticketInfoTextArea.getText());
        LOG.trace("Ticket printed");
    }

    void init(String ticketInfo) {
        ticketInfoTextArea.setText(ticketInfo);
    }

}
