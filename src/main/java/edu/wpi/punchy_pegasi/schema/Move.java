package edu.wpi.punchy_pegasi.schema;

import lombok.AllArgsConstructor;
import lombok.Getter;

import lombok.RequiredArgsConstructor;

import lombok.Data;

@Data
@AllArgsConstructor
public class Move {
        private Long uuid;
    private Long nodeID;
    private String longName;
    private String date;
    @RequiredArgsConstructor
    public enum Field {
        UUID("uuid"),
        NODE_ID("nodeID"),
        LONG_NAME("longName"),
        DATE("date");
        @Getter
        private final String colName;
        public Object getValue(edu.wpi.punchy_pegasi.schema.Move ref){
            return ref.getFromField(this);
        }
    }
    public Object getFromField(Field field) {
        return switch (field) {
            case UUID -> getUuid();
            case NODE_ID -> getNodeID();
            case LONG_NAME -> getLongName();
            case DATE -> getDate();
        };
    }

}