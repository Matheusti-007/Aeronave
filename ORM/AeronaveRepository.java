
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class AeronaveRepository {
    private static Database database;
    private static Dao<Aeronave, Integer> dao;
    private List<Aeronave> loadedAeronaves;
    private Aeronave loadedAeronave;

    public AeronaveRepository(Database database) {
        AeronaveRepository.setDatabase(database);
        loadedAeronaves = new ArrayList<>();
    }

    public static void setDatabase(Database database) {
        AeronaveRepository.database = database;
        try {
            dao = DaoManager.createDao(database.getConnection(), Aeronave.class);
            TableUtils.createTableIfNotExists(database.getConnection(), Aeronave.class);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Aeronave create(Aeronave aeronave) {
        int nrows = 0;
        try {
            nrows = dao.create(aeronave);
            if (nrows == 0)
                throw new SQLException("Error: object not saved");
            this.loadedAeronave = aeronave;
            loadedAeronaves.add(aeronave);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return aeronave;
    }

    public void update(Aeronave aeronave) {
        try {
            dao.update(aeronave);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void delete(Aeronave aeronave) {
        try {
            dao.delete(aeronave);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Aeronave loadFromId(int id) {
        try {
            this.loadedAeronave = dao.queryForId(id);
            if (this.loadedAeronave != null)
                this.loadedAeronaves.add(this.loadedAeronave);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return this.loadedAeronave;
    }

    public List<Aeronave> loadAll() {
        try {
            this.loadedAeronaves = dao.queryForAll();
            if (this.loadedAeronaves.size() != 0)
                this.loadedAeronave = this.loadedAeronaves.get(0);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return this.loadedAeronaves;
    }
}
