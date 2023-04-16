package edu.wpi.punchy_pegasi.schema;

import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class FoodServiceRequestEntry extends RequestEntry {
    private final String foodSelection;
    private final String tempType;
    private final List<String> additionalItems;
    private final String dietaryRestrictions;
    private final String patientName;
    private final String beverage;

    public FoodServiceRequestEntry(UUID serviceID, Long locationName, Long staffAssignment, String additionalNotes, Status status, String foodSelection, String tempType, List<String> additionalItems, String beverage, String dietaryRestrictions, String patientName) {
        super(serviceID, locationName, staffAssignment, additionalNotes, status);
        this.foodSelection = foodSelection;
        this.tempType = tempType;
        this.additionalItems = additionalItems;
        this.dietaryRestrictions = dietaryRestrictions;
        this.patientName = patientName;
        this.beverage = beverage;
    }

    public FoodServiceRequestEntry(Long locationName, Long staffAssignment, String additionalNotes, String foodSelection, String tempType, List<String> additionalItems, String beverage, String dietaryRestrictions, String patientName) {
        super(UUID.randomUUID(), locationName, staffAssignment, additionalNotes, Status.PROCESSING);
        this.foodSelection = foodSelection;
        this.tempType = tempType;
        this.additionalItems = additionalItems;
        this.dietaryRestrictions = dietaryRestrictions;
        this.patientName = patientName;
        this.beverage = beverage;
    }

    public Object getFromField(Field field) {
        return switch (field) {
            case SERVICE_ID -> getServiceID();
            case LOCATION_NAME -> getLocationName();
            case STAFF_ASSIGNMENT -> getStaffAssignment();
            case ADDITIONAL_NOTES -> getAdditionalNotes();
            case STATUS -> getStatus();
            case FOOD_SELECTION -> getFoodSelection();
            case TEMP_TYPE -> getTempType();
            case ADDITIONAL_ITEMS -> getAdditionalItems();
            case DIETARY_RESTRICTIONS -> getDietaryRestrictions();
            case PATIENT_NAME -> getPatientName();
            case BEVERAGE -> getBeverage();
        };
    }

    @lombok.RequiredArgsConstructor
    public enum Field {
        SERVICE_ID("serviceID"),
        LOCATION_NAME("locationName"),
        STAFF_ASSIGNMENT("staffAssignment"),
        ADDITIONAL_NOTES("additionalNotes"),
        STATUS("status"),
        FOOD_SELECTION("foodSelection"),
        TEMP_TYPE("tempType"),
        ADDITIONAL_ITEMS("additionalItems"),
        DIETARY_RESTRICTIONS("dietaryRestrictions"),
        PATIENT_NAME("patientName"),
        BEVERAGE("beverage");
        @lombok.Getter
        private final String colName;

        public Object getValue(edu.wpi.punchy_pegasi.schema.FoodServiceRequestEntry ref) {
            return ref.getFromField(this);
        }
    }

}