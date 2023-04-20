module advanced_topics_final {
    requires javafx.controls;
    requires javafx.fxml;

    opens advanced_topics_final to javafx.fxml;
    exports advanced_topics_final;
}
