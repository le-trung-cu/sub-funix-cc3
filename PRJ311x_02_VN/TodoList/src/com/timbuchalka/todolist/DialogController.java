package com.timbuchalka.todolist;

import com.timbuchalka.todolist.datamodel.TodoData;
import com.timbuchalka.todolist.datamodel.TodoItem;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class DialogController {
    @FXML
    private TextField shortDescriptionFiled;
    @FXML
    private TextArea detailsArea;
    @FXML
    private DatePicker deadlinePicker;

    public TodoItem processResults(){
        String shortDescription = shortDescriptionFiled.getText();
        String details = detailsArea.getText();
        LocalDate deadlineValue = deadlinePicker.getValue();
        TodoItem todoItem = new TodoItem(shortDescription, details, deadlineValue);
        TodoData.getInstance().addTodoItem(todoItem);
        return todoItem;
    }
}
