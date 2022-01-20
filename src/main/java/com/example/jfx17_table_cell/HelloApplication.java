package com.example.jfx17_table_cell;

import java.io.IOException;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.property.SimpleLongProperty;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class HelloApplication extends Application {

  public record Wrapper(Long value, Long value2) {

  }

  private static final Logger logger = Logger.getLogger(HelloApplication.class.getName());

  @Override
  public void start(Stage stage) throws IOException {
    BorderPane pane = new BorderPane();
    final TreeItem<Wrapper> root = new TreeItem<>(new Wrapper(0L, 0L));
    root.setExpanded(true);
    TreeTableView<Wrapper> table = new TreeTableView<>(root);
    table.setShowRoot(false);
    TreeTableColumn<Wrapper, Long> column = new TreeTableColumn<>();
    TreeTableColumn<Wrapper, Long> column2 = new TreeTableColumn<>();
    column2.setMinWidth(20);

    pane.setCenter(table);

    column.setCellFactory(new Callback<>() {
      @Override
      public TreeTableCell<Wrapper, Long> call(TreeTableColumn<Wrapper, Long> param) {
        return new TreeTableCell<>() {
          @Override
          protected void updateItem(Long item, boolean empty) {
            super.updateItem(item, empty);
            if (!empty) {
              logger.info("updating " + item);
            }
            FlowPane graphic = new FlowPane(new Label("Graphic " + item));
//            graphic.setMinWidth(120);
            setGraphic(graphic);
            setMinWidth(120);
          }
        };
      }
    });

    column.setCellValueFactory(
        param -> new SimpleLongProperty(param.getValue().getValue().value).asObject());
    column2.setCellValueFactory(param -> new SimpleLongProperty(param.getValue().getValue().value2).asObject());

    for (long i = 1; i < 3_000; i++) {
      root.getChildren().add(new TreeItem<>(new Wrapper(i, i*i)));
    }

    table.getColumns().addAll(column, column2);

    Scene scene = new Scene(pane, 500, 500);
    stage.setTitle("Hello! Scroll a bit up and down and watch the log");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }
}
