module com.aquadrat.parkplatzfrontend {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires spring.web;
    requires com.fasterxml.jackson.databind;

    opens com.aquadrat.parkplatzfrontend to javafx.fxml;
    exports com.aquadrat.parkplatzfrontend;

    opens com.aquadrat.parkplatzfrontend.model.dto to com.fasterxml.jackson.databind;
    exports  com.aquadrat.parkplatzfrontend.model.dto;

    opens com.aquadrat.parkplatzfrontend.model.enums to com.fasterxml.jackson.databind;
    exports  com.aquadrat.parkplatzfrontend.model.enums;

    opens com.aquadrat.parkplatzfrontend.model;
    exports com.aquadrat.parkplatzfrontend.model;
}