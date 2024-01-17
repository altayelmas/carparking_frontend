package com.aquadrat.parkplatzfrontend;

import com.aquadrat.parkplatzfrontend.exception.NotFoundException;
import com.aquadrat.parkplatzfrontend.model.*;
import com.aquadrat.parkplatzfrontend.model.dto.*;
import com.aquadrat.parkplatzfrontend.model.enums.VehicleType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    // getAll - Parking Lot
    @FXML
    private TableView<ParkingLotTableData> parkingLotTable;

    @FXML
    private TableColumn<ParkingLotTableData, Integer> parkingLot_addressIdColumn;

    @FXML
    private TableColumn<ParkingLotTableData, String> parkingLot_cityColumn;

    @FXML
    private TableColumn<ParkingLotTableData, String> parkingLot_countryColumn;

    @FXML
    private TableColumn<ParkingLotTableData, Integer> parkingLot_lotIdColumn;

    @FXML
    private TableColumn<ParkingLotTableData, String> parkingLot_nameColumn;

    @FXML
    private TableColumn<ParkingLotTableData, String> parkingLot_postCodeColumn;

    @FXML
    private TableColumn<ParkingLotTableData, String> parkingLot_streetColumn;

    // getAll - Ticket

    @FXML
    private TableView<TicketTableData> ticketTable;

    @FXML
    private TableColumn<TicketTableData, String> ticket_entryDateColumn;

    @FXML
    private TableColumn<TicketTableData, String> ticket_exitDateColumn;

    @FXML
    private TableColumn<TicketTableData, Boolean> ticket_isValidColumn;

    @FXML
    private TableColumn<TicketTableData, String> ticket_licencePlateColumn;

    @FXML
    private TableColumn<TicketTableData, Integer> ticket_lotIdColumn;

    @FXML
    private TableColumn<TicketTableData, Integer> ticket_slotIdColumn;

    @FXML
    private TableColumn<TicketTableData, Integer> ticket_ticketIdColumn;


    // getAll - Vehicle

    @FXML
    private TableView<VehicleTableData> vehicleTable;

    @FXML
    private TableColumn<VehicleTableData, String> vehicle_licencePlate;

    @FXML
    private TableColumn<VehicleTableData, Integer> vehicle_ticketID;

    @FXML
    private TableColumn<VehicleTableData, String> vehicle_vehicleType;

    // createParkingLot

    @FXML
    private TextField createParkingLot_city;

    @FXML
    private TextField createParkingLot_country;

    @FXML
    private TextField createParkingLot_name;

    @FXML
    private TextField createParkingLot_numberOfSlots;

    @FXML
    private TextField createParkingLot_postCode;

    @FXML
    private TextField createParkingLot_street;

    // getParkingLot

    @FXML
    private TableColumn<ParkingLotTableData, Integer> getParkingLot_AddressId;

    @FXML
    private TableColumn<ParkingLotTableData, String> getParkingLot_Country;

    @FXML
    private TableColumn<ParkingLotTableData, String> getParkingLot_city;

    @FXML
    private TableColumn<ParkingLotTableData, Integer> getParkingLot_lotId;

    @FXML
    private TableColumn<ParkingLotTableData, Integer> getParkingLot_name;

    @FXML
    private TableColumn<ParkingLotTableData, String> getParkingLot_postCode;

    @FXML
    private TableColumn<ParkingLotTableData, String> getParkingLot_street;

    @FXML
    private TableColumn<ParkSlotTableData, Integer> getParkingLot_parkSlotLotId;

    @FXML
    private TableColumn<ParkSlotTableData, Integer> getParkingLot_slotId;

    @FXML
    private TableColumn<ParkSlotTableData, Boolean> getParkingLot_isAvailable;

    @FXML
    private TextField getParkingLot_getLotId;

    @FXML
    private TableView<ParkSlotTableData> getParkingLot_parkSlotTable;

    @FXML
    private TableView<ParkingLotTableData> getParkingLot_parkingLotTable;

    // deleteParkingLot

    @FXML
    private Button deleteParkingLotButton;

    // updateParkingLot

    @FXML
    private TextField updateParkingLot_city;

    @FXML
    private TextField updateParkingLot_country;

    @FXML
    private TextField updateParkingLot_name;

    @FXML
    private TextField updateParkingLot_numberOfSlots;

    @FXML
    private TextField updateParkingLot_postCode;

    @FXML
    private TextField updateParkingLot_street;

    @FXML
    private TextField updateParkingLot_lotId;

    // createTicket
    @FXML
    private TextField createTicket_licencePlate;

    @FXML
    private TextField createTicket_lotId;

    @FXML
    private TextField createTicket_slotId;

    @FXML
    private ComboBox<String> createTicket_vehicleType;

    // parkOut

    @FXML
    private Button parkOutButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> items = new ArrayList<>();
        items.add("AUTO");
        items.add("SUV");
        items.add("MOTORCYCLE");
        items.add("TRUCK");
        createTicket_vehicleType.setItems(FXCollections.observableArrayList(items));
        initParkingLotTableSelectionListener();
        deleteParkingLotButton.setDisable(true);
        initTicketTableSelectionListener();
        parkOutButton.setDisable(true);
    }

    private void initParkingLotTableSelectionListener() {
        parkingLotTable.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<ParkingLotTableData>() {
            @Override
            public void onChanged(Change<? extends ParkingLotTableData> c) {
                List<? extends ParkingLotTableData> selected = null;
                while (c.next()) {
                    if (c.wasAdded()) {
                        selected = c.getList();
                    }
                    if (c.wasRemoved()) {
                        selected = c.getList();
                    }
                }
                if (selected.size() == 0) {
                    deleteParkingLotButton.setDisable(true);
                }
                if (selected.size() == 1) {
                    deleteParkingLotButton.setDisable(false);
                }
            }
        });
    }

    private void initTicketTableSelectionListener() {
        ticketTable.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<TicketTableData>() {
            @Override
            public void onChanged(Change<? extends TicketTableData> c) {
                List<? extends TicketTableData> selected = null;
                while (c.next()) {
                    if (c.wasAdded()) {
                        selected = c.getList();
                    }
                    if (c.wasRemoved()) {
                        selected = c.getList();
                    }
                }
                if (selected.size() == 0) {
                    parkOutButton.setDisable(true);
                }
                if (selected.size() == 1) {
                    parkOutButton.setDisable(false);
                }
            }
        });
    }

    @FXML
    void updateParkingLotTable() throws JsonProcessingException {

        final ObservableList<ParkingLotTableData> data = parseParkingLotTableData(getAllParkingLots());

        parkingLot_lotIdColumn.setCellValueFactory(new PropertyValueFactory<>("lotID"));
        parkingLot_nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        parkingLot_addressIdColumn.setCellValueFactory(new PropertyValueFactory<>("addressID"));
        parkingLot_streetColumn.setCellValueFactory(new PropertyValueFactory<>("street"));
        parkingLot_cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        parkingLot_postCodeColumn.setCellValueFactory(new PropertyValueFactory<>("postCode"));
        parkingLot_countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        parkingLotTable.setItems(data);

    }

    public List<ParkingLotDto> getAllParkingLots() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
        String url = "http://localhost:8080/parking-lot/getAll";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        List<ParkingLotDto> parkingLotDtoList = Arrays.asList(objectMapper.readValue(response.getBody(), ParkingLotDto[].class));
        return parkingLotDtoList;
    }

    public ObservableList<ParkingLotTableData> parseParkingLotTableData(List<ParkingLotDto> parkingLotDtoList) {
        List<ParkingLotTableData> parkingLotTableDataList = new ArrayList<>();

        for (ParkingLotDto parkingLotDto: parkingLotDtoList) {
            ParkingLotTableData parkingLotTableData = new ParkingLotTableData(parkingLotDto.getLotID(), parkingLotDto.getName(), parkingLotDto.getAddressDto().getAddressID(), parkingLotDto.getAddressDto().getStreet(), parkingLotDto.getAddressDto().getCity(), parkingLotDto.getAddressDto().getPostCode(), parkingLotDto.getAddressDto().getCountry());
            parkingLotTableDataList.add(parkingLotTableData);
        }

        ObservableList<ParkingLotTableData> parkingLotTableDataObservableList = FXCollections.observableArrayList(parkingLotTableDataList);
        return parkingLotTableDataObservableList;
    }

    @FXML
    void updateTicketTable() throws JsonProcessingException {
        final ObservableList<TicketTableData> data = parseTicketTableData(getAllTickets());

        ticket_ticketIdColumn.setCellValueFactory(new PropertyValueFactory<>("ticketID"));
        ticket_entryDateColumn.setCellValueFactory(new PropertyValueFactory<>("entryDate"));
        ticket_exitDateColumn.setCellValueFactory(new PropertyValueFactory<>("exitDate"));
        ticket_isValidColumn.setCellValueFactory(new PropertyValueFactory<>("isValid"));
        ticket_licencePlateColumn.setCellValueFactory(new PropertyValueFactory<>("licencePlate"));
        ticket_slotIdColumn.setCellValueFactory(new PropertyValueFactory<>("slotID"));
        ticket_lotIdColumn.setCellValueFactory(new PropertyValueFactory<>("lotID"));
        ticketTable.setItems(data);
    }

    public List<TicketDto> getAllTickets() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
        String url = "http://localhost:8080/ticket/getAll";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        List<TicketDto> ticketDtoList = Arrays.asList(objectMapper.readValue(response.getBody(), TicketDto[].class));
        return ticketDtoList;
    }

    public ObservableList<TicketTableData> parseTicketTableData(List<TicketDto> ticketDtoList) {
        List<TicketTableData> ticketTableDataList = new ArrayList<>();

        for (TicketDto ticketDto: ticketDtoList) {
            String entryDate = ticketDto.getEntryDate().toString();
            String exitDate = null;
            if (ticketDto.getExitDate() != null) {
                exitDate = ticketDto.getExitDate().toString();
            }
            TicketTableData ticketTableData = new TicketTableData(ticketDto.getTicketID(), entryDate, exitDate, ticketDto.isValid(), ticketDto.getLicencePlate(), ticketDto.getSlotID(), ticketDto.getLotID());
            ticketTableDataList.add(ticketTableData);
        }

        ObservableList<TicketTableData> ticketTableDataObservableList = FXCollections.observableArrayList(ticketTableDataList);
        return ticketTableDataObservableList;
    }

    @FXML
    void updateVehicleTable() throws JsonProcessingException {
        final ObservableList<VehicleTableData> data = parseVehicleTableData(getAllVehicles());
        vehicle_licencePlate.setCellValueFactory(new PropertyValueFactory<>("licencePlate"));
        vehicle_vehicleType.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
        vehicle_ticketID.setCellValueFactory(new PropertyValueFactory<>("ticketID"));
        vehicleTable.setItems(data);
    }

    public List<VehicleDto> getAllVehicles() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
        String url = "http://localhost:8080/vehicle/getAll";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        List<VehicleDto> vehicleDtoList = Arrays.asList(objectMapper.readValue(response.getBody(), VehicleDto[].class));
        return vehicleDtoList;
    }

    public ObservableList<VehicleTableData> parseVehicleTableData(List<VehicleDto> vehicleDtoList) {
        List<VehicleTableData> vehicleTableDataList = new ArrayList<>();

        for (VehicleDto vehicleDto: vehicleDtoList) {
            VehicleTableData vehicleTableData= new VehicleTableData(vehicleDto.getLicencePlate(), vehicleDto.getVehicleType().toString(), vehicleDto.getTicketID());
            vehicleTableDataList.add(vehicleTableData);
        }

        ObservableList<VehicleTableData> vehicleTableDataObservableList = FXCollections.observableArrayList(vehicleTableDataList);
        return vehicleTableDataObservableList;
    }

    @FXML
    void createParkingLotSubmit() throws JsonProcessingException {
        try {
            String name = createParkingLot_name.getText();
            String street = createParkingLot_street.getText();
            String city = createParkingLot_city.getText();
            String postCode = createParkingLot_postCode.getText();
            String country = createParkingLot_country.getText();
            Integer numberOfSlots = Integer.valueOf(createParkingLot_numberOfSlots.getText());

            ParkingLotCreateRequest request = new ParkingLotCreateRequest(name, street, city, postCode, country, numberOfSlots);
            RestTemplate restTemplate = new RestTemplate();
            /*String url = "http://localhost:8080/parking-lot/create";
            ResponseEntity<ParkingLotDto> result = restTemplate.postForEntity(url, request, ParkingLotDto.class);*/
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(request);
            restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
            HttpHeaders reqHeaders = new HttpHeaders();
            reqHeaders.setContentType(MediaType.APPLICATION_JSON);
            String url = "http://localhost:8080/parking-lot/create";
            HttpEntity<String> requestEntity = new HttpEntity<>(jsonString, reqHeaders);
            ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
            createParkingLot_name.clear();
            createParkingLot_street.clear();
            createParkingLot_city.clear();
            createParkingLot_postCode.clear();
            createParkingLot_country.clear();
            createParkingLot_numberOfSlots.clear();
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setHeaderText("Success");
            successAlert.setContentText("Parking Lot has been created successfully.");
            successAlert.showAndWait();

        } catch (NumberFormatException numberFormatException) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Input is not valid");
            errorAlert.setContentText("Number of slots must be an Integer value");
            errorAlert.showAndWait();
        } catch (HttpClientErrorException httpClientErrorException) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Input is not valid");
            ObjectMapper objectMapper = new ObjectMapper();
            ParkingLotResponse parkingLotResponse = objectMapper.readValue(httpClientErrorException.getResponseBodyAsString(), ParkingLotResponse.class);
            errorAlert.setContentText(parkingLotResponse.getMessage());
            errorAlert.showAndWait();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void getParkingLot_updateParkingLotTable() {
        try {
            Integer lotID = Integer.valueOf(getParkingLot_getLotId.getText());
            final ObservableList<ParkingLotTableData> data = parseParkingLotTableData(getParkingLot(lotID));

            getParkingLot_lotId.setCellValueFactory(new PropertyValueFactory<>("lotID"));
            getParkingLot_name.setCellValueFactory(new PropertyValueFactory<>("name"));
            getParkingLot_AddressId.setCellValueFactory(new PropertyValueFactory<>("addressID"));
            getParkingLot_street.setCellValueFactory(new PropertyValueFactory<>("street"));
            getParkingLot_city.setCellValueFactory(new PropertyValueFactory<>("city"));
            getParkingLot_postCode.setCellValueFactory(new PropertyValueFactory<>("postCode"));
            getParkingLot_Country.setCellValueFactory(new PropertyValueFactory<>("country"));
            getParkingLot_parkingLotTable.setItems(data);

        } catch (NumberFormatException | JsonProcessingException numberFormatException) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Input is not valid");
            errorAlert.setContentText("Lot ID must be an Integer value");
            errorAlert.showAndWait();
        } catch (NotFoundException notFoundException) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Input is not valid");
            errorAlert.setContentText(notFoundException.getMessage());
            errorAlert.showAndWait();
        }
    }

    public List<ParkingLotDto> getParkingLot(Integer lotID) throws JsonProcessingException {
        try {
            List<ParkingLotDto> parkingLotDtoList = new ArrayList<>();
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper objectMapper = new ObjectMapper();
            String url = "http://localhost:8080/parking-lot/" + lotID;
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            ParkingLotResponse parkingLotResponse = objectMapper.readValue(response.getBody(), ParkingLotResponse.class);

            parkingLotDtoList.add(parkingLotResponse.getParkingLotDto());
            return parkingLotDtoList;

        } catch (HttpClientErrorException httpClientErrorException) {
            throw new NotFoundException("Parking Lot not found");
        }

    }

    @FXML
    void getParkingLot_updateParkSlotTable() {
        try {
            Integer lotID = Integer.valueOf(getParkingLot_getLotId.getText());
            final ObservableList<ParkSlotTableData> data = parseParkSlotTableData(getParkingLot(lotID));

            getParkingLot_slotId.setCellValueFactory(new PropertyValueFactory<>("slotID"));
            getParkingLot_isAvailable.setCellValueFactory(new PropertyValueFactory<>("isAvailable"));
            getParkingLot_parkSlotLotId.setCellValueFactory(new PropertyValueFactory<>("lotID"));
            getParkingLot_parkSlotTable.setItems(data);

        } catch (NumberFormatException | JsonProcessingException numberFormatException) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Input is not valid");
            errorAlert.setContentText("Lot ID must be an Integer value");
            errorAlert.showAndWait();
        } catch (NotFoundException notFoundException) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Input is not valid");
            errorAlert.setContentText(notFoundException.getMessage());
            errorAlert.showAndWait();
        }
    }

    public ObservableList<ParkSlotTableData> parseParkSlotTableData(List<ParkingLotDto> parkingLotDtoList) {
        List<ParkSlotTableData> parkSlotTableDataList = new ArrayList<>();

        for (ParkSlotDto parkSlotDto: parkingLotDtoList.get(0).getParkSlotDtoList()) {
            ParkSlotTableData parkSlotTableData = new ParkSlotTableData(parkSlotDto.getSlotID(), parkSlotDto.isAvailable(), parkSlotDto.getLotID());
            parkSlotTableDataList.add(parkSlotTableData);
        }
        ObservableList<ParkSlotTableData> parkSlotTableDataObservableList = FXCollections.observableArrayList(parkSlotTableDataList);
        return parkSlotTableDataObservableList;
    }

    @FXML
    void deleteParkingLot() throws JsonProcessingException {
        try {
            ParkingLotTableData parkingLotTableData = parkingLotTable.getSelectionModel().getSelectedItem();
            Integer lotID = parkingLotTableData.getLotID();
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8080/parking-lot/delete/" + lotID;
            restTemplate.delete(url, ParkingLotDto.class);
            updateParkingLotTable();
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setHeaderText("Success");
            successAlert.setContentText("Parking Lot has been deleted successfully.");
            successAlert.showAndWait();

        } catch (NumberFormatException numberFormatException) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Input is not valid");
            errorAlert.setContentText("Lot ID must be an Integer value");
            errorAlert.showAndWait();
        } catch (HttpClientErrorException httpClientErrorException) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Input is not valid");
            ObjectMapper objectMapper = new ObjectMapper();
            ParkingLotResponse parkingLotResponse = objectMapper.readValue(httpClientErrorException.getResponseBodyAsString(), ParkingLotResponse.class);
            errorAlert.setContentText(parkingLotResponse.getMessage());
            errorAlert.showAndWait();
        } catch (NullPointerException nullPointerException) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("An error has occurred");
            errorAlert.setContentText("Please select a parking lot for the delete operation");
            errorAlert.showAndWait();
        }
    }

    @FXML
    void updateParkingLot() throws JsonProcessingException {
        try {
            Integer lotID = Integer.valueOf(updateParkingLot_lotId.getText());
            String name = updateParkingLot_name.getText();
            String street = updateParkingLot_street.getText();
            String city = updateParkingLot_city.getText();
            String postCode = updateParkingLot_postCode.getText();
            String country = updateParkingLot_country.getText();
            Integer numberOfSlots = Integer.valueOf(updateParkingLot_numberOfSlots.getText());

            ParkingLotUpdateRequest parkingLotUpdateRequest = new ParkingLotUpdateRequest(name, street, city, postCode, country, numberOfSlots);
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(parkingLotUpdateRequest);
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
            HttpHeaders reqHeaders = new HttpHeaders();
            reqHeaders.setContentType(MediaType.APPLICATION_JSON);
            String url = "http://localhost:8080/parking-lot/update/" + lotID;
            HttpEntity<String> requestEntity = new HttpEntity<>(jsonString, reqHeaders);
            restTemplate.patchForObject(url, requestEntity, String.class);
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setHeaderText("Success");
            successAlert.setContentText("Parking Lot has been updated successfully.");
            successAlert.showAndWait();

        } catch (NumberFormatException numberFormatException) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Input is not valid");
            errorAlert.setContentText("Lot ID and number of slots must be an Integer value");
            errorAlert.showAndWait();
        } catch (HttpClientErrorException httpClientErrorException) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("An error has occurred");
            ObjectMapper objectMapper = new ObjectMapper();
            ParkingLotResponse parkingLotResponse = objectMapper.readValue(httpClientErrorException.getResponseBodyAsString(), ParkingLotResponse.class);
            errorAlert.setContentText(parkingLotResponse.getMessage());
            errorAlert.showAndWait();
        }
    }

    @FXML
    void createTicket() throws JsonProcessingException {
        try {
            String licencePlate = createTicket_licencePlate.getText();
            VehicleType vehicleType = VehicleType.valueOf(createTicket_vehicleType.getValue());
            Integer lotID = Integer.valueOf(createTicket_lotId.getText());
            Integer slotID = Integer.valueOf(createTicket_slotId.getText());

            TicketCreateRequest request = new TicketCreateRequest(licencePlate, vehicleType, lotID, slotID);
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(request);
            restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
            HttpHeaders reqHeaders = new HttpHeaders();
            reqHeaders.setContentType(MediaType.APPLICATION_JSON);
            String url = "http://localhost:8080/ticket/create";
            HttpEntity<String> requestEntity = new HttpEntity<>(jsonString, reqHeaders);
            ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
            createTicket_licencePlate.clear();
            createTicket_lotId.clear();
            createTicket_slotId.clear();
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setHeaderText("Success");
            successAlert.setContentText("Ticket has been created successfully.");
            successAlert.showAndWait();

        } catch (NumberFormatException numberFormatException) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Input is not valid");
            errorAlert.setContentText("Lot ID and Slot ID must be an Integer value");
            errorAlert.showAndWait();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (HttpClientErrorException httpClientErrorException) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("An error has occurred");
            ObjectMapper objectMapper = new ObjectMapper();
            TicketResponse ticketResponse = objectMapper.readValue(httpClientErrorException.getResponseBodyAsString(), TicketResponse.class);
            errorAlert.setContentText(ticketResponse.getMessage());
            errorAlert.showAndWait();
        }
    }

    @FXML
    void parkOut() throws JsonProcessingException {
        try {
            TicketTableData ticketTableData = ticketTable.getSelectionModel().getSelectedItem();
            Integer ticketID = ticketTableData.getTicketID();
            String url = "http://localhost:8080/ticket/" + ticketID;
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
            HttpHeaders reqHeaders = new HttpHeaders();
            String jsonString = "";
            reqHeaders.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> requestEntity = new HttpEntity<>(jsonString , reqHeaders);
            restTemplate.patchForObject(url, requestEntity, String.class);
            updateTicketTable();
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setHeaderText("Success");
            successAlert.setContentText("Park out operation is successful.");
            successAlert.showAndWait();
        } catch (HttpClientErrorException httpClientErrorException) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("An error has occurred");
            ObjectMapper objectMapper = new ObjectMapper();
            TicketResponse ticketResponse = objectMapper.readValue(httpClientErrorException.getResponseBodyAsString(), TicketResponse.class);
            errorAlert.setContentText(ticketResponse.getMessage());
            errorAlert.showAndWait();
        } catch (NullPointerException nullPointerException) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("An error has occurred");
            errorAlert.setContentText("Please select a ticket for the park out operation");
            errorAlert.showAndWait();
        }

    }


}
