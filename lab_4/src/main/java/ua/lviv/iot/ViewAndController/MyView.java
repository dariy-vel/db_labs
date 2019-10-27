package ua.lviv.iot.ViewAndController;

import ua.lviv.iot.model.AirCompanyEntity;
import ua.lviv.iot.model.AircraftEntity;
import ua.lviv.iot.model.AirportEntity;
import ua.lviv.iot.model.CountryEntity;
import ua.lviv.iot.model.FlightEntity;
import ua.lviv.iot.model.ModelEntity;
import ua.lviv.iot.service.AirCompanyService;
import ua.lviv.iot.service.AircraftService;
import ua.lviv.iot.service.AirportService;
import ua.lviv.iot.service.CountryService;
import ua.lviv.iot.service.FlightService;
import ua.lviv.iot.service.ModelService;

import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MyView {
    private static Scanner input = new Scanner(System.in);
    private Map<String, String> menu;
    private Map<String, Printable> methodsMenu;

    public MyView() {
        menu = new LinkedHashMap<>();
        methodsMenu = new LinkedHashMap<>();
        menu.put("A", "   A - Select all table");
        menu.put("B", "   B - Select structure of DB");

        menu.put("1", "   1 - Table: Country");
        menu.put("11", "  11 - Create for Country");
        menu.put("12", "  12 - Update Country");
        menu.put("13", "  13 - Delete from Country");
        menu.put("14", "  14 - Select Country");
        menu.put("15", "  15 - Find Country by ID");

        menu.put("2", "   2 - Table: AirCompany");
        menu.put("21", "  21 - Create for AirCompany");
        menu.put("22", "  22 - Update AirCompany");
        menu.put("23", "  23 - Delete from AirCompany");
        menu.put("24", "  24 - Select AirCompany");
        menu.put("25", "  25 - Find AirCompany by ID");

        menu.put("3", "   3 - Table: Airport");
        menu.put("31", "  31 - Create for Airport");
        menu.put("32", "  32 - Update Airport");
        menu.put("33", "  33 - Delete from Airport");
        menu.put("34", "  34 - Select Airport");
        menu.put("35", "  35 - Find Airport by ID");

        menu.put("4", "   4 - Table: Model");
        menu.put("41", "  41 - Create for Model");
        menu.put("42", "  42 - Update Model");
        menu.put("43", "  43 - Delete from Model");
        menu.put("44", "  44 - Select Model");
        menu.put("45", "  45 - Find Model by ID");

        menu.put("5", "   5 - Table: Aircraft");
        menu.put("51", "  51 - Create for Aircraft");
        menu.put("52", "  52 - Update Aircraft");
        menu.put("53", "  53 - Delete from Aircraft");
        menu.put("54", "  54 - Select Aircraft");
        menu.put("55", "  55 - Find Aircraft by ID");

        menu.put("6", "   6 - Table: Flight");
        menu.put("61", "  61 - Create for Flight");
        menu.put("62", "  62 - Update Flight");
        menu.put("63", "  63 - Delete from Flight");
        menu.put("64", "  64 - Select Flight");
        menu.put("65", "  65 - Find Flight by ID");

        menu.put("Q", "   Q - exit");

        methodsMenu.put("A", this::selectAllTable);
//        methodsMenu.put("B", this::takeStructureOfDB);

        methodsMenu.put("11", this::createForCountry);
        methodsMenu.put("12", this::updateCountry);
        methodsMenu.put("13", this::deleteFromCountry);
        methodsMenu.put("14", this::selectCountry);
        methodsMenu.put("15", this::findCountryByID);

        methodsMenu.put("21", this::createForAirCompany);
        methodsMenu.put("22", this::updateAirCompany);
        methodsMenu.put("23", this::deleteFromAirCompany);
        methodsMenu.put("24", this::selectAirCompany);
        methodsMenu.put("25", this::findAirCompanyByID);

        methodsMenu.put("31", this::createForAirport);
        methodsMenu.put("32", this::updateAirport);
        methodsMenu.put("33", this::deleteFromAirport);
        methodsMenu.put("34", this::selectAirport);
        methodsMenu.put("35", this::findAirportByID);

        methodsMenu.put("41", this::createForModel);
        methodsMenu.put("42", this::updateModel);
        methodsMenu.put("43", this::deleteFromModel);
        methodsMenu.put("44", this::selectModel);
        methodsMenu.put("45", this::findModelByID);

        methodsMenu.put("51", this::createForAircraft);
        methodsMenu.put("52", this::updateAircraft);
        methodsMenu.put("53", this::deleteFromAircraft);
        methodsMenu.put("54", this::selectAircraft);
        methodsMenu.put("55", this::findAircraftByID);

        methodsMenu.put("61", this::createForFlight);
        methodsMenu.put("62", this::updateFlight);
        methodsMenu.put("63", this::deleteFromFlight);
        methodsMenu.put("64", this::selectFlight);
        methodsMenu.put("65", this::findFlightByID);
    }

    private void selectAllTable() throws SQLException {
        selectCountry();
        selectAirCompany();
        selectAirport();
        selectModel();
        selectAircraft();
        selectFlight();
    }

//    private void takeStructureOfDB() throws SQLException {
//        Connection connection = ConnectionManager.getConnection();
//        MetaDataService metaDataService = new MetaDataService();
//        List<TableMetaData> tables = metaDataService.getTablesStructure();
//        System.out.println("TABLE OF DATABASE: " + connection.getCatalog());
//
//        for (TableMetaData table : tables) {
//            System.out.println(table);
//        }
//    }


    //------------------------------------------------------------------------//
    private void deleteFromCountry() throws SQLException {
        System.out.println("Input ID(id) for Country: ");
        Integer id = Integer.parseInt(input.nextLine());
        CountryService countryService = new CountryService();
        int count = countryService.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void createForCountry() throws SQLException {
        System.out.println("Input ID(id) for Country: ");
        Integer id = Integer.parseInt(input.nextLine());
        System.out.println("Input name for Country: ");
        String name = input.nextLine();
        CountryEntity entity = new CountryEntity(id, name);

        CountryService countryService = new CountryService();
        int count = countryService.create(entity);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateCountry() throws SQLException {
        System.out.println("Input ID(id) for Country: ");
        Integer id = Integer.parseInt(input.nextLine());
        System.out.println("Input name for Country: ");
        String name = input.next();
        CountryEntity entity = new CountryEntity(id, name);

        CountryService countryService = new CountryService();
        int count = countryService.update(entity);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void selectCountry() throws SQLException {
        System.out.println("\nTable: Country");
        CountryService countryService = new CountryService();
        List<CountryEntity> countries = countryService.findAll();
        for (CountryEntity entity : countries) {
            System.out.println(entity);
        }
    }

    private void findCountryByID() throws SQLException {
        System.out.println("Input ID(id) for Country: ");
        Integer id = Integer.parseInt(input.nextLine());
        CountryService countryService = new CountryService();
        CountryEntity entity = countryService.findById(id);
        System.out.println(entity);
    }

    //------------------------------------------------------------------------//
    private void deleteFromAirCompany() throws SQLException {
        System.out.println("Input ID(id) for AirCompany: ");
        Integer id = Integer.parseInt(input.nextLine());
        AirCompanyService airCompanyService = new AirCompanyService();
        int count = airCompanyService.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void createForAirCompany() throws SQLException {
        System.out.println("Input ID(id) for AirCompany: ");
        Integer id = Integer.parseInt(input.nextLine());
        System.out.println("Input name for AirCompany: ");
        String name = input.nextLine();
        System.out.println("Input country_id for AirCompany: ");
        Integer country_id = Integer.parseInt(input.nextLine());
        AirCompanyEntity entity = new AirCompanyEntity(id, name, country_id);

        AirCompanyService airCompanyService = new AirCompanyService();
        int count = airCompanyService.create(entity);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateAirCompany() throws SQLException {
        System.out.println("Input ID(id) for AirCompany: ");
        Integer id = Integer.parseInt(input.nextLine());
        System.out.println("Input name for AirCompany: ");
        String name = input.nextLine();
        System.out.println("Input country_id for AirCompany: ");
        Integer country_id = Integer.parseInt(input.nextLine());
        AirCompanyEntity entity = new AirCompanyEntity(id, name, country_id);

        AirCompanyService airCompanyService = new AirCompanyService();
        int count = airCompanyService.update(entity);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void selectAirCompany() throws SQLException {
        System.out.println("\nTable: AirCompany");
        AirCompanyService countryService = new AirCompanyService();
        List<AirCompanyEntity> airCompanies = countryService.findAll();
        for (AirCompanyEntity entity : airCompanies) {
            System.out.println(entity);
        }
    }

    private void findAirCompanyByID() throws SQLException {
        System.out.println("Input ID(id) for AirCompany: ");
        Integer id = Integer.parseInt(input.nextLine());
        AirCompanyService airCompanyService = new AirCompanyService();
        AirCompanyEntity entity = airCompanyService.findById(id);
        System.out.println(entity);
    }

    //------------------------------------------------------------------------//
    private void deleteFromAirport() throws SQLException {
        System.out.println("Input ID(id) for Airport: ");
        Integer id = Integer.parseInt(input.nextLine());
        AirportService airportService = new AirportService();
        int count = airportService.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void createForAirport() throws SQLException {
        System.out.println("Input ID(id) for Airport: ");
        Integer id = Integer.parseInt(input.nextLine());
        System.out.println("Input name for Airport: ");
        String name = input.nextLine();
        AirportEntity entity = new AirportEntity(id, name);

        AirportService airportService = new AirportService();
        int count = airportService.create(entity);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateAirport() throws SQLException {
        System.out.println("Input ID(id) for Airport: ");
        Integer id = Integer.parseInt(input.nextLine());
        System.out.println("Input name for Airport: ");
        String name = input.nextLine();
        AirportEntity entity = new AirportEntity(id, name);

        AirportService airportService = new AirportService();
        int count = airportService.update(entity);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void selectAirport() throws SQLException {
        System.out.println("\nTable: Airport");
        AirportService airportService = new AirportService();
        List<AirportEntity> airCompanies = airportService.findAll();
        for (AirportEntity entity : airCompanies) {
            System.out.println(entity);
        }
    }

    private void findAirportByID() throws SQLException {
        System.out.println("Input ID(id) for Airport: ");
        Integer id = Integer.parseInt(input.nextLine());
        AirportService airportService = new AirportService();
        AirportEntity entity = airportService.findById(id);
        System.out.println(entity);
    }

    //------------------------------------------------------------------------//
    private void deleteFromModel() throws SQLException {
        System.out.println("Input ID(id) for Model: ");
        Integer id = Integer.parseInt(input.nextLine());
        ModelService modelService = new ModelService();
        int count = modelService.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void createForModel() throws SQLException {
        System.out.println("Input ID(id) for Model: ");
        Integer id = Integer.parseInt(input.nextLine());
        System.out.println("Input name for Model: ");
        String name = input.nextLine();
        System.out.println("Input manufacturer for Model: ");
        String manufacturer = input.nextLine();
        ModelEntity entity = new ModelEntity(id, name, manufacturer);

        ModelService modelService = new ModelService();
        int count = modelService.create(entity);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateModel() throws SQLException {
        System.out.println("Input ID(id) for Model: ");
        Integer id = Integer.parseInt(input.nextLine());
        System.out.println("Input name for Model: ");
        String name = input.nextLine();
        System.out.println("Input manufacturer for Model: ");
        String manufacturer = input.nextLine();
        ModelEntity entity = new ModelEntity(id, name, manufacturer);

        ModelService modelService = new ModelService();
        int count = modelService.update(entity);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void selectModel() throws SQLException {
        System.out.println("\nTable: Model");
        ModelService modelService = new ModelService();
        List<ModelEntity> models = modelService.findAll();
        for (ModelEntity entity : models) {
            System.out.println(entity);
        }
    }

    private void findModelByID() throws SQLException {
        System.out.println("Input ID(id) for Model: ");
        Integer id = Integer.parseInt(input.nextLine());
        ModelService modelService = new ModelService();
        ModelEntity entity = modelService.findById(id);
        System.out.println(entity);
    }

    //------------------------------------------------------------------------//
    private void deleteFromAircraft() throws SQLException {
        System.out.println("Input ID(id) for Aircraft: ");
        Integer id = Integer.parseInt(input.nextLine());
        AircraftService aircraftService = new AircraftService();
        int count = aircraftService.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void createForAircraft() throws SQLException {
        System.out.println("Input ID(id) for Aircraft: ");
        Integer id = Integer.parseInt(input.nextLine());
        System.out.println("Input boardNumber for Aircraft: ");
        String boardNumber = input.nextLine();
        System.out.println("Input registrationNumber for Aircraft: ");
        String registrationNumber = input.nextLine();
        System.out.println("Input registrationCountryId for Aircraft: ");
        Integer registrationCountryId = Integer.parseInt(input.nextLine());
        System.out.println("Input modelId for Aircraft: ");
        Integer modelId = Integer.parseInt(input.nextLine());
        System.out.println("Input airCompanyId for Aircraft: ");
        Integer airCompanyId = Integer.parseInt(input.nextLine());
        System.out.println("Input longitude for Aircraft: ");
        Double longitude = Double.parseDouble(input.nextLine());
        System.out.println("Input latitude for Aircraft: ");
        Double latitude = Double.parseDouble(input.nextLine());
        AircraftEntity entity = new AircraftEntity(id, boardNumber, registrationNumber, registrationCountryId,
                modelId, airCompanyId, longitude, latitude);

        AircraftService aircraftService = new AircraftService();
        int count = aircraftService.create(entity);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateAircraft() throws SQLException {
        System.out.println("Input ID(id) for Aircraft: ");
        Integer id = Integer.parseInt(input.nextLine());
        System.out.println("Input boardNumber for Aircraft: ");
        String boardNumber = input.nextLine();
        System.out.println("Input registrationNumber for Aircraft: ");
        String registrationNumber = input.nextLine();
        System.out.println("Input registrationCountryId for Aircraft: ");
        Integer registrationCountryId = Integer.parseInt(input.nextLine());
        System.out.println("Input modelId for Aircraft: ");
        Integer modelId = Integer.parseInt(input.nextLine());
        System.out.println("Input airCompanyId for Aircraft: ");
        Integer airCompanyId = Integer.parseInt(input.nextLine());
        System.out.println("Input longitude for Aircraft: ");
        Double longitude = Double.parseDouble(input.nextLine());
        System.out.println("Input latitude for Aircraft: ");
        Double latitude = Double.parseDouble(input.nextLine());
        AircraftEntity entity = new AircraftEntity(id, boardNumber, registrationNumber, registrationCountryId,
                modelId, airCompanyId, longitude, latitude);

        AircraftService aircraftService = new AircraftService();
        int count = aircraftService.update(entity);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void selectAircraft() throws SQLException {
        System.out.println("\nTable: Aircraft");
        AircraftService aircraftService = new AircraftService();
        List<AircraftEntity> countries = aircraftService.findAll();
        for (AircraftEntity entity : countries) {
            System.out.println(entity);
        }
    }

    private void findAircraftByID() throws SQLException {
        System.out.println("Input ID(id) for Aircraft: ");
        Integer id = Integer.parseInt(input.nextLine());
        AircraftService aircraftService = new AircraftService();
        AircraftEntity entity = aircraftService.findById(id);
        System.out.println(entity);
    }

    //------------------------------------------------------------------------//
    private void deleteFromFlight() throws SQLException {
        System.out.println("Input ID(id) for Flight: ");
        Integer id = Integer.parseInt(input.nextLine());
        FlightService flightService = new FlightService();
        int count = flightService.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void createForFlight() throws SQLException {
        System.out.println("Input ID(id) for Flight: ");
        Integer id = Integer.parseInt(input.nextLine());
        System.out.println("Input aircraftId for Flight: ");
        Integer aircraftId = Integer.parseInt(input.nextLine());
        System.out.println("Input fromAirportId for Flight: ");
        Integer fromAirportId = Integer.parseInt(input.nextLine());
        System.out.println("Input toAirportId for Flight: ");
        Integer toAirportId = Integer.parseInt(input.nextLine());
        System.out.println("Input scheduledDepartureTime for Flight: ");
        Timestamp scheduledDepartureTime = Timestamp.valueOf(input.nextLine());
        System.out.println("Input scheduledArrivalTime for Flight: ");
        Timestamp scheduledArrivalTime = Timestamp.valueOf(input.nextLine());
        System.out.println("Input actualDepartureTime for Flight: ");
        Timestamp actualDepartureTime = Timestamp.valueOf(input.nextLine());
        System.out.println("Input estArrivalTime for Flight: ");
        Timestamp estArrivalTime = Timestamp.valueOf(input.nextLine());
        FlightEntity entity = new FlightEntity(id, aircraftId, fromAirportId, toAirportId,
                scheduledDepartureTime, scheduledArrivalTime, actualDepartureTime, estArrivalTime);

        FlightService flightService = new FlightService();
        int count = flightService.create(entity);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateFlight() throws SQLException {
        System.out.println("Input ID(id) for Flight: ");
        Integer id = Integer.parseInt(input.nextLine());
        System.out.println("Input aircraftId for Flight: ");
        Integer aircraftId = Integer.parseInt(input.nextLine());
        System.out.println("Input fromAirportId for Flight: ");
        Integer fromAirportId = Integer.parseInt(input.nextLine());
        System.out.println("Input toAirportId for Flight: ");
        Integer toAirportId = Integer.parseInt(input.nextLine());
        System.out.println("Input scheduledDepartureTime for Flight: ");
        Timestamp scheduledDepartureTime = Timestamp.valueOf(input.nextLine());
        System.out.println("Input scheduledArrivalTime for Flight: ");
        Timestamp scheduledArrivalTime = Timestamp.valueOf(input.nextLine());
        System.out.println("Input actualDepartureTime for Flight: ");
        Timestamp actualDepartureTime = Timestamp.valueOf(input.nextLine());
        System.out.println("Input estArrivalTime for Flight: ");
        Timestamp estArrivalTime = Timestamp.valueOf(input.nextLine());
        FlightEntity entity = new FlightEntity(id, aircraftId, fromAirportId, toAirportId,
                scheduledDepartureTime, scheduledArrivalTime, actualDepartureTime, estArrivalTime);

        FlightService flightService = new FlightService();
        int count = flightService.update(entity);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void selectFlight() throws SQLException {
        System.out.println("\nTable: Flight");
        FlightService flightService = new FlightService();
        List<FlightEntity> flights = flightService.findAll();
        for (FlightEntity entity : flights) {
            System.out.println(entity);
        }
    }

    private void findFlightByID() throws SQLException {
        System.out.println("Input ID(id) for Flight: ");
        Integer id = Integer.parseInt(input.nextLine());
        FlightService flightService = new FlightService();
        FlightEntity entity = flightService.findById(id);
        System.out.println(entity);
    }

    //------------------------------------------------------------------------//
    private void outputMenu() {
        System.out.println("\nMENU:");
        for (String key : menu.keySet())
            if (key.length() == 1) System.out.println(menu.get(key));
    }

    private void outputSubMenu(String fig) {

        System.out.println("\nSubMENU:");
        for (String key : menu.keySet())
            if (key.length() != 1 && key.substring(0, 1).equals(fig)) System.out.println(menu.get(key));
    }

    public void show() {
        String keyMenu;
        do {
            outputMenu();
            System.out.println("Please, select menu point.");
            keyMenu = input.nextLine().toUpperCase();

            if (keyMenu.matches("^\\d")) {
                outputSubMenu(keyMenu);
                System.out.println("Please, select menu point.");
                keyMenu = input.nextLine().toUpperCase();
            }

            try {
                methodsMenu.get(keyMenu).print();
            } catch (Exception e) {
            }
        } while (!keyMenu.equals("Q"));
    }
}
