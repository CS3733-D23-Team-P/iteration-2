package edu.wpi.punchy_pegasi.generator;

import edu.wpi.punchy_pegasi.schema.IDao;
import edu.wpi.punchy_pegasi.backend.PdbController;
import edu.wpi.punchy_pegasi.App;
import edu.wpi.punchy_pegasi.schema.GenericRequestEntry;
import edu.wpi.punchy_pegasi.schema.TableType;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class GenericRequestEntryDaoImpl implements IDao<GenericRequestEntry, String> {

    static String[] fields = {/*fields*/};
    private final PdbController dbController = App.getSingleton().getPdb();

    @Override
    public Optional<GenericRequestEntry> get(String key) {
        try (var rs = dbController.searchQuery(TableType.GENERIC, ""/*idField*/, key)) {
            rs.next();
            GenericRequestEntry req/*fromResultSet*/ = null;
            return Optional.ofNullable(req);
        } catch (PdbController.DatabaseException | SQLException e) {
            log.error("", e);
            return Optional.empty();
        }
    }

    @Override
    public Map<String, GenericRequestEntry> getAll() {
        var map = new HashMap<String, GenericRequestEntry>();
        try (var rs = dbController.searchQuery(TableType.GENERIC)) {
            while (rs.next()) {
                GenericRequestEntry req/*fromResultSet*/ = null;
                if (req != null)
                    map.put("req"/*getID*/, req);
            }
        } catch (PdbController.DatabaseException | SQLException e) {
            log.error("", e);
        }
        return map;
    }

    @Override
    public void save(GenericRequestEntry genericRequestEntry) {
        Object[] values = {/*getFields*/};
        try {
            dbController.insertQuery(TableType.GENERIC, fields, values);
        } catch (PdbController.DatabaseException e) {
            log.error("Error saving", e);
        }

    }

    @Override
    public void update(GenericRequestEntry genericRequestEntry, Object[] params) {
        // What does this even mean?
    }

    @Override
    public void delete(GenericRequestEntry genericRequestEntry) {
        try {
            dbController.deleteQuery(TableType.GENERIC, ""/*idField*/, "genericRequestEntry"/*getID*/);
        } catch (PdbController.DatabaseException e) {
            log.error("Error deleting", e);
        }
    }
}