package edu.wpi.punchy_pegasi.generated;

import edu.wpi.punchy_pegasi.App;
import edu.wpi.punchy_pegasi.backend.PdbController;
import edu.wpi.punchy_pegasi.schema.RequestEntry;
import edu.wpi.punchy_pegasi.schema.IDao;
import edu.wpi.punchy_pegasi.schema.TableType;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class RequestEntryDaoImpl implements IDao<java.util.UUID, RequestEntry, RequestEntry.Field> {

    static String[] fields = {"serviceID", "locationName", "staffAssignment", "additionalNotes", "status", "invalidText"};
    private final PdbController dbController;

    public RequestEntryDaoImpl(PdbController dbController) {
        this.dbController = dbController;
    }

    public RequestEntryDaoImpl() {
        this.dbController = App.getSingleton().getPdb();
    }

    @Override
    public Optional<RequestEntry> get(java.util.UUID key) {
        try (var rs = dbController.searchQuery(TableType.REQUESTS, "serviceID", key)) {
            rs.next();
            RequestEntry req = new RequestEntry(
                    rs.getObject("serviceID", java.util.UUID.class),
                    rs.getObject("locationName", java.lang.Long.class),
                    rs.getObject("staffAssignment", java.lang.Long.class),
                    rs.getObject("additionalNotes", java.lang.String.class),
                    edu.wpi.punchy_pegasi.schema.RequestEntry.Status.valueOf(rs.getString("status")),
                    rs.getObject("invalidText", java.lang.String.class));
            return Optional.ofNullable(req);
        } catch (PdbController.DatabaseException | SQLException e) {
            log.error("", e);
            return Optional.empty();
        }
    }

    @Override
    public Map<java.util.UUID, RequestEntry> get(RequestEntry.Field column, Object value) {
        return get(new RequestEntry.Field[]{column}, new Object[]{value});
    }

    @Override
    public Map<java.util.UUID, RequestEntry> get(RequestEntry.Field[] params, Object[] value) {
        var map = new HashMap<java.util.UUID, RequestEntry>();
        try (var rs = dbController.searchQuery(TableType.REQUESTS, Arrays.stream(params).map(RequestEntry.Field::getColName).toList().toArray(new String[params.length]), value)) {
            while (rs.next()) {
                RequestEntry req = new RequestEntry(
                        rs.getObject("serviceID", java.util.UUID.class),
                        rs.getObject("locationName", java.lang.Long.class),
                        rs.getObject("staffAssignment", java.lang.Long.class),
                        rs.getObject("additionalNotes", java.lang.String.class),
                        edu.wpi.punchy_pegasi.schema.RequestEntry.Status.valueOf(rs.getString("status")),
                        rs.getObject("invalidText", java.lang.String.class));
                if (req != null)
                    map.put(req.getServiceID(), req);
            }
        } catch (PdbController.DatabaseException | SQLException e) {
            log.error("", e);
        }
        return map;
    }

    @Override
    public Map<java.util.UUID, RequestEntry> getAll() {
        var map = new HashMap<java.util.UUID, RequestEntry>();
        try (var rs = dbController.searchQuery(TableType.REQUESTS)) {
            while (rs.next()) {
                RequestEntry req = new RequestEntry(
                        rs.getObject("serviceID", java.util.UUID.class),
                        rs.getObject("locationName", java.lang.Long.class),
                        rs.getObject("staffAssignment", java.lang.Long.class),
                        rs.getObject("additionalNotes", java.lang.String.class),
                        edu.wpi.punchy_pegasi.schema.RequestEntry.Status.valueOf(rs.getString("status")),
                        rs.getObject("invalidText", java.lang.String.class));
                if (req != null)
                    map.put(req.getServiceID(), req);
            }
        } catch (PdbController.DatabaseException | SQLException e) {
            log.error("", e);
        }
        return map;
    }

    @Override
    public void save(RequestEntry requestEntry) {
        Object[] values = {requestEntry.getServiceID(), requestEntry.getLocationName(), requestEntry.getStaffAssignment(), requestEntry.getAdditionalNotes(), requestEntry.getStatus(), requestEntry.getInvalidText()};
        try {
            dbController.insertQuery(TableType.REQUESTS, fields, values);
        } catch (PdbController.DatabaseException e) {
            log.error("Error saving", e);
        }

    }

    @Override
    public void update(RequestEntry requestEntry, RequestEntry.Field[] params) {
        if (params.length < 1)
            return;
        try {
            dbController.updateQuery(TableType.REQUESTS, "serviceID", requestEntry.getServiceID(), Arrays.stream(params).map(RequestEntry.Field::getColName).toList().toArray(new String[params.length]), Arrays.stream(params).map(p -> p.getValue(requestEntry)).toArray());
        } catch (PdbController.DatabaseException e) {
            log.error("Error saving", e);
        }
    }

    @Override
    public void delete(RequestEntry requestEntry) {
        try {
            dbController.deleteQuery(TableType.REQUESTS, "serviceID", requestEntry.getServiceID());
        } catch (PdbController.DatabaseException e) {
            log.error("Error deleting", e);
        }
    }
}