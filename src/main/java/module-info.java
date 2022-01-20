module com.example.jfx17_table_cell {
  requires javafx.controls;
  requires javafx.fxml;

  requires org.controlsfx.controls;
  requires java.logging;

  opens com.example.jfx17_table_cell to javafx.fxml;
  exports com.example.jfx17_table_cell;
}
