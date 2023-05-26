package com.example.app6;

import Plants.Plant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;

import java.util.ArrayList;

public class Controller {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField name;
    @FXML
    private TextField familyName;
    @FXML
    private TextField plantSort;
    @FXML
    private TextField type;
    @FXML
    private TextField age;
    @FXML
    private Button edit;
    @FXML
    private Button check;
    @FXML
    private TableColumn<Plant, String> colName;
    @FXML
    private TableColumn<Plant, String> colFamilyName;
    @FXML
    private TableColumn<Plant, String> colPlantSort;
    @FXML
    private TableColumn<Plant, String> colType;
    @FXML
    private TableColumn<Plant, Double> colAge;
    @FXML
    private TableView<Plant> table;
    @FXML
    private TextField find;
    @FXML
    private Button back;

    ObservableList<Plant> list = FXCollections.observableArrayList();
    ArrayList<Plant> arrayList=new ArrayList<Plant>();

    public void initialize() {
        colName.setCellValueFactory(new PropertyValueFactory<Plant, String>("name"));
        colFamilyName.setCellValueFactory(new PropertyValueFactory<Plant, String>("familyName"));
        colPlantSort.setCellValueFactory(new PropertyValueFactory<Plant, String>("plantSort"));
        colType.setCellValueFactory(new PropertyValueFactory<Plant, String>("type"));
        colAge.setCellValueFactory(new PropertyValueFactory<Plant, Double>("age"));
        table.setEditable(true);
        arrayList.add(new Plant("Барбарис", "Барбарисовые", "Барбарис", "Обыкновенный", 50d));
        arrayList.add(new Plant("Жасмин", "Маслинные", "Жасмин", "Китайский", 10d));
        arrayList.add(new Plant("Ирис", "Ирисовые", "Ирис", "Японский", 50d));
        arrayList.add(new Plant("Туя", "Кипарисовые", "Туя", "Западная", 700d));
        arrayList.add(new Plant("Лох", "Лоховые", "Лох", "Колючий", 100d));
        list.addAll(arrayList);
        table.setItems(list);
    }

    @FXML //Метод удаления строки
    protected void delete(){
        if (!list.isEmpty())
        {
            int row = table.getSelectionModel().getSelectedIndex();
            if (row >= 0){
                arrayList.remove(row);
                list.remove(row);
                sort();
            }
            else {
                wrongInput("Удаление", "Не выбран ряд удаления");
            }
        }
        else {
            wrongInput("Удаление", "Список пуст");
        }
    }
    @FXML
    protected void addLine(){
        try {
            if (name.getText().matches("^[а-яА-Я]*$")
                    && familyName.getText().matches("^[а-яА-Я]*$")
                    && plantSort.getText().matches("^[а-яА-Я]*$")
                    && type.getText().matches("^[а-яА-Я]*$")
                    && Double.parseDouble(age.getText()) > 0 && age.getText().matches("^[1-9]\\d*$")) {
                Plant plantCol = new Plant(name.getText(), familyName.getText(), plantSort.getText(),
                        type.getText(), Double.parseDouble(age.getText()));
                list.add(plantCol);
                arrayList.add(plantCol);
                table.setItems(list);
                sort();
                freeInput();
            }
            else {
                wrongInput("Некорректный ввод", "Введите корректные данные\n Текст без пробелов\n Число положительное");
            }
        }catch (IllegalStateException | NumberFormatException exc){
            wrongInput("Некорректный ввод", "Введите корректные данные\n Текст без пробелов\n Число положительное");
        }

    }
    protected void wrongInput(String header, String exc) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(header);
        alert.setContentText(exc);
        freeInput();
        alert.showAndWait();
    }

    protected void freeInput(){
        name.setText("");
        familyName.setText("");
        plantSort.setText("");
        type.setText("");
        age.setText("");
    }

    @FXML //Метод сортировки по стране
    protected void sort(){
        if (!list.isEmpty()){
            table.getSortOrder().addAll(colName);
        }
        else {
            wrongInput("Сортировка", "Список пуст");
        }
    }

    @FXML
    protected void check() {
        int row = table.getSelectionModel().getSelectedIndex();
        if (row >= 0) {
            Plant chosen = list.get(row);
            name.setText(chosen.getName());
            familyName.setText(chosen.getFamilyName());
            plantSort.setText(chosen.getPlantSort());
            type.setText(chosen.getType());
            age.setText(Double.toString(chosen.getAge()));
        }
        else {
            wrongInput("Чек", "Не выбран ряд");
        }
    }
    @FXML
    protected void edit(){
        int row = table.getSelectionModel().getSelectedIndex();
        if(row >= 0)
        {
            if (!list.isEmpty())
            {
                try{
                    if (name.getText().matches("^[а-яА-Я]*$") && name.getText() != ""
                            && familyName.getText().matches("^[а-яА-Я]*$") && familyName.getText() != ""
                            && plantSort.getText().matches("^[а-яА-Я]*$") && plantSort.getText() != ""
                            && type.getText().matches("^[а-яА-Я]*$") && type.getText() != ""
                            && Double.parseDouble(age.getText()) > 0 && age.getText().matches("^[1-9]\\d*$")) {
                        Plant plant = new Plant(name.getText(), familyName.getText(), plantSort.getText(),
                                type.getText(), Double.parseDouble(age.getText()));
                        arrayList.remove(row);
                        list.remove(row);
                        arrayList.add(row,plant);
                        list.add(row,plant);
                        sort();
                    }
                    else {
                        wrongInput("Некорректный ввод", "Введите корректные данные\n Текст без пробелов\n Число положительное");
                    }
                }catch (IllegalStateException | NumberFormatException exc){
                    wrongInput("Некорректный ввод", "Введите корректные данные\n Текст без пробелов\n Число положительное");
                }
            }
            else {
                wrongInput("Редактирование", "Список пуст");
            }
        }
        else{
            wrongInput("Редактирование", "Не выбран ряд редактирования");
        }
    }

    @FXML //Метод поиска по возрасту
    protected void search(){
        if (find.getText().matches("^[1-9]\\d*$") && Double.parseDouble(find.getText()) > 0 && find.getText() != ""){
            table.setItems(list);
            ObservableList<Plant> ages=FXCollections.observableArrayList();
            if(!list.isEmpty()){
                for(int i=0;i<list.size();i++){
                    if (colAge.getCellData(i).equals(Double.parseDouble(find.getText()))) {
                        ages.add(list.get(i));
                    }}}
            else {
                wrongInput("Поиск", "Список пуст");
                find.setText("");
            }
            if (!ages.isEmpty()) {
                table.setItems(ages);
                back.setDisable(false);
            }
            else {
                wrongInput("Поиск", "Такого возраста растения не найдены");
                find.setText("");
            }
        }
        else {
            wrongInput("Поиск", "Некорректный ввод данных");
            find.setText("");
        }
    }
    @FXML //Метод осуществления возвращения к первоначальной таблице
    protected void back(){
        table.setItems(list);
        find.setText(null);
        back.setDisable(true);
    }

}